import co.isoft.nnita.logger.commons.Appender;
import co.isoft.nnita.logger.commons.ConsoleAppender;
import co.isoft.nnita.logger.commons.EmailAppender;
import co.isoft.nnita.logger.commons.FileAppender;
import co.isoft.nnita.logger.util.Log;
import co.isoft.nnita.logger.util.ModulesIsoft;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Clase para probar la configuracion de LOG sel sistema
 *
 * @author Yaher Carrillo
 * @Date 24/05/2018
 */
public class TestLog
{

    /**
     * Carga la configuracion de log que se indique
     *
     * @param nameConfig configuracion a cargar
     */
    public Properties loadConfigLog(String nameConfig)
    {
        Properties prop = new Properties();
        OutputStream output = null;
        try
        {
            InputStream is = ClassLoader.getSystemResourceAsStream("conf\\" + nameConfig);
            try
            {
                prop.load(is);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return prop;
    }

    /**
     * Generar los logs de ERROR, segun la cantidad de grupos que maneja
     * la aplicacion. Se debe especificar la ruta de generacion
     * ademas de esto se hace el consumo del archivo de propiedades
     * segun una ruta especificada
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void shouldGenerateLogsError() throws Exception
    {
        try
        {
            Properties props = loadConfigLog("log4jError.properties");
            //Lista de configuracion
            List<Appender> listaAppenders = new ArrayList<>();

            //Configuracion de consola
            ConsoleAppender appender = new ConsoleAppender();
            appender.setCodigo("afiliacion".toUpperCase());
            listaAppenders.add(appender);

            //Configuracion de Archivos Log para Archivos
            List<Appender> listaAppendersFiles = readModulesSystemForGenerateObjectsToFile(props);
            listaAppenders.addAll(listaAppendersFiles);

            //Cofiguracion de log via Email
            EmailAppender ap = readModulesSystemForGenerateObjectsToEmail(props);
            listaAppenders.add(ap);

            //CONFIGURACION DE FORMATOS DEL LOG
            //Log.getInstance().configure(listaAppenders, "%-5p %d %m%n");
            Log.getInstance().configure(listaAppenders, props.getProperty("log.softnnita.format"));
            Log.getInstance().setNivelActivo(Integer.parseInt(props.getProperty("log.softnnita.active.level")));

            //Evaluar activaciones en modo desarrollador
            boolean isDebug = props.getProperty("log.softnnita.active.debug") != null && props.getProperty("log.softnnita.active.debug").equals("true") ? true : false;
            boolean isMonitor = props.getProperty("log.softnnita.active.debug") != null && props.getProperty("log.softnnita.active.debug").equals("true") ? true : false;
            if (isDebug)
                Log.getInstance().activarDebug();
            if (isMonitor)
                Log.getInstance().activarSalidaConsola();
            putLogsAllLevels();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Generar los logs de TODOS los elementos, segun la cantidad de grupos que maneja
     * la aplicacion. Se debe especificar la ruta de generacion
     * ademas de esto se hace el consumo del archivo de propiedades
     * segun una ruta especificada
     *
     * @throws Exception
     */
    @Test
    public void shouldGenerateLogsAll() throws Exception
    {
        try
        {
            Properties props = loadConfigLog("log4j.properties");
            //Lista de configuracion
            List<Appender> listaAppenders = new ArrayList<>();

            //Configuracion de consola
            ConsoleAppender appender = new ConsoleAppender();
            appender.setCodigo("afiliacion".toUpperCase());
            listaAppenders.add(appender);

            //Configuracion de Archivos Log para Archivos
            List<Appender> listaAppendersFiles = readModulesSystemForGenerateObjectsToFile(props);
            listaAppenders.addAll(listaAppendersFiles);

            //Cofiguracion de log via Email
            EmailAppender ap = readModulesSystemForGenerateObjectsToEmail(props);
            listaAppenders.add(ap);

            //CONFIGURACION DE FORMATOS DEL LOG
            //Log.getInstance().configure(listaAppenders, "%-5p %d %m%n");
            Log.getInstance().configure(listaAppenders, props.getProperty("log.softnnita.format"));
            Log.getInstance().setNivelActivo(Integer.parseInt(props.getProperty("log.softnnita.active.level")));

            //Evaluar activaciones en modo desarrollador
            boolean isDebug = props.getProperty("log.softnnita.active.debug") != null && props.getProperty("log.softnnita.active.debug").equals("true") ? true : false;
            boolean isMonitor = props.getProperty("log.softnnita.active.monitor") != null && props.getProperty("log.softnnita.active.debug").equals("true") ? true : false;
            Log.getInstance().activarSalidaConsola();
            if (isDebug)
                Log.getInstance().activarDebug();
            if (isMonitor)
                Log.getInstance().activarMonitoreo();

            putLogsAllLevels();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que lee los modulos de la aplicacion y genera
     * los appenders para la configuracion de log
     *
     * @param props
     */
    public List<Appender> readModulesSystemForGenerateObjectsToFile(Properties props)
    {
        List<Appender> listaAppenders = new ArrayList<>();
        for (ModulesIsoft log : ModulesIsoft.values())
        {
            FileAppender fa = new FileAppender();
            fa.setCodigo(log.getCodigo());
            fa.setRutaArchivo(props.getProperty("log.softnnita.src.files") + log.getNombreArchivo());
            fa.setTamanhoMaximoArchivo(Integer.parseInt(props.getProperty("log.softnnita.size.max.files")));
            fa.setFormatotamanho(props.getProperty("log.softnnita.size.format.files"));
            fa.setCantidadMaximaArchivos(Integer.parseInt(props.getProperty("log.softnnita.count.max.files")));
            listaAppenders.add(fa);
        }
        return listaAppenders;
    }

    /**
     * Metodo que lee los modulos de la aplicacion y genera
     * los appenders para la configuracion de log via correo
     *
     * @param props
     */
    public EmailAppender readModulesSystemForGenerateObjectsToEmail(Properties props)
    {
        EmailAppender ap = new EmailAppender();
        ap.setAsuntoCorreo(props.getProperty("log.softnnita.email.affair"));
        ap.setCantidadEventosNoficar(Integer.parseInt(props.getProperty("log.softnnita.email.bufferSize")));
        ap.setClave(props.getProperty("log.softnnita.email.pass"));
        ap.setCorreoOrigen(props.getProperty("log.softnnita.email.origin"));
        ap.setCorreoDestino(props.getProperty("log.softnnita.email.destination"));
        ap.setUsuario("");
        ap.setHostServidorCorreo(props.getProperty("log.softnnita.email.host"));
        ap.setCodigo(props.getProperty("log.softnnita.email.code"));
        ap.setPuertoSMTP(props.getProperty("log.softnnita.email.port.smtp"));

        return ap;
    }

    /**
     * Metodo que escribe todos los niveles segun modulo
     * de las aplicaciones.
     */
    public void putLogsAllLevels()
    {
        for (int x = 0; x < 10; x++)
        {
            Log.getInstance().trace(ModulesIsoft.ISOFT_PAYMENT_CONTROL.getCodigo(), "Yaher", "Este es un mensaje de prueba a consola");
            Log.getInstance().debug(ModulesIsoft.ISOFT_PAYMENT_CONTROL.getCodigo(), "Yaher", "Este es un mensaje de prueba a archivo");
            Log.getInstance().info(ModulesIsoft.ISOFT_PAYMENT_CONTROL.getCodigo(), "Yaher", "Este es un mensaje de prueba a archivo");
            Log.getInstance().warn(ModulesIsoft.ISOFT_PAYMENT_CONTROL.getCodigo(), "Yaher", "Este es un mensaje de prueba a email");
            Log.getInstance().error(ModulesIsoft.ISOFT_PAYMENT_CONTROL.getCodigo(), "Yaher", "Este es un mensaje de prueba a email", new Exception());
            Log.getInstance().monitor(ModulesIsoft.ISOFT_PAYMENT_CONTROL.getCodigo(), "Yaher", "Este es un mensaje de prueba a email", new Exception());

            Log.getInstance().trace(ModulesIsoft.ISOFT_PROFILE.getCodigo(), "Yaher", "Este es un mensaje de prueba a consola");
            Log.getInstance().debug(ModulesIsoft.ISOFT_PROFILE.getCodigo(), "Yaher", "Este es un mensaje de prueba a archivo");
            Log.getInstance().info(ModulesIsoft.ISOFT_PROFILE.getCodigo(), "Yaher", "Este es un mensaje de prueba a archivo");
            Log.getInstance().warn(ModulesIsoft.ISOFT_PROFILE.getCodigo(), "Yaher", "Este es un mensaje de prueba a email");
            Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), "Yaher", "Este es un mensaje de prueba a email", new Exception());
            Log.getInstance().monitor(ModulesIsoft.ISOFT_PROFILE.getCodigo(), "Yaher", "Este es un mensaje de prueba a email", new Exception());
        }
    }
}
