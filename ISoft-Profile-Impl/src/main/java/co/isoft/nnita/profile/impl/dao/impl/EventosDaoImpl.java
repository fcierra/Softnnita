package co.isoft.nnita.profile.impl.dao.impl;

import co.isoft.nnita.profile.api.dao.EventosDao;
import co.isoft.nnita.profile.impl.dao.HibernateDaoImpl;
import co.isoft.nnita.profile.api.models.Eventos;
import org.springframework.stereotype.Repository;

/**
 * Clase abstracta para la manipulacion de operaciones crud de la entidad
 * Eventos.
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
@Repository("eventosDao")
public class EventosDaoImpl extends HibernateDaoImpl<Integer,Eventos> implements EventosDao
{

}
