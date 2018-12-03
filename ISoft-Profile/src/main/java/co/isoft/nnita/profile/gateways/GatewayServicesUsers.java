package co.isoft.nnita.profile.gateways;

import co.isoft.nnita.logger.util.Log;
import co.isoft.nnita.logger.util.ModulesIsoft;
import co.isoft.nnita.profile.api.dto.input.AddProfileToUserInputDTO;
import co.isoft.nnita.profile.api.dto.input.AdminStatusUsersInputDTO;
import co.isoft.nnita.profile.api.dto.input.NewUserInputDTO;
import co.isoft.nnita.profile.api.dto.input.NewUsersMassiveInputDTO;
import co.isoft.nnita.profile.api.dto.output.ProfilesToUserOutDTO;
import co.isoft.nnita.profile.api.dto.output.UsersAllOutDTO;
import co.isoft.nnita.profile.api.dto.output.UsersMassiveOutDTO;
import co.isoft.nnita.profile.api.exceptions.LicenseException;
import co.isoft.nnita.profile.api.exceptions.ParamsException;
import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.services.UsuariosService;
import co.isoft.nnita.profile.api.util.CommonsResponse;
import co.isoft.nnita.profile.api.util.EstatusGenericos;
import co.isoft.nnita.profile.api.util.GatewayBaseBean;
import co.isoft.nnita.profile.util.WebUtils;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static co.isoft.nnita.profile.api.util.ConstantesBaseBean.KEY_ERRORS_GENERIC;
import static co.isoft.nnita.profile.api.util.ConstantesBaseBean.MAP_USER_TRANSACTION;

/**
 * Gateway de microservicios de operaciones sobre usuarios ISoftProfiler
 *
 * @author Yaher Carrillo
 * @since 09/09/2018
 */
@RestController
@RequestMapping(value = "/GatewayServicesUsersISoftProfile")
public class GatewayServicesUsers
{
    /**
     * Objeto utilizado para el log
     */
    private static final org.apache.commons.logging.Log logger = LogFactory.getLog(GatewayServicesUsers.class);

    /**
     * Contexto spring
     */
    @Autowired
    private ServletContext context;
    /**
     * Servicio de usuarios
     */
    @Autowired
    @Qualifier("proxyUsuariosService")
    private UsuariosService userServices;
    /**
     * Recursos de mensajes internacionalizables
     */
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private WebUtils webUtils;

    /**
     * Configura los headers de aceptacion
     *
     * @param response response de salida
     */
    @RequestMapping(value = "/GatewayServicesUsersISoftProfile/**", method = RequestMethod.OPTIONS)
    public void corsHeaders(HttpServletResponse response)
    {
        response.addHeader("Access-Control-Allow-Origin", "*");
        //response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST");
        response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
        response.addHeader("Access-Control-Max-Age", "3600");
    }

    /**
     * Permite realizar ping sobre el servicio.
     *
     * @return Response comun con los datos de servicio
     */
    @RequestMapping(value = "/ping/{shared_key}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse ping(@PathVariable("shared_key") String nombrePantalla)
    {
        CommonsResponse response = new CommonsResponse();
        response.setCodigo(EstatusGenericos.INFO.getCode());
        response.setEstatus(EstatusGenericos.INFO.getCode());
        response.setDescripcion(EstatusGenericos.INFO.getDescription());
        return response;
    }

    /**
     * Consulta los datos basicos de un usuario.
     *
     * @param usuario Login de usuario
     * @param llave_seguridad Licencia de consumo
     * @return Response comun con los datos de servicio
     */
    @RequestMapping(value = "/consultarusuario", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse finduser(@RequestParam String usuario, @RequestParam String llave_seguridad, HttpServletRequest request)
    {
        CommonsResponse response = new CommonsResponse();
        try
        {
            logger.debug("Se valida la licencia si puede consumir los procesos.");
            GatewayBaseBean.validateLicenceToWS(llave_seguridad, webUtils.getClientIp(request));
            response.setResponse(userServices.findUser(usuario));
        }
        catch (ParamsException ex)
        {
            response.toParamsWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode());
            logger.error("Parametros  de Licencia Errados  WS [consultarusuario], key["+llave_seguridad+"].", ex);
            return response;
        }
        catch (LicenseException ex)
        {
            response.toLicenceWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode(), llave_seguridad);
            logger.error("Parametros  de Licencia Errados  WS [consultarusuario].", ex);
            return response;
        }
        catch (ServiceException ex)
        {
            response.toParamsWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode());
            logger.warn("Error de Servicio  WS [consultarusuario].", ex);
            return response;
        }
        catch (Exception ex)
        {
            logger.warn("Error Generico en  WS [consultarusuario].", ex);
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        logger.info("Se retorna respuesta efectiva del WS [consultarusuario].");
        return response.toOk();
    }

