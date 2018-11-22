package co.isoft.nnita.profile.api.dto.input;

import co.isoft.nnita.profile.api.dto.output.PermissionToProfileOutDTO;

import java.util.List;

/**
 * Modelo request, para la creacion de usuarios masivamente
 * ISoftProfile.
 *
 * @author Yaher Carrillo
 * @date 11/10/2018
 */
public class ModifyPermissionProfileInputDTO
{
    /**
     * Nombres de usuario
     */
    private List<PermissionToProfileOutDTO> permisos;

    /**
     * Obtiene el listado de permisos
     *
     * @return listado de permisos
     */
    public List<PermissionToProfileOutDTO> getPermisos()
    {
        return permisos;
    }

    /**
     * Asigna un valor al listado de permisos
     *
     * @param permisos valor a asignar
     */
    public void setPermisos(List<PermissionToProfileOutDTO> permisos)
    {
        this.permisos = permisos;
    }
}