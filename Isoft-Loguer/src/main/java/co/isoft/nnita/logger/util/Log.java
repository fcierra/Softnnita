package co.isoft.nnita.logger.util;
import co.isoft.nnita.logger.commons.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.net.SMTPAppender;
import org.apache.log4j.varia.LevelRangeFilter;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Clase de configuracion de log dinamico
 * para las aplicaciones
 *
 * @author Yaher Carrillo
 * @Date 24/05/2018
 */
public class Log
{

    private final int LEVEL_TRACE = 1;
    private final int LEVEL_DEBUG = 2;
    private final int LEVEL_INFO = 3;
    private final int LEVEL_WARN = 4;
    private final int LEVEL_ERROR = 5;
    private static Log instance = null;
    private boolean debugActivado = false;
    private boolean monitoreoActivo = false;
    private boolean redireccionarConsola = false;
    private LinkedHashMap<String, Logger> mapaLogs;
    private int nivelActivo = 0;
    public static final String CONSOLA_INTERNA = "consolaInternaLog";

    /**
     * Constructor de la clase
     *
     * @return
     */
    public static Log getInstance()
    {
        if (null == instance)
        {
            instance = new Log();
        }
        return instance;
    }

    /**
     * Metodo que establece la configuracion por defecto del sistema
     * que hace uso del log.
     *
     * @param appenders appenders disponibles a mostrar
     * @param patron    patron a escribir en archivos o consola.
     * @throws Exception Excepcion generica.
     */
    public void configure(List<Appender> appenders, String patron) throws Exception
    {
        try
        {
            PatternLayout layout = new PatternLayout(patron);
            mapaLogs = new LinkedHashMap<>();
            //Se configura el log console para errores de la app.
            org.apache.log4j.ConsoleAppender appenderConfiguracion = new org.apache.log4j.ConsoleAppender(layout);
            appenderConfiguracion.setName(CONSOLA_INTERNA);
            appenderConfiguracion.activateOptions();
            org.apache.log4j.Logger.getLogger(CONSOLA_INTERNA).addAppender(appenderConfiguracion);
            mapaLogs.put(CONSOLA_INTERNA, org.apache.log4j.Logger.getLogger(CONSOLA_INTERNA));
            for (Appender appender : appenders)
            {
                if (appender instanceof ConsoleAppender)
                {
                    org.apache.log4j.ConsoleAppender appenderConsola = new org.apache.log4j.ConsoleAppender(layout);
                    appenderConsola.setName(((ConsoleAppender) appender).getCodigo());
                    appenderConsola.activateOptions();
                    org.apache.log4j.Logger.getLogger(((ConsoleAppender) appender).getCodigo()).setLevel(Level.ALL);
                    org.apache.log4j.Logger.getLogger(((ConsoleAppender) appender).getCodigo()).addAppender(appenderConsola);
                }
                else if (appender instanceof EmailAppender)
                {
                    try
                    {
                        SMTPAppender appenderEmail = new SMTPAppender();
                        appenderEmail.setLayout(layout);
                        appenderEmail.setName(((EmailAppender) appender).getCodigo());
                        appenderEmail.setFrom(((EmailAppender) appender).getCorreoOrigen());
                        appenderEmail.setTo(((EmailAppender) appender).getCorreoDestino());
                        appenderEmail.setSubject(((EmailAppender) appender).getAsuntoCorreo());
                        appenderEmail.setBufferSize(((EmailAppender) appender).getCantidadEventosNoficar());
                        appenderEmail.setSMTPPort(Integer.parseInt(((EmailAppender) appender).getPuertoSMTP()));
                        appenderEmail.setSMTPHost(((EmailAppender) appender).getHostServidorCorreo());

                        //Solo se enviaran notificaciones si el nivel del error es FATAL
                        LevelRangeFilter filter = new LevelRangeFilter();
                        filter.setLevelMin(Level.FATAL);
                        filter.setLevelMax(Level.FATAL);
                        appenderEmail.addFilter(filter);
                        appenderEmail.setSMTPDebug(false);

                        appenderEmail.activateOptions();
                        org.apache.log4j.Logger.getLogger(((EmailAppender) appender).getCodigo()).addAppender(appenderEmail);
                    }
                    catch (Exception e)
                    {
                        Logger.getLogger("configuracionLog").error("Error configurando appender SMTP", e);
                    }
                }
                else if (appender instanceof FileAppender)
                {
                    RollingFileAppender fileAppender = new RollingFileAppender(layout, ((FileAppender) appender).getRutaArchivo());
                    fileAppender.setMaxFileSize(String.valueOf(((FileAppender) appender).getTamanhoMaximoArchivo()) + ((FileAppender) appender).getFormatotamanho());
                    fileAppender.setMaxBackupIndex(((FileAppender) appender).getCantidadMaximaArchivos());
                    fileAppender.activateOptions();
                    org.apache.log4j.Logger.getLogger(((FileAppender) appender).getCodigo()).setLevel(Level.ALL);
                    org.apache.log4j.Logger.getLogger(((FileAppender) appender).getCodigo()).addAppender(fileAppender);
                }
                else if (appender instanceof SocketAppender)
                {
                    Logger.getLogger("consolaInternaLog").info("Appender SocketAppender no immplementado");
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }

    }

    /**
     * Metodo Trace que imprime en log
     *
     * @param categoria     Categoria a plasmar
     * @param identificador Identificador de usuario o sesion a plasmar en el log
     * @param mensaje       Mensaje a mostrar en el log
     */
    public void trace(String categoria, String identificador, String mensaje)
    {
        if (nivelActivo <= LEVEL_TRACE)
        {
            StringBuilder contenidoMensaje = new StringBuilder("[").append(identificador.toUpperCase()).append("] ").append(mensaje);
            if (null == mapaLogs.get(categoria))
            {
                mapaLogs.put(categoria, Logger.getLogger(categoria));
            }
            mapaLogs.get(categoria).trace(contenidoMensaje);
            if (redireccionarConsola)
            {
                mapaLogs.get("consolaInternaLog").trace(contenidoMensaje);
            }
        }
    }

    /**
     * Metodo de impresion de logs DEBUG que imprime en log
     *
     * @param categoria     Categoria a plasmar
     * @param identificador Identificador de usuario o sesion a plasmar en el log
     * @param mensaje       Mensaje a mostrar en el log
     */
    public void debug(String categoria, String identificador, String mensaje)
    {
        if (debugActivado && nivelActivo <= LEVEL_DEBUG)
        {
            StringBuilder contenidoMensaje = new StringBuilder("[").append(identificador.toUpperCase()).append("] ").append(mensaje);
            if (null == mapaLogs.get(categoria))
            {
                mapaLogs.put(categoria, Logger.getLogger(categoria));
            }
            mapaLogs.get(categoria).debug(contenidoMensaje);
            if (redireccionarConsola)
            {
                mapaLogs.get("consolaInternaLog").debug(contenidoMensaje);
            }
        }
    }

    /**
     * Metodo de impresion de logs INFO que imprime en log
     *
     * @param categoria     Categoria a plasmar
     * @param identificador Identificador de usuario o sesion a plasmar en el log
     * @param mensaje       Mensaje a mostrar en el log
     */
    public void info(String categoria, String identificador, String mensaje)
    {
        if (nivelActivo <= LEVEL_INFO)
        {
            StringBuilder contenidoMensaje = new StringBuilder("[").append(identificador.toUpperCase()).append("] ").append(mensaje);
            if (null == mapaLogs.get(categoria))
            {
                mapaLogs.put(categoria, Logger.getLogger(categoria));
            }
            mapaLogs.get(categoria).info(contenidoMensaje);
            if (redireccionarConsola)
            {
                mapaLogs.get("consolaInternaLog").info(contenidoMensaje);
            }
        }
    }

    /**
     * Metodo de impresion de logs WARN que imprime en log
     *
     * @param categoria     Categoria a plasmar
     * @param identificador Identificador de usuario o sesion a plasmar en el log
     * @param mensaje       Mensaje a mostrar en el log
     */
    public void warn(String categoria, String identificador, String mensaje)
    {
        if (nivelActivo <= LEVEL_WARN)
        {
            StringBuilder contenidoMensaje = new StringBuilder("[").append(identificador.toUpperCase()).append("] ").append(mensaje);
            if (null == mapaLogs.get(categoria))
            {
                mapaLogs.put(categoria, Logger.getLogger(categoria));
            }
            mapaLogs.get(categoria).warn(contenidoMensaje);
            if (redireccionarConsola)
            {
                mapaLogs.get("consolaInternaLog").warn(contenidoMensaje);
            }
        }
    }

    /**
     * Metodo de impresion de logs WARN que imprime en log
     *
     * @param categoria     Categoria a plasmar
     * @param identificador Identificador de usuario o sesion a plasmar en el log
     * @param mensaje       Mensaje a mostrar en el log
     * @param t             Excepcion a mostrar de error de logica.
     */
    public void warn(String categoria, String identificador, String mensaje, Throwable t)
    {
        if (nivelActivo <= LEVEL_WARN)
        {
            StringBuilder contenidoMensaje = new StringBuilder("[").append(identificador.toUpperCase()).append("] ").append(mensaje);
            if (null == mapaLogs.get(categoria))
            {
                mapaLogs.put(categoria, Logger.getLogger(categoria));
            }
            mapaLogs.get(categoria).warn(contenidoMensaje, t);
            if (redireccionarConsola)
            {
                mapaLogs.get("consolaInternaLog").warn(contenidoMensaje, t);
            }
        }
    }

    /**
     * Metodo de impresion de logs ERROR que imprime en log
     *
     * @param categoria     Categoria a plasmar
     * @param identificador Identificador de usuario o sesion a plasmar en el log
     * @param mensaje       Mensaje a mostrar en el log
     */

    public void error(String categoria, String identificador, String mensaje)
    {
        if (nivelActivo <= LEVEL_ERROR)
        {
            StringBuilder contenidoMensaje = new StringBuilder("[").append(identificador.toUpperCase()).append("] ").append(mensaje);
            if (null == mapaLogs.get(categoria))
            {
                mapaLogs.put(categoria, Logger.getLogger(categoria));
            }
            mapaLogs.get(categoria).error(contenidoMensaje);
            if (redireccionarConsola)
            {
                mapaLogs.get("consolaInternaLog").error(contenidoMensaje);
            }
        }
    }

    /**
     * Metodo de impresion de logs WARN que imprime en log
     *
     * @param categoria     Categoria a plasmar
     * @param identificador Identificador de usuario o sesion a plasmar en el log
     * @param mensaje       Mensaje a mostrar en el log
     * @param t             Excepcion a mostrar de error de logica.
     */
    public void error(String categoria, String identificador, String mensaje, Throwable t)
    {
        if (nivelActivo <= LEVEL_ERROR)
        {
            StringBuilder contenidoMensaje = new StringBuilder("[").append(identificador.toUpperCase()).append("] ").append(mensaje);
            if (null == mapaLogs.get(categoria))
            {
                mapaLogs.put(categoria, Logger.getLogger(categoria));
            }
            mapaLogs.get(categoria).error(contenidoMensaje, t);
            if (redireccionarConsola)
            {
                mapaLogs.get("consolaInternaLog").error(contenidoMensaje, t);
            }
        }
    }

    /**
     * Metodo de impresion de logs a traves del MONITOR que imprime en log
     *
     * @param categoria     Categoria a plasmar
     * @param identificador Identificador de usuario o sesion a plasmar en el log
     * @param mensaje       Mensaje a mostrar en el log
     * @param t             Excepcion a mostrar de error de logica.
     */
    public void monitor(String categoria, String identificador, String mensaje, Throwable t)
    {
        if (monitoreoActivo)
        {
            StringBuilder contenidoMensaje = new StringBuilder("[").append(identificador.toUpperCase()).append("] ").append(mensaje);
            if (null == mapaLogs.get(categoria))
            {
                mapaLogs.put(categoria, Logger.getLogger(categoria));
            }
            mapaLogs.get(categoria).fatal(contenidoMensaje, t);
            if (redireccionarConsola)
            {
                mapaLogs.get("consolaInternaLog").fatal(contenidoMensaje, t);
            }
        }
    }

    public void activarDebug()
    {
        this.debugActivado = true;
    }

    public void desactivarDebug()
    {
        this.debugActivado = false;
    }

    public void activarMonitoreo()
    {
        this.monitoreoActivo = true;
    }

    public void desactivarMonitoreo()
    {
        this.monitoreoActivo = false;
    }

    public void activarSalidaConsola()
    {
        this.redireccionarConsola = true;
    }

    public void desactivarSalidaConsola()
    {
        this.redireccionarConsola = false;
    }

    public int getNivelActivo()
    {
        return nivelActivo;
    }

    public void setNivelActivo(int nivelActivo)
    {
        this.nivelActivo = nivelActivo;
    }
}
