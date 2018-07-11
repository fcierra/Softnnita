package co.isoft.nnita.profile.impl.dao;

import co.isoft.nnita.profile.api.dao.CanalesDao;
import co.isoft.nnita.profile.api.dao.DaoException;
import co.isoft.nnita.profile.api.models.Canales;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.dbunit.dataset.IDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Implementacion de procesos dao
 * de la entidad canales, operaciones basicas de la entidad
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
public class CanalesDaoTest extends EntityDaoImplTest
{
    /**
     * Dao de operaciones de la entidad.
     */
    @Autowired
    private CanalesDao canalesDao;

    /**
     * Crea un canal en el sistema
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void crearUnCanal() throws DaoException
    {
        Canales canal = new Canales();
        canal.setCodigo_canal("APP");
        canal.setNombre_canal("APP MOVILES");
        canal.setHabilitado(1);
        canalesDao.agregar(canal);

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
        Assert.assertNotNull(canalesDao.buscarPorId(new Long(1)));
    }

    /**
     * Busca un canal por su codigo
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void buscarPorCodigo() throws DaoException
    {
        Canales canal = new Canales();
        canal.setId(new Long("1"));
        Assert.assertNotNull(canalesDao.buscar(canal));
    }

    @Override
    protected IDataSet getDataSet() throws Exception
    {
        return null;
    }

}
