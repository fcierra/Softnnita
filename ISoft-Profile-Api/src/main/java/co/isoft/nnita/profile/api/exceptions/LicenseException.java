package co.isoft.nnita.profile.api.exceptions;

/**
 * Excepcion generica aplicada a la evaluacion
 * de las licencias del sistema.
 * @author Yaher Carrillo
 * @date 16/10/2018
 */
public class LicenseException extends Exception
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
    public LicenseException() {
        super();
    }

    /**
     * @param mensaje
     * @param descripcion
     */
    public LicenseException(String mensaje, String code, String descripcion) {
        super(mensaje);
        this.code = code;
        this.descripcion = descripcion;
    }

    /**
     * Crea la excepcion.
     *
     * @param mensaje Mensaje informador sobre la excepcion.
     */
    public LicenseException(String mensaje) {
        super(mensaje);
    }

    /**
     * Crea la excepcion.
     *
     * @param mensaje Mensaje informando sobre la excepcion.
     * @param causa   Causa de la excepcion.
     */
    public LicenseException(String mensaje, Throwable causa) {
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
