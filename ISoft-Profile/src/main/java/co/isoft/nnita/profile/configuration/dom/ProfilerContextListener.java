package co.isoft.nnita.profile.configuration.dom;

import co.isoft.nnita.profile.api.util.GatewayBaseBean;
import co.isoft.nnita.profile.impl.dao.impl.JwtImpl;
import co.isoft.nnita.profile.util.ServletJsfSpringUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Clase de configuracion de
 * subida de spring, en la misma se setean valores de inicio.
 * @author Yaher Carrillo
 * @Date 09/07/2018
 */
public class ProfilerContextListener implements ServletContextListener
{
    /**
     * Identificador unico de clase
     */
    private static final long serialVersionUID = 1L;

    /**
     * Llaves y propiedades de acceso
     */
    private Properties log4jProperties;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Define de forma global el contexto
        ServletJsfSpringUtil.setServletContext(sce.getServletContext());
        GatewayBaseBean.setJwtDao(new JwtImpl());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //mute
    }

    /**
     * Inicializa las configuraciones de log
     */


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
            //InputStream is = ClassLoader.getSystemResourceAsStream(nameConfig);
            InputStream is = getClass().getClassLoader().getResourceAsStream(nameConfig);
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

}
