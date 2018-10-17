package co.isoft.nnita.profile.api.services;

import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.Perfiles;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.modelsweb.DatosSesionUsuario;
import co.isoft.nnita.profile.api.modelsweb.UsuarioPerfilMassive;

import java.util.List;
import java.util.Map;

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
     * Crea un usuario y le asocia perfiles
     * @param usuario usuario a crear
     * @param perfiles perfiles a asociar
     * @throws ServiceException Ocurre si falla la operacion
     */
    public void createUserIsoftProfile(Usuarios usuario,Map<String ,Perfiles> perfiles) throws ServiceException;

    /**
     * Crea un usuario sin perfil
     * @param mapConfiguration mapa de configuracion
     * @param usuario usuario a crear
     * @throws ServiceException
     */
    public void createUserIsoftProfile(Map<String,String> mapConfiguration,Usuarios usuario) throws ServiceException;

    /**
     * Crea usuarios de forma masiva
     * @param mapConfiguration mapa de configuracion
     * @param passord clave de todos los usuarios
     * @param listUsers usuarios y perfiles a crear
     * @throws ServiceException
     */
    public List<UsuarioPerfilMassive> createUsersMassiveIsoftProfile(Map<String,String> mapConfiguration,String passord, List<UsuarioPerfilMassive> listUsers) throws ServiceException;

    /**
     * Agrega un listado de perfiles a un usuario indicado
     * @param mapConfiguration mapa de configuracion de datos utiles
     * @param loginuser usuario que se le asociara el perfil.
     * @param perfiles listado de perfiles
     * @throws ServiceException Ocurre si falla la operacion.
     */
    public void addProfilesUser(Map<String,String> mapConfiguration,String loginuser, List<String> perfiles) throws ServiceException;

    /**
     * Busca los perfiles disponibles de sistema.
     * @throws ServiceException Ocurre si falla la operacion.
     */
    public List<Perfiles> findProfilesSystem(Map<String,String> mapConfiguration) throws ServiceException;

}
