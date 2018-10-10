package co.isoft.nnita.profile.api.services;

import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.Bitacora;
import co.isoft.nnita.profile.api.models.Canales;
import co.isoft.nnita.profile.api.models.Eventos;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.util.EnumCanalesISoft;
import co.isoft.nnita.profile.api.util.EnumFuncionalityISoft;

/**
 * Interfaz que expone los servicios del modelo de negocio
 * que almacena los procesos de auditoria.
 *
 * @author Yaher Carrillo
 * @Date 26/09/2018
 */
public interface BitacoraService
{

    /**
     * Realiza un registro de bitacora asociada a un evento
     * de la aplicacion y el detalle general de la bitacora
     *
     * @param funcionalidad   evento a registrar
     * @param enumCanal    canal de acceso
     * @param usuario  usuario
     * @throws ServiceException Ocurre si falla la operacion.
     */
    public void registarBitacora(EnumFuncionalityISoft funcionalidad, EnumCanalesISoft enumCanal, Usuarios usuario) throws ServiceException;

}
