package co.isoft.nnita.profile.impl.dao.impl;

import co.isoft.nnita.profile.api.dao.MenusDao;
import co.isoft.nnita.profile.api.dao.UsuariosDao;
import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.models.*;
import co.isoft.nnita.profile.impl.dao.HibernateDaoImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Clase abstracta para la manipulacion de operaciones crud de la entidad
 * menu.
 * @author Yaher Carrillo
 * @Date 07/08/2018
 */
@Repository("menusDao")
public class MenuDaoImpl extends HibernateDaoImpl<Integer,Menus> implements MenusDao
{

    @Override
    public List<Menus_Item> getMenusItemPorMenuPadre(Long idPadre,Long idPerfil) throws DaoException
    {
        Session session = this.getSession();
        Query query = session.getNamedQuery("buscarItemsNavegacionDisponiblesPerfil");
        query.setParameter("PARAM_PERFIL", idPerfil);
        query.setParameter("PARAM_MENU_PADRE", idPadre);
        List<Menus_Item> items = (List<Menus_Item>) query.list();
        return items;// OK
    }

    @Override
    public List<Menus_Item> getMenusItemPorMenuPadreAdmin(Long idPadre) throws DaoException
    {
        Session session = this.getSession();
        Query query = session.getNamedQuery("buscarItemsAdmin");
        query.setParameter("PARAM_MENU_PADRE", idPadre);
        List<Menus_Item> menus = (List<Menus_Item>) query.list();
        return menus;// OK
    }

    @Override
    public List<Menus> getNavegacionPerfil(Perfiles perfil) throws DaoException
    {
        Session session = this.getSession();
        Query query = session.getNamedQuery("buscarNavegacionesPerfiles");
        query.setParameter("PARAM_PERFIL", perfil.getId());
        List<Menus> menus = (List<Menus>) query.list();
        return menus;// OK
    }

    @Override
    public List<Menus> getNavegacionPerfilAdmin() throws DaoException
    {
        Session session = this.getSession();
        Query query = session.getNamedQuery("buscarNavegacionesPerfilesAdmin");
        List<Menus> menus = (List<Menus>) query.list();
        return menus;// OK
    }

    @Override
    public List<Permisos> getPermisosUsuarios(String codeperfil) throws DaoException
    {
        Session session = this.getSession();
        Query query = session.getNamedQuery("buscarPermisosUsuario");
        query.setParameter("PARAM_PERFIL", codeperfil);
        List<Permisos> permisos = (List<Permisos>) query.list();
        return permisos;// OK
    }

}
