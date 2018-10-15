package co.isoft.nnita.profile.impl.service;

import co.isoft.nnita.profile.api.dao.PerfilesDao;
import co.isoft.nnita.profile.api.dao.UsuarioPerfilDao;
import co.isoft.nnita.profile.api.dao.UsuariosDao;
import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.Perfiles;
import co.isoft.nnita.profile.api.models.UsuarioPerfil;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.modelsweb.DatosSesionUsuario;
import co.isoft.nnita.profile.api.modelsweb.UsuarioPerfilMassive;
import co.isoft.nnita.profile.api.services.BitacoraService;
import co.isoft.nnita.profile.api.services.UsuariosService;
import co.isoft.nnita.profile.api.util.ConstantesBaseBean;
import co.isoft.nnita.profile.api.util.EnumCanalesISoft;
import co.isoft.nnita.profile.api.util.EnumFuncionalityISoft;
import co.isoft.nnita.profile.api.util.EstatusGenericos;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Implementacion Original de consulta de usuarios Isoftnnita
 *
 * @author Yaher Carrillo
 * @Date 10/07/2018
 */
@Service("usuariosServiceImpl")
@Transactional
public class UsuariosServiceImpl extends UtilServices implements UsuariosService
{

    /**
     * Objeto utilizado para el log
     */
    private static final Log logger = LogFactory.getLog(UsuariosServiceImpl.class);
    /**
     * Dao de servicios crud de usuarios
     */
    @Autowired
    private UsuariosDao usuariosDao;

    /**
     * Dao de servicios crud de perfiles
     */
    @Autowired
    private PerfilesDao perfilesDao;

    /**
     * Dao de servicios de perfiles sobre usuarios
     */
    @Autowired
    private UsuarioPerfilDao usuarioPerfilDao;

    /**
     * Bean de procesos de generacion de claves
     */
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * Servicio de auditorias
     */

    private BitacoraService bitacoraService;

    public UsuariosServiceImpl(BitacoraService bitacoraService)
    {
        this.bitacoraService = bitacoraService;
    }

    @Override
    public DatosSesionUsuario validateUser(String loginUsuario, String clave) throws ServiceException
    {
        try
        {
            DatosSesionUsuario datosUsuario = new DatosSesionUsuario();
            Usuarios usuario = usuariosDao.getUsuarioPorLogin(loginUsuario);
            if (usuario != null)
            {
                if (usuario.getClave().equals(clave.trim()))
                {
                    //Se buscan los perfiles de usuario
                    List<Perfiles> perfiles = usuarioPerfilDao.buscarPerfilesUsuarios(usuario);
                    if (perfiles != null && !perfiles.isEmpty())
                    {
                        datosUsuario.setUsuario(usuario);
                        datosUsuario.setPerfiles(perfiles);
                        return datosUsuario;
                    }
                    else
                        throw new DaoException(EstatusGenericos.PROFILER_USER_WITHOUT_PROFILES.getCode());
                }
                else
                    throw new DaoException(EstatusGenericos.PROFILER_USER_WRONG_KEY.getCode());
            }
            throw new DaoException(EstatusGenericos.PROFILER_USER_DOES_NOT_EXIST.getCode());
        }
        catch (DaoException e)
        {
            String mensaje = "Error al validar el usuario [" + loginUsuario + "]";
            throw new ServiceException(mensaje, e, e.getCode());
        }
    }

    @Override
    public Usuarios validateUser(String loginUsuario) throws ServiceException
    {
        try
        {
            Usuarios usuario = usuariosDao.getUsuarioPorLogin(loginUsuario);
            if (usuario != null)
            {
                return usuario;
            }
            throw new DaoException(EstatusGenericos.PROFILER_USER_DOES_NOT_EXIST.getCode());
        }
        catch (DaoException e)
        {
            String mensaje = "Error al validar el usuario [" + loginUsuario + "]";
            throw new ServiceException(mensaje, e, e.getCode());
        }
    }

    @Override
    public List<Usuarios> getAll(boolean estado) throws ServiceException
    {
        try
        {
            return usuariosDao.getUsuarioPorEstados(estado);
        }
        catch (DaoException e)
        {
            String mensaje = "Error al obtener el listado de Usuarios por estado [" + estado + "]";
            logger.error(mensaje, e);
            throw new ServiceException(mensaje, e);
        }
    }

    @Override
    public boolean validateCurrentPassUser(Usuarios usuario, String claveActual) throws ServiceException
    {
        try
        {
            Usuarios findUser = usuariosDao.buscarObjetoUnico(usuario);
            if (findUser != null)
                return findUser.getClave().equals(claveActual);
            else
                throw new DaoException(EstatusGenericos.PROFILER_USER_DOES_NOT_EXIST.getCode());
        }
        catch (DaoException e)
        {
            String mensaje = "Error al obtener el usuario [" + usuario.getLogin() + "]";
            logger.error(mensaje, e);
            throw new ServiceException(mensaje, e);
        }
    }

