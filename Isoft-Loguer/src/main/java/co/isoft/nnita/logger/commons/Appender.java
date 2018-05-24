package co.isoft.nnita.logger.commons;

/**
 * Clase de configuracion de definiciones de listas de agregado
 * para las aplicaciones
 * @author Yaher Carrillo
 * @Date 24/05/2018
 */
public abstract class Appender {

    private int nivelMaximo;
    private boolean registroInmediato;
    private String codigo;

    public int getNivelMaximo() {
        return nivelMaximo;
    }

    public void setNivelMaximo(int nivelMaximo) {
        this.nivelMaximo = nivelMaximo;
    }

    public boolean isRegistroInmediato() {
        return registroInmediato;
    }

    public void setRegistroInmediato(boolean registroInmediato) {
        this.registroInmediato = registroInmediato;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
