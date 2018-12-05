package co.isoft.nnita.profile.api.dto.output;

/**
 * Clase util para el manejo de usuarios y perfiles
 * de forma masiva.
 *
 * @author Yaher Carrillo
 * @date 11/10/2018
 */
public class UsersMassiveOutDTO
{
    /**
     * Login del usuario masivo a crear
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
     * Perfil del usuario masivo a crear
     */
    private String codeperfil;
    /**
     * Estatus de la transaccion de la creacion del usuario
     */
    private String estatus;
    /**
     * Codigo de la transaccion
     */
    private String codigo;
    /**
     * Descripcion de las respuesta de response
     */
    private String descripcion;

    /**
     * Constructor por defecto
     */
    public UsersMassiveOutDTO()
    {
    }

    /**
     * Constructor que inicializa el valor del login de usuario
     *
     * @param usuario login de usuario
     */
    public UsersMassiveOutDTO(String usuario)
    {
        this.usuario = usuario;
    }

    /**
     * Obtienen el usuario del usuario masivo a crear
     *
     * @return usuario
     */
    public String getUsuario()
    {
        return usuario;
    }

    /**
     * Asigna un usuario del usuario masivo a crear
     *
     * @param usuario valor a asignar
     */
    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    /**
     * Obtiene el codigo del perfil
     * a asignar a un usuario masivo
     *
     * @return codigo de perfil a asignar
     */
    public String getCodeperfil()
    {
        return codeperfil;
    }

    /**
     * Asigna un valor al codigo de perfil
     * a asignar a un usuario masivo.
     *
     * @param codeperfil
     */
    public void setCodeperfil(String codeperfil)
    {
        this.codeperfil = codeperfil;
    }

    /**
     * Obtiene los nombres del usuario
     *
     * @return
     */
    public String getNombres()
    {
        return nombres;
    }

    /**
     * Asigna un valor a los nombres al item
     *
     * @param nombres valor a asignar
     */
    public void setNombres(String nombres)
    {
        this.nombres = nombres;
    }

    /**
     * Obtiene el apellido del usuario
     *
     * @return apellido a usar
     */
    public String getApellidos()
    {
        return apellidos;
    }

    /**
     * Asigna un valor a los apellidos del usuario
     *
     * @param apellidos valor a asignar
     */
    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene la descripcion de las respuestas
     * rest
     *
     * @return descripcion de las respuestas
     */
    public String getDescripcion()
    {
        return descripcion;
    }

    /**
     * Asigna un valor a la descripcion
     *
     * @param descripcion valor a asignar.
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el codigo de la transaccion del item
     *
     * @return valor de la transaccion
     */
    public String getCodigo()
    {
        return codigo;
    }

    /**
     * Asigna un valor al codigo de la transaccion
     *
     * @param codigo valor a asignar
     */
    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    /**
     * Obtiene el estatus de la transaccion
     *
     * @return
     */
    public String getEstatus()
    {
        return estatus;
    }

    /**
     * Asigna un valor al status de la transaccion del usuario
     *
     * @param estatus
     */
    public void setEstatus(String estatus)
    {
        this.estatus = estatus;
    }
}
