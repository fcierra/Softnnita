package co.isoft.nnita.profile.api.dao;

import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.models.Perfiles;
import co.isoft.nnita.profile.api.models.UsuarioPerfil;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.modelsweb.PerfilesDeUsuario;

import java.util.List;

/**
 * Interfaz de acceso a servicios CRUD  de la entidad
 * Perfiles.
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
public interface PerfilesDao extends HibernateDao<Perfiles, Long>
{
    /**
     * Lista todos los perfiles de sistema
     * @return listado de perfiles
     * @param status estatus de los perfiles a buscar
     * @throws DaoException ocurre si falla la operacion.
     */
    public List<Perfiles> findProfilesSystem(boolean status) throws DaoException;

    /**
     * Lista todos los asociados a un usuario
     * @param usuario usuario a buscar
     * @return Listado de perfiles
     * @throws DaoException Ocurre si falla la operacion.
     */
    public List<PerfilesDeUsuario> findProfilesUsers(Usuarios usuario) throws DaoException;
}
