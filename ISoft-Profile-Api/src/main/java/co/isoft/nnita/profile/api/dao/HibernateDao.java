package co.isoft.nnita.profile.api.dao;

import co.isoft.nnita.profile.api.exceptions.DaoException;

import java.io.Serializable;
import java.util.List;

/**
 * Interfaz que deben implementar los Dao que buscan elementos en el sistema.
 * @author Yaher Carrillo
 * @Date 02/06/2018
 */
public abstract interface HibernateDao<ENTIDAD,TIPO_ID> extends Serializable
{
    /**
     * Busca un objeto en el sistema. El parametro
     * es el filtro que es utilizado para realizar la busqueda.
     * @param obj Objeto que es utilizado como filtro.
     * @return Lista con los objetos encontrados.
     * @exception DaoException Sucede si falla la operacion.
     */
    public abstract List<ENTIDAD> buscar(ENTIDAD obj) throws DaoException;

    /**
     * Busca un objeto en el sistema. El parametro
     * es el filtro que es utilizado para realizar la busqueda.
     * @param obj Objeto que es utilizado como filtro.
     * @return El objeto encontrado
     * @exception DaoException Sucede si falla la operacion o el resultado no es unico.
     */
    public abstract ENTIDAD buscarObjetoUnico(final ENTIDAD obj) throws DaoException;


    /**
     * Busca un objeto en el sistema por el ID.
     * @param id ID del objeto.
     * @return Objeto que se busca o null si no existen en el sistema.
     * @throws DaoException Sucede si falla la operacion.
     */
    public abstract ENTIDAD buscarPorId(TIPO_ID id) throws DaoException;

    /**
     * Regresa el numero de registros que concuerdan con los propiedades
     * no nulas de la entidad.
     * @param entity Entidad utilizada para filtrar los registros, se toman las propiedades no nulas.
     * @return Numero de registros en el sistema.
     */
    public abstract Long getNumeroRegistros(ENTIDAD entity) throws DaoException;

    /**
     * Regresa el numero de registros filtrados por el objeto pero con distint id.
     * @param entity Entity por la que se filtra
     * @exception DaoException Sucede si falla la operacion
     */
    public Long getNumeroRegistrosDistintoId(ENTIDAD entity) throws DaoException;

    /**
     * Agrega un dato en el sistema.
     * @param dato Dato que se agregra en el sistema.
     * @return id del objeto insertado.
     * @throws DaoException Sucede si falla la operacion.
     */
    public abstract Long agregar(ENTIDAD dato) throws DaoException;

    /**
     * Actualiza el dato en el sistema.
     * @param dato Dato que se actualiza.
     * @exception DaoException Sucede si falla la operacion.
     */
    public abstract void actualizar(ENTIDAD dato) throws DaoException;

    /**
     * Eliminar un registro
     * @param dato
     * @throws DaoException
     */
    public abstract void eliminar(ENTIDAD dato) throws DaoException;
}
