package co.isoft.nnita.profile.api.dao;

import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.models.Menus;
import co.isoft.nnita.profile.api.models.Usuarios;

import java.util.Date;
import java.util.List;

/**
 * Interfaz de acceso a servicios CRUD  de la entidad
 * Menus.
 * @author Yaher Carrillo
 * @Date 07/08/2018
 */
public interface MenusDao extends HibernateDao<Menus, Long>
{

}
