package co.isoft.nnita.profile.util.validators;

import co.isoft.nnita.profile.api.util.EnumErrorConfig;
import co.isoft.nnita.profile.util.ISoftProfilerBaseBean;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validator que confirma nuevas claves de ingreso.
 *
 * @author Yaher Carrillo
 * @Date 21/07/2018
 */
@FacesValidator("co.isoft.nnita.profile.util.validators.ConfirmPassValidator")
public class ConfirmPassValidator extends ISoftProfilerBaseBean implements Validator
{

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException
    {
        String newpass = o.toString();
        UIInput uiInputConfirmPassword = (UIInput) uiComponent.getAttributes().get("passconfirm");
        String confirmpass = uiInputConfirmPassword.getSubmittedValue().toString();

        if ((newpass != null && !newpass.trim().equals("")) && (confirmpass != null && !confirmpass.trim().equals("")))
        {
            if (!newpass.equals(confirmpass)){
                String message = ISoftProfilerBaseBean.findMessageError(EnumErrorConfig.PROFILER_USER_CONFIRM_PASS.getCode());
                addErrorMessageValidator(message);
            }
        }

    }
}
