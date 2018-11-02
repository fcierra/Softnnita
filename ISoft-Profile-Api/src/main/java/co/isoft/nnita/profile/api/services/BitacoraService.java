package co.isoft.nnita.profile.api.services;

import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.*;
import co.isoft.nnita.profile.api.util.EnumCanalesISoft;
import co.isoft.nnita.profile.api.util.EnumFuncionalityISoft;

import java.util.List;
import java.util.Map;

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

    /**
     * Realiza un registro de bitacora asociados
     * a un mapan de configuracion que contiene
     * elementos comunes, como el canal, el evento e IP
     * @param funcionalidad Funcionalidad a refistrar
     * @param mapconfiguration mapa de configuracion
     * @param listDetails listado de detalles
     * @throws ServiceException Ocurre si falla la operacion.
     */
    public void registrarBitacora(EnumFuncionalityISoft funcionalidad, Map<String,String> mapconfiguration,List<DetalleBitacora> listDetails) throws ServiceException;

}
