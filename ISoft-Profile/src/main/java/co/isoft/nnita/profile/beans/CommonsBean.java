package co.isoft.nnita.profile.beans;

import co.isoft.nnita.logger.util.Log;
import co.isoft.nnita.logger.util.ModulesIsoft;
import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.modelsweb.DatosSesionUsuario;
import co.isoft.nnita.profile.api.services.UsuariosService;
import co.isoft.nnita.profile.api.util.EnumErrorConfig;
import co.isoft.nnita.profile.configuration.dom.ISesionActive;
import co.isoft.nnita.profile.configuration.navigation.EnumNavigationConfig;
import co.isoft.nnita.profile.util.ISoftProfilerBaseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Map;

/**
 * Bean FRONT-END para el manejo de la informacion
 * en el login de sistema.
 *
 * @author Yaher Carrillo
 * @Date 09/07/2018
 */
@Component
@ManagedBean(name = "commonsBean")
@RequestScoped
public class CommonsBean extends ISoftProfilerBaseBean implements Serializable
{

}
