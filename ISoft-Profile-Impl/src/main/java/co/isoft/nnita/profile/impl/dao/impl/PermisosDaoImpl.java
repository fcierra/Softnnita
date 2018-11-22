package co.isoft.nnita.profile.impl.dao.impl;

import co.isoft.nnita.profile.api.dao.PermisosDao;
import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.models.Permisos;
import co.isoft.nnita.profile.api.dto.output.PermissionToProfileOutDTO;
import co.isoft.nnita.profile.impl.dao.HibernateDaoImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase abstracta para la manipulacion de operaciones crud de la entidad
 * permisos.
 * @author Yaher Carrillo
 * @Date 12/08/2018
 */
@Repository("permisosDao")
public class PermisosDaoImpl extends HibernateDaoImpl<Integer,Permisos> implements PermisosDao
{

    @Override
    public List<PermissionToProfileOutDTO> buscarPermisosPerfil(String codeperfil) throws DaoException
    {
        Session session = this.getSession();
        Query query = session.getNamedQuery("buscarPermisosPerfil");
        query.setParameter("PARAM_PERFIL", codeperfil);
        List<PermissionToProfileOutDTO> permisos = query.list();
        return permisos;// OK
    }
}
