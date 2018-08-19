package co.isoft.nnita.profile.api.services;

import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.Menus;
import co.isoft.nnita.profile.api.models.Perfiles;
import co.isoft.nnita.profile.api.models.Permisos;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.modelsweb.DatosSesionUsuario;

import java.util.List;

/**
 * Interfaz que expone los servicios del modelo de negocio
 * referentes al tratado de usuarios.
 * @author Yaher Carrillo
 * @Date 10/07/2018
 */
public interface UsuariosService
{
    /**
     * Se valida un usuario segun su login de acceso
     * @param login login a validar
     * @param clave clave a validar
     * @return Objeto con el los datos de usuario
     * @throws ServiceException sucede si falla la operacion
     */
    public DatosSesionUsuario validateUser(String login,String clave) throws ServiceException;

    /**
     * Regersa la informacion de usuario
     * @param login nombre de login de usuario
     * @return Objeto usuarios
     * @throws ServiceException Sucede si falla la operacion.
     */
    public Usuarios validateUser(String login) throws ServiceException;

    /**
     * Obtiene todos los usuarios del sistema
     * @param estado estado que se desea buscar
     * @return Listado de usuarios
     * @throws ServiceException Excepcion que ocurre si falla la operacion
     */
    public List<Usuarios> getAll(boolean estado) throws ServiceException;

    /**
     * Valida la clave actual del usuario
     * @param usuario usuario a validar
     * @param claveActual clave actual a validar
     * @return logico que indica si la clave es valida
     * @throws ServiceException Excepcion que ocurre si falla la operacion.
     */
    public boolean validateCurrentPassUser(Usuarios usuario,String claveActual) throws ServiceException;

    /**
     * Cambia la clave actual del usuario
     * @param usuario usuario a cambiar la clave
     * @param nuevaClave clave nueva
     * @throws ServiceException Excepcion que ocurre si falla la operacion.
     */
    public void changePassUser(Usuarios usuario,String nuevaClave) throws ServiceException;


    /**
     * Busca los permisos del usuario con respecto al sistema
     * @param perfil perfil a buscar
     * @return Listado de permisos del perfil
     * @throws ServiceException ocurre si falla la operacion
     */
    public List<Permisos> findGrantPermisions(Perfiles perfil) throws ServiceException;

    /**
     * Busca las navegacions disponibles por un determinado perfil
     * @param perfil perfil de navegacion
     * @return listado de navegaciones disponibles
     * @throws ServiceException ocurre si falla la operacion
     */
    public List<Menus> findItemsNavigation(Perfiles perfil) throws ServiceException;

}
