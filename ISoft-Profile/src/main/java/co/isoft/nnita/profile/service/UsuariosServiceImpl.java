package co.isoft.nnita.profile.service;

import co.isoft.nnita.profile.dao.DaoException;
import co.isoft.nnita.profile.dao.UsuariosDao;
import co.isoft.nnita.profile.models.Usuarios;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("usuariosService")
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

    public Usuarios validarUsuario(String loginUsuario) throws ServiceException
    {
        try
        {
            return usuariosDao.getUsuarioPorLogin(loginUsuario);
        }
        catch (DaoException e)
        {
            String mensaje = "Error al validar los usuarios el usuarios [" + loginUsuario + "]";
            logger.error(mensaje, e);
            throw new ServiceException(mensaje, e);
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