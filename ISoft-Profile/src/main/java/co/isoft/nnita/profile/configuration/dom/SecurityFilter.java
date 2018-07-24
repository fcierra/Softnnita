/*
 * 
 * ESTE COMPONENTE FUE REALIZADO BAJO LA METODOLOGIA DE DESARROLLO DE 
 * INFORMATICA & TECNOLOGIA Y SE ENCUENTRA PROTEGIDO POR LAS LEYES DE 
 * DERECHOS DE AUTOR.
 */
package co.isoft.nnita.profile.configuration.dom;

import co.isoft.nnita.profile.api.modelsweb.DatosSesionUsuario;
import co.isoft.nnita.profile.configuration.navigation.EnumNavigationConfig;
import co.isoft.nnita.profile.util.ISoftProfilerBaseBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Esta clase filtro maneja la seguridad de la aplicacion. Debe ser configurada dentro de web.xml.
 * @author Yaher Carrillo
 * @date 14/07/2018
 */
public class SecurityFilter implements Filter
{
    /** Objeto utilizado para el log */
    private static final Log logger = LogFactory.getLog(SecurityFilter.class);
    /** Almacena la ruta a la pagina de error */
    private String errorPage;
    /** Almacena la ruta a la pagina de inicio de sesion */
    private String loginPage;
    /** Almacena la ruta a la pagina de la ventanilla */
    private String ventanillaPage;
    /** Almacena la ruta a la pagina de bienvenida */
    private String welcomePage;
    /** Pagina de inicio de la aplicacion */
    private String inicioPage;
    /** Nombre del bean que indica que el inicio fue exitoso */
    private String nombreLoginActivo;

    /**
     * Initializes the Filter.
     */
    public void init(FilterConfig filterConfig) throws ServletException
    {
        // Obtener las paginas que redirecciona el filtro.

    }

    /**
     * Metodo que filtra las peticiones a la aplicacion. Realiza las siguientes tareas: 1. Si no hay usuario, redirecciona a la pagina de inicio. 2. Si hay un error al procesa una
     * peticion enviar a la pagina de error. 3. En caso de que exista un sesion no hacer nada.
     * @param req
     * @param res
     * @param chain
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
    {        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        String url = request.getServletPath();
        logger.debug("url=" + url );
        try
        {

            if (session == null)
            {
                logger.debug("La sesi\u00F3n ha caducado");
                request.getRequestDispatcher(EnumNavigationConfig.LOGIN_PAGE.getPath()).forward(req, res);
                return;
            }   
            
            // Si el usuario ya ha iniciado sesion no mostrar la pagina de inicio de
            // sesion si no enviar a la pagina de bienvenida.
            ISesionActive datosSesionUsuario = (ISesionActive)request.getSession().getAttribute(ISoftProfilerBaseBean.CONSTANT_USER_SESION);
            if( (datosSesionUsuario != null)  )
            {
                if (url.equals(EnumNavigationConfig.LOGIN_PAGE.getPath()) )
                {
                    // Redireccionar a la pagina de bienvenida del usuario
                    request.getRequestDispatcher(EnumNavigationConfig.WELCOME_PAGE.getPath()).forward(req, res);
                    return;
                }
            }
            chain.doFilter(req, res);
        }
        catch (Exception e)
        {
            // Enviar a la pagina de error.
            logger.error("Error al procesar una peticion", e);
            session.invalidate();
            request.getRequestDispatcher(errorPage).forward(req, res);
        }
    }

    public void destroy()
    {
    }
}
