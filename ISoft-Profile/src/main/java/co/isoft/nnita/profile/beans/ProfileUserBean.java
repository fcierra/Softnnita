package co.isoft.nnita.profile.beans;

import co.isoft.nnita.logger.util.Log;
import co.isoft.nnita.logger.util.ModulesIsoft;
import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.modelsweb.DatosSesionUsuario;
import co.isoft.nnita.profile.api.services.UsuariosService;
import co.isoft.nnita.profile.configuration.dom.ISesionActive;
import co.isoft.nnita.profile.configuration.navigation.EnumNavigationConfig;
import co.isoft.nnita.profile.util.ISoftProfilerBaseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * Bean FRONT-END para el manejo de la informacion
 * del perfil de usuario conectado.
 *
 * @author Yaher Carrillo
 * @Date 19/07/2018
 */
@Component
@ManagedBean(name = "profileUserBean")
@SessionScoped
public class ProfileUserBean extends ISoftProfilerBaseBean implements Serializable
{

    /**
     * Nombre de usuario
     */
    private String namesUser;
    /**
     * Apellidos de usuario
     */
    private String lastNamesUser;
    /**
     * Correo de usuario
     */
    private String emailUser;
    /**
     * Sexo del usuario
     */
    private String sexUser;
    /**
     * Ultima visita del usuario
     */
    private String lastConnection;
    /**
     * Login de usuario de sistema
     */
    private String loginUser;
    /**
     * Servicio de informacion de sesion del usuario
     */
    @Autowired
    private ISesionActive iSesionActive;

    /**
     * Servicio de Consulta de usuarios.
     */
    @Autowired
    @Qualifier("proxyUsuariosService")
    private UsuariosService userServices;

    /**
     * Evento iniacl invocado por la vista profileUser.xhtml para iniciar el bean
     *
     * @param event Evento
     */
    public void preRenderView(ComponentSystemEvent event)
    {
        init();
    }

    /**
     * Inicializa los valor de datos
     * personlaes de usuarios
     */
    public void init()
    {
        this.setNamesUser(iSesionActive.getDatosSesion().getUsuario().getNombres());
        this.setLastNamesUser(iSesionActive.getDatosSesion().getUsuario().getApellidos());
        this.setEmailUser(iSesionActive.getDatosSesion().getUsuario().getEmail());
        this.setSexUser(ISoftProfilerBaseBean.findMessageTextSex(iSesionActive.getDatosSesion().getUsuario().getSexo()));
        this.setLastConnection(iSesionActive.getDatosSesion().getUsuario().getFecha_ultima_visita().toString());
        this.setLoginUser(iSesionActive.getDatosSesion().getUsuario().getLogin());
    }

    /**
     * Metodo que cierra la sesion de usuario
     * @return navegacion de usario al login.
     */
    public String closeSession(){
            HttpSession session = ISoftProfilerBaseBean.getHttpSession();
        session.invalidate();
        return EnumNavigationConfig.LOGIN_PAGE.getName();
    }

    /**
     * Obtiene el nombre de usuario del sistema a mostrar
     *
     * @return nombre de usuario
     */
    public String getNamesUser()
    {
        return namesUser;
    }

    /**
     * Asigna el nombre de usuario del sistema que esta conectado
     *
     * @param namesUser valor a asignar
     */
    public void setNamesUser(String namesUser)
    {
        this.namesUser = namesUser;
    }

    /**
     * Obtiene los apellidos del usuario conectado
     *
     * @return apellidos a mostrar
     */
    public String getLastNamesUser()
    {
        return lastNamesUser;
    }

    /**
     * Asigna un valor a los apellidos de usuario del sistema
     *
     * @param lastNamesUser valor a asignar
     */
    public void setLastNamesUser(String lastNamesUser)
    {
        this.lastNamesUser = lastNamesUser;
    }

    /**
     * Obtiene el email del usuario
     *
     * @return email del usuario
     */
    public String getEmailUser()
    {
        return emailUser;
    }

    /**
     * Asigna un valor al email del usuario
     *
     * @param emailUser valor a asignar
     */
    public void setEmailUser(String emailUser)
    {
        this.emailUser = emailUser;
    }

    /**
     * Obtiene el sexo del usuario
     *
     * @return
     */
    public String getSexUser()
    {
        return sexUser;
    }

    /**
     * Asigna un valor al sexo del usuario
     *
     * @param sexUser valor a asignar
     */
    public void setSexUser(String sexUser)
    {
        this.sexUser = sexUser;
    }

    /**
     * Obtiene el valor de la ultima conexion de usuario
     *
     * @return ultima conexion del usuario
     */
    public String getLastConnection()
    {
        return lastConnection;
    }

    /**
     * Asigna un valor a la ultima conexion del usuario.
     *
     * @param lastConnection valor a asignar
     */
    public void setLastConnection(String lastConnection)
    {
        this.lastConnection = lastConnection;
    }

    /**
     * Obtiene el login de usuario a mostrar
     *
     * @return login de usuario
     */
    public String getLoginUser()
    {
        return loginUser;
    }

    /**
     * Aasigna un valor al login de usuario a mostrar
     *
     * @param loginUser valor a asignar
     */
    public void setLoginUser(String loginUser)
    {
        this.loginUser = loginUser;
    }
}
