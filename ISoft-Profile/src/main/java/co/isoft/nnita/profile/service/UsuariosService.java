package co.isoft.nnita.profile.service;

import co.isoft.nnita.profile.models.Usuarios;

import java.util.List;

/**
 * Interfaz que expone los servicios del modelo de negocio
 * referentes al tratado de usuarios
 */
public interface UsuariosService
{
    /**
     * Se valida un usuario segun su login de acceso
     * @param login login a validar
     * @return Objeto con el los datos de usuario
     * @throws ServiceException sucede si falla la operacion
     */
    public Usuarios validarUsuario(String login) throws ServiceException;

    /**
     * Obtiene todos los usuarios del sistema
     * @param estado estado que se desea buscar
     * @return Listado de usuarios
     * @throws ServiceException Excepcion que ocurre si falla la operacion
     */
    public List<Usuarios> obtenerTodo(boolean estado) throws ServiceException;

}
