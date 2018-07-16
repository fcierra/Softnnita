package co.isoft.nnita.profile.impl.dao.impl;

import co.isoft.nnita.profile.api.dao.UsuarioPerfilDao;
import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.models.Perfiles;
import co.isoft.nnita.profile.api.models.UsuarioPerfil;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.impl.dao.HibernateDaoImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase abstracta para la manipulacion de operaciones crud de la entidad
 * Usuario Perfiles.
 *
 * @author Yaher Carrillo
 * @Date 13/07/2018
 */
@Repository("usuarioPerfilDao")
public class UsuarioPerfilDaoImpl extends HibernateDaoImpl<Integer, UsuarioPerfil> implements UsuarioPerfilDao
{

    @Override
    public List<Perfiles> buscarPerfilesUsuarios(Usuarios usuario) throws DaoException
    {
        Session session = this.getSession();
        Query query = session.getNamedQuery("buscarPerfilesUsuarios");
        query.setParameter("PARAM_USER", usuario.getId());
        List<Perfiles> usuarioPerfiles = (List<Perfiles>)query.list();
        return usuarioPerfiles;// OK
    }
}
