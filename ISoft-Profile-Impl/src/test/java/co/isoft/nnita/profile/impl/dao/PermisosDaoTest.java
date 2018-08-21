package co.isoft.nnita.profile.impl.dao;

import co.isoft.nnita.profile.api.dao.MenusDao;
import co.isoft.nnita.profile.api.dao.PerfilesDao;
import co.isoft.nnita.profile.api.dao.PermisosDao;
import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.models.Menus;
import co.isoft.nnita.profile.api.models.Perfiles;
import co.isoft.nnita.profile.api.models.Permisos;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.dbunit.dataset.IDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PermisosDaoTest extends EntityDaoImplTest
{

    /**
     * Dao Permisos
     */
    @Autowired
    private PermisosDao permisosDao;

    /**
     * Dao de menus
     */
    @Autowired
    private MenusDao menusDao;

    /**
     * Dao de operaciones
     */
    @Autowired
    private PerfilesDao perfilesDao;

    /**
     * Crea un usuarion en el sistema
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void crearUnPermiso() throws DaoException
    {
        Permisos permiso = new Permisos();
        Menus menu = menusDao.buscarPorId(new Long("1"));
        Perfiles perfil = perfilesDao.buscarPorId(new Long("1"));


        permiso.setPerfil(perfil);
        permisosDao.agregar(permiso);
    }

    /**
     * Busca un permiso existente
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void buscarPermisoExistente() throws DaoException
    {
        Assert.assertNotNull(permisosDao.buscarPorId(new Long("1")));
    }

    /**
     * Busca un permiso existente
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void buscarPermisoNoExistente() throws DaoException
    {
        Assert.assertNull(permisosDao.buscarPorId(new Long("150")));
    }

    @Override
    protected IDataSet getDataSet() throws Exception
    {
        return null;
    }

}
