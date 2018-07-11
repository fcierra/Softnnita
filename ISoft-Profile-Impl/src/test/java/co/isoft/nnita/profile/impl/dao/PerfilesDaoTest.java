package co.isoft.nnita.profile.impl.dao;

import co.isoft.nnita.profile.api.dao.DaoException;
import co.isoft.nnita.profile.api.dao.PerfilesDao;
import co.isoft.nnita.profile.api.models.Perfiles;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.dbunit.dataset.IDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Implementacion de procesos dao
 * de la entidad Perfiles, operaciones basicas de la entidad.
 *
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
public class PerfilesDaoTest extends EntityDaoImplTest
{
    /**
     * Dao de operaciones
     */
    @Autowired
    private PerfilesDao perfilesDao;

    /**
     * Crea un perfil en el sistema
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void crearUnPerfil() throws DaoException
    {
        Perfiles perfil = new Perfiles();
        perfil.setNombre_perfil("GENERAL");
        perfil.setAdministrador(1);
        perfil.setHabilitado(1);
        perfilesDao.agregar(perfil);
    }

    /**
     * Busca un perfil con el id 1
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void buscarPorId() throws DaoException
    {
        Assert.assertNotNull(perfilesDao.buscarPorId(new Long(1)));
    }

    /**
     * Busca un perfil por su nombre
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void buscarPorNombre() throws DaoException
    {
        Perfiles canal = new Perfiles();
        canal.setId(new Long("1"));
        canal.setNombre_perfil("GENERAL");
        canal.setAdministrador(1);
        Assert.assertNotNull(perfilesDao.buscar(canal));
    }

    @Override
    protected IDataSet getDataSet() throws Exception
    {
        return null;
    }

}
