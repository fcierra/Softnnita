package co.isoft.nnita.profile.api.dao;

import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.models.Perfiles;

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
}
