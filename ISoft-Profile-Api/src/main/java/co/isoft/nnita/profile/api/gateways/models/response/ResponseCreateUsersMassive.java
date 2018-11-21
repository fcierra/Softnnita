package co.isoft.nnita.profile.api.gateways.models.response;

import co.isoft.nnita.profile.api.gateways.models.CommonsResponse;
import co.isoft.nnita.profile.api.gateways.models.request.users.UsuarioPerfilMassive;

import java.util.List;

/**
 * Response de salida de las peticiones al metodo
 * crearusuariosisoftmasivo, con un listado de
 * objetos y el detalle de sus respuestas.
 *
 * @author Yaher Carrillo
 * @date 12/10/2018
 */
public class ResponseCreateUsersMassive extends CommonsResponse
{
    /**
     * Listado de respuestas de la creacion masiva de usuarios
     */
    private List<UsuarioPerfilMassive> listResponse;

    /**
     * Obtiene el listado de respuesta de la solicitud
     * de creacion de usuarios masivos
     *
     * @return Listado de usuarios creados
     */
    public List<UsuarioPerfilMassive> getListResponse()
    {
        return listResponse;
    }

    /**
     * Asigna un valor de respuesya de la solicitud
     * de creacion de usuarios masivos.
     *
     * @param listResponse
     */
    public void setListResponse(List<UsuarioPerfilMassive> listResponse)
    {
        this.listResponse = listResponse;
    }
}