    @Override
    public void changePassUser(Usuarios usuario, String nuevaClave) throws ServiceException
    {
        try
        {
            Usuarios findUser = usuariosDao.buscarObjetoUnico(usuario);
            if (findUser != null)
            {
                findUser.setClave(nuevaClave);
                usuariosDao.actualizar(findUser);
            }
            else
                throw new DaoException(EstatusGenericos.PROFILER_USER_DOES_NOT_EXIST.getCode());
        }
        catch (DaoException e)
        {
            String mensaje = "Error al obtener el usuario [" + usuario.getLogin() + "]";
            logger.error(mensaje, e);
            throw new ServiceException(mensaje, e);
        }
    }

    @Override
    public void createUserIsoftProfile(Usuarios usuario, Map<String, Perfiles> perfiles) throws ServiceException
    {
        try
        {
            Long idUsuario = usuariosDao.agregar(usuario);
            if (idUsuario != null)
            {
                UsuarioPerfil perfilUsuario = new UsuarioPerfil();
                perfilUsuario.setHabilitado(new Long("1"));
                perfilUsuario.setUsuario(usuario);
                for (Map.Entry<String, Perfiles> entry : perfiles.entrySet())
                {
                    perfilUsuario.setPerfil(entry.getValue());
                    usuarioPerfilDao.agregar(perfilUsuario);
                }
            }
            else
                throw new DaoException(EstatusGenericos.PROFILER_USER_DOES_NOT_EXIST.getCode());
        }
        catch (DaoException e)
        {
            String mensaje = "Error al crear usuario [" + usuario.getLogin() + "]";
            logger.error(mensaje, e);
            throw new ServiceException(mensaje, e);
        }
    }

    @Override
    public void createUserIsoftProfile(Usuarios usuario) throws ServiceException
    {
        try
        {
            Usuarios userExiste = usuariosDao.getUsuarioPorLogin(usuario.getLogin());
            if (userExiste != null)
                throw new DaoException(EstatusGenericos.PROFILER_USER_EXIST.getCode());

            String pass = passwordEncoder.encode(usuario.getClave());
            usuario.setClave(pass);
            usuario.setFecha_registro(new Date());
            //Transformar todos los string en mayusculas
            convertAtrrUppercase(usuario);
            //Agrega al usuario en bdd
            usuariosDao.agregar(usuario);
            logger.error("Se agrega al usuario [" + usuario.getLogin() + "]");
            //Se realiza la auditoria de la operacion
            bitacoraService.registarBitacora(EnumFuncionalityISoft.FUNCIONALIDAD_CREAR_USUARIO, EnumCanalesISoft.WEB, new Usuarios());
        }
        catch (DaoException e)
        {
            String mensaje = "Error al crear usuario [" + usuario.getLogin() + "]";
            logger.error(mensaje, e);
            throw new ServiceException(e.getMessage(), e, e.getCode());
        }
    }

