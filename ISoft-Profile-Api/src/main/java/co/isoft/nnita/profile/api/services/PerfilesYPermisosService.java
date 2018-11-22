package co.isoft.nnita.profile.api.services;

import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.gateways.models.request.profile.RequestCreateProfile;
import co.isoft.nnita.profile.api.gateways.models.request.users.PermissionGrants;
import co.isoft.nnita.profile.api.gateways.models.request.users.RequestModifyPermissionProfile;
import co.isoft.nnita.profile.api.models.Menus;
import co.isoft.nnita.profile.api.models.Menus_Item;
import co.isoft.nnita.profile.api.models.Perfiles;
import co.isoft.nnita.profile.api.models.Permisos;
import co.isoft.nnita.profile.api.gateways.models.request.users.PermisosDTO;

import java.util.List;
import java.util.Map;

/**
 * Interfaz que expone los servicios de permisologia
 * de los perfiles de usuario
 *
 * @author Yaher Carrillo
 * @Date 19/08/2018
 */
public interface PerfilesYPermisosService
{

    /**
     * Busca las navegacions disponibles por un determinado perfil
     *
     * @param perfil perfil de navegacion
     * @return listado de navegaciones disponibles
     * @throws ServiceException ocurre si falla la operacion
     */
    public List<Menus> findMenusItemNavigation(Perfiles perfil) throws ServiceException;

    /**
     * Busca los permisos del usuario con respecto al sistema
     *
     * @param codeperfil perfil a buscar
     * @return Listado de permisos del perfil
     * @throws ServiceException ocurre si falla la operacion
     */
    public List<Permisos> findGrantPermisions(String codeperfil) throws ServiceException;

    /**
     * Busca los permisos a configurar dentro del sistema
     * y que serviran de regla para todos los usuarios que autentiquen.
     *
     * @return Listado de permisos
     * @throws ServiceException Ocurre si falla la operacion.
     */
    public List<Menus_Item> findNavigationSystem() throws ServiceException;

    /**
     * Busca los perfiles disponibles de sistema.
     *
     * @throws ServiceException Ocurre si falla la operacion.
     */
    public List<Perfiles> findProfilesSystem(Map<String, String> mapConfiguration) throws ServiceException;

    /**
     * Crea un perfil en el sistema
     *
     * @param mapConfiguration mapa de configuracion
     * @param nombrePerfil     nombre o codigo del perfil
     * @param habilitado       status del perfil
     * @throws ServiceException Ocurre si falla la operacion.
     */
    public void createProfile(Map<String, String> mapConfiguration, String nombrePerfil, Long habilitado) throws ServiceException;

    /**
     * Modifica un perfil del sistema
     *
     * @param mapConfiguration mapa de configuracion
     * @param dto     objeto a modificar
     * @throws ServiceException Ocurre si falla la operacion
     */
    public void modifyProfile(Map<String, String> mapConfiguration, RequestCreateProfile dto) throws ServiceException;

    /**
     * Busca los permisos de un perfil
     * @param mapConfiguration mapa de configuracion de la licencia
     * @param profile perfil a consultar
     * @return Listado de permisos
     * @throws ServiceException Ocurre si falla la operacion
     */
    public List<PermisosDTO> findPermissionProfile(Map<String, String> mapConfiguration, String profile) throws ServiceException;

    /**
     * Modifica los permisos de un perfil
     * @param mapConfiguration mapa de configuracion de la licencia
     * @param dto listado de permisos
     * @return Lista de respuesta
     * @throws ServiceException
     */
    public  List<PermissionGrants> modifyPermissionProfile(Map<String, String> mapConfiguration, RequestModifyPermissionProfile dto) throws ServiceException;

}
