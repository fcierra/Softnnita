package co.isoft.nnita.profile.api.gateways;

/**
 * Excepcion generica aplicada a la evaluacion
 * de parametros de entrada y es lanzada si no se
 * cumplen con los parametros.
 * @author Yaher Carrillo
 * @date 09/09/2018
 */
public class ParamsException extends Exception
{
    /**
     * Version de la clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * Codigo generado de la excepcion
     */
    private String code;

    /**
     * Descrpcion de la excepcion
     */
    private String descripcion;


    /**
     * Crea la excepcion.
     */
    public ParamsException() {
        super();
    }

    /**
     * @param mensaje
     * @param descripcion
     */
    public ParamsException(String mensaje, String code, String descripcion) {
        super(mensaje);
        this.code = code;
        this.descripcion = descripcion;
    }

    /**
     * Crea la excepcion.
     *
     * @param mensaje Mensaje informador sobre la excepcion.
     */
    public ParamsException(String mensaje) {
        super(mensaje);
    }

    /**
     * Crea la excepcion.
     *
     * @param mensaje Mensaje informando sobre la excepcion.
     * @param causa   Causa de la excepcion.
     */
    public ParamsException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    /**
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene Codigo de la excepcion
     * @return
     */
    public String getCode()
    {
        return code;
    }

    /**
     * Asigna un valor al codigo de la excepcion
     * @param code valor a asignar
     */
    public void setCode(String code)
    {
        this.code = code;
    }
}
