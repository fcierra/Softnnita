package co.isoft.nnita.profile.beans;


import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.modelsweb.DatosSesionUsuario;
import co.isoft.nnita.profile.api.services.UsuariosService;
import co.isoft.nnita.profile.api.util.EstatusGenericos;
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
@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean extends ISoftProfilerBaseBean implements Serializable
{
    /**
     * Nombre de acceso de un determinado usuario
     */
    private String username;
    /**
     * Clave de acceso de usuario
     */
    private String password;
    /**
     * Servicio de Consulta de usuarios.
     */
    @Autowired
    @Qualifier("proxyUsuariosService")
    private UsuariosService userServices;
    /**
     * Servicio de recursos en mensajes.
     */
    @Autowired
    private MessageSource messageSource;
    /**
     * Servicio de datos de sesion de usuario
     */
    @Autowired
    private ISesionActive iSesionActive;

    /**
     * Evento iniacl invocado por la vista profile_misdatos.xhtml para iniciar el bean
     *
     * @param event Evento
     */
    public void preRenderViewLogin(ComponentSystemEvent event)
    {
        loadSession();
    }

    /**
     * Evento iniacl invocado por la vista profile_misdatos.xhtml para iniciar el bean
     *
     * @param event Evento
     */
    public void preRenderViewIndex(ComponentSystemEvent event)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String errorAuth = paramMap.get("error");
        if (errorAuth != null)
        {
            String message = ISoftProfilerBaseBean.findMessage(EstatusGenericos.PROFILER_USER_OR_PASS_DOES_NOT_EXIST.getRefbundle());
            addErrorMessage(message);
        }
    }

    /**
     * Carga la sesion del ususario
     * en el bean de sesion para ser usado
     * a demanda.
     */
    public void loadSession()
    {
        username = this.loginUserContext();
    }

    /**
     * Metodo de procesamiento de la informacion del bean
     * que consulta la informacion de usuarios y permite su acceso
     * o no a las bondades del sistema
     *
     * @return recurso de navegacion a donde redireccionara la pagina.
     */
    public synchronized String process()
    {
        Usuarios usuario;
        try
        {
            HttpSession session = ISoftProfilerBaseBean.getHttpSession();
            usuario = userServices.findUserToAuthenticated(username.toUpperCase());
            if (usuario != null)
            {
                DatosSesionUsuario datosSesionUsuario = new DatosSesionUsuario();
                datosSesionUsuario.setUsuario(usuario);
                datosSesionUsuario.setIdSesionWeb(session.getId());
                iSesionActive.setDatosSesion(datosSesionUsuario);
                session.setAttribute(CONSTANT_USER_SESION, iSesionActive);
                return EnumNavigationConfig.WELCOME_PAGE.getName()+"?faces-redirect=true";
            }

        }
        catch (ServiceException e)
        {
            String code_error = e.getCode();
            String message = ISoftProfilerBaseBean.findMessageError(code_error);
            addErrorMessage(message);

        }
        catch (Exception e)
        {

        }
        return EnumNavigationConfig.DONT_ACCESS.getName();
    }

    /**
     * Metodo que extrae la informacion de usuario autenticado del contexto
     *
     * @return login name
     */
    public String loginUserContext()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User ctxUser = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        return ctxUser.getUsername();
    }

    /**
     * Obtiene el recurso de nombre de usuario del bean de manejo
     *
     * @return Cadena con el nombre de usuario.
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Asigna un valor al nombre de usuario en el bean
     * de manejo.
     *
     * @param username
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * Obtiene la clave de acceso del usuario
     *
     * @return clave de acceso
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Asigna un valor a la clave de acceso
     *
     * @param password valor a asignar
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
}
