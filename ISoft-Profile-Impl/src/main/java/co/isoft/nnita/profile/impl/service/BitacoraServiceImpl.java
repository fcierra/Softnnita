package co.isoft.nnita.profile.impl.service;

import co.isoft.nnita.profile.api.dao.BitacoraDao;
import co.isoft.nnita.profile.api.dao.CanalesDao;
import co.isoft.nnita.profile.api.dao.EventosDao;
import co.isoft.nnita.profile.api.dao.UsuariosDao;
import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.Bitacora;
import co.isoft.nnita.profile.api.models.Canales;
import co.isoft.nnita.profile.api.models.Eventos;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.services.BitacoraService;
import co.isoft.nnita.profile.api.util.EnumCanalesISoft;
import co.isoft.nnita.profile.api.util.EnumFuncionalityISoft;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Implementacion de logica de acceso a registros de auditoria
 *
 * @author Yaher Carrillo
 * @Date 26/09/2018
 */
@Service("bitacoraServiceImpl")
@Transactional
public class BitacoraServiceImpl implements BitacoraService
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
    public void registarBitacora(EnumFuncionalityISoft funcionalidad, EnumCanalesISoft enumCanal, Usuarios usuario) throws ServiceException
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
            Usuarios usuario1 = new Usuarios();
            usuario1.setId(new Long("24"));
            usuario1 = usuariosDao.buscarObjetoUnico(usuario1);



            Bitacora bitacora = new Bitacora();
            bitacora.setEvento(evento);
            bitacora.setCanal(canal);
            bitacora.setUsuario(usuario1);
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
}
