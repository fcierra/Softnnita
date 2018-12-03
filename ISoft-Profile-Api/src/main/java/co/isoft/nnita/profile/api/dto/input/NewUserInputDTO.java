package co.isoft.nnita.profile.api.dto.input;

/**
 * Modelo request, para la creacion de usuarios
 * ISoftProfile
 *
 * @author Yaher Carrillo
 * @date 09/09/2018
 */
public class NewUserInputDTO
{
    /**
     * Login de usuario a crear
     */
    private String usuario;
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
    private String correo;
    /**
     * Clave de acceso de usuario
     */
    private String clave;

    /**
     * Obtiene el login de usuario a crear
     *
     * @return login de usuario a crear
     */
    public String getUsuario()
    {
        return usuario;
    }

    /**
     * Asigna un valor al login de usuario
     *
     * @param usuario valor a asignar
     */
    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
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
     * Obtiene el correo del usuario a crear
     *
     * @return correo del usuario a crear
     */
    public String getCorreo()
    {
        return correo;
    }

    /**
     * Asigna un valor al correo del usuario a crear
     *
     * @param correo valor a asignar
     */
    public void setCorreo(String correo)
    {
        this.correo = correo;
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
