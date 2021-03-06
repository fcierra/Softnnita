package co.isoft.nnita.profile.api.modelsweb;

import co.isoft.nnita.profile.api.util.EnumTypesLicense;

import java.util.Date;

/**
 * Objeto que contendra los datos
 * de creacion y soporte de linceciamiento
 * de la aplicacion.
 *
 * @author Yaher Carrillo
 */
public class DatosLicencia
{
    /**
     * Cliente licenciado
     */
    private String clienteISoft;
    /**
     * Canal de transaccion
     */
    private int canal;
    /**
     * Fecha de inicio de uso hacia la aplicacion
     */
    private Date fechaInicio;
    /**
     * Fecha de fin de uso hacia la aplicacion
     */
    private Date fechaFin;
    /**
     * Ip de uso y acceso
     */
    private String ip;
    /**
     * Cantidad de usuarios concurrentes
     */
    private Long ususariosConcurrentes;
    /**
     * Tipo Licensia
     */
    private EnumTypesLicense tipoLicencia;

    /**
     * Obtiene el cliente asociado a isoft.
     *
     * @return
     */
    public String getClienteISoft()
    {
        return clienteISoft;
    }

    /**
     * Asigna un valor al cliente isoft.
     *
     * @param clienteISoft
     */
    public void setClienteISoft(String clienteISoft)
    {
        this.clienteISoft = clienteISoft;
    }

    /**
     * Fecha de inicio de la vigencia de la licencia.
     *
     * @return
     */
    public Date getFechaInicio()
    {
        return fechaInicio;
    }

    /**
     * Asigna un valor a la fecha de inicio de vigencia.
     *
     * @param fechaInicio fecha de inicio
     */
    public void setFechaInicio(Date fechaInicio)
    {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Ontiene un valor a la fecha fin de vigencia de la
     * licencia.
     *
     * @return valor a asignar
     */
    public Date getFechaFin()
    {
        return fechaFin;
    }

    /**
     * Asigna un valor a la fecga fin de vigencia de la
     * licencia.
     *
     * @param fechaFin
     */
    public void setFechaFin(Date fechaFin)
    {
        this.fechaFin = fechaFin;
    }

    /**
     * Obtiene la ip de accceso de la aplicacion.
     *
     * @return
     */
    public String getIp()
    {
        return ip;
    }

    /**
     * Asigna un valor a la ip de acceso del
     * licenciamiento.
     *
     * @param ip
     */
    public void setIp(String ip)
    {
        this.ip = ip;
    }

    /**
     * Obtiene el tipo de licencia asignada
     *
     * @return tipo de licencia
     */
    public EnumTypesLicense getTipoLicencia()
    {
        return tipoLicencia;
    }

    /**
     * Asigna un valor al tipo de licencia a crear
     *
     * @param tipoLicencia valor a asignar.
     */
    public void setTipoLicencia(EnumTypesLicense tipoLicencia)
    {
        this.tipoLicencia = tipoLicencia;
    }

    /**
     * Obtiene el canal de la transaccion
     *
     * @return canal de la transaccion
     */
    public int getCanal()
    {
        return canal;
    }

    /**
     * Asigna un valor al canal de la transaccion
     *
     * @param canal valor a asignar.
     */
    public void setCanal(int canal)
    {
        this.canal = canal;
    }

    /**
     * Obtiene la cantidad de usuarios concurrentes
     * permitidos por la licencia
     *
     * @return cantidad de usuarios
     */
    public Long getUsusariosConcurrentes()
    {
        return ususariosConcurrentes;
    }

    /**
     * Asigna una cantidad de usuarios concurrentes a
     * permitir por la licencia
     *
     * @param ususariosConcurrentes Cantidad de usuarios concurrentes
     */
    public void setUsusariosConcurrentes(Long ususariosConcurrentes)
    {
        this.ususariosConcurrentes = ususariosConcurrentes;
    }

    @Override
    public String toString()
    {
        return "Datos [cliente=" + clienteISoft + ", " + "Fecha Inicio=" + fechaInicio + ", " + "Fecha Fin=" + fechaFin + ", " + "IP=" + ip + " , Usuarios" + ususariosConcurrentes + " , Tipo Licencia: " + tipoLicencia.getDescription() + " ]";
    }
}
