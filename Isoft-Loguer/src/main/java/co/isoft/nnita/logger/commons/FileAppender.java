package co.isoft.nnita.logger.commons;

/**
 * Clase de configuracion de definiciones de los archivos
 * logs a generar dentro de las aplicaciones.
 * @author Yaher Carrillo
 * @Date 24/05/2018
 */
public class FileAppender extends Appender{

    String rutaArchivo;
    boolean anexarTrazas;    
    int tamanhoMaximoArchivo;
    String formatotamanho;
    int cantidadMaximaArchivos;

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public boolean isAnexarTrazas() {
        return anexarTrazas;
    }

    public void setAnexarTrazas(boolean anexarTrazas) {
        this.anexarTrazas = anexarTrazas;
    }

    public int getTamanhoMaximoArchivo() {
        return tamanhoMaximoArchivo;
    }

    public void setTamanhoMaximoArchivo(int tamanhoMaximoArchivo) {
        this.tamanhoMaximoArchivo = tamanhoMaximoArchivo;
    }

    public int getCantidadMaximaArchivos() {
        return cantidadMaximaArchivos;
    }

    public void setCantidadMaximaArchivos(int cantidadMaximaArchivos) {
        this.cantidadMaximaArchivos = cantidadMaximaArchivos;
    }

    public String getFormatotamanho()
    {
        return formatotamanho;
    }

    public void setFormatotamanho(String formatotamanho)
    {
        this.formatotamanho = formatotamanho;
    }
}
