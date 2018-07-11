package co.isoft.nnita.profile.impl.dao.impl;

import co.isoft.nnita.profile.api.dao.BitacoraDao;
import co.isoft.nnita.profile.impl.dao.HibernateDaoImpl;
import co.isoft.nnita.profile.api.models.Bitacora;
import org.springframework.stereotype.Repository;

/**
 * Clase abstracta para la manipulacion de operaciones crud de la entidad
 * Bitacora y DetalleBitacora.
 * @author Yaher Carrillo
 * @Date 11/06/2018
 */
@Repository("bitacoraDao")
public class BitacoraDaoImpl extends HibernateDaoImpl<Integer,Bitacora> implements BitacoraDao
{
}