    /**
     * Consulta todos los usuarios del sistema
     *
     * @param llave_seguridad Licencia de consumo
     * @return Response comun con los datos de servicio
     */
    @RequestMapping(value = "/consultarusuariossistema", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse findallusers(@RequestParam String llave_seguridad, HttpServletRequest request)
    {
        CommonsResponse response = new CommonsResponse();
        Map<String, String> mapConfiguration = null;
        try
        {
            logger.debug("Se valida la licencia si puede consumir los procesos.");
            GatewayBaseBean.validateLicenceToWS(llave_seguridad, webUtils.getClientIp(request));

            List<UsersAllOutDTO> usuarios = userServices.findAllUsers();
            if (usuarios != null && !usuarios.isEmpty())
                response.setResponse(usuarios);
            else
                return response.toEmpty();
        }
        catch (LicenseException ex)
        {
            response.toLicenceWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode(), llave_seguridad);
            logger.error("Parametros  de Licencia Errados  WS [consultarusuariossistema], key["+llave_seguridad+"].", ex);
            return response;
        }
        catch (ServiceException ex)
        {
            response.toParamsWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode());
            logger.warn("Error de Servicio  WS [consultarusuariossistema].", ex);
            return response;
        }
        catch (Exception ex)
        {
            logger.warn("Error Generico en  WS [consultarusuariossistema].", ex);
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        logger.info("Se retorna respuesta efectiva del WS [consultarusuariossistema].");
        return response.toOk();
    }

