package co.isoft.nnita.profile.api.dao;

import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.models.Permisos;
import co.isoft.nnita.profile.api.dto.output.PermissionToProfileOutDTO;

import java.util.List;

/**
 * Interfaz de acceso a servicios CRUD  de la entidad
 * Permisos.
 *
 * @author Yaher Carrillo
 * @Date 12/08/2018
 */
public interface PermisosDao extends HibernateDao<Permisos, Long>
{
    /**
     * Busca los permisos asociados a un pérfil
     *
     * @param codeperfil codigo de perfil a buscar
     * @return Listado de permisos asociados
     * @throws DaoException Ocurre si falla la operacion.
     */
    public List<PermissionToProfileOutDTO> buscarPermisosPerfil(String codeperfil) throws DaoException;
}
