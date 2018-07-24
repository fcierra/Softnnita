/*
 * ESTE COMPONENTE FUE REALIZADO BAJO LA METODOLOGIA DE DESARROLLO DE 
 * INFORMATICA & TECNOLOGIA Y SE ENCUENTRA PROTEGIDO POR LAS LEYES DE 
 * DERECHOS DE AUTOR.
 */
package co.isoft.nnita.profile.util;


import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.el.ELException;
import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Clase con metodos utiles para trabajar Faces y Servlets.
 * @author Alejandro Vivas
 * @version 3.1.b.56 5/07/2010
 * @date5/07/2010
 * @since 3.b.12 22/07/2008
 */
public final class ServletJsfSpringUtil
{

    /** Constante con } para optimizar */
    private static final String RIGHT = "}";
    /** Constante con #{ para optimizar */
    private static final String LEFT = "#{";
    /** Contexto de la aplicacion */
    private static ServletContext servletContext;

    /**
     * Utilizado para evitar instancias de esta clase.
     */
    private ServletJsfSpringUtil()
    {
    }

    /**
     * Regresa el contexto de faces a partir de un contexto de servlet.
     * @param request Request de la peticion del Servlet
     * @param response Response del Servlet
     * @param servletContext Contexto del servlet.
     * @return FacesContext.
     */
    public static FacesContext getFacesContext(ServletRequest request, ServletResponse response, ServletContext servletContext)
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null)
            return facesContext;

        FacesContextFactory contextFactory = (FacesContextFactory) FactoryFinder.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
        LifecycleFactory lifecycleFactory = (LifecycleFactory) FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
        Lifecycle lifecycle = lifecycleFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);

        // Crear el faces context.
        facesContext = contextFactory.getFacesContext(servletContext, request, response, lifecycle);

        return facesContext;
    }

    /**
     * Regresa un bean de JSF.
     * @param name Nombre del bean de JSF
     * @param request Request de Servlet
     * @param response Response de Servlet
     * @param servletContext Contexto del servlet.
     * @return Bean de JSF.
     */
    public static Object getJsfBean(String name, ServletRequest request, ServletResponse response, ServletContext servletContext, Class<?> clazz)
    {
        FacesContextFactory contextFactory = (FacesContextFactory) FactoryFinder.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
        LifecycleFactory lifecycleFactory = (LifecycleFactory) FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
        Lifecycle lifecycle = lifecycleFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);

        FacesContext facesContext = contextFactory.getFacesContext(servletContext, request, response, lifecycle);

        UIViewRoot view = facesContext.getApplication().getViewHandler().createView(facesContext, "yourOwnID");
        facesContext.setViewRoot(view);
        return getJsfBean(name, facesContext, clazz);
    }

    /**
     * Regresa un bean de JSF que este definido en los archivos XML de faces.
     * @param name Nombre del bean de JSF
     * @param facesContext Contexto de faces.
     * @param clazz Clase esperada del bean.
     * @return Objeto creado
     */
    public static Object getJsfBean(String name, FacesContext facesContext, Class<?> clazz) throws ELException
    {
        // Obtener la aplicacion
        Application application = facesContext.getApplication();
        // Regresar el bean de faces.
        return application.evaluateExpressionGet(facesContext, LEFT + name + RIGHT, clazz);
    }

    /**
     * Regresa un objeto creado en Spring por medio del FacesContext.
     * @param name Nombre del bean
     * @param facesContext Nombre del contexto
     * @return Objeto creado por spring
     */
    public static Object getSpringBean(String name, FacesContext facesContext)
    {
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        WebApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
        return appContext.getBean(name);
    }

    /**
     * Regresa un objeto creado por spring por medio del ServletContext.
     * @param name Nombre del bean
     * @param servletContext Servlet context.
     * @return Objeto creado por spring.
     */
    public static Object getSpringBean(String name, ServletContext servletContext)
    {
        WebApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        return appContext.getBean(name);
    }

    /**
     * Regresa un objeto creado por spring por medio del ServletContext. Se debe haver definido previamente el contexto mediante la funcion setServletContext.
     * @param name Nombre del bean
     * @return Objeto creado por spring.
     */
    @SuppressWarnings("unchecked")
    public static <CLASS> CLASS getSpringBean(String name)
    {
        WebApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        return (CLASS) appContext.getBean(name);
    }

    /**
     * Cambia le valor de la propiedad servletContext
     * @param servletContext Valor nuevo de la propiedad servletContext.
     */
    public static void setServletContext(ServletContext servletContext)
    {
        ServletJsfSpringUtil.servletContext = servletContext;
        SpringUtil.setServletContext(servletContext);
    }

    /**
     * Regresa el valor de la propiedad servletContext
     * @return Valor de la propieda servletContext
     */
    public static ServletContext getServletContext()
    {
        return servletContext;
    }

    /**
     * Regresa la sesion HTTP dado el sessionID
     * @param sessionId id de la sesion
     * @return HTTPSession del id dado o null si no se encuentra
     */
    public static HttpSession getSession(final String sessionId)
    {
        final ServletContext context = getServletContext();
        final HttpSession session = (HttpSession) context.getAttribute(sessionId);
        return session;
    }

    /**
     * Caduca una sesion.
     * @param sessionId Id de la sesion.
     */
    public static void caducarSesion(final String sessionId)
    {
        HttpSession httpSession = getSession(sessionId);
        if (httpSession != null)
        {
            httpSession.invalidate();
        }
    }

    public static String getValorCookieJsf(String nombreCookie)
    {
        Cookie cookie = (Cookie)FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap().get(nombreCookie);
        if( cookie != null )
        {
            return (String)cookie.getValue() ;
        }
        else
        {
            return null;
        }
    }
    
    /**
     * Regresa el valor de una cookie
     * @param request Request con los datos de la peticion.
     * @param nombreCookie Nombre de la cookie
     * @return Valor de la cookie o null si no se encuentra la cookie
     */
    public static String getValorCookie(HttpServletRequest request,String nombreCookie)
    {
        Cookie[] cookies = request.getCookies();
        if(cookies == null)
        {
            return null;
        }
        for (int i = 0; i < cookies.length; i++)
        {
            Cookie cookie = cookies[i];
            if (nombreCookie.equals(cookie.getName()))
            {
                return (cookie.getValue());
            }
        }
        return null;
    }
}
