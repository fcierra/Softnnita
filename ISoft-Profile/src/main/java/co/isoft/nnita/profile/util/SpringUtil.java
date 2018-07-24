package co.isoft.nnita.profile.util;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

/**
 * Utilidad de Spring.
 * @author Alejandro Vivas
 * @version 4.01.00 13/09/2012
 * @date 13/09/2012
 * @since 3.1.b.33 28/04/2010
 */
public abstract class SpringUtil
{
    /** Contexto de la aplicacion */
    private static ServletContext servletContext;
    
    /**
     * Constructor para evitar crear el objeto.
     */
    private SpringUtil()
    {
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
        SpringUtil.servletContext = servletContext;
    }
    
    /**
     * Obtiene el servletContext
     * @return el servletContext
     */
    public static ServletContext getServletContext()
    {
        return servletContext;
    }
}
