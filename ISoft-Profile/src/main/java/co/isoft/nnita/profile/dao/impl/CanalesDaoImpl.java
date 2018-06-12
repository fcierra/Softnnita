package co.isoft.nnita.profile.dao.impl;

import co.isoft.nnita.profile.dao.CanalesDao;
import co.isoft.nnita.profile.dao.DaoException;
import co.isoft.nnita.profile.dao.HibernateDaoImpl;
import co.isoft.nnita.profile.dao.UsuariosDao;
import co.isoft.nnita.profile.models.Canales;
import co.isoft.nnita.profile.models.Usuarios;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Clase abstracta para la manipulacion de operaciones crud de la entidad
 * Canales.
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
@Repository("canalesDao")
public class CanalesDaoImpl extends HibernateDaoImpl<Integer,Canales> implements CanalesDao
{

}
