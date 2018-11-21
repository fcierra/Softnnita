package co.isoft.nnita.profile.impl.service;

import co.isoft.nnita.profile.api.dao.MenusDao;
import co.isoft.nnita.profile.api.dao.MenusItemDao;
import co.isoft.nnita.profile.api.dao.PerfilesDao;
import co.isoft.nnita.profile.api.dao.PermisosDao;
import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.gateways.models.request.profile.RequestCreateProfile;
import co.isoft.nnita.profile.api.gateways.models.request.users.PermisosDTO;
import co.isoft.nnita.profile.api.gateways.models.request.users.RequestModifyPermissionProfile;
import co.isoft.nnita.profile.api.models.*;
import co.isoft.nnita.profile.api.services.BitacoraService;
import co.isoft.nnita.profile.api.services.PerfilesYPermisosService;
import co.isoft.nnita.profile.api.util.EnumCanalesISoft;
import co.isoft.nnita.profile.api.util.EnumFuncionalityISoft;
import co.isoft.nnita.profile.api.util.EstatusGenericos;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static co.isoft.nnita.profile.api.util.ConstantesBaseBean.MAP_CANAL_TRANSACTION;
import static co.isoft.nnita.profile.api.util.ConstantesBaseBean.MAP_USER_TRANSACTION;

/**
 * Implementacion de logica de acceso a permisos
 * de perfiles de usuarios.
 *
 * @author Yaher Carrillo
 * @Date 19/08/2018
 */
@Service("perfilesYPermisosServiceImpl")
@Transactional
public class PerfilesYPermisosServiceImpl extends UtilServices implements PerfilesYPermisosService
{
    /**
     * Objeto utilizado para el log
     */
    private static final Log logger = LogFactory.getLog(PerfilesYPermisosServiceImpl.class);

    /**
     * Dao de menus
     */
    @Autowired
    private MenusDao menusDao;
    /**
     * Dao de menus item
     */
    @Autowired
    private MenusItemDao menusItemDao;

    /**
     * Dao de servicios crud de perfiles
     */
    @Autowired
    private PerfilesDao perfilesDao;
    /**
     * Dao de permisos
     */
    @Autowired
    private PermisosDao permisosDao;
    /**
     * Servicio de auditorias
     */
    private BitacoraService bitacoraService;

    /**
     * Constructor que inicializa el objeto con el
     * servicio de bitacora
     *
     * @param bitacoraService servicio de bitacora
     */
    public PerfilesYPermisosServiceImpl(BitacoraService bitacoraService)
    {
        this.bitacoraService = bitacoraService;
    }

    @Override
    public List<Menus> findMenusItemNavigation(Perfiles perfil) throws ServiceException
    {
        try
        {
            boolean isAdmin = perfil.getAdministrador() == 1 ? true : false;
            List<Menus> listaMenus;
            if (isAdmin)
            {
                listaMenus = menusDao.getNavegacionPerfilAdmin();
                if (listaMenus != null && !listaMenus.isEmpty())
                {
                    for (Menus menu : listaMenus)
                    {
                        try
                        {
                            menu.setItems(menusDao.getMenusItemPorMenuPadreAdmin(menu.getId()));
                        }
                        catch (DaoException e)
                        {
                            String mensaje = "Error al obtener los items Administrador de: [" + menu.getMenu_label() + "]";
                            logger.error(mensaje, e);
                        }
                    }
                    return listaMenus;
                }
            }
            else
            {
                listaMenus = menusDao.getNavegacionPerfil(perfil);
                if (listaMenus != null && !listaMenus.isEmpty())
                {
                    for (Menus menu : listaMenus)
                    {
                        try
                        {
                            menu.setItems(menusDao.getMenusItemPorMenuPadre(menu.getId(), perfil.getId()));
                        }
                        catch (DaoException e)
                        {
                            String mensaje = "Error al obtener los items de: [" + menu.getMenu_label() + "]";
                            logger.error(mensaje, e);
                        }
                    }
                    return listaMenus;
                }
            }

        }
        catch (DaoException e)
        {
            String mensaje = "Error al obtener permisos sobre el perfil: [" + perfil.getNombre_perfil() + "]";
            logger.error(mensaje, e);
            throw new ServiceException(mensaje, e);
        }
        return null;
    }

    @Override
    public List<Permisos> findGrantPermisions(String perfil) throws ServiceException
    {
        try
        {
            List<Permisos> lista = menusDao.getPermisosUsuarios(perfil);
            if (lista != null && !lista.isEmpty())
            {
                return lista;
            }
            else
                throw new DaoException(EstatusGenericos.PROFILER_USER_PROFILE_DONT_PERMISION.getCode());
        }
        catch (DaoException e)
        {
            String mensaje = "Error al obtener permisos sobre el perfil: [" + perfil + "]";
            logger.error(mensaje, e);
            throw new ServiceException(mensaje, e);
        }
    }

    @Override
    public List<Menus_Item> findNavigationSystem() throws ServiceException
    {
        try
        {
            return menusItemDao.getItemsDeSistema();
        }
        catch (DaoException e)
        {
            String mensaje = "Error al obtener los permisos de sistema";
            logger.error(mensaje, e);
            throw new ServiceException(mensaje, e);
        }
    }

