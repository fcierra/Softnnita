package co.isoft.nnita.profile.impl.dao.impl;

import co.isoft.nnita.profile.api.dao.CanalesDao;
import co.isoft.nnita.profile.impl.dao.HibernateDaoImpl;
import co.isoft.nnita.profile.api.models.Canales;
import org.springframework.stereotype.Repository;

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
