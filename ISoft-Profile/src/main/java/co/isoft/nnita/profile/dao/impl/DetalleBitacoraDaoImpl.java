package co.isoft.nnita.profile.dao.impl;

import co.isoft.nnita.profile.dao.DetalleBitacoraDao;
import co.isoft.nnita.profile.dao.HibernateDaoImpl;
import co.isoft.nnita.profile.models.DetalleBitacora;
import org.springframework.stereotype.Repository;

/**
 * Clase abstracta para la manipulacion de operaciones crud de la entidad
 * Bitacora y DetalleBitacora.
 * @author Yaher Carrillo
 * @Date 11/06/2018
 */
@Repository("detalleBitacoraDao")
public class DetalleBitacoraDaoImpl extends HibernateDaoImpl<Integer,DetalleBitacora> implements DetalleBitacoraDao
{
}
