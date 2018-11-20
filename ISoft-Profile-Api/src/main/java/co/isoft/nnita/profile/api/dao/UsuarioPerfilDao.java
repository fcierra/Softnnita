package co.isoft.nnita.profile.api.dao;

import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.models.Perfiles;
import co.isoft.nnita.profile.api.models.UsuarioPerfil;
import co.isoft.nnita.profile.api.models.Usuarios;

import java.util.List;

/**
 * Interfaz de acceso a servicios CRUD  de la entidad
 * Usuario_Perfil.
 * @author Yaher Carrillo
 * @Date 13/07/2018
 */
public interface UsuarioPerfilDao extends HibernateDao<UsuarioPerfil, Long>
{
    /**
     * Busca los perfiles de un usuario.
     * @return listado de ID'S de perfiles de usuario
     * @throws DaoException Excepcion que ocurre si falla la operacion
     */
    public List<Perfiles> buscarPerfilesUsuarios(Usuarios usuario) throws DaoException;
}
