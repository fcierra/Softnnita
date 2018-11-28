package co.isoft.nnita.profile.impl.service;

import co.isoft.nnita.profile.api.dao.PerfilesDao;
import co.isoft.nnita.profile.api.dao.UsuarioPerfilDao;
import co.isoft.nnita.profile.api.dao.UsuariosDao;
import co.isoft.nnita.profile.api.dto.output.UserDTO;
import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.exceptions.ParamsException;
import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.DetalleBitacora;
import co.isoft.nnita.profile.api.models.Perfiles;
import co.isoft.nnita.profile.api.models.UsuarioPerfil;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.modelsweb.DatosSesionUsuario;
import co.isoft.nnita.profile.api.dto.output.ProfilesToUserOutDTO;
import co.isoft.nnita.profile.api.dto.output.UsersMassiveOutDTO;
import co.isoft.nnita.profile.api.dto.output.UsersAllOutDTO;
import co.isoft.nnita.profile.api.services.BitacoraService;
import co.isoft.nnita.profile.api.services.UsuariosService;
import co.isoft.nnita.profile.api.util.ConstantesBaseBean;
import co.isoft.nnita.profile.api.util.EnumFuncionalityISoft;
import co.isoft.nnita.profile.api.util.EstatusGenericos;
import co.isoft.nnita.profile.impl.util.ValidationsBasicModelUsuarios;
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

