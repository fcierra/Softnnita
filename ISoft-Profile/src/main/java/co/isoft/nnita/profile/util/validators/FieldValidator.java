package co.isoft.nnita.profile.util.validators;

import co.isoft.nnita.profile.api.util.EstatusGenericos;
import co.isoft.nnita.profile.util.ISoftProfilerBaseBean;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Validator del login de usaurio.
 *
 * @author Yaher Carrillo
 * @Date 18/07/2018
 */
@FacesValidator("co.isoft.nnita.profile.util.validators.FieldValidator")
public class FieldValidator extends ISoftProfilerBaseBean implements Validator
{
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException
    {
        String field = o.toString();
        UIInput uiInputConfirmPassword = (UIInput) uiComponent.getAttributes().get("password");
        String password = uiInputConfirmPassword.getSubmittedValue().toString();
        if ((field == null || field.equals("")) || (password == null || password.equals("")))
        {
            String message = ISoftProfilerBaseBean.findMessage(EstatusGenericos.PROFILER_GENERIC_ERROR_FIELD_REQUIRED.getRefbundle());
            addErrorMessageValidator(message);
        }
    }
}
