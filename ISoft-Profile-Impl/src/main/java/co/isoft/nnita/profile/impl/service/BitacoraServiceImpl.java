package co.isoft.nnita.profile.impl.service;

import co.isoft.nnita.profile.api.dao.*;
import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.*;
import co.isoft.nnita.profile.api.services.BitacoraService;
import co.isoft.nnita.profile.api.util.EnumCanalesISoft;
import co.isoft.nnita.profile.api.util.EnumFuncionalityISoft;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static co.isoft.nnita.profile.api.util.ConstantesBaseBean.MAP_CANAL_TRANSACTION;
import static co.isoft.nnita.profile.api.util.ConstantesBaseBean.MAP_IP_TRANSACTION;
import static co.isoft.nnita.profile.api.util.ConstantesBaseBean.MAP_USER_TRANSACTION;

/**
 * Implementacion de logica de acceso a registros de auditoria
 *
 * @author Yaher Carrillo
 * @Date 26/09/2018
 */
@Service("bitacoraServiceImpl")
@Transactional
public class BitacoraServiceImpl extends UtilServices implements BitacoraService
{
    /**
     * Objeto utilizado para el log
     */
    private static final Log logger = LogFactory.getLog(BitacoraServiceImpl.class);

    /**
     * Dao de Bitacora
     */
    @Autowired
    private BitacoraDao bitacoraDao;
    /**
     * Detalle de bitacora
     */
    @Autowired
    private DetalleBitacoraDao detalleBitacoraDao;
    /**
     * Dao de Eventos
     */
    @Autowired
    private EventosDao eventosDao;
    /**
     * Dao de Canales
     */
    @Autowired
    private CanalesDao canalesDao;

    /**
     * Dao de Canales
     */
    @Autowired
    private UsuariosDao usuariosDao;

    @Override
    public void registrarBitacora(EnumFuncionalityISoft funcionalidad, EnumCanalesISoft enumCanal, String loginuser) throws ServiceException
    {
        try
        {
            //Se busca el evento
            Eventos evento = new Eventos();
            evento.setCodigo_evento(funcionalidad.getCodigo());
            evento = eventosDao.buscarObjetoUnico(evento);

            //Se busca el evento
            Canales canal = new Canales();
            canal.setCodigo_canal(enumCanal.getCodigo());
            canal = canalesDao.buscarObjetoUnico(canal);

            //Se busca al usuario
            Usuarios usuario = new Usuarios();
            usuario.setLogin(loginuser);
            //Se transforman los string en mayusculas
            convertAtrrUppercase(usuario);
            usuario = usuariosDao.buscarObjetoUnico(usuario);

            Bitacora bitacora = new Bitacora();
            bitacora.setEvento(evento);
            bitacora.setCanal(canal);
            bitacora.setUsuario(usuario);
            bitacora.setFecha_registro(new Date());
            bitacora.setFecha_registro_segundos(new Date().getTime());
            bitacora.setHabilitado(Long.valueOf("1"));
            //Se realiza el registro
            bitacoraDao.agregar(bitacora);
        }
        catch (DaoException e)
        {
            String mensaje = "Error al generear la auditoria del Evento [" + funcionalidad.getNombre() + "]";
            throw new ServiceException(mensaje, e, e.getCode());
        }
    }