import static co.isoft.nnita.profile.api.util.ConstantesBaseBean.MAP_USER_TRANSACTION;

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
    public DatosSesionUsuario findUser(String loginUsuario, String clave) throws ServiceException
    {
        try
        {
            DatosSesionUsuario datosUsuario = new DatosSesionUsuario();
            Usuarios usuario = usuariosDao.getUsuarioPorLogin(loginUsuario.toUpperCase());
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
    public UserDTO findUser(String loginUsuario) throws ServiceException,ParamsException
    {
        try
        {
            logger.debug("Validando parametros");
            ValidationsBasicModelUsuarios.validarLoginUsuarios(loginUsuario);

            logger.debug("Inicia la busqueda del usuario");
            Usuarios usuario = this.findUserToAuthenticated(loginUsuario);
            if (usuario != null)
            {
                logger.info("Se encuentra el usuario efectivamente ["+loginUsuario+"]");
                return cloneUser(usuario);
            }
            throw new DaoException(EstatusGenericos.PROFILER_USER_DOES_NOT_EXIST.getCode());
        }
        catch (DaoException e)
        {
            String mensaje = "Error al validar el usuario [" + loginUsuario + "]";
            throw new ServiceException(mensaje, e, e.getCode());
        }
        catch (ParamsException e)
        {
            String mensaje = "Error al validar parametros";
            throw new ParamsException(mensaje, e.getCode(),e.getDescripcion());
        }
    }

    @Override
    public Usuarios findUserToAuthenticated(String login) throws ServiceException, ParamsException
    {
        try
        {
            logger.debug("Validando parametros");
            validarParametrosGenericos(login.trim());

            logger.debug("Inicia la busqueda del usuario");
            Usuarios usuario = usuariosDao.getUsuarioPorLogin(login.trim().toUpperCase());
            if (usuario != null)
            {
                logger.info("Se encuentra el usuario efectivamente ["+login+"]");
                return usuario;
            }
            throw new DaoException(EstatusGenericos.PROFILER_USER_DOES_NOT_EXIST.getCode());
        }
        catch (DaoException e)
        {
            String mensaje = "Error al validar el usuario [" + login + "]";
            throw new ServiceException(mensaje, e, e.getCode());
        }
        catch (ParamsException e)
        {
            String mensaje = "Error al validar parametros";
            throw new ParamsException(mensaje, e.getCode(),e.getDescripcion());
        }
    }

    @Override
    public List<UsersAllOutDTO> findAllUsers() throws ServiceException
    {
        try
        {
            return usuariosDao.getTodosLosUsuarios();
        }
        catch (DaoException e)
        {
            String mensaje = "Error al obtener el listado de todos los usuarios del sistema";
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
                perfilUsuario.setHabilitado(1L);
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
    public void createUserIsoftProfile(Map<String, String> mapConfiguration, Usuarios usuario) throws ServiceException
    {
        try
        {
            List<DetalleBitacora> listDetails = new ArrayList<>();
            convertAtrrUppercase(usuario);
            Usuarios userExisteLogin = usuariosDao.getUsuarioPorLogin(usuario.getLogin());
            if (userExisteLogin != null)
                throw new DaoException(EstatusGenericos.PROFILER_USER_EXIST.getCode());
            Usuarios userExisteEmail = usuariosDao.getUsuarioPorEmail(usuario.getEmail());
            if (userExisteEmail != null)
                throw new DaoException(EstatusGenericos.PROFILER_USER_EMAIL_EXIST.getCode());

            String pass = passwordEncoder.encode(usuario.getClave());
            usuario.setClave(pass);
            usuario.setFecha_registro(new Date());
            //Transformar todos los string en mayusculas
            convertAtrrUppercase(usuario);
            //Agrega al usuario en bdd
            usuariosDao.agregar(usuario);
            logger.info("Se agrega al usuario [" + usuario.getLogin() + "]");
            //Se realiza la auditoria de la operacion
            listDetails.add(recordDetailBinnacleUsersMassiveSucess("", usuario.getLogin()));
            bitacoraService.registrarBitacora(EnumFuncionalityISoft.FUNCIONALIDAD_CREAR_USUARIO, mapConfiguration, listDetails);
        }
        catch (DaoException e)
        {
            String mensaje = "Error al crear usuario [" + usuario.getLogin() + "]";
            logger.error(mensaje, e);
            throw new ServiceException(e.getMessage(), e, e.getCode());
        }
    }

    @Override
    public void modifyUser(Map<String, String> mapConfiguration, Usuarios usuario) throws ServiceException
    {
        try
        {
            List<DetalleBitacora> listDetails = new ArrayList<>();
            convertAtrrUppercase(usuario);
            Usuarios userExisteLogin = usuariosDao.getUsuarioPorLogin(usuario.getLogin());
            if (userExisteLogin == null)
                throw new DaoException(EstatusGenericos.PROFILER_USER_DOES_NOT_EXIST.getCode());
            Usuarios userExisteEmail = usuariosDao.getUsuarioPorEmail(usuario.getEmail());
            if (userExisteEmail!=null && !userExisteEmail.getId().equals(userExisteLogin.getId()))
                throw new DaoException(EstatusGenericos.PROFILER_USER_EMAIL_EXIST.getCode());
            convertAtrrUppercase(userExisteLogin);

            //Se evaluan los registros para verificar si similitud de lo contrario se registra en bitacora el cambio
            if (!usuario.getEmail().equals(userExisteLogin.getEmail())){
                String antes = userExisteLogin.getEmail();
                String despues = usuario.getEmail();
                userExisteLogin.setEmail(usuario.getEmail());
                listDetails.add(recordDetailBinnacleUsersModifySucess( usuario.getLogin(),"Email",antes,despues));
            }
            if (!usuario.getNombres().equals(userExisteLogin.getNombres())){
                String antes = userExisteLogin.getNombres();
                String despues = usuario.getNombres();
                userExisteLogin.setNombres(usuario.getNombres());
                listDetails.add(recordDetailBinnacleUsersModifySucess( usuario.getLogin(),"Nombres",antes,despues));
            }
            if (!usuario.getApellidos().equals(userExisteLogin.getApellidos())){
                String antes = userExisteLogin.getApellidos();
                String despues = usuario.getApellidos();
                userExisteLogin.setApellidos(usuario.getApellidos());
                listDetails.add(recordDetailBinnacleUsersModifySucess( usuario.getLogin(),"Apellidos",antes,despues));
            }
            //Modifica al usuario en bdd
            usuariosDao.actualizar(userExisteLogin);
            logger.info("Se actualiza al usuario [" + usuario.getLogin() + "]");
            //Registro de bitacora.
            bitacoraService.registrarBitacora(EnumFuncionalityISoft.FUNCIONALIDAD_ACTUALIZAR_USUARIO, mapConfiguration, listDetails);
        }
        catch (DaoException e)
        {
            String mensaje = "Error al modificar al usuario [" + usuario.getLogin() + "]";
            logger.error(mensaje, e);
            throw new ServiceException(e.getMessage(), e, e.getCode());
        }
    }

    @Override
    public List<UsersMassiveOutDTO> createUsersMassiveIsoftProfile(Map<String, String> mapConfiguration, String passord, List<UsersMassiveOutDTO> listUsers) throws ServiceException
    {

        List<UsersMassiveOutDTO> listResponse = new ArrayList<>();
        List<DetalleBitacora> listDetails = new ArrayList<>();
        try
        {
            for (UsersMassiveOutDTO item : listUsers)
            {
                Usuarios userExist = usuariosDao.getUsuarioPorLogin(item.getLoginname().toUpperCase());

                //Se crea la respuesta de la lista response
                UsersMassiveOutDTO usersCreate = new UsersMassiveOutDTO();
                usersCreate.setLoginname(item.getLoginname());

                if (userExist == null)
                {
                    userExist = new Usuarios();
                    userExist.setLogin(item.getLoginname());
                    userExist.setNombres(item.getNames() != null && !item.getNames().trim().equals("") ? item.getNames() : ConstantesBaseBean.EMPTY);
                    userExist.setApellidos(item.getLastname() != null && !item.getLastname().trim().equals("") ? item.getLastname() : ConstantesBaseBean.EMPTY);
                    //Se encripta la clave
                    passord = passwordEncoder.encode(passord);
                    userExist.setClave(passord);
                    userExist.setFecha_registro(new Date());
                    userExist.setHabilitado(1L);
                    convertAtrrUppercase(userExist);
                    usuariosDao.agregar(userExist);
                    logger.info("Se agrega al usuario [" + userExist.getLogin() + "]");

                    Perfiles perfil = new Perfiles();
                    perfil.setNombre_perfil(item.getCodeperfil());
                    perfil = perfilesDao.buscarObjetoUnico(perfil);

                    //Transformar todos los string en mayusculas
                    UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
                    if (perfil != null)
                    {
                        convertAtrrUppercase(perfil);

                        usuarioPerfil.setUsuario(userExist);
                        usuarioPerfil.setPerfil(perfil);
                        usuarioPerfil.setHabilitado(1L);

                        //Se busca si la relacion no existe
                        UsuarioPerfil userProfileExist = usuarioPerfilDao.buscarObjetoUnico(usuarioPerfil);
                        logger.debug("Se agrega el perfil: [" + item.getCodeperfil() + "] al usuario [" + item.getLoginname() + "]");
                        if (userProfileExist == null)
                        {
                            //Se agrega la relacion.
                            usuarioPerfilDao.agregar(usuarioPerfil);

                            //Se indica que el usuario se agrego satisfactoriamente en su creacion y su perfil
                            usersCreate.setCodeperfil(perfil.getNombre_perfil());
                            usersCreate.setDescription("Se agrega el elemento");
                            //Se lista el detalle de la transaccion
                            listDetails.add(recordDetailBinnacleUsersMassiveSucess(item.getCodeperfil(), item.getLoginname()));
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
                            usuarioPerfil.setUsuario(userExist);
                            usuarioPerfil.setPerfil(perfil);
                            usuarioPerfil.setHabilitado(1L);
                            //Se busca si la relacion no existe
                            UsuarioPerfil userPerfilExist = usuarioPerfilDao.buscarObjetoUnico(usuarioPerfil);
                            logger.debug("Se agrega el perfil: [" + item.getCodeperfil() + "] al usuario [" + item.getLoginname() + "]");
                            if (userPerfilExist == null)
                            {
                                //Se agrega la relacion.
                                usuarioPerfilDao.agregar(usuarioPerfil);

                                //Se asocia el perfil por defecto
                                usersCreate.setCodeperfil(ConstantesBaseBean.GUEST);
                                usersCreate.setDescription("Se agrega el elemento con el perfil por defecto, el perfil indicado no existia");
                                //Se lista el detalle de la transaccion
                                listDetails.add(recordDetailBinnacleUsersMassiveProfileDefault(ConstantesBaseBean.GUEST, item.getCodeperfil(), item.getLoginname()));
                            }
                        }

                    }
                }
                else
                {
                    usersCreate.setCodeperfil(ConstantesBaseBean.EMPTY);
                    usersCreate.setDescription("El usuario ya existe");
                    listDetails.add(recordDetailBinnacleUsersMassiveUserExist(item.getLoginname()));
                }
                //Se agrega al item como respuesta
                listResponse.add(usersCreate);
            }
            //Se realiza la auditoria de la operacion
            try
            {
                bitacoraService.registrarBitacora(EnumFuncionalityISoft.FUNCIONALIDAD_CREAR_USUARIOS_MASIVOS, mapConfiguration, listDetails);
            }
            catch (ServiceException ex)
            {
                logger.error("Falla el registro de bitacora de [" + mapConfiguration.get(MAP_USER_TRANSACTION) + "]");
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
    public void manageStatusEnabledUsers(Map<String, String> mapConfiguration, String loginuser, boolean status) throws ServiceException
    {
        List<DetalleBitacora> listDetails = new ArrayList<>();
        try
        {
            //Se consulta el usuario para saber si existe y garantizar las consultas posteriores
            Usuarios usuario = usuariosDao.getUsuarioPorLogin(loginuser.toUpperCase());
            if (usuario == null)
                throw new DaoException(EstatusGenericos.PROFILER_USER_DOES_NOT_EXIST.getCode());

            if (status)
                usuario.setHabilitado(1L);
            else
                usuario.setHabilitado(0L);

            usuariosDao.actualizar(usuario);
            //Se registra la transaccion
            //Se lista el detalle de la transaccion
            listDetails.add(recordDetailBinnacleStatusUsers(usuario.getLogin(), status));
            bitacoraService.registrarBitacora(EnumFuncionalityISoft.FUNCIONALIDAD_ADMINISTRAR_STATUS_USUARIOS, mapConfiguration,listDetails);
        }
        catch (DaoException e)
        {
            String mensaje = "Error tratando de habilitar / deshabilitar :["+status+"] para el usuario :[" + loginuser + "]";
            logger.error(mensaje, e);
            throw new ServiceException(e.getMessage(), e, e.getCode());
        }
    }

    @Override
    public List<UsersMassiveOutDTO> addProfilesUser(Map<String, String> mapConfiguration, String loginname, List<String> perfiles) throws ServiceException
    {
        List<UsersMassiveOutDTO> listResponse = new ArrayList<>();
        List<DetalleBitacora> listDetails = new ArrayList<>();
        try
        {
            //Se consulta el usuario para saber si existe y garantizar el agregado del perfil
            Usuarios usuario = usuariosDao.getUsuarioPorLogin(loginname.toUpperCase());
            if (usuario == null)
                throw new DaoException(EstatusGenericos.PROFILER_USER_DOES_NOT_EXIST.getCode());

            for (String itemperfil : perfiles)
            {
                //Se busca el perfil
                Perfiles perfil = new Perfiles(itemperfil);
                perfil = perfilesDao.buscarObjetoUnico(perfil);

                //Transformar todos los string en mayusculas
                if (perfil != null)
                {
                    convertAtrrUppercase(perfil);

                    //Se crea la relacion
                    UsuarioPerfil usuarioPerfil = new UsuarioPerfil(usuario,perfil,1l);

                    //Se agrega la relacion
                    UsuarioPerfil usuarioPerfilExist = usuarioPerfilDao.buscarObjetoUnico(usuarioPerfil);
                    logger.debug("Se agrega el perfil: [" + itemperfil + "] al usuario [" + loginname + "]");
                    if (usuarioPerfilExist == null){
                        usuarioPerfilDao.agregar(usuarioPerfil);
                        //Se crea la respuesta
                        listResponse.add(construcObjectResponseAddProfileOk(usuario,itemperfil));
                        //Se lista el detalle de la transaccion
                        listDetails.add(recordDetailBinnacleUsersAddProfileOk(usuario.getLogin(), itemperfil));
                    }
                    else
                        logger.debug("Se repite relacion: [" + itemperfil + "] al usuario [" + loginname + "], no se agrega nuevamente");
                }
                else
                {
                    //Se crea la respuesta
                    listResponse.add(construcObjectResponseAddProfileFail(usuario,itemperfil));
                    //Se lista el detalle de la transaccion
                    listDetails.add(recordDetailBinnacleUsersAddProfileFail(usuario.getLogin(), itemperfil));
                    logger.debug("El perfil: [" + itemperfil + "] no se agrega por que no existe");
                }
            }
            //Se registra la transaccion
            bitacoraService.registrarBitacora(EnumFuncionalityISoft.FUNCIONALIDAD_ASOCIAR_PERFIL, mapConfiguration,listDetails);
        }
        catch (DaoException e)
        {
            String mensaje = "Error tratando de asociar perfiles al usuario:  [" + loginname + "]";
            logger.error(mensaje, e);
            throw new ServiceException(e.getMessage(), e, e.getCode());
        }
        return listResponse;
    }

    @Override
    public List<UsersMassiveOutDTO> unAddProfilesUser(Map<String, String> mapConfiguration, String loginuser, List<String> perfiles) throws ServiceException
    {
        List<UsersMassiveOutDTO> listResponse = new ArrayList<>();
        List<DetalleBitacora> listDetails = new ArrayList<>();
        try
        {
            Usuarios usuario = usuariosDao.getUsuarioPorLogin(loginuser.toUpperCase());
            if (usuario == null)
                throw new DaoException(EstatusGenericos.PROFILER_USER_DOES_NOT_EXIST.getCode());

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
                    UsuarioPerfil usuarioPerfil = new UsuarioPerfil(usuario,perfil,1l);

                    //Se agrega la relacion
                    usuarioPerfil = usuarioPerfilDao.buscarObjetoUnico(usuarioPerfil);
                    logger.debug("Se agrega el perfil: [" + itemperfil + "] al usuario [" + loginuser + "]");
                    if (usuarioPerfil != null){
                        usuarioPerfilDao.eliminar(usuarioPerfil);
                        //Se crea la respuesta
                        listResponse.add(construcObjectResponseUnAddProfileOk(usuario,itemperfil));
                        //Se lista el detalle de la transaccion
                        listDetails.add(recordDetailBinnacleUsersUnAddProfileOk(usuario.getLogin(), itemperfil));
                    }
                    else{
                        logger.debug("La relacion: [" + itemperfil + "] al usuario [" + loginuser + "], no existe");
                        //Se crea la respuesta
                        listResponse.add(construcObjectResponseUnAddProfileFail(usuario,itemperfil));
                        //Se lista el detalle de la transaccion
                        listDetails.add(recordDetailBinnacleUsersUnAddProfileFail(usuario.getLogin(), itemperfil));
                    }

                }
                else
                {
                    //Se crea la respuesta
                    listResponse.add(construcObjectResponseUnAddProfileFail(usuario,itemperfil));
                    //Se lista el detalle de la transaccion
                    listDetails.add(recordDetailBinnacleUsersUnAddProfileFail(usuario.getLogin(), itemperfil));
                    logger.debug("El perfil: [" + itemperfil + "] no existe");
                }
            }
            //Se registra la transaccion
            bitacoraService.registrarBitacora(EnumFuncionalityISoft.FUNCIONALIDAD_DESASOCIAR_PERFIL, mapConfiguration,listDetails);
        }
        catch (DaoException e)
        {
            String mensaje = "Error tratando de desasociar perfiles al usuario:  [" + loginuser + "]";
            logger.error(mensaje, e);
            throw new ServiceException(e.getMessage(), e, e.getCode());
        }
        return listResponse;
    }

    @Override
    public List<ProfilesToUserOutDTO> findProfilesUsers(Map<String, String> mapConfiguration, String loginuser) throws ServiceException
    {
        try
        {
            List<DetalleBitacora> listDetails = new ArrayList<>();
            Usuarios usuario = usuariosDao.getUsuarioPorLogin(loginuser.toUpperCase());
            if (usuario == null)
                throw new DaoException(EstatusGenericos.PROFILER_USER_DOES_NOT_EXIST.getCode());

            List<ProfilesToUserOutDTO> listProfiles = perfilesDao.findProfilesUsers(usuario);
            for (ProfilesToUserOutDTO perfil : listProfiles){
                //Se lista el detalle de la transaccion
                listDetails.add(recordDetailBinnacleUsersFindProfileOk(usuario.getLogin(), perfil.getNombre_perfil()));
            }
            bitacoraService.registrarBitacora(EnumFuncionalityISoft.FUNCIONALIDAD_CONSULTAR_PERFILES_USUARIO, mapConfiguration,listDetails);
            return listProfiles;
        }
        catch (DaoException e)
        {
            String mensaje = "Error tratando de consultar los perfiles del usuario ["+loginuser+"]";
            logger.error(mensaje, e);
            throw new ServiceException(e.getMessage(), e, e.getCode());
        }
    }
}
