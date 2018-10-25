package co.isoft.nnita.profile.gateways;

import co.isoft.nnita.profile.api.exceptions.LicenseException;
import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.exceptions.ParamsException;
import co.isoft.nnita.profile.api.gateways.models.CommonsResponse;
import co.isoft.nnita.profile.api.gateways.models.request.profile.RequestAddProfileUser;
import co.isoft.nnita.profile.api.gateways.models.request.users.RequestNewUserISoftProfile;
import co.isoft.nnita.profile.api.gateways.models.request.users.RequestNewUsersMassiveISoftProfile;
import co.isoft.nnita.profile.api.gateways.util.GatewayBaseBean;
import co.isoft.nnita.profile.api.models.Perfiles;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.modelsweb.PerfilesDeUsuario;
import co.isoft.nnita.profile.api.modelsweb.UsuarioPerfilMassive;
import co.isoft.nnita.profile.api.services.UsuariosService;
import co.isoft.nnita.profile.api.util.EstatusGenericos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Gateway de servicio de usuarios ISoftProfiler
 *
 * @author Yaher Carrillo
 * @Since 09/09/2018
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
    @Autowired
    MessageSource messageSource;

    //@RequestMapping(value = "/GatewayServicesUsersISoftProfile/**", method = RequestMethod.OPTIONS)
    public void corsHeaders(HttpServletResponse response)
    {
        response.addHeader("Access-Control-Allow-Origin", "*");
        //response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST");
        response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
        response.addHeader("Access-Control-Max-Age", "3600");
    }

    /**
     * Crea un usuario en el sistema ISoftProfile
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
     * Crea un usuario en el sistema ISoftProfile
     *
     * @param request Datos de entrada de elementos de usuario
     * @return Response comun con los datos de servicio
     */
    @RequestMapping(value = "/crearusuarioisoft", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse crearusuarioisoft(@RequestParam String sharedkey, @RequestBody RequestNewUserISoftProfile request)
    {
        CommonsResponse response = new CommonsResponse();
        try
        {
            //Se valida la licencia si puede consumir los procesos
            Map<String,String> mapConfiguration = GatewayBaseBean.validateLicence(sharedkey);

            GatewayBaseBean.validarParametrosGenericos(request.getLoginname(), request.getNombres(), request.getClave());
            Usuarios user = GatewayBaseBean.clonUsersRequest(request);
            //Crea un usuario sin perfil, no puedra ingresar
            userServices.createUserIsoftProfile(mapConfiguration,user);
        }
        catch (ParamsException ex)
        {
            String code_error = "login.error." + ex.getCode();
            String message = messageSource.getMessage( code_error, new Object[]{"App"},Locale.getDefault());
            GatewayBaseBean.matchToResponses(response, ex.getCode(), message, EstatusGenericos.WARN.getCode());
            return response;
        }
        catch (LicenseException ex)
        {
            String code_error = "login.error." + ex.getCode();
            String message = messageSource.getMessage( code_error, new Object[]{"App"},Locale.getDefault());
            GatewayBaseBean.matchToResponses(response, ex.getCode(), message, EstatusGenericos.WARN.getCode());
            response.setResponse(sharedkey);
            return response;
        }
        catch (ServiceException ex)
        {
            String code_error = "login.error." + ex.getCode();
            String message = messageSource.getMessage( code_error, new Object[]{"App"},Locale.getDefault());
            GatewayBaseBean.matchToResponses(response, ex.getCode(),message, EstatusGenericos.WARN.getCode());
            return response;
        }
        catch (Exception ex)
        {
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        return response.toOk();
    }


    @RequestMapping(value = "/crearusuariosisoftmasivo", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse crearusuarioisoftmasivo(@RequestParam String sharedkey, @RequestBody RequestNewUsersMassiveISoftProfile request)
    {
        CommonsResponse response = new CommonsResponse();
        try
        {
            //Se valida la licencia si puede consumir los procesos.
            Map<String,String> mapConfiguration = GatewayBaseBean.validateLicence(sharedkey);

            //Se validan los parametros de entrada.
            GatewayBaseBean.validarParametrosGenericos(request.getPassword());

            //Crea un usuario sin perfil, no puedra ingresar.
            List<UsuarioPerfilMassive> list = userServices.createUsersMassiveIsoftProfile(mapConfiguration,request.getPassword(),request.getUsuariosYPerfil());
            response.setResponse(list);
        }
        catch (ParamsException ex)
        {
            String code_error = "login.error." + ex.getCode();
            String message = messageSource.getMessage( code_error, new Object[]{"App"},Locale.getDefault());
            GatewayBaseBean.matchToResponses(response, ex.getCode(), message, EstatusGenericos.WARN.getCode());
            return response;
        }
        catch (LicenseException ex)
        {
            String code_error = "login.error." + ex.getCode();
            String message = messageSource.getMessage( code_error, new Object[]{"App"},Locale.getDefault());
            GatewayBaseBean.matchToResponses(response, ex.getCode(), message, EstatusGenericos.WARN.getCode());
            response.setResponse(sharedkey);
            return response;
        }
        catch (ServiceException ex)
        {
            String code_error = "login.error." + ex.getCode();
            String message = messageSource.getMessage( code_error, new Object[]{"App"},Locale.getDefault());
            GatewayBaseBean.matchToResponses(response, ex.getCode(),message, EstatusGenericos.WARN.getCode());
            return response;
        }
        catch (Exception ex)
        {
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        response.toOk();
        return response;
    }

    @RequestMapping(value = "/asociarperfilusuario", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse asociarperfilusuario(@RequestParam String sharedkey, @RequestBody RequestAddProfileUser request)
    {
        CommonsResponse response = new CommonsResponse();
        try
        {
            //Se valida la licencia si puede consumir los procesos.
            Map<String,String> mapConfiguration = GatewayBaseBean.validateLicence(sharedkey);

            //Se validan los parametros de entrada
            GatewayBaseBean.validarParametrosGenericos(request.getLoginname());
            GatewayBaseBean.validarParametrosGenericos(request.getCodesProfiles());

            //Crea un usuario sin perfil, no puedra ingresar
            List<UsuarioPerfilMassive> list = userServices.addProfilesUser(mapConfiguration,request.getLoginname(),request.getCodesProfiles());
            response.setResponse(list);
        }
        catch (ParamsException ex)
        {
            String code_error = "login.error." + ex.getCode();
            String message = messageSource.getMessage( code_error, new Object[]{"App"},Locale.getDefault());
            GatewayBaseBean.matchToResponses(response, ex.getCode(), message, EstatusGenericos.WARN.getCode());
            return response;
        }
        catch (LicenseException ex)
        {
            String code_error = "login.error." + ex.getCode();
            String message = messageSource.getMessage( code_error, new Object[]{"App"},Locale.getDefault());
            GatewayBaseBean.matchToResponses(response, ex.getCode(), message, EstatusGenericos.WARN.getCode());
            response.setResponse(sharedkey);
            return response;
        }
        catch (ServiceException ex)
        {
            String code_error = "login.error." + ex.getCode();
            String message = messageSource.getMessage( code_error, new Object[]{"App"},Locale.getDefault());
            GatewayBaseBean.matchToResponses(response, ex.getCode(),message, EstatusGenericos.WARN.getCode());
            return response;
        }
        catch (Exception ex)
        {
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        return response.toOk();
    }

    @RequestMapping(value = "/desasociarperfilusuario", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse desasociarperfilusuario(@RequestParam String sharedkey, @RequestBody RequestAddProfileUser request)
    {
        CommonsResponse response = new CommonsResponse();
        try
        {
            //Se valida la licencia si puede consumir los procesos.
            Map<String,String> mapConfiguration = GatewayBaseBean.validateLicence(sharedkey);

            //Se validan los parametros de entrada
            GatewayBaseBean.validarParametrosGenericos(request.getLoginname());
            GatewayBaseBean.validarParametrosGenericos(request.getCodesProfiles());

            //Crea un usuario sin perfil, no puedra ingresar
            List<UsuarioPerfilMassive> list = userServices.unAddProfilesUser(mapConfiguration,request.getLoginname(),request.getCodesProfiles());
            response.setResponse(list);
        }
        catch (ParamsException ex)
        {
            String code_error = "login.error." + ex.getCode();
            String message = messageSource.getMessage( code_error, new Object[]{"App"},Locale.getDefault());
            GatewayBaseBean.matchToResponses(response, ex.getCode(), message, EstatusGenericos.WARN.getCode());
            return response;
        }
        catch (LicenseException ex)
        {
            String code_error = "login.error." + ex.getCode();
            String message = messageSource.getMessage( code_error, new Object[]{"App"},Locale.getDefault());
            GatewayBaseBean.matchToResponses(response, ex.getCode(), message, EstatusGenericos.WARN.getCode());
            response.setResponse(sharedkey);
            return response;
        }
        catch (ServiceException ex)
        {
            String code_error = "login.error." + ex.getCode();
            String message = messageSource.getMessage( code_error, new Object[]{"App"},Locale.getDefault());
            GatewayBaseBean.matchToResponses(response, ex.getCode(),message, EstatusGenericos.WARN.getCode());
            return response;
        }
        catch (Exception ex)
        {
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        return response.toOk();
    }

    /**
     * Crea un usuario en el sistema ISoftProfile
     *
     * @return Response comun con los datos de servicio
     */
    @RequestMapping(value = "/consultarperfiles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse findprofiles(@RequestParam String sharedkey)
    {
        CommonsResponse response = new CommonsResponse();
        try
        {
            //Se valida la licencia si puede consumir los procesos.
            Map<String,String> mapConfiguration = GatewayBaseBean.validateLicence(sharedkey);

            //Crea un usuario sin perfil, no puedra ingresar
            List<Perfiles> list = userServices.findProfilesSystem(mapConfiguration);
            if (list==null || list.isEmpty())
                return response.toEmpty();
            response.setResponse(GatewayBaseBean.clonProfileResponse(list));
        }
        catch (LicenseException ex)
        {
            String code_error = "login.error." + ex.getCode();
            String message = messageSource.getMessage( code_error, new Object[]{"App"},Locale.getDefault());
            GatewayBaseBean.matchToResponses(response, ex.getCode(), message, EstatusGenericos.WARN.getCode());
            response.setResponse(sharedkey);
            return response;
        }
        catch (ServiceException ex)
        {
            String code_error = "login.error." + ex.getCode();
            String message = messageSource.getMessage( code_error, new Object[]{"App"},Locale.getDefault());
            GatewayBaseBean.matchToResponses(response, ex.getCode(),message, EstatusGenericos.WARN.getCode());
            return response;
        }
        catch (Exception ex)
        {
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        return response.toOk();
    }

    /**
     * Crea un usuario en el sistema ISoftProfile
     *
     * @return Response comun con los datos de servicio
     */
    @RequestMapping(value = "/consultarperfilesusuario", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonsResponse findprofilesusers(@RequestParam String loginuser,@RequestParam String sharedkey)
    {
        CommonsResponse response = new CommonsResponse();
        try
        {
            //Se valida la licencia si puede consumir los procesos.
            Map<String,String> mapConfiguration = GatewayBaseBean.validateLicence(sharedkey);

            //Se consulta los perfiles de usuario
            List<PerfilesDeUsuario> list = userServices.findProfilesUsers(mapConfiguration,loginuser);
            if (list==null || list.isEmpty())
                return response.toEmpty();
            response.setResponse(list);
        }
        catch (LicenseException ex)
        {
            String code_error = "login.error." + ex.getCode();
            String message = messageSource.getMessage( code_error, new Object[]{"App"},Locale.getDefault());
            GatewayBaseBean.matchToResponses(response, ex.getCode(), message, EstatusGenericos.WARN.getCode());
            response.setResponse(sharedkey);
            return response;
        }
        catch (ServiceException ex)
        {
            String code_error = "login.error." + ex.getCode();
            String message = messageSource.getMessage( code_error, new Object[]{"App"},Locale.getDefault());
            GatewayBaseBean.matchToResponses(response, ex.getCode(),message, EstatusGenericos.WARN.getCode());
            return response;
        }
        catch (Exception ex)
        {
            GatewayBaseBean.matchToResponses(response);
            return response;
        }
        return response.toOk();
    }
}
