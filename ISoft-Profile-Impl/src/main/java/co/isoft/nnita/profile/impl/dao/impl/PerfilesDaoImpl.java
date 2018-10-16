package co.isoft.nnita.profile.impl.dao.impl;

import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.impl.dao.HibernateDaoImpl;
import co.isoft.nnita.profile.api.dao.PerfilesDao;
import co.isoft.nnita.profile.api.models.Perfiles;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import sun.misc.Perf;

import java.util.List;

/**
 * Clase abstracta para la manipulacion de operaciones crud de la entidad
 * Perfiles.
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
@Repository("perfilesDao")
public class PerfilesDaoImpl extends HibernateDaoImpl<Integer,Perfiles> implements PerfilesDao
{

    @Override
    public List<Perfiles> findProfilesSystem(boolean status) throws DaoException
    {
        Session session = this.getSession();
        Query query = session.getNamedQuery("findProfilesSystem");
        query.setParameter("PARAM_STATUS", status?new Long("1"):new Long("0"));
        List<Perfiles> list = (List<Perfiles>) query.list();
        return list;// OK
    }
}
