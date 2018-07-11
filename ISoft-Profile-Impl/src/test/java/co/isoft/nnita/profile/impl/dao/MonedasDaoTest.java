package co.isoft.nnita.profile.impl.dao;

import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.dao.MonedasDao;
import co.isoft.nnita.profile.api.models.Monedas;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.dbunit.dataset.IDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Implementacion de procesos dao
 * de la entidad Monedas, operaciones basicas de la entidad.
 *
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
public class MonedasDaoTest extends EntityDaoImplTest
{
    /**
     * Dao de operaciones
     */
    @Autowired
    private MonedasDao monedasDao;

    /**
     * Crea una moneda en el sistema
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void crearUnaMoneda() throws DaoException
    {
        Monedas moneda = new Monedas();
        moneda.setCodigo_moneda("COP1");
        moneda.setNombre_moneda("Pesos Colombianos");
        moneda.setHabilitado(1);
        monedasDao.agregar(moneda);
    }

    /**
     * Busca una Moneda con el id 1
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void buscarPorId() throws DaoException
    {
        Assert.assertNotNull(monedasDao.buscarPorId(new Long(1)));
    }

    /**
     * Busca una Moneda por su codigo
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void buscarPorCodigo() throws DaoException
    {
        Monedas moneda = new Monedas();
        moneda.setId(new Long("1"));
        moneda.setCodigo_moneda("COP");
        Assert.assertNotNull(monedasDao.buscar(moneda));
    }

    @Override
    protected IDataSet getDataSet() throws Exception
    {
        return null;
    }

}
