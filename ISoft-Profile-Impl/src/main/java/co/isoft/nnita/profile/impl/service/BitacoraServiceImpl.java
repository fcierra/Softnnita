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
    public void registarBitacora(EnumFuncionalityISoft funcionalidad, EnumCanalesISoft enumCanal, String loginuser) throws ServiceException
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
    public void registarBitacora(EnumFuncionalityISoft funcionalidad, EnumCanalesISoft enumCanal, String loginuser, List<DetalleBitacora> listDetails) throws ServiceException
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
        }
        catch (DaoException e)
        {
            String mensaje = "Error al generear el detalle de auditoria del Evento [" + funcionalidad.getNombre() + "]";
            throw new ServiceException(mensaje, e, e.getCode());
        }
    }
}
