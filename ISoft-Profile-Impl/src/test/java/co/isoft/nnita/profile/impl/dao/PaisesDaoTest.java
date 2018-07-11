package co.isoft.nnita.profile.impl.dao;

import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.dao.PaisesDao;
import co.isoft.nnita.profile.api.models.Paises;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.dbunit.dataset.IDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Implementacion de procesos dao
 * de la entidad Paises, operaciones basicas de la entidad.
 *
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
public class PaisesDaoTest extends EntityDaoImplTest
{
    /**
     * Dao de operaciones
     */
    @Autowired
    private PaisesDao paisesDao;

    /**
     * Crea un pais en el sistema
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void crearUnPais() throws DaoException
    {
        Paises canal = new Paises();
        canal.setCodigo_pais("VE");
        canal.setNombre_pais("VENEZUELA");
        canal.setHabilitado(1);
        paisesDao.agregar(canal);

    }

    /**
     * Busca un pais con el id 1
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void buscarPorId() throws DaoException
    {
        Assert.assertNotNull(paisesDao.buscarPorId(new Long(1)));
    }

    /**
     * Busca un pais por su codigo
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void buscarPorCodigo() throws DaoException
    {
        Paises pais = new Paises();
        pais.setCodigo_pais("CO");
        pais.setNombre_pais("COLOMBIA");
        Assert.assertNotNull(paisesDao.buscar(pais));
    }

    @Override
    protected IDataSet getDataSet() throws Exception
    {
        return null;
    }

}