    @Override
    public List<Perfiles> findProfilesSystem(Map<String, String> mapConfiguration) throws ServiceException
    {
        try
        {
            List<Perfiles> listProfiles = perfilesDao.findProfilesSystem(true);
            bitacoraService.registrarBitacora(EnumFuncionalityISoft.FUNCIONALIDAD_CONSULTAR_PERFILES, EnumCanalesISoft.valueOf(Integer.parseInt(mapConfiguration.get(MAP_CANAL_TRANSACTION))), mapConfiguration.get(MAP_USER_TRANSACTION));
            return listProfiles;
        }
        catch (DaoException e)
        {
            String mensaje = "Error tratando de consultar los perfiles de sistema";
            logger.error(mensaje, e);
            throw new ServiceException(e.getMessage(), e, e.getCode());
        }
    }

    @Override
    public void createProfile(Map<String, String> mapConfiguration, String nombrePerfil, Long habilitado) throws ServiceException
    {
        try
        {
            List<DetalleBitacora> listDetails = new ArrayList<>();
            Perfiles perfil = new Perfiles();
            perfil.setNombre_perfil(nombrePerfil);
            perfil = perfilesDao.buscarObjetoUnico(perfil);

            if (perfil != null)
            {
                throw new DaoException(EstatusGenericos.PROFILER_PROXILE_EXIST.getCode());
            }
            perfil = new Perfiles();
            perfil.setNombre_perfil(nombrePerfil);
            perfil.setHabilitado(habilitado);
            convertAtrrUppercase(perfil);
            perfilesDao.agregar(perfil);

            listDetails.add(recordDetailBinnacleCreateProfileSucess(nombrePerfil, habilitado));
            bitacoraService.registrarBitacora(EnumFuncionalityISoft.FUNCIONALIDAD_CREAR_PERFILES, EnumCanalesISoft.valueOf(Integer.parseInt(mapConfiguration.get(MAP_CANAL_TRANSACTION))), mapConfiguration.get(MAP_USER_TRANSACTION), listDetails);
        }
        catch (DaoException e)
        {
            String mensaje = "Error tratando de crear el perfil de sistema [" + nombrePerfil + "]";
            logger.error(mensaje, e);
            throw new ServiceException(e.getMessage(), e, e.getCode());
        }
    }

    @Override
    public void modifyProfile(Map<String, String> mapConfiguration, RequestCreateProfile dto) throws ServiceException
    {
        try
        {
            List<DetalleBitacora> listDetails = new ArrayList<>();
            Perfiles perfil = new Perfiles();
            perfil.setNombre_perfil(dto.getNombreperfil());
            List<Perfiles> list = perfilesDao.buscar(perfil);
            if (list == null)
            {
                throw new DaoException(EstatusGenericos.PROFILER_PROXILE_DONT_EXIST.getCode());
            }
            else if (list.size() > 1)
            {
                throw new DaoException(EstatusGenericos.PROFILER_PROXILE_EXIST.getCode());
            }
            else
            {
                perfil = list.get(0);
                String nombreperfil_antes = perfil.getNombre_perfil();
                Long habilitado_antes = perfil.getHabilitado();

                String nombreperfil_despues = dto.getNombre_perfil_despues() != null && !dto.getNombre_perfil_despues().trim().equals("") ? dto.getNombre_perfil_despues() : dto.getNombreperfil();
                Long habilitado_despues = dto.getHabilitado();

                perfil.setNombre_perfil(nombreperfil_despues);
                perfil.setHabilitado(habilitado_despues);
                perfilesDao.actualizar(perfil);

                //Registro de bitacora
                listDetails.add(recordDetailBinnacleModifyProfileSucess(nombreperfil_antes, habilitado_antes, nombreperfil_despues, habilitado_despues));
                bitacoraService.registrarBitacora(EnumFuncionalityISoft.FUNCIONALIDAD_MODIFICAR_PERFILES, EnumCanalesISoft.valueOf(Integer.parseInt(mapConfiguration.get(MAP_CANAL_TRANSACTION))), mapConfiguration.get(MAP_USER_TRANSACTION), listDetails);
            }
        }
        catch (DaoException e)
        {
            String mensaje = "Error tratando de modificar el perfil de sistema [" + dto.getNombreperfil() + "] ";
            logger.error(mensaje, e);
            throw new ServiceException(e.getMessage(), e, e.getCode());
        }
    }

    @Override
    public List<PermisosDTO> findPermissionProfile(Map<String, String> mapConfiguration, String profile) throws ServiceException
    {
        try
        {
            return permisosDao.buscarPermisosPerfil(profile);
        }
        catch (DaoException e)
        {
            String mensaje = "Error tratando de consultar los permisos del perfil de sistema [" + profile + "] ";
            logger.error(mensaje, e);
            throw new ServiceException(e.getMessage(), e, e.getCode());
        }
    }

    @Override
    public void modifyPermissionProfile(Map<String, String> mapConfiguration, RequestModifyPermissionProfile permissions) throws ServiceException
    {
        try
        {
            for (PermisosDTO item : permissions.getPermisos())
            {
                Permisos permiso = permisosDao.buscarPorId(item.getId());
                if (permiso!=null){
                    permiso.setHabilitado(item.getHabilitado());
                    permisosDao.actualizar(permiso);
                }

            }
        }
        catch (DaoException e)
        {
            String mensaje = "Error tratando de modificar los permisos ";
            logger.error(mensaje, e);
            throw new ServiceException(e.getMessage(), e, e.getCode());
        }
    }

}
