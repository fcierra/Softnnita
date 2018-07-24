package co.isoft.nnita.profile.impl.dao;



import co.isoft.nnita.profile.api.exceptions.DaoException;
import co.isoft.nnita.profile.api.dao.UsuariosDao;
import co.isoft.nnita.profile.api.models.Usuarios;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.dbunit.dataset.IDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UsuariosDaoTest extends EntityDaoImplTest{

	@Autowired
	private UsuariosDao usuariosDao;


	/**
	 * Crea un usuarion en el sistema
	 * @throws DaoException
	 */
	@Test
	@Ignore
	public void crearUnUsuario() throws DaoException
	{
		Usuarios usuario = new Usuarios();
		usuario.setNombres("Saher Oscar");
		usuario.setApellidos("Carrillo Domador");
		usuario.setLogin("saher");
		usuario.setClave("saher");
		usuario.setFecha_registro(new Date());
		usuario.setFecha_ultima_visita(new Date());
		//Long id = usuariosDao.agregar(usuario);
		Assert.assertNotNull(usuariosDao.getUsuarioPorLogin("saher"));
	}

	/**
	 * Busca un usuario con el id 1
	 * @throws DaoException
	 */
	@Test
	@Ignore
	public void buscarPorId() throws DaoException
	{
		Assert.assertNotNull(usuariosDao.buscarPorId(new Long(1)));
	}

	/**
	 * Busca un usuario por su nombre
	 * @throws DaoException
	 */
	@Test
	@Ignore
	public void buscarPorLogin() throws DaoException
	{
		Assert.assertNotNull(usuariosDao.getUsuarioPorLogin("isolina"));
		//Assert.assertNull(usuariosDao.getUsuarioPorLogin("yaher1"));
	}

	@Test
	@Ignore
	public void buscarUsuarioActivoMes() throws DaoException
	{
		Assert.assertNotNull(usuariosDao.getUsuarioActivosMes("yaher"));
	}

	@Test
	@Ignore
	public void buscarUsuarioActivoPorFecha() throws DaoException
	{
		//Se resta un mes a la fecha actual
		Date fecha_inicio = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha_inicio);
		calendar.add(Calendar.MONTH,-1);
		fecha_inicio = calendar.getTime();

		Date fecha_fin = new Date();
		Assert.assertNotNull(usuariosDao.getUsuarioActivosEntreFechas(fecha_inicio,fecha_fin));
	}

	@Test
	@Ignore
	public void buscarTodosLosUsuarios() throws DaoException
	{
		List<Usuarios> listado = usuariosDao.getUsuarioPorEstados(true);
		listado.size();
	}

	@Test
	//@Ignore
	public void ActualizarUsuario() throws DaoException
	{
		Usuarios usuario = usuariosDao.buscarPorId(new Long("1"));
		usuario.setHabilitado(new Long("0"));
		usuariosDao.actualizar(usuario);
	}


	@Override
	protected IDataSet getDataSet() throws Exception
	{
		return null;
	}

}
