package co.isoft.nnita.profile.gateways;

import co.isoft.nnita.profile.api.services.PerfilesYPermisosService;
import co.isoft.nnita.profile.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

/**
 * Gateway de microservicios de operaciones sobre
 * perfiles y permisos de éstos.
 *
 * @author Yaher Carrillo
 * @since 08/11/2018
 */
@RestController
@RequestMapping(value = "/GatewayServicesProfilersAndPermisions")
public class GatewayServicesProfilersAndPermisions
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
    private PerfilesYPermisosService perfilesYPermisosService;
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
     * Busca todos los perfiles que maneja el sistema
     *
     * @param sharedkey Llave de licenciamiento
     * @return Response comun con los datos de servicio
     */
    /*@RequestMapping(value = "/consultarperfiles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse findprofiles(@RequestParam String sharedkey, HttpServletRequest requestTransaction)
    {
        CommonsResponse response = new CommonsResponse();
        Map<String, String> mapConfiguration = null;
        try
        {
            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), sharedkey, "Se valida la licencia si puede consumir los procesos.");
            mapConfiguration = GatewayBaseBean.validateLicenceToWS(sharedkey, webUtils.getClientIp(requestTransaction));

            List<Perfiles> list = perfilesYPermisosService.findProfilesSystem(mapConfiguration);
            if (list == null || list.isEmpty())
                return response.toEmpty();
            response.setResponse(GatewayBaseBean.clonProfileResponse(list));
        }
        catch (LicenseException ex)
        {
            String message = response.toLicenceWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode(), sharedkey);
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), sharedkey, message, ex);
            return response;
        }
        catch (ServiceException ex)
        {
            String message = response.toParamsWarn(messageSource, KEY_ERRORS_PROFILER_GENERIC + ex.getCode());
            Log.getInstance().warn(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), message, ex);
            return response;
        }
        catch (Exception ex)
        {
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "[consultarperfiles]", ex);
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        Log.getInstance().info(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se retorna respuesta efectiva del WS [consultarperfiles].");
        return response.toOk();
    }*/

    /**
     * Crea un perfil en el sistema
     *
     * @param sharedkey Llave de licenciamiento
     * @return Response comun con los datos de servicio
     */
    /*@RequestMapping(value = "/crearperfil", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse createprofile(@RequestParam String sharedkey, @RequestBody NewProfileInputDTO request, HttpServletRequest requestTransaction)
    {
        CommonsResponse response = new CommonsResponse();
        Map<String, String> mapConfiguration = null;
        try
        {
            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), sharedkey, "Se valida la licencia si puede consumir los procesos.");
            mapConfiguration = GatewayBaseBean.validateLicenceToWS(sharedkey, webUtils.getClientIp(requestTransaction));
            GatewayBaseBean.validarParametrosGenericos(request.getNombreperfil(), request.getHabilitado().toString());
            perfilesYPermisosService.createProfile(mapConfiguration, request.getNombreperfil(), request.getHabilitado());
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
            String message = response.toParamsWarn(messageSource, KEY_ERRORS_PROFILER_GENERIC + ex.getCode());
            Log.getInstance().warn(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), message, ex);
            return response;
        }
        catch (Exception ex)
        {
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "[crearperfil]", ex);
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        Log.getInstance().info(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se retorna respuesta efectiva del WS [crearperfil].");
        return response.toOk();
    }/*

    /**
     * Crea un perfil en el sistema
     *
     * @param sharedkey Llave de licenciamiento
     * @return Response comun con los datos de servicio
     */
    /*@RequestMapping(value = "/modificarperrfil", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse modificarperrfil(@RequestParam String sharedkey, @RequestBody NewProfileInputDTO request, HttpServletRequest requestTransaction)
    {
        CommonsResponse response = new CommonsResponse();
        Map<String, String> mapConfiguration = null;
        try
        {
            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), sharedkey, "Se valida la licencia si puede consumir los procesos.");
            mapConfiguration = GatewayBaseBean.validateLicenceToWS(sharedkey, webUtils.getClientIp(requestTransaction));
            GatewayBaseBean.validarParametrosGenericos(request.getNombreperfil(), request.getHabilitado().toString());
            perfilesYPermisosService.modifyProfile(mapConfiguration, request);
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
            String message = response.toParamsWarn(messageSource, KEY_ERRORS_PROFILER_GENERIC + ex.getCode());
            Log.getInstance().warn(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), message, ex);
            return response;
        }
        catch (Exception ex)
        {
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "[modificarperrfil]", ex);
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        Log.getInstance().info(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se retorna respuesta efectiva del WS [modificarperrfil].");
        return response.toOk();
    }*/

    /**
     * Consulta los permisos asociados a un perfil
     *
     * @param sharedkey          llave de licencia
     * @param nombreperfil       nombre de perfil a consultar
     * @param requestTransaction request del ambito
     * @return response comun
     */
    /*@RequestMapping(value = "/consultarpermisosperfil", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse consultarpermisosperfil(@RequestParam String sharedkey, @RequestParam String nombreperfil, HttpServletRequest requestTransaction)
    {
        CommonsResponse response = new CommonsResponse();
        Map<String, String> mapConfiguration = null;
        try
        {
            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), sharedkey, "Se valida la licencia si puede consumir los procesos.");
            mapConfiguration = GatewayBaseBean.validateLicenceToWS(sharedkey, webUtils.getClientIp(requestTransaction));
            GatewayBaseBean.validarParametrosGenericos(nombreperfil);
            List<PermissionToProfileOutDTO> listaPermisos = perfilesYPermisosService.findPermissionProfile(mapConfiguration, nombreperfil);
            if (listaPermisos == null || listaPermisos.isEmpty())
                return response.toEmpty();
            else
                response.setResponse(listaPermisos);
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
            String message = response.toParamsWarn(messageSource, KEY_ERRORS_PROFILER_GENERIC + ex.getCode());
            Log.getInstance().warn(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), message, ex);
            return response;
        }
        catch (Exception ex)
        {
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "[crearperfil]", ex);
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        Log.getInstance().info(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se retorna respuesta efectiva del WS [crearperfil].");
        return response.toOk();
    }*/

    /**
     * Modifica los permisos de un determinado perfil
     *
     * @param sharedkey          llave de acceso
     * @param requestTransaction request del ambito
     * @return Response comun.
     */
    /*@RequestMapping(value = "/modificarpermisosperfil", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse modificarpermisosperfil(@RequestParam String sharedkey, @RequestBody ModifyPermissionProfileInputDTO request, HttpServletRequest requestTransaction)
    {
        CommonsResponse response = new CommonsResponse();
        Map<String, String> mapConfiguration = null;
        try
        {
            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), sharedkey, "Se valida la licencia si puede consumir los procesos.");
            mapConfiguration = GatewayBaseBean.validateLicenceToWS(sharedkey, webUtils.getClientIp(requestTransaction));

            List<ModifyPermissionProfileOutDTO> listDetailsOut = perfilesYPermisosService.modifyPermissionProfile(mapConfiguration, request);
            response.setResponse(listDetailsOut);
        }
        catch (LicenseException ex)
        {
            String message = response.toLicenceWarn(messageSource, KEY_ERRORS_GENERIC + ex.getCode(), sharedkey);
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), sharedkey, message, ex);
            return response;
        }
        catch (ServiceException ex)
        {
            String message = response.toParamsWarn(messageSource, KEY_ERRORS_PROFILER_GENERIC + ex.getCode());
            Log.getInstance().warn(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), message, ex);
            return response;
        }
        catch (Exception ex)
        {
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "[modificarpermisosperfil]", ex);
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        Log.getInstance().info(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se retorna respuesta efectiva del WS [modificarpermisosperfil].");
        return response.toOk();
    }*/
}