    /**
     * Crea un usuario en el sistema ISoftProfile
     *
     * @param llave_seguridad Licencia de consumo
     * @param request   Datos de entrada de elementos de usuario
     * @return Response comun con los datos de servicio
     */
    @RequestMapping(value = "/crearusuario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse createuser(@RequestParam String llave_seguridad, @RequestBody NewUserInputDTO request, HttpServletRequest requestTransaction)
    {
        CommonsResponse response = new CommonsResponse();
        Map<String, String> mapConfiguration = null;
        try
        {
            logger.debug("Se valida la licencia si puede consumir los procesos.");
            mapConfiguration = GatewayBaseBean.validateLicenceToWS(llave_seguridad, webUtils.getClientIp(requestTransaction));

            userServices.createUser(mapConfiguration, request);
        }
        catch (ParamsException ex)
        {
            response.toParamsWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode());
            logger.error("Parametros  de Licencia Errados  WS [crearusuarioisoft], key["+llave_seguridad+"].", ex);
            return response;
        }
        catch (LicenseException ex)
        {
            response.toLicenceWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode(), llave_seguridad);
            logger.error("Parametros  de Licencia Errados  WS [consultarusuariossistema], key["+llave_seguridad+"].", ex);
            return response;
        }
        catch (ServiceException ex)
        {
            response.toParamsWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode());
            logger.warn("Error de Servicio  WS [crearusuarioisoft].", ex);
            return response;
        }
        catch (Exception ex)
        {
            logger.warn("Error Generico en  WS [crearusuarioisoft].", ex);
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        logger.info("Se retorna respuesta efectiva del WS [crearusuario].");
        return response.toOk();
    }

    /**
     * Crea usuarios masivos en el sistema
     *
     * @param sharedkey Licencia de consumo
     * @param request   cuerpo del request
     * @return Response comun con los datos de respuesta
     */
    @RequestMapping(value = "/crearusuariosisoftmasivo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse createusersmassive(@RequestParam String sharedkey, @RequestBody NewUsersMassiveInputDTO request, HttpServletRequest requestTransaction)
    {
        CommonsResponse response = new CommonsResponse();
        Map<String, String> mapConfiguration = null;
        try
        {
            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), sharedkey, "Se valida la licencia si puede consumir los procesos.");
            mapConfiguration = GatewayBaseBean.validateLicenceToWS(sharedkey, webUtils.getClientIp(requestTransaction));

            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se validan los parametros de entrada.");
            GatewayBaseBean.validarParametrosGenericos(request.getPassword());

            List<UsersMassiveOutDTO> list = userServices.createUsersMassiveIsoftProfile(mapConfiguration, request.getPassword(), request.getUsuariosYPerfil());
            response.setResponse(list);
        }
        catch (ParamsException ex)
        {
            String message = response.toParamsWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode());
            Log.getInstance().warn(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), message, ex);
            return response;
        }
        catch (LicenseException ex)
        {
            String message = response.toLicenceWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode(), sharedkey);
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), sharedkey, message, ex);
            return response;
        }
        catch (ServiceException ex)
        {
            String message = response.toParamsWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode());
            Log.getInstance().warn(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), message, ex);
            return response;
        }
        catch (Exception ex)
        {
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "[crearusuariosisoftmasivo]", ex);
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        Log.getInstance().info(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se retorna respuesta efectiva del WS [crearusuariosisoftmasivo].");
        response.toOk();
        return response;
    }

    /**
     * Crea un usuario en el sistema ISoftProfile
     *
     * @param sharedkey Licencia de consumo
     * @param request   Datos de entrada de elementos de usuario
     * @return Response comun con los datos de servicio
     */
    @RequestMapping(value = "/modificarusuarioisoft", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse modifyuser(@RequestParam String sharedkey, @RequestBody NewUserInputDTO request, HttpServletRequest requestTransaction)
    {
        CommonsResponse response = new CommonsResponse();
        Map<String, String> mapConfiguration = null;
        try
        {
            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), sharedkey, "Se valida la licencia si puede consumir los procesos.");
            mapConfiguration = GatewayBaseBean.validateLicenceToWS(sharedkey, webUtils.getClientIp(requestTransaction));

            /*Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se validan los parametros de entrada.");
            GatewayBaseBean.validarParametrosGenericos(request.getUsuario(), request.getNombres());
            GatewayBaseBean.validarEmail(request.getCorreo());
            Usuarios user = GatewayBaseBean.clonUsersRequest(request);*/

            userServices.modifyUser(mapConfiguration, new Usuarios());
        }
        catch (LicenseException ex)
        {
            String message = response.toLicenceWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode(), sharedkey);
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), sharedkey, message, ex);
            return response;
        }
        catch (ServiceException ex)
        {
            String message = response.toParamsWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode());
            Log.getInstance().warn(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), message, ex);
            return response;
        }
        catch (Exception ex)
        {
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "[crearusuarioisoft]", ex);
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        Log.getInstance().info(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se retorna respuesta efectiva del WS [crearusuarioisoft].");
        return response.toOk();
    }

    /**
     * Asocia perfiles por el codigo de perfil a un usuario
     * determinado.
     *
     * @param sharedkey llave de linceciamiento
     * @param request   Body de request
     * @return Response comun
     */
    @RequestMapping(value = "/asociarperfilusuario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse addprofileuser(@RequestParam String sharedkey, @RequestBody AddProfileToUserInputDTO request, HttpServletRequest requestTransaction)
    {
        CommonsResponse response = new CommonsResponse();
        Map<String, String> mapConfiguration = null;
        try
        {
            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), sharedkey, "Se valida la licencia si puede consumir los procesos.");
            mapConfiguration = GatewayBaseBean.validateLicenceToWS(sharedkey, webUtils.getClientIp(requestTransaction));

            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se validan los parametros de entrada.");
            GatewayBaseBean.validarParametrosGenericos(request.getLoginname());
            GatewayBaseBean.validarParametrosGenericos(request.getCodesProfiles());

            //Crea un usuario sin perfil, no puedra ingresar
            List<UsersMassiveOutDTO> list = userServices.addProfilesUser(mapConfiguration, request.getLoginname(), request.getCodesProfiles());
            response.setResponse(list);
        }
        catch (ParamsException ex)
        {
            String message = response.toParamsWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode());
            Log.getInstance().warn(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), message, ex);
            return response;
        }
        catch (LicenseException ex)
        {
            String message = response.toLicenceWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode(), sharedkey);
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), sharedkey, message, ex);
            return response;
        }
        catch (ServiceException ex)
        {
            String message = response.toParamsWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode());
            Log.getInstance().warn(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), message, ex);
            return response;
        }
        catch (Exception ex)
        {
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "[asociarperfilusuario]", ex);
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        Log.getInstance().info(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se retorna respuesta efectiva del WS [asociarperfilusuario].");
        return response.toOk();
    }

    /**
     * Desasocia un perfil de un usuario indicado
     *
     * @param sharedkey Llave de lincenciamiento
     * @param request   Cuerpo de Respuesto
     * @return Response comun
     */
    @RequestMapping(value = "/desasociarperfilusuario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse unaddprofileuser(@RequestParam String sharedkey, @RequestBody AddProfileToUserInputDTO request, HttpServletRequest requestTransaction)
    {
        CommonsResponse response = new CommonsResponse();
        Map<String, String> mapConfiguration = null;
        try
        {
            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), sharedkey, "Se valida la licencia si puede consumir los procesos.");
            mapConfiguration = GatewayBaseBean.validateLicenceToWS(sharedkey, webUtils.getClientIp(requestTransaction));

            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se validan los parametros de entrada.");
            GatewayBaseBean.validarParametrosGenericos(request.getLoginname());
            GatewayBaseBean.validarParametrosGenericos(request.getCodesProfiles());

            //Crea un usuario sin perfil, no puedra ingresar
            List<UsersMassiveOutDTO> list = userServices.unAddProfilesUser(mapConfiguration, request.getLoginname(), request.getCodesProfiles());
            response.setResponse(list);
        }
        catch (ParamsException ex)
        {
            String message = response.toParamsWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode());
            Log.getInstance().warn(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), message, ex);
            return response;
        }
        catch (LicenseException ex)
        {
            String message = response.toLicenceWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode(), sharedkey);
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), sharedkey, message, ex);
            return response;
        }
        catch (ServiceException ex)
        {
            String message = response.toParamsWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode());
            Log.getInstance().warn(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), message, ex);
            return response;
        }
        catch (Exception ex)
        {
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "[desasociarperfilusuario]", ex);
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        Log.getInstance().info(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se retorna respuesta efectiva del WS [desasociarperfilusuario].");
        return response.toOk();
    }

    /**
     * Servicio que administra el estatus de un usuario
     *
     * @param sharedkey Llave de licenciamiento
     * @param request   Cuerpo de Entrada
     * @return Response comun
     */
    @RequestMapping(value = "/adminstatususuarios", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse managedstatususuarios(@RequestParam String sharedkey, @RequestBody AdminStatusUsersInputDTO request, HttpServletRequest requestTransaction)
    {
        CommonsResponse response = new CommonsResponse();
        Map<String, String> mapConfiguration = null;
        try
        {
            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), sharedkey, "Se valida la licencia si puede consumir los procesos.");
            mapConfiguration = GatewayBaseBean.validateLicenceToWS(sharedkey, webUtils.getClientIp(requestTransaction));

            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se validan los parametros de entrada.");
            GatewayBaseBean.validarParametrosGenericos(request.getLoginname(), request.getStatus().toString());

            //Crea un usuario sin perfil, no puedra ingresar
            userServices.manageStatusEnabledUsers(mapConfiguration, request.getLoginname(), request.getStatus().equals(1L) ? true : false);
        }
        catch (ParamsException ex)
        {
            String message = response.toParamsWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode());
            Log.getInstance().warn(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), message, ex);
            return response;
        }
        catch (LicenseException ex)
        {
            String message = response.toLicenceWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode(), sharedkey);
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), sharedkey, message, ex);
            return response;
        }
        catch (ServiceException ex)
        {
            String message = response.toParamsWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode());
            Log.getInstance().warn(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), message, ex);
            return response;
        }
        catch (Exception ex)
        {
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "[adminstatususuarios]", ex);
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        Log.getInstance().info(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se retorna respuesta efectiva del WS [adminstatususuarios].");
        return response.toOk();
    }

    /**
     * Servicio que consulta los perfiles de un determinado usuario
     *
     * @param loginuser Usuario a CONSULTAR
     * @return Response comun con los datos de servicio
     */
    @RequestMapping(value = "/consultarperfilesusuario", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse findprofilesusers(@RequestParam String loginuser, @RequestParam String sharedkey, HttpServletRequest requestTransaction)
    {
        CommonsResponse response = new CommonsResponse();
        Map<String, String> mapConfiguration = null;
        try
        {
            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), sharedkey, "Se valida la licencia si puede consumir los procesos.");
            mapConfiguration = GatewayBaseBean.validateLicenceToWS(sharedkey, webUtils.getClientIp(requestTransaction));

            List<ProfilesToUserOutDTO> list = userServices.findProfilesUsers(mapConfiguration, loginuser);
            if (list == null || list.isEmpty())
                return response.toEmpty();
            response.setResponse(list);
        }
        catch (LicenseException ex)
        {
            String message = response.toLicenceWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode(), sharedkey);
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), sharedkey, message, ex);
            return response;
        }
        catch (ServiceException ex)
        {
            String message = response.toParamsWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode());
            Log.getInstance().warn(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), message, ex);
            return response;
        }
        catch (Exception ex)
        {
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "[consultarperfilesusuario]", ex);
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        Log.getInstance().info(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se retorna respuesta efectiva del WS [consultarperfilesusuario].");
        return response.toOk();
    }
}