    @Override
    public List<UsuarioPerfilMassive> createUsersMassiveIsoftProfile(String passord, List<UsuarioPerfilMassive> listUsers) throws ServiceException
    {
        /**
         * Todo: ajustar los elementos de bitacora para cada tipo de insercion.
         */
        List<UsuarioPerfilMassive> listResponse = new ArrayList<>();
        try
        {
            for (UsuarioPerfilMassive item : listUsers)
            {
                Usuarios userExiste = usuariosDao.getUsuarioPorLogin(item.getLoginname());

                //Se crea la respuesta de la lista
                UsuarioPerfilMassive usuarioCreado = new UsuarioPerfilMassive();
                usuarioCreado.setLoginname(item.getLoginname());

                if (userExiste == null)
                {
                    userExiste = new Usuarios();
                    userExiste.setLogin(item.getLoginname());
                    userExiste.setNombres(item.getNames() != null && !item.getNames().trim().equals("") ? item.getNames() : ConstantesBaseBean.EMPTY);
                    userExiste.setApellidos(item.getLastname() != null && !item.getLastname().trim().equals("") ? item.getLastname() : ConstantesBaseBean.EMPTY);
                    //Se encripta la clave
                    passord = passwordEncoder.encode(passord);
                    userExiste.setClave(passord);
                    userExiste.setFecha_registro(new Date());
                    userExiste.setHabilitado(new Long("1"));
                    convertAtrrUppercase(userExiste);
                    usuariosDao.agregar(userExiste);
                    logger.error("Se agrega al usuario [" + userExiste.getLogin() + "]");

                    //Se realiza la auditoria de la operacion
                    bitacoraService.registarBitacora(EnumFuncionalityISoft.FUNCIONALIDAD_CREAR_USUARIO, EnumCanalesISoft.WEB, new Usuarios());

                    Perfiles perfil = new Perfiles();
                    perfil.setNombre_perfil(item.getCodeperfil());
                    perfil = perfilesDao.buscarObjetoUnico(perfil);

                    //Transformar todos los string en mayusculas
                    UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
                    if (perfil != null)
                    {
                        convertAtrrUppercase(perfil);

                        usuarioPerfil.setUsuario(userExiste);
                        usuarioPerfil.setPerfil(perfil);
                        usuarioPerfil.setHabilitado(new Long("1"));

                        //Se busca si la relacion no existe
                        UsuarioPerfil usuarioPerfilExist = usuarioPerfilDao.buscarObjetoUnico(usuarioPerfil);
                        logger.debug("Se agrega el perfil: [" + item.getCodeperfil() + "] al usuario [" + item.getLoginname() + "]");
                        if (usuarioPerfilExist == null)
                        {
                            //Se agrega la relacion.
                            usuarioPerfilDao.agregar(usuarioPerfil);

                            //Se indica que el usuario se agrego satisfactoriamente en su creacion y su perfil
                            usuarioCreado.setCodeperfil(perfil.getNombre_perfil());
                            usuarioCreado.setDescription("Se agrega el elemento");
                        }
                    }
                    else
                    {
                        perfil = new Perfiles();
                        perfil.setNombre_perfil(ConstantesBaseBean.GUEST);
                        perfil = perfilesDao.buscarObjetoUnico(perfil);
                        if (perfil == null)
                            throw new DaoException(EstatusGenericos.PROFILER_USER_DOES_NOT_EXIST.getCode());
                        else
                        {
                            usuarioPerfil.setUsuario(userExiste);
                            usuarioPerfil.setPerfil(perfil);
                            usuarioPerfil.setHabilitado(new Long("1"));
                            //Se busca si la relacion no existe
                            UsuarioPerfil usuarioPerfilExist = usuarioPerfilDao.buscarObjetoUnico(usuarioPerfil);
                            logger.debug("Se agrega el perfil: [" + item.getCodeperfil() + "] al usuario [" + item.getLoginname() + "]");
                            if (usuarioPerfilExist == null)
                            {
                                //Se agrega la relacion.
                                usuarioPerfilDao.agregar(usuarioPerfil);

                                //Se asocia el perfil por defecto
                                usuarioCreado.setCodeperfil(ConstantesBaseBean.GUEST);
                                usuarioCreado.setDescription("Se agrega el elemento con el perfil por defecto, el perfil indicado no existia");
                            }
                        }

                    }
                }
                else
                {
                    usuarioCreado.setCodeperfil(ConstantesBaseBean.EMPTY);
                    usuarioCreado.setDescription("El usuario ya existe");
                }
                //Se agrega al item como respuesta
                listResponse.add(usuarioCreado);
            }
        }
        catch (DaoException e)
        {
            String mensaje = "Error al crear usuarios masivos";
            logger.error(mensaje, e);
            throw new ServiceException(e.getMessage(), e, e.getCode());
        }
        return listResponse;
    }

    @Override
    public void addProfilesUser(String loginname, List<String> perfiles) throws ServiceException
    {
        try
        {
            Usuarios usuario = usuariosDao.getUsuarioPorLogin(loginname);
            if (usuario == null)
                throw new DaoException(EstatusGenericos.PROFILER_USER_DOES_NOT_EXIST.getCode());

            //Transformar todos los string en mayusculas
            convertAtrrUppercase(usuario);

            for (String itemperfil : perfiles)
            {
                Perfiles perfil = new Perfiles();
                perfil.setNombre_perfil(itemperfil);
                perfil = perfilesDao.buscarObjetoUnico(perfil);

                //Transformar todos los string en mayusculas
                if (perfil != null)
                {
                    convertAtrrUppercase(perfil);

                    //Se crea la relacion
                    UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
                    usuarioPerfil.setUsuario(usuario);
                    usuarioPerfil.setPerfil(perfil);
                    usuarioPerfil.setHabilitado(new Long("1"));

                    //Se agrega la relacion
                    UsuarioPerfil usuarioPerfilExist = usuarioPerfilDao.buscarObjetoUnico(usuarioPerfil);
                    logger.debug("Se agrega el perfil: [" + itemperfil + "] al usuario [" + loginname + "]");
                    if (usuarioPerfilExist == null)
                        usuarioPerfilDao.agregar(usuarioPerfil);
                    else
                        logger.debug("Se repite relacion: [" + itemperfil + "] al usuario [" + loginname + "], no se agrega nuevamente");
                }
                else
                {
                    logger.debug("El perfil: [" + itemperfil + "] no se agrega por que no existe");
                }
            }
        }
        catch (DaoException e)
        {
            String mensaje = "Error tratando de asociar perfiles al usuario:  [" + loginname + "]";
            logger.error(mensaje, e);
            throw new ServiceException(e.getMessage(), e, e.getCode());
        }
    }
}
