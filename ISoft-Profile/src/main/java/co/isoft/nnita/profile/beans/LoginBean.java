package co.isoft.nnita.profile.beans;

import co.isoft.nnita.logger.util.Log;
import co.isoft.nnita.logger.util.ModulesIsoft;
import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.modelsweb.DatosSesionUsuario;
import co.isoft.nnita.profile.api.services.UsuariosService;
import co.isoft.nnita.profile.configuration.navigation.EnumNavigationConfig;
import co.isoft.nnita.profile.util.ISoftProfilerBaseBean;
import org.omg.CORBA.CODESET_INCOMPATIBLE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

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
     * Clave de acceso de un usuario determinado
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
     * Metodo de procesamiento de la informacion del bean
     * que consulta la informacion de usuarios y permite su acceso
     * o no a las bondades del sistema
     *
     * @return recurso de navegacion a donde redireccionara la pagina.
     */
    public synchronized String process()
    {
        DatosSesionUsuario datosSesionUsuario;
        try
        {
            HttpSession session = ISoftProfilerBaseBean.getHttpSession();
            String passwordEncoder = ISoftProfilerBaseBean.encryptPass(password);
            datosSesionUsuario = userServices.validarUsuario(username, passwordEncoder);
            if (datosSesionUsuario != null)
            {
                datosSesionUsuario.setIdSesionWeb(session.getId());
                session.setAttribute(CONSTANT_USER_SESION, datosSesionUsuario);
                return EnumNavigationConfig.WELCOME_PAGE.getName();
            }

        }
        catch (ServiceException e)
        {
            String code_error = e.getCode();
            String message = ISoftProfilerBaseBean.findMessageError(code_error);
            addErrorMessage(message);
            Log.getInstance().warn(ModulesIsoft.ISOFT_PROFILE.getCodigo(), username, "Error consultando usuario", e);
        }
        catch (Exception e)
        {
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), ISoftProfilerBaseBean.USER_GUEST_LOG, "Error GENERICO consultando usuario", e);
        }
        return EnumNavigationConfig.DONT_ACCESS.getName();
    }

    /**
     * Obtiene el recurso de clave de acceso del bean
     *
     * @return Cadena con la clave de acceso
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Asigna un valor a la clave de acceso de usuarios
     * en el bean de manejo
     *
     * @param password clave a asignar
     */
    public void setPassword(String password)
    {
        this.password = password;
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
}
