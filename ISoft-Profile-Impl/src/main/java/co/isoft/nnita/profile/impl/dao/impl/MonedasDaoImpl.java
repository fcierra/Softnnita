package co.isoft.nnita.profile.impl.dao.impl;

import co.isoft.nnita.profile.impl.dao.HibernateDaoImpl;
import co.isoft.nnita.profile.api.dao.MonedasDao;
import co.isoft.nnita.profile.api.models.Monedas;
import org.springframework.stereotype.Repository;

/**
 * Clase abstracta para la manipulacion de operaciones crud de la entidad
 * Monedas.
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
@Repository("monedasDao")
public class MonedasDaoImpl extends HibernateDaoImpl<Integer,Monedas> implements MonedasDao
{

}
