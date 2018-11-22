package co.isoft.nnita.profile.api.dto.output;

/**
 * DTO para el tralado de informacion
 * referente a los permisos de un perfil
 *
 * @author Yaher Carrillo
 * @date 19/11/2018
 */
public class PermissionToProfileOutDTO
{
    /**
     * Identificador del permiso
     */
    private Long id;

    /**
     * Permisos asignado
     */
    private String permiso;
    /**
     * Indica si el objeto debe actualizarce o no
     */
    private Long habilitado;

    /**
     * Contructor por defecto
     */
    public PermissionToProfileOutDTO()
    {
    }

    /**
     * Constructor que inicializa los atributos del objeto
     *
     * @param id      identificaoor del permiso
     * @param permiso permiso asignado
     */
    public PermissionToProfileOutDTO(Long id, String permiso)
    {
        this.id = id;
        this.permiso = permiso;
    }

    /**
     * Obtiene el identificador del permiso
     *
     * @return
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Asigna un valor al permiso
     *
     * @param id valor a asignar
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Obtiene la referencia del permiso
     *
     * @return referencia del permiso
     */
    public String getPermiso()
    {
        return permiso;
    }

    /**
     * Asigna un valor a la referencia del permiso
     *
     * @param permiso valor a asignar
     */
    public void setPermiso(String permiso)
    {
        this.permiso = permiso;
    }

    /**
     * Obtiene el atributo para indicar si esta habilitado o no el servicios
     *
     * @return valor de hailitado
     */
    public Long getHabilitado()
    {
        return habilitado;
    }

    /**
     * Asigna un valor de habilitado o deshabilitado al permiso
     *
     * @param habilitado valor a asignar
     */
    public void setHabilitado(Long habilitado)
    {
        this.habilitado = habilitado;
    }
}
