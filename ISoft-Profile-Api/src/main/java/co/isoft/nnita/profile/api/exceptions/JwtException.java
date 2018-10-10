package co.isoft.nnita.profile.api.exceptions;

/**
 * Excepcion lanzada en caso de fallar una operacion de crud.
 * @author Alejandro Vivas
 * @version 1.0 19/01/2009
 * @date 19/01/2009
 */
public class JwtException extends Exception
{
    /** Version de la clase */
    private static final long serialVersionUID = 1L;

    private String description;
    /**
     * Codigo de Error
     */
    private String code;

    /**
     * Crea la excepcion.
     * @param mensaje Mensaje informador sobre la excepcion.
     */
    public JwtException(String mensaje) {
        super(mensaje);
    }

    public JwtException(String mensaje, String code) {
        super(mensaje);
        this.description = code;
    }

    /**
     * Crea la excepcion.
     * @param mensaje Mensaje informando sobre la excepcion.
     * @param causa Causa de la excepcion.
     */
    public JwtException(String mensaje, Throwable causa)
    {
        super(mensaje,causa);
    }

    public JwtException(String mensaje, Throwable causa,String code)
    {
        super(mensaje,causa);
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
