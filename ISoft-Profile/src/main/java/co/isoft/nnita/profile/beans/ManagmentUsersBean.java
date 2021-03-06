package co.isoft.nnita.profile.beans;

import java.io.Serializable;

/**
 * Bean de administracion de procesos de usuarios
 * la gestion de sus procesos y asignacion de
 * permisos sobre los usuarios.
 *
 * @author Yaher Carrillo
 */
public class ManagmentUsersBean implements Serializable
{
    /**
     * Login de usuario a crear
     */
    private String loginname;
    /**
     * Nombres de usuario
     */
    private String nombres;
    /**
     * Apellidos de usuario
     */
    private String apellidos;
    /**
     * Correo electronico del usuario
     */
    private String email;
    /**
     * Clave de acceso de usuario
     */
    private String clave;

    /**
     * Metodo que procesa la informacion de formulario
     * y crea un usuario en ISoftProfile
     *
     * @return navegacion.
     */
    public String crearUsuarioISoftProfile()
    {
        String navegacion = "";
        return navegacion;
    }

    /**
     * Obtiene el login de usuario a crear
     *
     * @return login de usuario a crear
     */
    public String getLoginname()
    {
        return loginname;
    }

    /**
     * Asigna un valor al login de usuario
     *
     * @param loginname valor a asignar
     */
    public void setLoginname(String loginname)
    {
        this.loginname = loginname;
    }

    /**
     * Obtiene los nombres del usuario a crear
     *
     * @return nombres de usuario
     */
    public String getNombres()
    {
        return nombres;
    }

    /**
     * Asgina un valor a los nombres de usuarios
     *
     * @param nombres valor a asignar
     */
    public void setNombres(String nombres)
    {
        this.nombres = nombres;
    }

    /**
     * Obtiene los apellidos del usuario a crear
     *
     * @return apellidos del usuario a crear
     */
    public String getApellidos()
    {
        return apellidos;
    }

    /**
     * Asigna un valor a los apellidos
     * del usuario a crear
     *
     * @param apellidos valor a asignar
     */
    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el email del usuario a crear
     *
     * @return email del usuario a crear
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Asigna un valor al email del usuario a crear
     *
     * @param email valor a asignar
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Obtiene la clave del usuario a crear
     *
     * @return clave del usuario a crear
     */
    public String getClave()
    {
        return clave;
    }

    /**
     * Asigna un valor a la clave de acceso del cliente
     *
     * @param clave valor a asignar
     */
    public void setClave(String clave)
    {
        this.clave = clave;
    }
}
