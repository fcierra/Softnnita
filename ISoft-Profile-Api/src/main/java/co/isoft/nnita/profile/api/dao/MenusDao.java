package co.isoft.nnita.profile.api.dao;

import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.models.*;

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
    /**
     * Obtiene los sub menus asociados a un menu padre
     * @param idPadre id del padre
     * @param idPerfil id del perfil
     * @return Listado de items del menu
     * @throws DaoException Ocurre si falla la operacion
     */
    public List<Menus_Item> getMenusItemPorMenuPadre(Long idPadre,Long idPerfil) throws DaoException;

    /**
     * Listado de menues para el administrador
     * @param idPadre id del padre
     * @return listado de items del sistema
     * @throws DaoException sucede si falla la operacion
     */
    public List<Menus_Item> getMenusItemPorMenuPadreAdmin(Long idPadre) throws DaoException;

    /**
     * Obtiene las navgeaciones disponibles para el cliente
     * @param perfil perfil a buscar
     * @return listado de menus disponibles
     * @throws DaoException Ocurre si falla la operacion
     */
    public List<Menus> getNavegacionPerfil(Perfiles perfil) throws DaoException;

    /**
     * Busca los elementos del usuarios administrador
     * @return Listado de todos los menus
     * @throws DaoException
     */
    public List<Menus> getNavegacionPerfilAdmin() throws DaoException;

    /**
     * Busca los permisos a los que puede accceder el perfil indicado.
     * @param codeperfil perfil a buscar
     * @return listado de permisos
     * @throws DaoException Ocurre si falla la operacion
     */
    public List<Permisos> getPermisosUsuarios(String codeperfil) throws DaoException;


}
