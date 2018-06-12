package co.isoft.nnita.profile.dao.impl;

import co.isoft.nnita.profile.dao.EventosDao;
import co.isoft.nnita.profile.dao.HibernateDaoImpl;
import co.isoft.nnita.profile.models.Eventos;
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
