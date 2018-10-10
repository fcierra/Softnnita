package co.isoft.nnita.profile.impl.service;

import co.isoft.nnita.profile.api.dao.MenusDao;
import co.isoft.nnita.profile.api.dao.MenusItemDao;
import co.isoft.nnita.profile.api.dao.PermisosDao;
import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.Menus;
import co.isoft.nnita.profile.api.models.Menus_Item;
import co.isoft.nnita.profile.api.models.Perfiles;
import co.isoft.nnita.profile.api.models.Permisos;
import co.isoft.nnita.profile.api.services.PermisosService;
import co.isoft.nnita.profile.api.util.EstatusGenericos;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementacion de logica de acceso a permisos
 * de perfiles de usuarios.
 * @author Yaher Carrillo
 * @Date 19/08/2018
 */
@Service("permisosServiceImpl")
@Transactional
public class PermisosServiceImpl implements PermisosService
{
    /**
     * Objeto utilizado para el log
     */
    private static final Log logger = LogFactory.getLog(PermisosServiceImpl.class);

    /**
     * Dao de menus
     */
    @Autowired
    private MenusDao menusDao;

    @Autowired
    private MenusItemDao menusItemDao;


    /**
     * Dao de permisos
     */
    @Autowired
    private PermisosDao permisosDao;


    @Override
    public List<Menus> findMenusItemNavigation(Perfiles perfil) throws ServiceException
    {
        try
        {
            boolean isAdmin = perfil.getAdministrador()==1?true:false;
            List<Menus> listaMenus;
            if (isAdmin){
                listaMenus  = menusDao.getNavegacionPerfilAdmin();
                if (listaMenus!=null && !listaMenus.isEmpty())
                {
                    for (Menus menu : listaMenus)
                    {
                        try
                        {
                            menu.setItems(menusDao.getMenusItemPorMenuPadreAdmin(menu.getId()));
                        }catch (DaoException e){
                            String mensaje = "Error al obtener los items Administrador de: [" + menu.getMenu_label() + "]";
                            logger.error(mensaje, e);
                        }
                    }
                    return listaMenus;
                }
            }else{
                listaMenus  = menusDao.getNavegacionPerfil(perfil);
                if (listaMenus!=null && !listaMenus.isEmpty())
                {
                    for (Menus menu : listaMenus)
                    {
                        try
                        {
                            menu.setItems(menusDao.getMenusItemPorMenuPadre(menu.getId(),perfil.getId()));
                        }catch (DaoException e){
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
    public List<Permisos> findGrantPermisions(Perfiles perfil) throws ServiceException
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
            String mensaje = "Error al obtener permisos sobre el perfil: [" + perfil.getNombre_perfil() + "]";
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



}
