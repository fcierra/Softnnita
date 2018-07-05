package co.isoft.nnita.profile.dao.impl;

import co.isoft.nnita.profile.dao.HibernateDaoImpl;
import co.isoft.nnita.profile.dao.PaisesDao;
import co.isoft.nnita.profile.models.Paises;
import org.springframework.stereotype.Repository;

/**
 * Clase abstracta para la manipulacion de operaciones crud de la entidad
 * Paises.
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
@Repository("paisesDao")
public class PaisesDaoImpl extends HibernateDaoImpl<Integer,Paises> implements PaisesDao
{

}