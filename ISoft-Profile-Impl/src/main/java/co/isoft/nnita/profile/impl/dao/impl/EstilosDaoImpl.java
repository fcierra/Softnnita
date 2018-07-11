package co.isoft.nnita.profile.impl.dao.impl;

import co.isoft.nnita.profile.api.dao.EstilosDao;
import co.isoft.nnita.profile.impl.dao.HibernateDaoImpl;
import co.isoft.nnita.profile.api.models.Estilos;
import org.springframework.stereotype.Repository;

/**
 * Clase abstracta para la manipulacion de operaciones crud de la entidad
 * Estilos.
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
@Repository("estilosDao")
public class EstilosDaoImpl extends HibernateDaoImpl<Integer,Estilos> implements EstilosDao
{

}
