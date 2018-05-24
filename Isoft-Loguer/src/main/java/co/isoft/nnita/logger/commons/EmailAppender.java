package co.isoft.nnita.logger.commons;

/**
 * Clase de configuracion de definiciones de listas
 * para el manejo de configuraciones para el envio de correos
 * para las aplicaciones
 * @author Yaher Carrillo
 * @Date 24/05/2018
 */
public class EmailAppender extends Appender {

    private String correoDestino;
    private String correoOrigen;
    private String puertoSMTP;
    private String asuntoCorreo;
    private String ubicacionEvento;
    private int cantidadEventosNoficar;
    private String hostServidorCorreo;
    private String usuario;
    private String clave;

    public String getCorreoDestino() {
        return correoDestino;
    }

    public void setCorreoDestino(String correoDestino) {
        this.correoDestino = correoDestino;
    }

    public String getCorreoOrigen() {
        return correoOrigen;
    }

    public void setCorreoOrigen(String correoOrigen) {
        this.correoOrigen = correoOrigen;
    }

    public String getPuertoSMTP() {
        return puertoSMTP;
    }

    public void setPuertoSMTP(String puertoSMTP) {
        this.puertoSMTP = puertoSMTP;
    }

    public String getAsuntoCorreo() {
        return asuntoCorreo;
    }

    public void setAsuntoCorreo(String asuntoCorreo) {
        this.asuntoCorreo = asuntoCorreo;
    }

    public String getUbicacionEvento() {
        return ubicacionEvento;
    }

    public void setUbicacionEvento(String ubicacionEvento) {
        this.ubicacionEvento = ubicacionEvento;
    }

    public int getCantidadEventosNoficar() {
        return cantidadEventosNoficar;
    }

    public void setCantidadEventosNoficar(int cantidadEventosNoficar) {
        this.cantidadEventosNoficar = cantidadEventosNoficar;
    }

    public String getHostServidorCorreo() {
        return hostServidorCorreo;
    }

    public void setHostServidorCorreo(String hostServidorCorreo) {
        this.hostServidorCorreo = hostServidorCorreo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}
