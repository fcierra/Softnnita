package co.isoft.nnita.profile.gateways;

import co.isoft.nnita.logger.util.Log;
import co.isoft.nnita.logger.util.ModulesIsoft;
import co.isoft.nnita.profile.api.exceptions.LicenseException;
import co.isoft.nnita.profile.api.exceptions.ParamsException;
import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.util.CommonsResponse;
import co.isoft.nnita.profile.api.dto.input.AddProfileToUserInputDTO;
import co.isoft.nnita.profile.api.dto.input.AdminStatusUsersInputDTO;
import co.isoft.nnita.profile.api.dto.input.NewUserInputDTO;
import co.isoft.nnita.profile.api.dto.input.NewUsersMassiveInputDTO;
import co.isoft.nnita.profile.api.util.GatewayBaseBean;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.dto.output.ProfilesToUserOutDTO;
import co.isoft.nnita.profile.api.dto.output.UsersMassiveOutDTO;
import co.isoft.nnita.profile.api.dto.output.UsersAllOutDTO;
import co.isoft.nnita.profile.api.services.UsuariosService;
import co.isoft.nnita.profile.api.util.EstatusGenericos;
import co.isoft.nnita.profile.util.WebUtils;
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
        response.setCodeTrasacction(EstatusGenericos.INFO.getCode());
        response.setStatus(EstatusGenericos.INFO.getCode());
        response.setDescriptionTransacction(EstatusGenericos.INFO.getDescription());
        return response;
    }

    /**
     * Consulta los datos basicos de un usuario.
     *
     * @param loginuser Login de usuario
     * @param sharedkey Licencia de consumo
     * @return Response comun con los datos de servicio
     */
    @RequestMapping(value = "/consultarusuario", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse finduser(@RequestParam String loginuser, @RequestParam String sharedkey, HttpServletRequest request)
    {
        CommonsResponse response = new CommonsResponse();
        Map<String, String> mapConfiguration = null;
        try
        {
            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), loginuser, "Se valida la licencia si puede consumir los procesos.");
            mapConfiguration = GatewayBaseBean.validateLicenceToWS(sharedkey, webUtils.getClientIp(request));

            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se validan los parametros de entrada.");
            GatewayBaseBean.validarParametrosGenericos(loginuser);

            Usuarios usuario = userServices.findUser(loginuser);
            usuario.setClave(null);
            response.setResponse(usuario);
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
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "[consultarusuario].", ex);
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        Log.getInstance().info(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se retorna respuesta efectiva del WS [consultarusuario].");
        return response.toOk();
    }

    /**
     * Consulta todos los usuarios del sistema
     *
     * @param sharedkey Licencia de consumo
     * @return Response comun con los datos de servicio
     */
    @RequestMapping(value = "/consultarusuariossistema", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse findallusers(@RequestParam String sharedkey, HttpServletRequest request)
    {
        CommonsResponse response = new CommonsResponse();
        Map<String, String> mapConfiguration = null;
        try
        {
            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), "GET", "Se valida la licencia si puede consumir los procesos.");
            mapConfiguration = GatewayBaseBean.validateLicenceToWS(sharedkey, webUtils.getClientIp(request));

            List<UsersAllOutDTO> usuarios = userServices.findAllUsers();
            if (usuarios != null && !usuarios.isEmpty())
                response.setResponse(usuarios);
            else
                return response.toEmpty();
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
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "[consultarusuariossistema].", ex);
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        Log.getInstance().info(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se retorna respuesta efectiva del WS [consultarusuariossistema].");
        return response.toOk();
    }

    /**
     * Crea un usuario en el sistema ISoftProfile
     *
     * @param sharedkey Licencia de consumo
     * @param request   Datos de entrada de elementos de usuario
     * @return Response comun con los datos de servicio
     */
    @RequestMapping(value = "/crearusuarioisoft", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse createuser(@RequestParam String sharedkey, @RequestBody NewUserInputDTO request, HttpServletRequest requestTransaction)
    {
        CommonsResponse response = new CommonsResponse();
        Map<String, String> mapConfiguration = null;
        try
        {
            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), sharedkey, "Se valida la licencia si puede consumir los procesos.");
            mapConfiguration = GatewayBaseBean.validateLicenceToWS(sharedkey, webUtils.getClientIp(requestTransaction));

            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se validan los parametros de entrada.");
            GatewayBaseBean.validarParametrosGenericos(request.getLoginname(), request.getNombres(), request.getClave());
            GatewayBaseBean.validarEmail(request.getEmail());
            Usuarios user = GatewayBaseBean.clonUsersRequest(request);

            userServices.createUserIsoftProfile(mapConfiguration, user);
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
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "[crearusuarioisoft]", ex);
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        Log.getInstance().info(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se retorna respuesta efectiva del WS [crearusuarioisoft].");
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

            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se validan los parametros de entrada.");
            GatewayBaseBean.validarParametrosGenericos(request.getLoginname(), request.getNombres());
            GatewayBaseBean.validarEmail(request.getEmail());
            Usuarios user = GatewayBaseBean.clonUsersRequest(request);

            userServices.modifyUser(mapConfiguration, user);
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
