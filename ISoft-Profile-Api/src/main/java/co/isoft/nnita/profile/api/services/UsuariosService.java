package co.isoft.nnita.profile.api.services;

import co.isoft.nnita.profile.api.dto.input.NewUserInputDTO;
import co.isoft.nnita.profile.api.dto.output.UserDTO;
import co.isoft.nnita.profile.api.exceptions.ParamsException;
import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.Perfiles;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.modelsweb.DatosSesionUsuario;
import co.isoft.nnita.profile.api.dto.output.ProfilesToUserOutDTO;
import co.isoft.nnita.profile.api.dto.output.UsersMassiveOutDTO;
import co.isoft.nnita.profile.api.dto.output.UsersAllOutDTO;

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
    public DatosSesionUsuario findUser(String login,String clave) throws ServiceException;

    /**
     * Regersa la informacion de usuario
     * @param login nombre de login de usuario
     * @return Objeto usuarios
     * @throws ServiceException Sucede si falla la operacion.
     */
    public UserDTO findUser(String login) throws ServiceException,ParamsException;

    /**
     * Buscar un usuario para autenticar
     * @param login login de usuario
     * @return
     * @throws ServiceException Ocurre si falla la operacion
     * @throws ParamsException Ocurre si faltan parametros
     */
    public Usuarios findUserToAuthenticated(String login) throws ServiceException,ParamsException;

    /**
     * Obtiene todos los usuarios del sistema
     * @return Listado de usuarios
     * @throws ServiceException Excepcion que ocurre si falla la operacion
     */
    public List<UsersAllOutDTO> findAllUsers() throws ServiceException;

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
    public void createUser(Usuarios usuario,Map<String ,Perfiles> perfiles) throws ServiceException;

    /**
     * Crea un usuario sin perfil
     * @param mapConfiguration mapa de configuracion
     * @param usuario usuario a crear
     * @throws ServiceException
     */
    public void createUser(Map<String,String> mapConfiguration,NewUserInputDTO usuario) throws ServiceException,ParamsException;

    /**
     * Modifica los datos basicos de un usuario indicado
     * @param mapConfiguration mapa de configuraciones de acceso
     * @param usuario usuario a modificar
     * @throws ServiceException Ocurre si falla la operacion
     */
    public void modifyUser(Map<String,String> mapConfiguration,Usuarios usuario) throws ServiceException;

    /**
     * Crea usuarios de forma masiva
     * @param mapConfiguration mapa de configuracion
     * @param passord clave de todos los usuarios
     * @param listUsers usuarios y perfiles a crear
     * @throws ServiceException
     */
    public List<UsersMassiveOutDTO> createUsersMassive(Map<String,String> mapConfiguration,String passord, List<UsersMassiveOutDTO> listUsers) throws ServiceException, ParamsException;

    /**
     * Metodo que administra la disponibilidad o estatus de un usuario
     * se puede habilitar o deshabilitar
     * @param mapConfiguration mapa de configuracion
     * @param loginuser usuario a habilitar o deshabilitar
     * @param status status a asignar
     * @throws ServiceException Ocurre si falla la operacion.
     */
    public void manageStatusEnabledUsers(Map<String,String> mapConfiguration,String loginuser, boolean status) throws ServiceException;

    /**
     * Agrega un listado de perfiles a un usuario indicado
     * @param mapConfiguration mapa de configuracion de datos utiles
     * @param loginuser usuario que se le asociara el perfil.
     * @param perfiles listado de perfiles
     * @throws ServiceException Ocurre si falla la operacion.
     */
    public List<UsersMassiveOutDTO> addProfilesUser(Map<String,String> mapConfiguration,String loginuser, List<String> perfiles) throws ServiceException;

    /**
     * Desasociar perfiles de un determinado usuario.
     * @param mapConfiguration mapa de configuracion de acceso.
     * @param loginuser usuario a desasociar perfiles
     * @param perfiles perfiles a desasociar.
     * @throws ServiceException Ocurre si falla la operacion.
     */
    public List<UsersMassiveOutDTO> unAddProfilesUser(Map<String,String> mapConfiguration,String loginuser, List<String> perfiles) throws ServiceException;

    /**
     * Busca los perfiles disponibles de sistema.
     * @param loginuser usuario que se desea consultar.
     * @param mapConfiguration mapa de configuracion.
     * @throws ServiceException Ocurre si falla la operacion.
     */
    public List<ProfilesToUserOutDTO> findProfilesUsers(Map<String,String> mapConfiguration,String loginuser) throws ServiceException;

}
