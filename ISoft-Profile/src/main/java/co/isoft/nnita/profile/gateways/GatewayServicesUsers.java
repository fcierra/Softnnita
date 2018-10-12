package co.isoft.nnita.profile.gateways;

import co.isoft.nnita.logger.util.Log;
import co.isoft.nnita.logger.util.ModulesIsoft;
import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.gateways.ParamsException;
import co.isoft.nnita.profile.api.gateways.models.CommonsResponse;
import co.isoft.nnita.profile.api.gateways.models.request.profile.RequestAddProfileUser;
import co.isoft.nnita.profile.api.gateways.models.request.users.RequestNewUserISoftProfile;
import co.isoft.nnita.profile.api.gateways.models.request.users.RequestNewUsersMassiveISoftProfile;
import co.isoft.nnita.profile.api.gateways.util.GatewayBaseBean;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.services.UsuariosService;
import co.isoft.nnita.profile.api.util.EstatusGenericos;
import co.isoft.nnita.profile.util.ISoftProfilerBaseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

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
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
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
    @RequestMapping(value = "/crearusuarioisoft", method = RequestMethod.POST)
    public CommonsResponse crearusuarioisoft(@RequestParam String sharedkey, @RequestBody RequestNewUserISoftProfile request)
    {
        CommonsResponse response = new CommonsResponse();
        try
        {
            GatewayBaseBean.validarParametrosGenericos(request.getLoginname(), request.getNombres(), request.getClave());
            Usuarios user = GatewayBaseBean.clonUsersRequest(request);
            //Crea un usuario sin perfil, no puedra ingresar
            userServices.createUserIsoftProfile(user);
        }
        catch (ParamsException ex)
        {
            String code_error = "login.error." + ex.getCode();
            String message = messageSource.getMessage( code_error, new Object[]{"App"},Locale.getDefault());
            GatewayBaseBean.matchToResponses(response, ex.getCode(), message, EstatusGenericos.WARN.getCode());
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


    @RequestMapping(value = "/crearusuariosisoftmasivo", method = RequestMethod.POST)
    public CommonsResponse crearusuarioisoftmasivo(@RequestParam String sharedkey, @RequestBody RequestNewUsersMassiveISoftProfile request)
    {
        CommonsResponse response = new CommonsResponse();
        try
        {
            GatewayBaseBean.validarParametrosGenericos(request.getPassword());
            //Crea un usuario sin perfil, no puedra ingresar
            userServices.createUsersMassiveIsoftProfile(request.getPassword(),request.getUsuariosYPerfil());
        }
        catch (ParamsException ex)
        {
            String code_error = "login.error." + ex.getCode();
            String message = messageSource.getMessage( code_error, new Object[]{"App"},Locale.getDefault());
            GatewayBaseBean.matchToResponses(response, ex.getCode(), message, EstatusGenericos.WARN.getCode());
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

    @RequestMapping(value = "/asociarperfilusuario", method = RequestMethod.POST)
    public CommonsResponse asociarperfilusuario(@RequestParam String sharedkey, @RequestBody RequestAddProfileUser request)
    {
        CommonsResponse response = new CommonsResponse();
        try
        {
            GatewayBaseBean.validarParametrosGenericos(request.getLoginname());
            GatewayBaseBean.validarParametrosGenericos(request.getCodesProfiles());
            //Crea un usuario sin perfil, no puedra ingresar
            userServices.addProfilesUser(request.getLoginname(),request.getCodesProfiles());
        }
        catch (ParamsException ex)
        {
            String code_error = "login.error." + ex.getCode();
            String message = messageSource.getMessage( code_error, new Object[]{"App"},Locale.getDefault());
            GatewayBaseBean.matchToResponses(response, ex.getCode(), message, EstatusGenericos.WARN.getCode());
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
