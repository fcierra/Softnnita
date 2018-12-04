package co.isoft.nnita.profile.beans;


import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.Menus;
import co.isoft.nnita.profile.api.models.Menus_Item;
import co.isoft.nnita.profile.api.services.PerfilesYPermisosService;
import co.isoft.nnita.profile.configuration.dom.ISesionActive;
import co.isoft.nnita.profile.util.ISoftProfilerBaseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import java.io.Serializable;
import java.util.List;

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
     * Navegacion de usuario
     */
    private List<Menus> menus;
    /**
     * Servicio de informacion de sesion del usuario
     */
    @Autowired
    private ISesionActive iSesionActive;


    @Autowired
    private PerfilesYPermisosService perfilesYPermisosService;

    /**
     * Evento iniacl invocado por la vista profile_misdatos.xhtml para iniciar el bean
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
        loadNavigation();
        this.setNamesUser(iSesionActive.getDatosSesion().getUsuario().getNombres());
        this.setLastNamesUser(iSesionActive.getDatosSesion().getUsuario().getApellidos());
        this.setEmailUser(iSesionActive.getDatosSesion().getUsuario().getEmail());
        this.setSexUser(ISoftProfilerBaseBean.findMessageTextSex(iSesionActive.getDatosSesion().getUsuario().getSexo()));
        this.setLastConnection(iSesionActive.getDatosSesion().getUsuario().getFecha_ultima_visita().toString());
        this.setLoginUser(iSesionActive.getDatosSesion().getUsuario().getLogin());
    }

    /**
     * Carga la navegacion del cliente autenticado, segun los permisos
     * de sus perfil.
     */
    public void loadNavigation(){
        try
        {
            menus = perfilesYPermisosService.findMenusItemNavigation(iSesionActive.getDatosSesion().getUsuario().getPerfilDefault());
        }catch (ServiceException e){
            String code_error = e.getCode();
            String message = ISoftProfilerBaseBean.findMessageError(code_error);
            addErrorMessage(message);

        }
    }

    public String navigationSelected(Menus_Item item){
        return item.getMenu_link()+"?faces-redirect=true";
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

    /**
     * Obtiene los menus de navegacion del perfil
     * actual.
     * @return Listado de menus
     */
    public List<Menus> getMenus()
    {
        return menus;
    }

    /**
     * Asigna un valor al listado de menus
     * @param menus valor a asignar
     */
    public void setMenus(List<Menus> menus)
    {
        this.menus = menus;
    }
}
