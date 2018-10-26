package co.isoft.nnita.profile.api.dao;

import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.models.Menus;
import co.isoft.nnita.profile.api.models.Perfiles;
import co.isoft.nnita.profile.api.models.Permisos;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.modelsweb.UsuariosTodos;

import java.util.Date;
import java.util.List;

/**
 * Interfaz de acceso a servicios CRUD  de la entidad
 * Usuarios.
 * @author Yaher Carrillo
 * @Date 02/06/2018
 */
public interface UsuariosDao extends HibernateDao<Usuarios, Long>
{
    /**
     * Busca un usuario por su login
     * @param login nombre de login de usuario
     * @return objeto usuario
     * @throws DaoException excepcion que ocurre durante la consulta
     */
    public Usuarios getUsuarioPorLogin(String login)throws DaoException;

    /**
     * Busca un usuario por correo
     * @param correo correo de usuario
     * @return usuario encontrado
     * @throws DaoException Ocurre si falla la operacion
     */
    public Usuarios getUsuarioPorEmail(String correo)throws DaoException;

    /**
     * Busca un usuario activo durante las fechas establecidas
     * @param login nombre de usuario
     * @return Objeto usuario
     * @throws DaoException Excepcion que ocurre si falla la consulta
     */
    public List<Usuarios> getUsuarioActivosMes(String login) throws DaoException;

    /**
     * Busca un usuario activo en la aplicacion entre las
     * ultimas 2 fechas
     * @param fecha_inicio fecha de inicio de busqueda
     * @param fecha_fin fecha fin de busqueda
     * @return Listado de usuarios
     * @throws DaoException Excepcion que ocurre si falla la consulta
     */
    public List<Usuarios> getUsuarioActivosEntreFechas(Date fecha_inicio, Date fecha_fin) throws DaoException;

    /**
     * Obtiene todos los usuarios del sistema segun su estado
     * @param activo estado que indica la disponibilidad del usuario
     * @return Listado de usuarios
     * @throws DaoException Excepcion que ocurre si falla la consulta
     */
    public List<Usuarios> getUsuarioPorEstados(boolean activo) throws DaoException;

    /**
     * Busca todos los usuarios del sistema
     * @return Listado de usuarios
     * @throws DaoException Ocurre si falla la excepcion.
     */
    public List<UsuariosTodos> getTodosLosUsuarios() throws DaoException;

}
