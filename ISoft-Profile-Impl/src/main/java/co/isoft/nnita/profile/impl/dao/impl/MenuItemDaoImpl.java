package co.isoft.nnita.profile.impl.dao.impl;

import co.isoft.nnita.profile.api.dao.MenusDao;
import co.isoft.nnita.profile.api.dao.MenusItemDao;
import co.isoft.nnita.profile.api.models.Menus;
import co.isoft.nnita.profile.api.models.Menus_Item;
import co.isoft.nnita.profile.impl.dao.HibernateDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * Clase abstracta para la manipulacion de operaciones crud de la entidad
 * menu.
 * @author Yaher Carrillo
 * @Date 07/08/2018
 */
@Repository("menusItemDao")
public class MenuItemDaoImpl extends HibernateDaoImpl<Integer,Menus_Item> implements MenusItemDao
{
}
