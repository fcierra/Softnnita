package co.isoft.nnita.profile.impl.dao;

import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.dao.EstilosDao;
import co.isoft.nnita.profile.api.models.Estilos;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.dbunit.dataset.IDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Implementacion de procesos dao
 * de la entidad estilos, operaciones basicas de la entidad.
 *
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
public class EstilosDaoTest extends EntityDaoImplTest
{
    /**
     * Dao de operaciones
     */
    @Autowired
    private EstilosDao estilosDao;

    /**
     * Crea un estilo en el sistema
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void crearUnEstilo() throws DaoException
    {
        Estilos estilo = new Estilos();
        estilo.setCodigo_estilo("GENERAL");
        estilo.setNombre_estilo("DEFAULT");
        estilo.setHabilitado(1);
        estilosDao.agregar(estilo);
    }

    /**
     * Busca un estilo con el id 1
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void buscarPorId() throws DaoException
    {
        Assert.assertNotNull(estilosDao.buscarPorId(new Long(1)));
    }

    /**
     * Busca un estilo por su codigo
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void buscarPorCodigo() throws DaoException
    {
        Estilos canal = new Estilos();
        canal.setId(new Long("1"));
        canal.setCodigo_estilo("GENERAL");
        Assert.assertNotNull(estilosDao.buscar(canal));
    }

    @Override
    protected IDataSet getDataSet() throws Exception
    {
        return null;
    }

}
