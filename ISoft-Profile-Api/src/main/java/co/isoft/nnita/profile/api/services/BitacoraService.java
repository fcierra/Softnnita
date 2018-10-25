package co.isoft.nnita.profile.api.services;

import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.*;
import co.isoft.nnita.profile.api.util.EnumCanalesISoft;
import co.isoft.nnita.profile.api.util.EnumFuncionalityISoft;

import java.util.List;

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
     * @param loginuser  usuario que realiza la transaccion
     * @throws ServiceException Ocurre si falla la operacion.
     */
    public void registrarBitacora(EnumFuncionalityISoft funcionalidad, EnumCanalesISoft enumCanal, String loginuser) throws ServiceException;

    /**
     * Realiza un registro de bitacora asociada a un evento
     * de la aplicacion y genera detalles sobre la transaccion
     * @param funcionalidad evento a registrar
     * @param enumCanal canal de uso
     * @param loginuser usuario que realiza la transaccion
     * @param listDetails detalle de la transaccion.
     * @throws ServiceException Ocurre si falla la operacion.
     */
    public void registrarBitacora(EnumFuncionalityISoft funcionalidad, EnumCanalesISoft enumCanal, String loginuser,List<DetalleBitacora> listDetails) throws ServiceException;

}
