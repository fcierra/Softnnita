package co.isoft.nnita.profile.util.validators;

import co.isoft.nnita.logger.util.Log;
import co.isoft.nnita.logger.util.ModulesIsoft;
import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.services.UsuariosService;
import co.isoft.nnita.profile.api.util.EnumErrorConfig;
import co.isoft.nnita.profile.api.util.EnumServicesIsoftConfig;
import co.isoft.nnita.profile.impl.configuration.hibernate.ContextProvider;
import co.isoft.nnita.profile.util.ISoftProfilerBaseBean;
import co.isoft.nnita.profile.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

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
     * Contructor por defecto
     */
    public CurrentPassValidator(){
        init();
    }

    /**
     * Se inicializan los valores y servicios a usar en el validator.
     */
    public void init(){
        userServices = SpringUtil.getSpringBean(EnumServicesIsoftConfig.SERVICES_USERS_PROXY.getNameService());
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException
    {
        String loginUsuario = o.toString();
        UIInput uiInputConfirmPassword = (UIInput) uiComponent.getAttributes().get("loginconnect");
        String claveActual = uiInputConfirmPassword.getSubmittedValue().toString();

        Usuarios usuario = new Usuarios();
        usuario.setLogin(loginUsuario);
        try
        {
            claveActual = ISoftProfilerBaseBean.encryptPass(claveActual);
            boolean logic = userServices.validateCurrentPassUser(usuario,claveActual);
            if (!logic){
                String message = ISoftProfilerBaseBean.findMessageError(EnumErrorConfig.PROFILER_USER_CURRENT_PASS.getCode());
                addErrorMessageValidator(message);
            }
        }
        catch (ServiceException e)
        {
            String code_error = e.getCode();
            String message = ISoftProfilerBaseBean.findMessageError(code_error);
            addErrorMessage(message);
            Log.getInstance().warn(ModulesIsoft.ISOFT_PROFILE.getCodigo(), claveActual, "Error VALIDATOR validando la clave actual de usuario", e);
        }
    }
}
