package co.isoft.nnita.profile.impl.dao.impl;

import co.isoft.nnita.profile.api.dao.MenusDao;
import co.isoft.nnita.profile.api.dao.MenusItemDao;
import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.models.Menus;
import co.isoft.nnita.profile.api.models.Menus_Item;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.impl.dao.HibernateDaoImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

/**
 * Clase abstracta para la manipulacion de operaciones crud de la entidad
 * menu.
 * @author Yaher Carrillo
 * @Date 07/08/2018
 */
@Repository("menusItemDao")
public class MenuItemDaoImpl extends HibernateDaoImpl<Integer,Menus_Item> implements MenusItemDao
{
    @Override
    public List<Menus_Item> getItemsDeSistema() throws DaoException
    {
        Session session = this.getSession();
        Query query = session.getNamedQuery("buscarItemsDeSistema");
        List<Menus_Item> items = (List<Menus_Item>) query.list();
        return items;// OK
    }
}
