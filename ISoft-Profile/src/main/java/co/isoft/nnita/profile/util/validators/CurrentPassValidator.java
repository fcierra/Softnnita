package co.isoft.nnita.profile.util.validators;


import co.isoft.nnita.profile.api.services.UsuariosService;
import co.isoft.nnita.profile.api.util.EstatusGenericos;
import co.isoft.nnita.profile.api.util.EnumServicesIsoftConfig;
import co.isoft.nnita.profile.util.ISoftProfilerBaseBean;
import co.isoft.nnita.profile.util.SpringUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validator de la clave actual de sistema.
 *
 * @author Yaher Carrillo
 * @Date 20/07/2018
 */
@FacesValidator("co.isoft.nnita.profile.util.validators.CurrentPassValidator")
public class CurrentPassValidator extends ISoftProfilerBaseBean implements Validator
{
    /**
     * Servicio de Consulta de usuarios.
     */
    private UsuariosService userServices;
    /**
     * Servicio de autenticacion
     */
    private AuthenticationManager authenticationManager;

    /**
     * Contructor por defecto
     */
    public CurrentPassValidator()
    {
        init();
    }

    /**
     * Se inicializan los valores y servicios a usar en el validator.
     */
    public void init()
    {
        userServices = SpringUtil.getSpringBean(EnumServicesIsoftConfig.SERVICES_USERS_PROXY.getNameService());
        authenticationManager = SpringUtil.getSpringBean(EnumServicesIsoftConfig.SERVICES_AUTH_SPRING.getNameService());
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException
    {
        String loginUsuario = o.toString();
        UIInput uiInputConfirmPassword = (UIInput) uiComponent.getAttributes().get("loginconnect");
        String claveActual = uiInputConfirmPassword.getSubmittedValue().toString();
        try
        {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario, claveActual));
        }
        catch (AuthenticationException e)
        {
            String message = ISoftProfilerBaseBean.findMessageError(EstatusGenericos.PROFILER_USER_CURRENT_PASS.getCode());
            addErrorMessageValidator(message);

        }
    }
}
