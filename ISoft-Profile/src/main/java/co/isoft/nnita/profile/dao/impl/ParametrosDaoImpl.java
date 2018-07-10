package co.isoft.nnita.profile.dao.impl;

import co.isoft.nnita.profile.dao.HibernateDaoImpl;
import co.isoft.nnita.profile.dao.ParametrosDao;
import co.isoft.nnita.profile.models.Parametros;
import org.springframework.stereotype.Repository;

/**
 * Clase abstracta para la manipulacion de operaciones crud de la entidad
 * Parametros.
 *
 * @author Yaher Carrillo
 * @Date 10/07/2018
 */
@Repository("parametrosDao")
public class ParametrosDaoImpl extends HibernateDaoImpl<Integer, Parametros> implements ParametrosDao
{

}
