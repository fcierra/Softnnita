package co.isoft.nnita.profile.impl.service;

import co.isoft.nnita.profile.api.dao.PerfilesDao;
import co.isoft.nnita.profile.api.dao.UsuarioPerfilDao;
import co.isoft.nnita.profile.api.dao.UsuariosDao;
import co.isoft.nnita.profile.api.dto.input.NewUserInputDTO;
import co.isoft.nnita.profile.api.dto.output.ProfilesToUserOutDTO;
import co.isoft.nnita.profile.api.dto.output.UserDTO;
import co.isoft.nnita.profile.api.dto.output.UsersAllOutDTO;
import co.isoft.nnita.profile.api.dto.output.UsersMassiveOutDTO;
import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.exceptions.ParamsException;
import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.DetalleBitacora;
import co.isoft.nnita.profile.api.models.Perfiles;
import co.isoft.nnita.profile.api.models.UsuarioPerfil;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.modelsweb.DatosSesionUsuario;
import co.isoft.nnita.profile.api.services.BitacoraService;
import co.isoft.nnita.profile.api.services.UsuariosService;
import co.isoft.nnita.profile.api.util.ConstantesBaseBean;
import co.isoft.nnita.profile.api.util.EnumFuncionalityISoft;
import co.isoft.nnita.profile.api.util.EstatusGenericos;
import co.isoft.nnita.profile.impl.util.ValidationsBasicModelUsuarios;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static co.isoft.nnita.profile.api.util.ConstantesBaseBean.MAP_USER_TRANSACTION;
import static co.isoft.nnita.profile.api.util.EstatusGenericos.*;

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


    /**
     * Contructyor que inicializa el servicio de bitacora
     * @param bitacoraService
     */
    public UsuariosServiceImpl(BitacoraService bitacoraService)
    {
        this.bitacoraService = bitacoraService;
    }

    /**
     * Constructor que inicializa los servicios de bitacora
     * y de mensajeria
     * @param bitacoraService
     */
    public UsuariosServiceImpl(BitacoraService bitacoraService,MessageSource messageSource)
    {
        this.bitacoraService = bitacoraService;
        this.setMessageSource(messageSource);
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
    public UserDTO findUser(String loginUsuario) throws ServiceException, ParamsException
    {
        loginUsuario = loginUsuario.toUpperCase();
        try
        {
            logger.debug("Validando parametros");
            ValidationsBasicModelUsuarios.validateLoginUsers(loginUsuario);

            logger.debug("Inicia la busqueda del usuario");
            Usuarios usuario = this.findUserToAuthenticated(loginUsuario);
            if (usuario != null)
            {
                logger.info("Se encuentra el usuario efectivamente [" + loginUsuario + "]");
                return cloneUserToDto(usuario);
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
            throw new ParamsException(mensaje, e.getCode(), e.getDescripcion());
        }
    }

    @Override
    public Usuarios findUserToAuthenticated(String login) throws ServiceException, ParamsException
    {
        try
        {
            logger.debug("Validando parametros");
            validarParametrosGenericos(login.trim());

            logger.debug("Inicia la busqueda de todos los usuarios");
            Usuarios usuario = usuariosDao.getUsuarioPorLogin(login.trim().toUpperCase());
            if (usuario != null)
            {
                logger.info("Se encuentra el usuario efectivamente [" + login + "]");
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
            throw new ParamsException(mensaje, e.getCode(), e.getDescripcion());
        }
    }

    @Override
    public List<UsersAllOutDTO> findAllUsers() throws ServiceException
    {
        try
        {
            logger.debug("Inicia la busqueda de todos los usuarios");
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
    public void createUser(Usuarios usuario, Map<String, Perfiles> perfiles) throws ServiceException
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
    public void createUser(Map<String, String> mapConfiguration, NewUserInputDTO usuarioDto) throws ServiceException, ParamsException
    {
        try
        {
            logger.debug("Validando parametros");
            ValidationsBasicModelUsuarios.validateIntegriyObjectUsers(usuarioDto);

            List<DetalleBitacora> listDetails = new ArrayList<>();
            convertAtrrUppercase(usuarioDto);
            Usuarios userExisteLogin = usuariosDao.getUsuarioPorLogin(usuarioDto.getUsuario());
            if (userExisteLogin != null)
                throw new DaoException(EstatusGenericos.PROFILER_USER_EXIST.getCode());
            Usuarios userExisteEmail = usuariosDao.getUsuarioPorEmail(usuarioDto.getCorreo());
            if (userExisteEmail != null)
                throw new DaoException(EstatusGenericos.PROFILER_USER_EMAIL_EXIST.getCode());

            usuarioDto.setClave(passwordEncoder.encode(usuarioDto.getClave()));
            Usuarios usuario = clonDtoToUser(usuarioDto);

            //Se agrega el perfil por defecto
            Perfiles perfil = new Perfiles();
            perfil.setNombre_perfil(ConstantesBaseBean.GUEST);
            perfil.setHabilitado(1l);
            perfil = perfilesDao.buscarObjetoUnico(perfil);
            usuario.setPerfilDefault(perfil);
            usuariosDao.agregar(usuario);
            logger.info("Se agrega el usuario [" + usuario.getLogin() + "]");
            listDetails.add(recordDetailBinnacleUsersMassiveSucess("", usuario.getLogin()));
            bitacoraService.registrarBitacora(EnumFuncionalityISoft.FUNCIONALIDAD_CREAR_USUARIO, mapConfiguration, listDetails);
            logger.info("Se registra la auditoria");
        }
        catch (DaoException e)
        {
            String mensaje = "Error al crear usuario [" + usuarioDto.getUsuario() + "]";
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
            if (userExisteEmail != null && !userExisteEmail.getId().equals(userExisteLogin.getId()))
                throw new DaoException(EstatusGenericos.PROFILER_USER_EMAIL_EXIST.getCode());
            convertAtrrUppercase(userExisteLogin);

            //Se evaluan los registros para verificar si similitud de lo contrario se registra en bitacora el cambio
            if (!usuario.getEmail().equals(userExisteLogin.getEmail()))
            {
                String antes = userExisteLogin.getEmail();
                String despues = usuario.getEmail();
                userExisteLogin.setEmail(usuario.getEmail());
                listDetails.add(recordDetailBinnacleUsersModifySucess(usuario.getLogin(), "Email", antes, despues));
            }
            if (!usuario.getNombres().equals(userExisteLogin.getNombres()))
            {
                String antes = userExisteLogin.getNombres();
                String despues = usuario.getNombres();
                userExisteLogin.setNombres(usuario.getNombres());
                listDetails.add(recordDetailBinnacleUsersModifySucess(usuario.getLogin(), "Nombres", antes, despues));
            }
            if (!usuario.getApellidos().equals(userExisteLogin.getApellidos()))
            {
                String antes = userExisteLogin.getApellidos();
                String despues = usuario.getApellidos();
                userExisteLogin.setApellidos(usuario.getApellidos());
                listDetails.add(recordDetailBinnacleUsersModifySucess(usuario.getLogin(), "Apellidos", antes, despues));
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
    public List<UsersMassiveOutDTO> createUsersMassive(Map<String, String> mapConfiguration, String passord, List<UsersMassiveOutDTO> listUsers) throws ServiceException, ParamsException
    {
        List<UsersMassiveOutDTO> listResponse = new ArrayList<>();
        List<DetalleBitacora> listDetails = new ArrayList<>();
        try
        {
            //Valida que la clave general cumpla con los requerimientos basicos
            ValidationsBasicModelUsuarios.validatePasswordUsers(passord);
            for (UsersMassiveOutDTO item : listUsers)
            {

                Usuarios userExist = usuariosDao.getUsuarioPorLogin(item.getUsuario().toUpperCase());

                //Se crea la respuesta de la lista response
                UsersMassiveOutDTO usersCreate = new UsersMassiveOutDTO();
                usersCreate.setUsuario(item.getUsuario());

                if (userExist == null)
                {
                    try
                    {
                        logger.debug("Se validan los parametros de entrada");
                        ValidationsBasicModelUsuarios.validateIntegriyObjectUsersMassive(item);
                        userExist = new Usuarios();
                        userExist.setLogin(item.getUsuario());
                        userExist.setNombres(item.getNombres() != null && !item.getNombres().trim().equals("") ? item.getNombres() : ConstantesBaseBean.EMPTY_NAMES);
                        userExist.setApellidos(item.getApellidos() != null && !item.getApellidos().trim().equals("") ? item.getApellidos() : ConstantesBaseBean.EMPTY_NAMES);

                        //Se encripta la clave
                        passord = passwordEncoder.encode(passord);
                        userExist.setClave(passord);
                        userExist.setFecha_registro(new Date());
                        userExist.setHabilitado(1L);
                        convertAtrrUppercase(userExist);

                        logger.debug("Se agrega el perfil por defecto del usuario.");
                        Perfiles perfildefecto = new Perfiles();
                        perfildefecto.setNombre_perfil(ConstantesBaseBean.GUEST);
                        perfildefecto = perfilesDao.buscarObjetoUnico(perfildefecto);
                        if (perfildefecto == null){
                            logger.debug("El perfil indicado ["+ConstantesBaseBean.GUEST+"] no existe.");
                            throw new DaoException(EstatusGenericos.PROFILER_PROFILE_GUEST_NOT_EXIST.getCode());
                        }
                        userExist.setPerfilDefault(perfildefecto);

                        usuariosDao.agregar(userExist);
                        logger.debug("Se agrega al usuario [" + userExist.getLogin() + "]");

                        Perfiles perfil = new Perfiles();
                        perfil.setNombre_perfil(item.getCodeperfil());
                        perfil = perfilesDao.buscarObjetoUnico(perfil);

                        //Transformar todos los string en mayusculas
                        UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
                        if (perfil != null)
                        {
                            logger.debug("Se valida el perfil indicado ["+item.getCodeperfil()+"] ");
                            convertAtrrUppercase(perfil);

                            usuarioPerfil.setUsuario(userExist);
                            usuarioPerfil.setPerfil(perfil);
                            usuarioPerfil.setHabilitado(1L);
                            UsuarioPerfil userProfileExist = usuarioPerfilDao.buscarObjetoUnico(usuarioPerfil);
                            if (userProfileExist == null)
                            {
                                logger.debug("Se agrega el perfil: [" + item.getCodeperfil() + "] al usuario [" + userExist.getLogin() + "]");
                                usuarioPerfilDao.agregar(usuarioPerfil);

                                usersCreate = recordOutCreateUsersMassive(perfil.getNombre_perfil(), PROFILER_USER_ADD_SUCCESS.getDescription(), PROFILER_USER_ADD_SUCCESS.getRefbundle(), INFO.getCode());
                                logger.debug("Se lista la transaccion en base de datos.");
                                listDetails.add(recordDetailBinnacleUsersMassiveSucess(item.getCodeperfil(), item.getUsuario()));
                            }
                        }
                        else
                        {
                            logger.debug("El perfil indicado ["+item.getCodeperfil()+"] no existe, se procede a asociar el perfil ["+ConstantesBaseBean.GUEST+"] ");
                            perfil = new Perfiles();
                            perfil.setNombre_perfil(ConstantesBaseBean.GUEST);
                            perfil = perfilesDao.buscarObjetoUnico(perfil);
                            if (perfil == null){
                                logger.debug("El perfil indicado ["+ConstantesBaseBean.GUEST+"] no existe.");
                                throw new DaoException(EstatusGenericos.PROFILER_PROFILE_GUEST_NOT_EXIST.getCode());
                            }
                            else
                            {
                                usuarioPerfil.setUsuario(userExist);
                                usuarioPerfil.setPerfil(perfil);
                                usuarioPerfil.setHabilitado(1L);
                                //Se busca si la relacion no existe
                                UsuarioPerfil userPerfilExist = usuarioPerfilDao.buscarObjetoUnico(usuarioPerfil);

                                if (userPerfilExist == null)
                                {
                                    logger.debug("Se agrega el perfil: [" + item.getCodeperfil() + "] al usuario [" + item.getUsuario() + "].");
                                    usuarioPerfilDao.agregar(usuarioPerfil);

                                    usersCreate = recordOutCreateUsersMassive( item.getCodeperfil() , PROFILER_USER_ADD_PROFILE_GUEST_SUCCESS.getDescription(), PROFILER_USER_ADD_PROFILE_GUEST_SUCCESS.getRefbundle(), INFO.getCode());
                                    logger.debug("Se lista la transaccion en base de datos.");
                                    listDetails.add(recordDetailBinnacleUsersMassiveProfileDefault( item.getCodeperfil() , item.getCodeperfil(), item.getUsuario()));
                                }else{
                                    logger.debug("El usuario ["+userExist.getLogin()+"] ya tiene asociado el perfil ["+perfil.getNombre_perfil()+"].");
                                }
                            }
                        }
                    }
                    catch (ParamsException ex)
                    {
                        usersCreate = recordOutCreateUsersMassive(ConstantesBaseBean.EMPTY, PROFILER_USER_NAMES_MAX_LENGTH.getDescription(), PROFILER_USER_NAMES_MAX_LENGTH.getRefbundle(), WARN.getCode());
                    }
                }
                else
                {
                    logger.debug("Se busca al usuario y se encuentra repetido");
                    usersCreate = recordOutCreateUsersMassive(ConstantesBaseBean.EMPTY, PROFILER_USER_EXIST.getDescription(), PROFILER_USER_EXIST.getRefbundle(), WARN.getCode());
                    listDetails.add(recordDetailBinnacleUsersMassiveUserExist(item.getUsuario()));
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
        } return listResponse;
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
            bitacoraService.registrarBitacora(EnumFuncionalityISoft.FUNCIONALIDAD_ADMINISTRAR_STATUS_USUARIOS, mapConfiguration, listDetails);
        }
        catch (DaoException e)
        {
            String mensaje = "Error tratando de habilitar / deshabilitar :[" + status + "] para el usuario :[" + loginuser + "]";
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
                    UsuarioPerfil usuarioPerfil = new UsuarioPerfil(usuario, perfil, 1l);

                    //Se agrega la relacion
                    UsuarioPerfil usuarioPerfilExist = usuarioPerfilDao.buscarObjetoUnico(usuarioPerfil);
                    logger.debug("Se agrega el perfil: [" + itemperfil + "] al usuario [" + loginname + "]");
                    if (usuarioPerfilExist == null)
                    {
                        usuarioPerfilDao.agregar(usuarioPerfil);
                        //Se crea la respuesta
                        listResponse.add(construcObjectResponseAddProfileOk(usuario, itemperfil));
                        //Se lista el detalle de la transaccion
                        listDetails.add(recordDetailBinnacleUsersAddProfileOk(usuario.getLogin(), itemperfil));
                    }
                    else
                        logger.debug("Se repite relacion: [" + itemperfil + "] al usuario [" + loginname + "], no se agrega nuevamente");
                }
                else
                {
                    //Se crea la respuesta
                    listResponse.add(construcObjectResponseAddProfileFail(usuario, itemperfil));
                    //Se lista el detalle de la transaccion
                    listDetails.add(recordDetailBinnacleUsersAddProfileFail(usuario.getLogin(), itemperfil));
                    logger.debug("El perfil: [" + itemperfil + "] no se agrega por que no existe");
                }
            }
            //Se registra la transaccion
            bitacoraService.registrarBitacora(EnumFuncionalityISoft.FUNCIONALIDAD_ASOCIAR_PERFIL, mapConfiguration, listDetails);
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
                    UsuarioPerfil usuarioPerfil = new UsuarioPerfil(usuario, perfil, 1l);

                    //Se agrega la relacion
                    usuarioPerfil = usuarioPerfilDao.buscarObjetoUnico(usuarioPerfil);
                    logger.debug("Se agrega el perfil: [" + itemperfil + "] al usuario [" + loginuser + "]");
                    if (usuarioPerfil != null)
                    {
                        usuarioPerfilDao.eliminar(usuarioPerfil);
                        //Se crea la respuesta
                        listResponse.add(construcObjectResponseUnAddProfileOk(usuario, itemperfil));
                        //Se lista el detalle de la transaccion
                        listDetails.add(recordDetailBinnacleUsersUnAddProfileOk(usuario.getLogin(), itemperfil));
                    }
                    else
                    {
                        logger.debug("La relacion: [" + itemperfil + "] al usuario [" + loginuser + "], no existe");
                        //Se crea la respuesta
                        listResponse.add(construcObjectResponseUnAddProfileFail(usuario, itemperfil));
                        //Se lista el detalle de la transaccion
                        listDetails.add(recordDetailBinnacleUsersUnAddProfileFail(usuario.getLogin(), itemperfil));
                    }

                }
                else
                {
                    //Se crea la respuesta
                    listResponse.add(construcObjectResponseUnAddProfileFail(usuario, itemperfil));
                    //Se lista el detalle de la transaccion
                    listDetails.add(recordDetailBinnacleUsersUnAddProfileFail(usuario.getLogin(), itemperfil));
                    logger.debug("El perfil: [" + itemperfil + "] no existe");
                }
            }
            //Se registra la transaccion
            bitacoraService.registrarBitacora(EnumFuncionalityISoft.FUNCIONALIDAD_DESASOCIAR_PERFIL, mapConfiguration, listDetails);
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
            for (ProfilesToUserOutDTO perfil : listProfiles)
            {
                //Se lista el detalle de la transaccion
                listDetails.add(recordDetailBinnacleUsersFindProfileOk(usuario.getLogin(), perfil.getNombre_perfil()));
            }
            bitacoraService.registrarBitacora(EnumFuncionalityISoft.FUNCIONALIDAD_CONSULTAR_PERFILES_USUARIO, mapConfiguration, listDetails);
            return listProfiles;
        }
        catch (DaoException e)
        {
            String mensaje = "Error tratando de consultar los perfiles del usuario [" + loginuser + "]";
            logger.error(mensaje, e);
            throw new ServiceException(e.getMessage(), e, e.getCode());
        }
    }
}
