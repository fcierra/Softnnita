package co.isoft.nnita.profile.api.services;

import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.Usuarios;

/**
 * Interfaz de servicio para el manejo de la informacion
 * de sesion de usuarios conectados. Solo se emitiran procesos de
 * consulta.
 * @author Yaher Carrill
 * @Date 13/07/2018
 */
public interface SesionActivaService
{
    /**
     * Obtiene los datos basicos de sesion del usuario.
     *
     * @return Objeto de usuarios
     * @throws ServiceException Excepcion que ocurre si falla la operacion.
     */
    public Usuarios getUsuarioSesion() throws ServiceException;
}
