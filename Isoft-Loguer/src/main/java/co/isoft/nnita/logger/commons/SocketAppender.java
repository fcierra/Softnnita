package co.isoft.nnita.logger.commons;

/**
 * Clase de configuracion de los puertos de comunicacion
 * para la generacion de logs dentro de las aplicaciones.
 * @author Yaher Carrillo
 * @Date 24/05/2018
 */
public class SocketAppender extends Appender {

    int puertoHostReceptor;
    String ipHostReceptor;
    int tiempoReconexion;

    public int getPuertoHostReceptor() {
        return puertoHostReceptor;
    }

    public void setPuertoHostReceptor(int puertoHostReceptor) {
        this.puertoHostReceptor = puertoHostReceptor;
    }

    public String getIpHostReceptor() {
        return ipHostReceptor;
    }

    public void setIpHostReceptor(String ipHostreceptor) {
        this.ipHostReceptor = ipHostreceptor;
    }

    public int getTiempoReconexion() {
        return tiempoReconexion;
    }

    public void setTiempoReconexion(int tiempoReconexion) {
        this.tiempoReconexion = tiempoReconexion;
    }

}
