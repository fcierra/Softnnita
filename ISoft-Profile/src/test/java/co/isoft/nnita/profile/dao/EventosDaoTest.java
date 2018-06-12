package co.isoft.nnita.profile.dao;

import co.isoft.nnita.profile.models.Bancos;
import co.isoft.nnita.profile.models.Eventos;
import co.isoft.nnita.profile.models.Paises;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.dbunit.dataset.IDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Implementacion de procesos dao
 * de la entidad Eventos, operaciones basicas de la entidad
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
public class EventosDaoTest extends EntityDaoImplTest
{
    /**
     * Dao de operaciones de la entidad.
     */
    @Autowired
    private EventosDao eventosDao;

    /**
     * Crea un evento en el sistema
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void crearUnEvento() throws DaoException
    {
        Eventos evento = new Eventos();
        evento.setCodigo_evento("INICIO-SESION");
        evento.setNombre_evento("Inicio de Sesion");
        evento.setHabilitado(1);
        eventosDao.agregar(evento);
    }

    /**
     * Busca un evento con el id 1
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void buscarPorId() throws DaoException
    {
        Assert.assertNotNull(eventosDao.buscarPorId(new Long(1)));
    }

    /**
     * Busca un evento por su codigo
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void buscarPorCodigo() throws DaoException
    {
        Eventos evento = new Eventos();
        evento.setId(new Long("1"));
        evento.setCodigo_evento("OLVIDO-CLAVE");
        Assert.assertNotNull(eventosDao.buscar(evento));
    }

    @Override
    protected IDataSet getDataSet() throws Exception
    {
        return null;
    }

}
