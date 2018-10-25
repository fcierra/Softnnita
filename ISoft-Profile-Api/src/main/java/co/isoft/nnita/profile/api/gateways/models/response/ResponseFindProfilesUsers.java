package co.isoft.nnita.profile.api.gateways.models.response;

/**
 * Lista todos los perfiles del sistema
 * en el formato conformado
 * por id  y codigo.
 * Response del metodo : consultarperfilesusuario
 */
public class ResponseFindProfilesUsers extends ResponseFindProfiles
{
    /**
     * Registro habilitado de la consulta
     */
    private Long habilitado;

    /**
     * Obtiene el campo de registro de estatus de la respuesta
     * de busqueda de perfiles de usuarios.
     * @return
     */
    public Long getHabilitado()
    {
        return habilitado;
    }

    /**
     * Asigna un valor al registro de status de la busqueda
     * de perfiles de usuarios.
     * @param habilitado
     */
    public void setHabilitado(Long habilitado)
    {
        this.habilitado = habilitado;
    }
}
