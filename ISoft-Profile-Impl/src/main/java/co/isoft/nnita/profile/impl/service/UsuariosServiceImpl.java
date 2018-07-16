package co.isoft.nnita.profile.impl.service;

import co.isoft.nnita.profile.api.dao.UsuarioPerfilDao;
import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.dao.UsuariosDao;
import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.Perfiles;
import co.isoft.nnita.profile.api.modelsweb.DatosSesionUsuario;
import co.isoft.nnita.profile.api.services.UsuariosService;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.util.EnumErrorConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * Implementacion Original de consulta de usuarios Isoftnnita
 * @author Yaher Carrillo
 * @Date 10/07/2018
 */

@Service("usuariosServiceImpl")
@Transactional
public class UsuariosServiceImpl implements UsuariosService
{

    /**
     * Objeto utilizado para el log
     */
    private static final Log logger = LogFactory.getLog(UsuariosServiceImpl.class);
    /**
     * Dao de servicios crud de usuarios
     */
    @Autowired
    private UsuariosDao usuariosDao;

    @Autowired
    private UsuarioPerfilDao usuarioPerfilDao;


    @Override
    public DatosSesionUsuario validarUsuario(String loginUsuario,String clave) throws ServiceException
    {
        try
        {
            DatosSesionUsuario datosUsuario = new DatosSesionUsuario();
            Usuarios usuario = usuariosDao.getUsuarioPorLogin(loginUsuario);
            if (usuario!=null){
                if (usuario.getClave().equals(clave)){
                    //Se buscan los perfiles de usuario
                    List<Perfiles> perfiles = usuarioPerfilDao.buscarPerfilesUsuarios(usuario);
                    if (perfiles!=null && !perfiles.isEmpty()){
                        datosUsuario.setUsuario(usuario);
                        datosUsuario.setPerfiles(perfiles);
                        return  datosUsuario;
                    }
                    else
                        throw new DaoException(EnumErrorConfig.PROFILER_USER_WITHOUT_PROFILES.getCode());
                }else
                    throw new DaoException(EnumErrorConfig.PROFILER_USER_WRONG_KEY.getCode());
            }
            throw new DaoException(EnumErrorConfig.PROFILER_USER_DOES_NOT_EXIST.getCode());
        }
        catch (DaoException e)
        {
            String mensaje = "Error al validar el usuario [" + loginUsuario + "]";
            throw new ServiceException(mensaje, e,e.getCode());
        }
    }

    @Override
    public List<Usuarios> obtenerTodo(boolean estado) throws ServiceException
    {
        try
        {
            return usuariosDao.getUsuarioPorEstados(estado);
        }
        catch (DaoException e)
        {
            String mensaje = "Error al obtener el listado de Usuarios por estado [" + estado + "]";
            logger.error(mensaje, e);
            throw new ServiceException(mensaje, e);
        }
    }
}
