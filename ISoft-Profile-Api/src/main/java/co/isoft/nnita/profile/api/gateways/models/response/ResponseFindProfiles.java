package co.isoft.nnita.profile.api.gateways.models.response;

/**
 * Lista todos los perfiles del sistema
 * en el formato conformado
 * por id  y codigo.
 * Response del metodo : asociarperfilusuario
 */
public class ResponseFindProfiles
{
    /**
     * ID del perfil
     */
    private String id;
    /**
     * Nombre del perfil
     */
    private String nombre;

    /**
     * Obtiene el ID del perfil
     *
     * @return id del perfil
     */
    public String getId()
    {
        return id;
    }

    /**
     * Asigna un valor al ID del perfil
     *
     * @param id valor a asignar
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * Obtiene el nombre del perfil
     *
     * @return nombre del perfil
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Asigna un valor al nombre del perfil
     *
     * @param nombre valor a asignar
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
}
