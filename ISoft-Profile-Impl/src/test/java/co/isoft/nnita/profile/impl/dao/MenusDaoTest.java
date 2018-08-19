package co.isoft.nnita.profile.impl.dao;

import co.isoft.nnita.profile.api.dao.MenusDao;
import co.isoft.nnita.profile.api.dao.MenusItemDao;
import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.models.Menus;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.dbunit.dataset.IDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Clase test de objetos menus
 * @author Yaher Carrillo
 * @date 07/08/2018
 */
public class MenusDaoTest extends EntityDaoImplTest
{

    /**
     * Dao de menus
     */
    @Autowired
    private MenusDao menusDao;
    /**
     * Dao de menus item
     */
    @Autowired
    private MenusItemDao menusItemDao;

    /**
     * Crea un Menu en el sistema
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void crearUnMenu() throws DaoException
    {
        Menus menu = new Menus();
        menu.setOrden(2);
        menu.setMenu_label("Menu de Pruebas");
        menu.setRef_security("pruebas");
        menu.setHabilitado(new Long("1"));
        menusDao.agregar(menu);
    }

    /**
     * Consulta un Menu por su id en el sistema
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void consultarMenuPorId() throws DaoException
    {
        Assert.assertNotNull(menusDao.buscarPorId(new Long("1")));
    }

    /**
     * Consulta un Menu por su objeto en el sistema
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void consultarMenu() throws DaoException
    {
        Menus menu = new Menus();
        menu.setMenu_label("Inicio");
        Assert.assertNotNull(menusDao.buscar(menu));
    }
    /**
     * Actualiza un Menu por su id en el sistema
     *
     * @throws DaoException
     */
    @Test
    @Ignore
    public void ActualizarMenu() throws DaoException
    {
        Menus menu = menusDao.buscarPorId(new Long("1"));
        menu.setHabilitado(new Long("0"));
        menusDao.actualizar(menu);
    }

    @Override
    protected IDataSet getDataSet() throws Exception
    {
        return null;
    }

}
