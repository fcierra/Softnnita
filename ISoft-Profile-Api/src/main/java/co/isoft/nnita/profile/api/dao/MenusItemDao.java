package co.isoft.nnita.profile.api.dao;

import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.models.Menus_Item;


import java.util.List;

/**
 * Interfaz de acceso a servicios CRUD  de la entidad
 * Menu Item.
 * @author Yaher Carrillo
 * @Date 07/08/2018
 */
public interface MenusItemDao extends HibernateDao<Menus_Item, Long>
{

    public List<Menus_Item> getItemsDeSistema() throws DaoException;
}
