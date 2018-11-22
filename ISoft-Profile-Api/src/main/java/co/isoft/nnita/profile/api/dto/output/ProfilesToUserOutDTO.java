package co.isoft.nnita.profile.api.dto.output;

/**
 * Modelo de datos para la transmision
 * de la logica de perfiles asociados
 * a un usuario en especifico para la consulta
 * general.
 *
 * @author Yaher Carrillo.
 * @since 25/10/2018
 */
public class ProfilesToUserOutDTO
{
    /**
     * Id del registro
     */
    private Long id;
    /**
     * Codigo del perfil
     */
    private String nombre_perfil;
    /**
     * Indica un logico sobre si el perfil esta habilitado
     */
    private Long habilitado;

    /**
     * Constructor por defecto
     */
    public ProfilesToUserOutDTO()
    {
    }

    /**
     * Constructor que inicializa los valores
     * del objeto cuando se instancia
     *
     * @param nombre_perfil nombre del perfil
     * @param habilitado    indica si el registro esta habilitado
     */
    public ProfilesToUserOutDTO(String nombre_perfil, Long habilitado)
    {
        this.nombre_perfil = nombre_perfil;
        this.habilitado = habilitado;
    }

    /**
     * Obtiene el valor del id
     *
     * @return id del registro
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Asigna un valor al id del
     * registro
     *
     * @param id valor a asignar
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Obtiene el nombre del perfil
     *
     * @return nombre del perfil
     */
    public String getNombre_perfil()
    {
        return nombre_perfil;
    }

    /**
     * Asigna un valor al nombre del perfil
     *
     * @param nombre_perfil valor a asignar
     */
    public void setNombre_perfil(String nombre_perfil)
    {
        this.nombre_perfil = nombre_perfil;
    }

    /**
     * Obtiene el logico que indica si el
     * registro esta habilitado
     *
     * @return valor de habilitado
     */
    public Long getHabilitado()
    {
        return habilitado;
    }

    /**
     * Asigna un valor de habilitado al registro
     *
     * @param habilitado valor a asignar
     */
    public void setHabilitado(Long habilitado)
    {
        this.habilitado = habilitado;
    }
}
