package co.isoft.nnita.profile.api.modelsweb;

/**
 * DTO para el tralado de informacion
 * referente a los permisos de un perfil
 *
 * @author Yaher Carrillo
 * @date 19/11/2018
 */
public class PermisosDTO
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
     * Contructor por defecto
     */
    public PermisosDTO()
    {
    }

    /**
     * Constructor que inicializa los atributos del objeto
     *
     * @param id      identificaoor del permiso
     * @param permiso permiso asignado
     */
    public PermisosDTO(Long id, String permiso)
    {
        this.id = id;
        this.permiso = permiso;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getPermiso()
    {
        return permiso;
    }

    public void setPermiso(String permiso)
    {
        this.permiso = permiso;
    }
}
