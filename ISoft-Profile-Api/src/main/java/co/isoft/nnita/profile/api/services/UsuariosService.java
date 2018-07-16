package co.isoft.nnita.profile.api.services;

import co.isoft.nnita.profile.api.exceptions.ServiceException;
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
    public DatosSesionUsuario validarUsuario(String login,String clave) throws ServiceException;

    /**
     * Obtiene todos los usuarios del sistema
     * @param estado estado que se desea buscar
     * @return Listado de usuarios
     * @throws ServiceException Excepcion que ocurre si falla la operacion
     */
    public List<Usuarios> obtenerTodo(boolean estado) throws ServiceException;

}
