package co.isoft.nnita.profile.impl.dao;

import co.isoft.nnita.profile.api.dao.DaoException;
import co.isoft.nnita.profile.api.dao.ParametrosDao;
import co.isoft.nnita.profile.api.models.Parametros;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.dbunit.dataset.IDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Implementacion de procesos dao
 * de la entidad Parametros, operaciones basicas de la entidad
 * @author Yaher Carrillo
 * @Date 10/07/2018
 */
public class ParametrosDaoTest extends EntityDaoImplTest
{
    /**
     * Dao de operaciones de la entidad.
     */
    @Autowired
    private ParametrosDao parametrosDao;

    /**
     * Crea un parametro en el sistema
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void crearUnParametro() throws DaoException
    {
        Parametros parametro = new Parametros();
        parametro.setNombre("NUEVO");
        parametro.setDescripcion("NUEVO PARAMETRO");
        parametro.setTipo_dato("boolean");
        parametro.setValor("VALOR");
        parametro.setGrupo("GRUPO");
        parametrosDao.agregar(parametro);
    }

    /**
     * Busca un parametro con el id 1
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void buscarPorId() throws DaoException
    {
        Assert.assertNotNull(parametrosDao.buscarPorId(new Long(1)));
    }

    /**
     * Busca un parametro por su nombre
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void buscarPorNombre() throws DaoException
    {
        Parametros parametro = new Parametros();
        parametro.setNombre("SERVICIO_CLIENTE");
        Assert.assertNotNull(parametrosDao.buscar(parametro));
    }

    @Override
    protected IDataSet getDataSet() throws Exception
    {
        return null;
    }

}
