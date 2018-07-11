package co.isoft.nnita.profile.impl.dao.impl;

import co.isoft.nnita.profile.impl.dao.HibernateDaoImpl;
import co.isoft.nnita.profile.api.dao.PerfilesDao;
import co.isoft.nnita.profile.api.models.Perfiles;
import org.springframework.stereotype.Repository;

/**
 * Clase abstracta para la manipulacion de operaciones crud de la entidad
 * Perfiles.
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
@Repository("perfilesDao")
public class PerfilesDaoImpl extends HibernateDaoImpl<Integer,Perfiles> implements PerfilesDao
{

}
