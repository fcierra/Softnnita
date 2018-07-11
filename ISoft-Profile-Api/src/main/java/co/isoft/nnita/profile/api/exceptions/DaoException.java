package co.isoft.nnita.profile.api.exceptions;

/**
 * Excepcion lanzada en caso de fallar una operacion de un Dao.
 * @author Yaher Carrillo.
 * @Date 01/06/2018
 */
public class DaoException extends Exception
{
    /** Version de la clase */
    private static final long serialVersionUID = 1L;

    /**
     * Crea la excepcion.
     * @param mensaje Mensaje de la excepcion.
     */
    public DaoException(String mensaje)
    {
        super(mensaje);
    }

    /**
     * Crea la excepcion.
     * @param mensaje Mensaje
     * @param throwable Excepcion padre.
     */
    public DaoException(String mensaje, Throwable throwable)
    {
        super(mensaje,throwable);
    }
}
