package co.isoft.nnita.profile.api.exceptions;

/**
 * Excepcion lanzada en caso de fallar una operacion de crud.
 * @author Alejandro Vivas
 * @version 1.0 19/01/2009
 * @date 19/01/2009
 */
public class ServiceException extends Exception
{
    /** Version de la clase */
    private static final long serialVersionUID = 1L;

    private String descripcion;
    /**
     * Codigo de Error
     */
    private String codigo;

    /**
     * Crea la excepcion.
     * @param mensaje Mensaje informador sobre la excepcion.
     */
    public ServiceException(String mensaje) {
        super(mensaje);
    }

    public ServiceException(String mensaje, String descripcion) {
        super(mensaje);
        this.descripcion = descripcion;
    }

    public ServiceException(String mensaje, String descripcion,String codigo) {
        super(mensaje);
        this.descripcion = descripcion;
        this.codigo = codigo;
    }

    /**
     * Crea la excepcion.
     * @param mensaje Mensaje informando sobre la excepcion.
     * @param causa Causa de la excepcion.
     */
    public ServiceException(String mensaje, Throwable causa)
    {
        super(mensaje,causa);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
