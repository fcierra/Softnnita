package co.isoft.nnita.profile.dao.impl;

import co.isoft.nnita.profile.dao.EstilosDao;
import co.isoft.nnita.profile.dao.HibernateDaoImpl;
import co.isoft.nnita.profile.dao.PerfilesDao;
import co.isoft.nnita.profile.models.Estilos;
import co.isoft.nnita.profile.models.Perfiles;
import org.springframework.stereotype.Repository;

/**
 * Clase abstracta para la manipulacion de operaciones crud de la entidad
 * Perfiles.
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
@Repository("perfilesDao")
public class PerfilesDaoImpl extends HibernateDaoImpl<Integer,Perfiles> implements PerfilesDao
{

}
