package co.isoft.nnita.profile.impl.dao;

import co.isoft.nnita.profile.api.dao.BancosDao;
import co.isoft.nnita.profile.api.dao.DaoException;
import co.isoft.nnita.profile.api.models.Bancos;
import co.isoft.nnita.profile.api.models.Paises;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.dbunit.dataset.IDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Implementacion de procesos dao
 * de la entidad Bancos, operaciones basicas de la entidad
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
public class BancosDaoTest extends EntityDaoImplTest
{
    /**
     * Dao de operaciones de la entidad.
     */
    @Autowired
    private BancosDao bancosDao;

    /**
     * Crea un canal en el sistema
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void crearUnBanco() throws DaoException
    {
        Bancos banco = new Bancos();
        Paises pais = new Paises();
        pais.setId(new Long("1"));
        banco.setPais(pais);
        banco.setCodigo_banco("BKY");
        banco.setNombre_banco("Banco Yaher");
        banco.setHabilitado(1);
        bancosDao.agregar(banco);
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
        Assert.assertNotNull(bancosDao.buscarPorId(new Long(1)));
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
        Bancos bancos = new Bancos();
        bancos.setId(new Long("1"));
        Assert.assertNotNull(bancosDao.buscar(bancos));
    }

    @Override
    protected IDataSet getDataSet() throws Exception
    {
        return null;
    }

}
