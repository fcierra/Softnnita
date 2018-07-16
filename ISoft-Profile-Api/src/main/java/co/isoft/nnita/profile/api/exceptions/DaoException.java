package co.isoft.nnita.profile.api.exceptions;

/**
 * Excepcion lanzada en caso de fallar una operacion de un Dao.
 *
 * @author Yaher Carrillo.
 * @Date 01/06/2018
 */
public class DaoException extends Exception
{
    /**
     * Version de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * Codigo de Error
     */
    private String code;

    /**
     * Crea la excepcion.
     *
     * @param mensaje Mensaje de la excepcion.
     */
    public DaoException(String mensaje)
    {
        super(mensaje);
        code = mensaje;
    }

    /**
     * Excepcion que ejemplifica el uso a traves de un codigo y descripcion del error
     *
     * @param mensaje mensaje generico
     * @param code    codigo de error
     */
    public DaoException(String mensaje, String code)
    {
        super(mensaje);
        this.code = code;
    }

    /**
     * Excepcion que ejemplifica el uso a traves de un codigo y descripcion del error
     *
     * @param mensaje     mensaje generico
     * @param code        codigo de error
     * @param description
     */
    public DaoException(String mensaje, String code, String description)
    {
        super(mensaje);
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    /**
     * Crea la excepcion.
     *
     * @param mensaje   Mensaje
     * @param throwable Excepcion padre.
     */
    public DaoException(String mensaje, Throwable throwable)
    {
        super(mensaje, throwable);
    }
}
