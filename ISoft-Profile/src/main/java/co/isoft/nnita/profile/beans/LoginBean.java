package co.isoft.nnita.profile.beans;

import co.isoft.nnita.profile.models.Usuarios;
import co.isoft.nnita.profile.service.ServiceException;
import co.isoft.nnita.profile.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;
@Component
@ManagedBean(name="loginBean")
@RequestScoped
public class LoginBean implements Serializable
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
     * Servicio de Consulta de usuarios
     */
    @Autowired
    UsuariosService userServices;

    @Autowired
    MessageSource messageSource;

    /**
     * Metodo de procesamiento de la informacion del bean
     * @return
     */
    public String process(){
        Usuarios usuarios;
        try
        {
            usuarios = userServices.validarUsuario(username);
        }
        catch (ServiceException e)
        {
            e.printStackTrace();
        }
        return "welcome";
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
}
