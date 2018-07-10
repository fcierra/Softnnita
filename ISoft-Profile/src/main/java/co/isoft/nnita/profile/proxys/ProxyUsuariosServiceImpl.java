package co.isoft.nnita.profile.proxys;

import co.isoft.nnita.profile.configuration.references.ReferencesServiceUsers;
import co.isoft.nnita.profile.configuration.spring.ContextProvider;
import co.isoft.nnita.profile.dao.DaoException;
import co.isoft.nnita.profile.dao.ParametrosDao;
import co.isoft.nnita.profile.dao.UsuariosDao;
import co.isoft.nnita.profile.models.Parametros;
import co.isoft.nnita.profile.models.Usuarios;
import co.isoft.nnita.profile.service.ServiceException;
import co.isoft.nnita.profile.service.UsuariosService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    private ReferencesServiceUsers referencesServiceUsers;

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
        return (UsuariosService) contextProvider.getBean(referencesServiceUsers.getMapServicesUsers().get(getParameterDefinitionUsuariosService()));
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
    public Usuarios validarUsuario(String login) throws ServiceException
    {
        return getUsuariosService().validarUsuario(login);
    }

    @Override
    public List<Usuarios> obtenerTodo(boolean estado) throws ServiceException
    {
        String cadena = "";
        return null;
    }
}
