package co.isoft.nnita.profile.api.services;

import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.*;
import co.isoft.nnita.profile.api.modelsweb.DatosSesionUsuario;

import java.util.List;

/**
 * Interfaz que expone los servicios de permisologia
 * de los perfiles de usuario
 * @author Yaher Carrillo
 * @Date 19/08/2018
 */
public interface PermisosService
{

    /**
     * Busca las navegacions disponibles por un determinado perfil
     * @param perfil perfil de navegacion
     * @return listado de navegaciones disponibles
     * @throws ServiceException ocurre si falla la operacion
     */
    public List<Menus> findMenusItemNavigation(Perfiles perfil) throws ServiceException;

    /**
     * Busca los permisos del usuario con respecto al sistema
     * @param perfil perfil a buscar
     * @return Listado de permisos del perfil
     * @throws ServiceException ocurre si falla la operacion
     */
    public List<Permisos> findGrantPermisions(Perfiles perfil) throws ServiceException;

    /**
     *
     * Busca los permisos a configurar dentro del sistema
     * y que serviran de regla para todos los usuarios que autentiquen.
     * @return Listado de permisos
     * @throws ServiceException Ocurre si falla la operacion.
     */
    public List<Menus_Item> findNavigationSystem() throws ServiceException;


}
