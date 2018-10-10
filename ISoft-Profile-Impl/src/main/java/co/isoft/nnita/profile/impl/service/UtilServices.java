package co.isoft.nnita.profile.impl.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;

/**
 * Implementacion Original de consulta de usuarios Isoftnnita
 *
 * @author Yaher Carrillo
 * @Date 10/07/2018
 */
public class UtilServices
{

    /**
     * Objeto utilizado para el log
     */
    private static final Log logger = LogFactory.getLog(UtilServices.class);

    /**
     * Convierte todos los fields String a Uppercase
     * de una clase para las implementaciones de uso
     * con la capa transaccional
     *
     * @param obj clase a usar
     */
    public void convertAtrrUppercase(Object obj)
    {
        for (Field f : obj.getClass().getDeclaredFields())
        {
            if (f.getType().equals(String.class))
            {
                f.setAccessible(true);
                try
                {
                    String valor = (String) (f.get(obj)!=null?f.get(obj):"");
                    if (valor!=null && !valor.trim().equals(""))
                        f.set(obj, (valor).toUpperCase());
                }
                catch (IllegalAccessException e)
                {
                    logger.error("Ha ocurrido un error tratando de volver mayusculas todos ls String de la clase: " + obj.getClass());
                }
            }
        }
    }
}
