package co.isoft.nnita.profile.api.gateways.models.request.users;

import java.util.List;

/**
 * Modelo request, para la creacion de usuarios masivamente
 * ISoftProfile.
 *
 * @author Yaher Carrillo
 * @date 11/10/2018
 */
public class RequestModifyPermissionProfile
{
    /**
     * Nombres de usuario
     */
    private List<PermisosDTO> permisos;

    /**
     * Obtiene el listado de permisos
     *
     * @return listado de permisos
     */
    public List<PermisosDTO> getPermisos()
    {
        return permisos;
    }

    /**
     * Asigna un valor al listado de permisos
     *
     * @param permisos valor a asignar
     */
    public void setPermisos(List<PermisosDTO> permisos)
    {
        this.permisos = permisos;
    }
}