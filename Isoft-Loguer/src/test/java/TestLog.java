import co.isoft.nnita.logger.commons.Appender;
import co.isoft.nnita.logger.commons.ConsoleAppender;
import co.isoft.nnita.logger.commons.EmailAppender;
import co.isoft.nnita.logger.commons.FileAppender;
import co.isoft.nnita.logger.util.Log;
import co.isoft.nnita.logger.util.ModulesIsoft;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Ignore;
import org.junit.Test;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.util.*;

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

    @Test
    @Ignore
    public void enviarCorreo(){

        Properties props = loadConfigLog("settings.properties");



        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("yahercarrillo@gmail.com", "CLAVE");
                    }
                });


        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress("yahercarrillo@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("yahercarrillo@gmail.com"));
            message.setSubject("$SOFT INFORMATICA");

            BodyPart body = new MimeBodyPart();

            String workingDir = System.getProperty("user.dir");


            // freemarker stuff.
            Configuration cfg = new Configuration();
            String url2 = workingDir+"\\src\\test\\java\\conf\\";
            try {
                cfg.setDirectoryForTemplateLoading(new File(url2));
            } catch (IOException e) {
                e.printStackTrace();
            }


            Template template = null;
            try {
                template = cfg.getTemplate("template_app.ftl");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, String> rootMap = new HashMap<String, String>();

            rootMap.put("correousuario", "correo");
            rootMap.put("mensajeUsuario", "mensaje");

            Writer out = new StringWriter();
            try {
                template.process(rootMap, out);
            } catch (TemplateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // freemarker stuff ends.

            /* you can add html tags in your text to decorate it. */
            body.setContent(out.toString(), "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(body);

            body = new MimeBodyPart();

            String url3 = workingDir+"\\src\\test\\java\\conf\\text.txt";
            String filename = "text.txt";
            DataSource source = new FileDataSource(url3);
            body.setDataHandler(new DataHandler(source));
            body.setFileName(filename);
            multipart.addBodyPart(body);

            message.setContent(multipart);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        System.out.println("Done....");
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
        ap.setUsuario("yaher");
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
