package co.isoft.nnita.profile.api.dto.output;

/**
 * Objeto DTO para la consulta de usuarios
 *
 * @author Yaher Carrillo
 * @date 27/11/2018
 */
public class UserDTO
{
    /**
     * Nombres de usuario
     */
    private String nombres;
    /**
     * Apellidos de usuario
     */
    private String apellidos;
    /**
     * Fecha de ultima visita
     */
    private String fecha_ultima_visita;
    /**
     * Correo del usuario
     */
    private String correo;
    /**
     * Estado del usuario
     */
    private boolean estado;
    /**
     * Nombre del perfil por defecto del cliente
     */
    private String nombre_perfil_defecto;

    /**
     * Constructor por defecto del objeto
     */
    public UserDTO()
    {
    }

    /**
     * Constructor que inicializa los atributos del objeto
     *
     * @param nombres               nombres del usuario
     * @param apellidos             apellidos del usuario
     * @param fecha_ultima_visita   fecha de ultima visita
     * @param estado                estado del usuario
     * @param nombre_perfil_defecto nombre del perfil por defecto
     */
    public UserDTO(String nombres, String apellidos, String fecha_ultima_visita, boolean estado, String nombre_perfil_defecto)
    {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fecha_ultima_visita = fecha_ultima_visita;
        this.estado = estado;
        this.nombre_perfil_defecto = nombre_perfil_defecto;
    }

    /**
     * Obtiene los nombres del usuario
     *
     * @return nombres del usuario
     */
    public String getNombres()
    {
        return nombres;
    }

    /**
     * Asigna el valor de nombres al usuario
     *
     * @param nombres valor a asignar
     */
    public void setNombres(String nombres)
    {
        this.nombres = nombres;
    }

    /**
     * Obtiene los apellidos del usuario
     *
     * @return apellidos de usuarios
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
     * Obtiene la fecha de ultima visita
     *
     * @return fecha de ultima visita
     */
    public String getFecha_ultima_visita()
    {
        return fecha_ultima_visita;
    }

    /**
     * Asigna un valor a la fecha de ultima visita
     *
     * @param fecha_ultima_visita valor a asignar
     */
    public void setFecha_ultima_visita(String fecha_ultima_visita)
    {
        this.fecha_ultima_visita = fecha_ultima_visita;
    }

    /**
     * Obtiene el estado del usuario
     *
     * @return estado del usuario
     */
    public boolean getEstado()
    {
        return estado;
    }

    /**
     * Asigna un valor al estado del usuario
     *
     * @param estado valor a asignar
     */
    public void setEstado(boolean estado)
    {
        this.estado = estado;
    }

    /**
     * Obtiene el nombre del perfil por defecto
     *
     * @return nombre del perfil por defecto
     */
    public String getNombre_perfil_defecto()
    {
        return nombre_perfil_defecto;
    }

    /**
     * Asigna un valor al perfil por defecto
     *
     * @param nombre_perfil_defecto valor a asignar
     */
    public void setNombre_perfil_defecto(String nombre_perfil_defecto)
    {
        this.nombre_perfil_defecto = nombre_perfil_defecto;
    }

    /**
     * Obtiene el correo del usuario
     *
     * @return correo del usuario
     */
    public String getCorreo()
    {
        return correo;
    }

    /**
     * Asigna el correo del usuario
     *
     * @param correo valor a asignar
     */
    public void setCorreo(String correo)
    {
        this.correo = correo;
    }
}