    @Override
    public void registrarBitacora(EnumFuncionalityISoft funcionalidad, EnumCanalesISoft enumCanal, String loginuser, List<DetalleBitacora> listDetails) throws ServiceException
    {
        try
        {
            //Se busca el evento
            Eventos evento = new Eventos();
            evento.setCodigo_evento(funcionalidad.getCodigo());
            evento = eventosDao.buscarObjetoUnico(evento);

            //Se busca el evento
            Canales canal = new Canales();
            canal.setCodigo_canal(enumCanal.getCodigo());
            canal = canalesDao.buscarObjetoUnico(canal);

            //Se busca al usuario
            Usuarios usuario = new Usuarios();
            usuario.setLogin(loginuser);
            //Se transforman los string en mayusculas
            convertAtrrUppercase(usuario);
            usuario = usuariosDao.buscarObjetoUnico(usuario);

            Bitacora bitacora = new Bitacora();
            bitacora.setEvento(evento);
            bitacora.setCanal(canal);
            bitacora.setUsuario(usuario);
            bitacora.setFecha_registro(new Date());
            bitacora.setFecha_registro_segundos(new Date().getTime());
            bitacora.setHabilitado(Long.valueOf("1"));
            //Se realiza el registro
            Long idTransacction = bitacoraDao.agregar(bitacora);
            crearRegistroDetalle(funcionalidad,listDetails,evento,idTransacction);
        }
        catch (DaoException e)
        {
            String mensaje = "Error al generear el detalle de auditoria del Evento [" + funcionalidad.getNombre() + "]";
            throw new ServiceException(mensaje, e, e.getCode());
        }
    }

    @Override
    public void registrarBitacora(EnumFuncionalityISoft funcionalidad, Map<String, String> mapconfiguration, List<DetalleBitacora> listDetails) throws ServiceException
    {
        try
        {
            //Se busca el evento
            Eventos evento = new Eventos();
            evento.setCodigo_evento(funcionalidad.getCodigo());
            evento = eventosDao.buscarObjetoUnico(evento);

            //Se busca el canal
            EnumCanalesISoft enumCanal = EnumCanalesISoft.valueOf(Integer.parseInt(mapconfiguration.get(MAP_CANAL_TRANSACTION)));
            Canales canal = new Canales();
            canal.setCodigo_canal(enumCanal.getCodigo());
            canal = canalesDao.buscarObjetoUnico(canal);

            //Se busca al usuario
            String loginuser = mapconfiguration.get(MAP_USER_TRANSACTION);
            Usuarios usuario = new Usuarios();
            usuario.setLogin(loginuser);
            //Se transforman los string en mayusculas
            convertAtrrUppercase(usuario);
            usuario = usuariosDao.buscarObjetoUnico(usuario);

            Bitacora bitacora = new Bitacora();
            bitacora.setEvento(evento);
            bitacora.setCanal(canal);
            bitacora.setUsuario(usuario);
            bitacora.setFecha_registro(new Date());
            bitacora.setFecha_registro_segundos(new Date().getTime());
            bitacora.setIp(mapconfiguration.get(MAP_IP_TRANSACTION));
            bitacora.setHabilitado(Long.valueOf("1"));
            //Se realiza el registro
            Long idTransacction = bitacoraDao.agregar(bitacora);
            //Se realiza el registro del detalle de la transaccion
            crearRegistroDetalle(funcionalidad,listDetails,evento,idTransacction);
        }
        catch (DaoException e)
        {
            String mensaje = "Error al generear el detalle de auditoria del Evento [" + funcionalidad.getNombre() + "]";
            throw new ServiceException(mensaje, e, e.getCode());
        }
    }

    public void crearRegistroDetalle (EnumFuncionalityISoft funcionalidad, List<DetalleBitacora> listDetails, Eventos evento,Long idTransacction) throws ServiceException{
        try
        {
            for (DetalleBitacora detail : listDetails){
                detail.setEvento(evento);
                detail.setHabilitado(new Long("1"));
                Bitacora binnacle = new Bitacora();
                binnacle.setId(idTransacction);
                detail.setBitacora(binnacle);
                detail.setHora_inicio(new Date().getTime());
                detail.setHora_fin(new Date().getTime());
                detalleBitacoraDao.agregar(detail);
            }
        }catch (DaoException e){
            String mensaje = "Error al generear el detalle de auditoria del Evento [" + funcionalidad.getNombre() + "]";
            throw new ServiceException(mensaje, e, e.getCode());
        }
    }
}
