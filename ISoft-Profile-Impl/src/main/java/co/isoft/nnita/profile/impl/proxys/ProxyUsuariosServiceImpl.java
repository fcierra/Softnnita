package co.isoft.nnita.profile.impl.proxys;

import co.isoft.nnita.profile.api.dao.ParametrosDao;
import co.isoft.nnita.profile.api.dao.UsuariosDao;
import co.isoft.nnita.profile.api.dto.input.NewUserInputDTO;
import co.isoft.nnita.profile.api.dto.output.UserDTO;
import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.exceptions.ParamsException;
import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.Parametros;
import co.isoft.nnita.profile.api.models.Perfiles;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.modelsweb.DatosSesionUsuario;
import co.isoft.nnita.profile.api.dto.output.ProfilesToUserOutDTO;
import co.isoft.nnita.profile.api.dto.output.UsersMassiveOutDTO;
import co.isoft.nnita.profile.api.dto.output.UsersAllOutDTO;
import co.isoft.nnita.profile.api.services.UsuariosService;
import co.isoft.nnita.profile.impl.configuration.hibernate.ContextProvider;
import co.isoft.nnita.profile.impl.configuration.hibernate.ServicesReferencesMapping;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Proxy de consulta de los servicios de usuarios.
 * a traves de el se filtrara las implementaciones necesarias
 * segun dicten los parametros.
 *
 * @author Yaher Carrillo
 * @Date 10/07/2018
 */
@Service("proxyUsuariosService")
@Transactional
public class ProxyUsuariosServiceImpl implements UsuariosService
{

    /**
     * Objeto utilizado para el log
     */
    private static final Log logger = LogFactory.getLog(ProxyUsuariosServiceImpl.class);
    /**
     * Dao de  parametros
     */
    @Autowired
    private ParametrosDao parametrosDao;
    /**
     * Dao de servicios crud de usuarios
     */
    @Autowired
    private UsuariosDao usuariosDao;

    @Autowired
    private ServicesReferencesMapping servicesReferencesMapping;

    @Autowired
    private ContextProvider contextProvider;

    /**
     * Nombre de servcio a consumir por configuracion
     */
    private String service_proxy;

    /**
     * Metodo del proxy que permite saber cual sera la implementacion a usar
     * para el consumo de datos de usuarios.
     *
     * @return
     */
    private UsuariosService getUsuariosService()
    {
        return getBeanDefinition();
    }

    /**
     * Metodo que busca el bean del contexto, definido segun un parametro
     * para que sera usado para los procesos del servicio de usuarios
     *
     * @return Servicio de usuarios
     */
    private UsuariosService getBeanDefinition()
    {
        return (UsuariosService) contextProvider.getBean(servicesReferencesMapping.getMapServicesUsers().get(getParameterDefinitionUsuariosService()));
    }

    /**
     * Metodo que busca el parametro que indica que servicio se va aplicar
     * para el servicio de usuarios
     *
     * @return nombre de servicio
     */
    private String getParameterDefinitionUsuariosService()
    {
        try
        {
            Parametros parametro = parametrosDao.buscarPorId(new Long("1"));
            return parametro.getValor();
        }
        catch (DaoException ex)
        {
            logger.error("Ha ocurrido un error configurando el servicio de consulta de datos de usuarios");
        }
        return "";
    }

    @Override
    public DatosSesionUsuario findUser(String login, String clave) throws ServiceException
    {
        return getUsuariosService().findUser(login, clave);
    }

    @Override
    public UserDTO findUser(String login) throws ServiceException,ParamsException
    {
        return getUsuariosService().findUser(login);
    }

    @Override
    public Usuarios findUserToAuthenticated(String login) throws ServiceException, ParamsException
    {
        return getUsuariosService().findUserToAuthenticated(login);
    }

    @Override
    public List<UsersAllOutDTO> findAllUsers() throws ServiceException
    {
        return getUsuariosService().findAllUsers();
    }

    @Override
    public boolean validateCurrentPassUser(Usuarios usuario, String claveActual) throws ServiceException
    {
        return getUsuariosService().validateCurrentPassUser(usuario, claveActual);
    }

    @Override
    public void changePassUser(Usuarios usuario, String nuevaClave) throws ServiceException
    {
        getUsuariosService().changePassUser(usuario, nuevaClave);
    }

    @Override
    public void createUser(Usuarios usuario, Map<String, Perfiles> perfiles) throws ServiceException
    {
        getUsuariosService().createUser(usuario, perfiles);
    }

    @Override
    public void createUser(Map<String,String> mapConfiguration, NewUserInputDTO usuario) throws ServiceException,ParamsException
    {
        getUsuariosService().createUser(mapConfiguration, usuario);
    }

    @Override
    public void modifyUser(Map<String, String> mapConfiguration, Usuarios usuario) throws ServiceException
    {
        getUsuariosService().modifyUser(mapConfiguration, usuario);
    }

    @Override
    public List<UsersMassiveOutDTO> createUsersMassive(Map<String,String> mapConfiguration, String passord, List<UsersMassiveOutDTO> listUsers) throws ServiceException, ParamsException
    {
        return getUsuariosService().createUsersMassive(mapConfiguration, passord, listUsers);
    }

    @Override
    public void manageStatusEnabledUsers(Map<String, String> mapConfiguration, String loginuser, boolean status) throws ServiceException
    {
        getUsuariosService().manageStatusEnabledUsers(mapConfiguration, loginuser, status);
    }

    @Override
    public List<UsersMassiveOutDTO> addProfilesUser(Map<String,String> mapConfiguration,String loginname, List<String> perfiles) throws ServiceException
    {
        return getUsuariosService().addProfilesUser(mapConfiguration,loginname, perfiles);
    }

    @Override
    public List<UsersMassiveOutDTO> unAddProfilesUser(Map<String, String> mapConfiguration, String loginuser, List<String> perfiles) throws ServiceException
    {
        return getUsuariosService().unAddProfilesUser(mapConfiguration,loginuser, perfiles);
    }

    @Override
    public List<ProfilesToUserOutDTO> findProfilesUsers(Map<String, String> mapConfiguration, String loginuser) throws ServiceException
    {
        return getUsuariosService().findProfilesUsers(mapConfiguration,loginuser);
    }
}
