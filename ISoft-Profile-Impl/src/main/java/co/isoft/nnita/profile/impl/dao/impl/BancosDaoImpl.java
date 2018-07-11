package co.isoft.nnita.profile.impl.dao.impl;

import co.isoft.nnita.profile.api.dao.BancosDao;
import co.isoft.nnita.profile.impl.dao.HibernateDaoImpl;
import co.isoft.nnita.profile.api.models.Bancos;
import org.springframework.stereotype.Repository;

/**
 * Clase abstracta para la manipulacion de operaciones crud de la entidad
 * Bancos.
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
@Repository("bancosDao")
public class BancosDaoImpl extends HibernateDaoImpl<Integer,Bancos> implements BancosDao
{

}
