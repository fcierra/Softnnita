package co.isoft.nnita.profile.impl.dao;

import co.isoft.nnita.profile.api.dao.BitacoraDao;
import co.isoft.nnita.profile.api.dao.CanalesDao;
import co.isoft.nnita.profile.api.dao.DaoException;
import co.isoft.nnita.profile.api.dao.DetalleBitacoraDao;
import co.isoft.nnita.profile.api.dao.EventosDao;
import co.isoft.nnita.profile.api.dao.UsuariosDao;
import co.isoft.nnita.profile.api.models.*;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.dbunit.dataset.IDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * Implementacion de procesos dao
 * de la entidad canales, operaciones basicas de la entidad
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
public class BitacoraDaoTest extends EntityDaoImplTest
{
    /**
     * Dao de operaciones de la entidad bitacora.
     */
    @Autowired
    private BitacoraDao bitacoraDao;
    @Autowired
    private DetalleBitacoraDao detalleBitacoraDao;

    /**
     * Dao de operaciones de la entidad canales.
     */
    @Autowired
    private CanalesDao canalesDao;

    /**
     * Dao de operaciones de la entidad eventos.
     */
    @Autowired
    private EventosDao eventosDao;

    /**
     * Dao de operaciones de la entidad usuarios.
     */
    @Autowired
    private UsuariosDao usuariosDao;

    /**
     * Crea un registro en la bitacora.
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void crearBitacora() throws DaoException
    {
        //Se crea el canal por donde se estan realizando las transacciones
        Canales canal = canalesDao.buscarPorId(new Long("1"));
        //Se crea el evento descriptor
        Eventos evento  = eventosDao.buscarPorId(new Long("1"));
        // Se asocia al usuario
        Usuarios usuario = usuariosDao.buscarPorId(new Long("1"));

        Bitacora bitacora = new Bitacora();
        bitacora.setCanal(canal);
        bitacora.setEvento(evento);
        bitacora.setFecha_registro(new Date());
        bitacora.setFecha_registro_segundos(new Date().getTime());
        bitacora.setIp("127.0.0.1");
        bitacora.setHabilitado(1);
        bitacora.setUsuario(usuario);
        bitacoraDao.agregar(bitacora);

    }

    /**
     * Crea un registro en el detalle de la bitacora.
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void crearDetalleBitacora() throws DaoException
    {
        //Se crea el canal por donde se estan realizando las transacciones
        Canales canal = canalesDao.buscarPorId(new Long("1"));
        //Se crea el evento descriptor
        Eventos evento  = eventosDao.buscarPorId(new Long("1"));
        // Se asocia al usuario
        Usuarios usuario = usuariosDao.buscarPorId(new Long("1"));

        //Crear la bitacora
        Bitacora bitacora = new Bitacora();
        bitacora.setCanal(canal);
        bitacora.setEvento(evento);
        bitacora.setFecha_registro(new Date());
        bitacora.setFecha_registro_segundos(new Date().getTime());
        bitacora.setIp("127.0.0.1");
        bitacora.setHabilitado(1);
        bitacora.setUsuario(usuario);
        bitacoraDao.agregar(bitacora);

        //Crear el detalle de un registro.
        DetalleBitacora detalle = new DetalleBitacora();
        detalle.setBitacora(bitacora);
        detalle.setDescripcion("SE CAMBIA VALORES DE USUARIOS");
        detalle.setDetalle_valor_inicio("NOMBRE = YAHER");
        detalle.setDetalle_valor_inicio("nombre = YAHER2");
        detalle.setEvento(evento);
        detalle.setHora_inicio(new Date().getTime());
        detalle.setHora_fin(new Date().getTime());
        detalle.setHabilitado(1);
        detalleBitacoraDao.agregar(detalle);
    }

    /**
     * Busca un canal con el id 1
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void buscarPorId() throws DaoException
    {
        Assert.assertNull(bitacoraDao.buscarPorId(new Long(1)));
    }



    @Override
    protected IDataSet getDataSet() throws Exception
    {
        return null;
    }

}
