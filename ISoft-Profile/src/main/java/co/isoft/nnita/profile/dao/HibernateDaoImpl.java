package co.isoft.nnita.profile.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class HibernateDaoImpl<PK extends Serializable, ENTIDAD> {

    // Campos utilizados para optimizar
    private static final String RAYA_AL_PISO = "_";

    private static final String PARAMETRO = " =:_";
    private static final String PARAMETRO_DISTINTO = " !=:_";

    private static final String PUNTO = ".";
    private static final String ESPACIO = " ";
    private static final String ESPACIO_Y = " and";
    private static final String ESPACIO_DONDE = " where";
    private static final String ESPACIO_AS_ESPACIO = " as ";
	/** Query que consulta todos los elementos */
	private String CONSULTA_TODOS;
	/** Alias de la clase utilizado para la consulta  */
	private String ALIAS_ENTIDAD;
	/** Cadena con la consulta para contar el numero de registros */
	private String CONSULTA_NUMERO_REGISTROS;
	
	private final Class<ENTIDAD> persistentClass;
	/**
	 * Session hibernate bdd
	 */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Constructor de la clase
	 */
	@SuppressWarnings("unchecked")
	public HibernateDaoImpl(){
		this.persistentClass =(Class<ENTIDAD>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		this.CONSULTA_TODOS = "from " + persistentClass.getName();
		this.ALIAS_ENTIDAD = RAYA_AL_PISO + persistentClass.getSimpleName();
		this.CONSULTA_NUMERO_REGISTROS = "select count(" + ALIAS_ENTIDAD +") from " + persistentClass.getName();
	}

	/**
	 * Obtiene todos los elementos de una entidad
	 * @return Listado de la entidad en base de datos
	 */
	public List<ENTIDAD> obtenerTodo()
	{
		Session session = this.getSession();
		List<ENTIDAD> resultado = session.createQuery(CONSULTA_TODOS).list();
		return resultado;
	}

	/**
	 * Agrega el objeto al sistema.
	 * @param dato Objeto que se agrega.
	 * @return Id del objeto que se agrega.
	 */
	public Long agregar(ENTIDAD dato)
	{
		Session session = this.getSession();
		Long id = (Long)session.save(dato);
		session.flush();
		return id;
	}

	/**
	 * Elimina el objeto del sistema.
	 * @param obj Objeto que se desea eliminar del sistema.
	 */
	public void eliminar(ENTIDAD obj)
	{
		Session session = this.getSession();
		session.delete(obj);
	}

	/**
	 * Actualiza los datos del objeto en el sistema.
	 * @param dato dato que se desea actualizar.
	 */
	public void actualizar(ENTIDAD dato)
	{
		Session session = this.getSession();
		// FIXME Ver si se puede eliminar el merge
		//session.merge(dato);
		session.update(dato);
	}

	/**
	 * Busca un elemento de una entidad por su id unico
	 * @param id id unico
	 * @return Elemento encontrado
	 */
	public ENTIDAD buscarPorId(Long id)
	{
		Session session = this.getSession();
		ENTIDAD valor = (ENTIDAD)session.get(persistentClass,id);
		return valor;
	}

	/**
	 * Regresa una lista con los objetos encontrados al filtrar por los atributos no
	 * nulor del objeto pasado como parametro.
	 * @param entity Objeto utlizado para filtrar.
	 * @return Lista con los objetos encontrados.
	 */
	public List<ENTIDAD> buscar(final ENTIDAD entity)
	{
		return buscarConFiltro(entity,null);
	}

	/**
	 * Busca un resultado unico. Si no encuentra el objeto regresa null y si encuentra mas de uno lanza una excepcion.
	 * @param entity Entidad utilizada para filtrar.
	 * @return Entidad encontrada o null si no se encuentra.
	 */
	@SuppressWarnings("unchecked")
	public ENTIDAD buscarObjetoUnico(final ENTIDAD entity)
	{
		QueryResultado queryResultado = this.createQuery(entity,CONSULTA_TODOS,null,false);
		int numeroPropiedades = queryResultado.getNumeroPropiedades();

		Query hquery;
		if( numeroPropiedades > 0 )
		{
			hquery = crearQuery(queryResultado);
		}
		// Consultar todos los elementos si no hay parametros
		else
		{
			Session session = this.getSession();
			hquery = session.createQuery(CONSULTA_TODOS);
		}

		ENTIDAD resultado = (ENTIDAD)hquery.uniqueResult();
		return resultado;
	}

	/**
	 * Regresa el numero de objetos filtrados por las propiedades no nulas en la entidad.
	 * @param entity Entidad utilizada para filtrar.
	 * @return Regresa el numero de registros.
	 */
	public Long getNumeroRegistros(ENTIDAD entity)
	{
		QueryResultado queryResultado = this.createQuery(entity,CONSULTA_NUMERO_REGISTROS,null,false);
		Query hquery = crearQuery(queryResultado);
		return ((Number)hquery.uniqueResult()).longValue();
	}

	/**
	 * Cuenta el numero de registros filtrados por el objeto pasado como parametro con distinto id
	 * @param entity Objeto por el que se filtra
	 * @return Numero de registros filtrados por el objeto pasado como parametro con distinto id
	 * @author Rafael Camacho
	 */
	public Long getNumeroRegistrosDistintoId(ENTIDAD entity)
	{
		QueryResultado queryResultado = this.createQuery(entity,CONSULTA_NUMERO_REGISTROS,null,true);
		Query hquery = crearQuery(queryResultado);
		return ((Number)hquery.uniqueResult()).longValue();
	}

	/**
	 * Busca todos los elementos en el sistema filtrado por la propiedades no nulas
	 * de la entidad adicionandole un filtro.
	 * @param entity Entidad utilizada para filtrar.
	 * @param filtro Filtro adicional en HQL.
	 * @return Lista de objetos encontrados.
	 * @author Rafael Camacho
	 */
	@SuppressWarnings("unchecked")
	public List<ENTIDAD> buscarConFiltro(final ENTIDAD entity,String filtro)
	{
		QueryResultado queryResultado = this.createQuery(entity,CONSULTA_TODOS,filtro,false);
		int numeroPropiedades = queryResultado.getNumeroPropiedades();

		Query hquery;
		if( numeroPropiedades > 0 )
		{
			hquery = crearQuery(queryResultado);
		}
		// Consultar todos los elementos si no hay parametros
		else
		{
			Session session = this.getSession();
			hquery = session.createQuery(CONSULTA_TODOS);
		}

		List<ENTIDAD> resultado = hquery.list();
		return resultado;
	}

	/**
	 * Crea el HQL para el entity con las propiedades no nulas.
	 * @param entity Entidad utilizada para la consulta.
	 * @param tipoFiltro Tipo de filtro, corresponde al HQL inicial.
	 * @param filtro Filtro adicional a la consulta.
	 * @return Objeto con el resultado del query.
	 * @author Rafael Camacho
	 */
	private QueryResultado createQuery(ENTIDAD entity,String tipoFiltro,String filtro, boolean distintoId)
	{
		// Buffer con el query que se genera
		StringBuilder queryBuffer = new StringBuilder(tipoFiltro);
		queryBuffer.append(ESPACIO_AS_ESPACIO);
		queryBuffer.append(ALIAS_ENTIDAD);
		queryBuffer.append(ESPACIO_DONDE);

		final int sizeEmptyParams = queryBuffer.length();

		if( filtro != null )
		{
			queryBuffer.append(ESPACIO + filtro);
		}

		// Metadatos para determinar los mapeados por Hibernate.
		ClassMetadata clazzMetadata = (ClassMetadata)this.getSession().getSessionFactory().getAllClassMetadata().get(this.persistentClass.getName() );

		String[] propertyNames        = clazzMetadata.getPropertyNames();  // Nombre de las propiedades usadas por hibernate
		String[] propertyNamesNotNull = new String[propertyNames.length+1];// Propiedades con valor no nulo
		Object[] propertyValues       = new Object[propertyNames.length+1];// Valor de las propiedades

		int i = 0;// Indice para determinar el numero de propiedades no nulas.
		for(String nameProperty : propertyNames )
		{
			Object valueProperty = clazzMetadata.getPropertyValue(entity, nameProperty);
			if(valueProperty != null)
			{
				if(queryBuffer.length() > sizeEmptyParams)
				{
					queryBuffer.append(ESPACIO_Y);
				}

				// Agregar parametro de consulta
				queryBuffer.append(ESPACIO);
				queryBuffer.append(ALIAS_ENTIDAD);
				queryBuffer.append(PUNTO);
				queryBuffer.append(nameProperty);
				queryBuffer.append(PARAMETRO);
				queryBuffer.append(nameProperty);

				// Agregar valor y nombre no nulo
				propertyValues[i] = valueProperty;
				propertyNamesNotNull[i++] = nameProperty;
			}
		}

		// Agrega el parametro del identificador.
		if( clazzMetadata.hasIdentifierProperty() )
		{
			Serializable id = clazzMetadata.getIdentifier(entity);
			String nameId = clazzMetadata.getIdentifierPropertyName();
			if( id != null )
			{
				if(queryBuffer.length() > sizeEmptyParams)
				{
					queryBuffer.append(ESPACIO_Y);
				}

				// FIXME Codigo que puede ayudar
				//clazzMetadata.getIdentifierType()


				// Agregar parametros de consulta
				queryBuffer.append(ESPACIO);
				queryBuffer.append(ALIAS_ENTIDAD);
				queryBuffer.append(PUNTO);
				queryBuffer.append(nameId);
				if(distintoId)
				{
					queryBuffer.append(PARAMETRO_DISTINTO);
				}
				else
				{
					queryBuffer.append(PARAMETRO);
				}
				queryBuffer.append(nameId);

				// Agregar valor y nombre no nulo
				propertyValues[i] = id;
				propertyNamesNotNull[i++] = nameId;
			}
		}

		// Crear el objeto con el resultado
		QueryResultado queryResultado = new QueryResultado(queryBuffer.toString(), i,propertyNamesNotNull,propertyValues);

		return queryResultado;
	}

	/**
	 * Crea un Query de Hibernate a partir de un QueryResultado
	 * @param queryResultado Objeto utilizado para crear el Query de hibernate.
	 * @return Query de hibernate.
	 */
	protected Query crearQuery(QueryResultado queryResultado)
	{
		String[] propertyNamesNotNullParam = queryResultado.getPropertyNamesNotNull() ;
		Object[] propertyValuesParam = queryResultado.getPropertyValues();

		String hql = queryResultado.getQuery();
		Session session = this.getSession();
		Query hquery = session.createQuery(hql);
		int numeroPropiedades = queryResultado.getNumeroPropiedades();
		// Agregar los parametros al Query
		for(int j = 0; j < numeroPropiedades ;j++)
		{
			hquery.setParameter(RAYA_AL_PISO +propertyNamesNotNullParam[j], propertyValuesParam[j]);
		}
		return hquery;
	}



	/**
	 * Regresa un query por su nombre.
	 * @param nombre Nombre del query.
	 * @return Query creado por su nombre.
	 */
	protected Query getNamedQuery(String nombre)
	{
		return getSession().getNamedQuery(nombre);
	}

	/**
	 * Regresa el alias de la entidad utilizado para realizar consutas.
	 * @return Alias de la entidad utilizado para realizar la consulta.
	 */
	public String getAliasEntidad()
	{
		return ALIAS_ENTIDAD;
	}

	/**
	 * Borra la sesion
	 */
	public void clear()
	{
		getSession().clear();
	}

	/**
	 * Limpia la sesion
	 */
	public void flush()
	{
		getSession().flush();
	}

	/**
	 * Metodo que retorna la sesion actual
	 * @return
	 */
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Metodo que retorna el dao registrado
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ENTIDAD getByKey(PK key) {
		return (ENTIDAD) getSession().get(persistentClass, key);
	}

	/**
	 * Clase utilizada para contener los datos resultantes de la funcion createQuery.
	 */
	protected class QueryResultado
	{
		/** HQL utilizado para la consulta */
		private String query;
		/** Numero de propiedades involucaradas en la consulta */
		private int numeroPropiedades;
		/** Nombres de las propiedades */
		private String[] propertyNamesNotNull;
		/** Valor de las propiedades */
		private Object[] propertyValues;

		/**
		 * Crea el objeto.
		 * @param query HQL generado.
		 * @param numeroPropiedades Numero de propiedades utilizadas como parametro de la consulta.
		 * @param propertyNamesNotNull Nombre de las propiedades
		 * @param propertyValues Valor de las propiedades
		 */
		public QueryResultado(String query,int numeroPropiedades,String[] propertyNamesNotNull, Object[] propertyValues)
		{
			this.query = query;
			this.numeroPropiedades = numeroPropiedades;
			this.propertyNamesNotNull = propertyNamesNotNull;
			this.propertyValues = propertyValues;
		}

		/**
		 * Regresal el HQL generado.
		 * @return HQL generado.
		 */
		public String getQuery()
		{
			return query;
		}

		/**
		 * Numero de propiedades utilizadas como parametros en el HQL generado.
		 * @return Numero de propiedades generadas en el HQL.
		 */
		public int getNumeroPropiedades()
		{
			return numeroPropiedades;
		}

		/**
		 * Nombres de las propiedades
		 * @return
		 */
		public String[] getPropertyNamesNotNull()
		{
			return propertyNamesNotNull;
		}

		/**
		 * Valores de las propiedades.
		 * @return Valores de las propiedades
		 */
		public Object[] getPropertyValues()
		{
			return propertyValues;
		}
	}


}
