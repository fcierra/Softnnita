package co.isoft.nnita.logger.util;

/**
 * Enum que clasifica los modulos del sistema
 * y sirve para segmentar los logs correspondientes a visualizar en sistama.
 * @author Yaher Carrillo
 * @Date 24/05/2018
 */
public enum ModulesIsoft
{

    ISOFT_PROFILE("isfot.profile", "MODULE PROFILES USERS", "ISOFT_PROFILE\\isfot-profile"),
    ISOFT_PAYMENT_CONTROL("isfot.payment.control", "MODULE PAYMENT CONTROL", "ISOFT_PAYMENT_CONTROL\\isfot-payment-control");

    private String codigo;
    private String nombre;
    private String nombreArchivo;

    ModulesIsoft(String codigo, String nombre, String nombreArchivo)
    {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nombreArchivo = nombreArchivo;
    }

    public String getCodigo()
    {
        return codigo;
    }

    public String getNombreArchivo()
    {
        return nombreArchivo;
    }

    public static String getCodigoLogMonitor()
    {
        return "log.error.monitor";
    }
}
