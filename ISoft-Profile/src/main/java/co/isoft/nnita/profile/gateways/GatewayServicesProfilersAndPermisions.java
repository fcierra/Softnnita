package co.isoft.nnita.profile.gateways;

import co.isoft.nnita.logger.util.Log;
import co.isoft.nnita.logger.util.ModulesIsoft;
import co.isoft.nnita.profile.api.exceptions.LicenseException;
import co.isoft.nnita.profile.api.exceptions.ParamsException;
import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.gateways.models.CommonsResponse;
import co.isoft.nnita.profile.api.gateways.models.request.profile.RequestCreateProfile;
import co.isoft.nnita.profile.api.gateways.util.GatewayBaseBean;
import co.isoft.nnita.profile.api.models.Perfiles;
import co.isoft.nnita.profile.api.modelsweb.PermisosDTO;
import co.isoft.nnita.profile.api.services.PerfilesYPermisosService;
import co.isoft.nnita.profile.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static co.isoft.nnita.profile.api.util.ConstantesBaseBean.*;

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
    @RequestMapping(value = "/consultarperfiles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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
    }

    /**
     * Crea un perfil en el sistema
     *
     * @param sharedkey Llave de licenciamiento
     * @return Response comun con los datos de servicio
     */
    @RequestMapping(value = "/crearperfil", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse createprofile(@RequestParam String sharedkey, @RequestBody RequestCreateProfile request, HttpServletRequest requestTransaction)
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
    }

    /**
     * Crea un perfil en el sistema
     *
     * @param sharedkey Llave de licenciamiento
     * @return Response comun con los datos de servicio
     */
    @RequestMapping(value = "/modificarperrfil", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse modificarperrfil(@RequestParam String sharedkey, @RequestBody RequestCreateProfile request, HttpServletRequest requestTransaction)
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
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "[crearperfil]", ex);
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        Log.getInstance().info(ModulesIsoft.ISOFT_PROFILE.getCodigo(), mapConfiguration.get(MAP_USER_TRANSACTION), "Se retorna respuesta efectiva del WS [crearperfil].");
        return response.toOk();
    }

    @RequestMapping(value = "/consultarpermisosperfil", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse consultarpermisosperfil(@RequestParam String sharedkey, @RequestParam String nombreperfil, HttpServletRequest requestTransaction)
    {
        CommonsResponse response = new CommonsResponse();
        Map<String, String> mapConfiguration = null;
        try
        {
            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), sharedkey, "Se valida la licencia si puede consumir los procesos.");
            mapConfiguration = GatewayBaseBean.validateLicenceToWS(sharedkey, webUtils.getClientIp(requestTransaction));
            GatewayBaseBean.validarParametrosGenericos(nombreperfil);
            List<PermisosDTO> listaPermisos = perfilesYPermisosService.findPermissionProfile(mapConfiguration, nombreperfil);
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
    }
}
