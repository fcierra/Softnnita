package co.isoft.nnita.profile.impl.service;

import co.isoft.nnita.profile.api.models.DetalleBitacora;
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
                    if (!f.getName().equals("clave"))
                    {
                        String valor = (String) (f.get(obj) != null ? f.get(obj) : "");
                        if (valor != null && !valor.trim().equals(""))
                            f.set(obj, (valor).toUpperCase());
                    }

                }
                catch (IllegalAccessException e)
                {
                    logger.error("Ha ocurrido un error tratando de volver mayusculas todos ls String de la clase: " + obj.getClass());
                }
            }
        }
    }

    /**
     * Crea un detalle de bitacora de la creacion de usuarios masivos
     * para detallar lo ocurrido en su creacion
     *
     * @return
     */
    public DetalleBitacora recordDetailBinnacleUsersMassiveSucess(String profile, String usercreate)
    {
        DetalleBitacora detail = new DetalleBitacora();
        detail.setDetalle_valor_fin("Se crea el usuario [" + usercreate + "] y se agrega el perfil: [" + profile + "]");
        detail.setDescripcion("Se agrega el elemento satisfactoriamente");
        return detail;
    }

    /**
     * Crea un detalle de bitacora de la creacion de usuarios masivos
     * para detallar que se agrega un usuario con el perfil por defecto
     *
     * @return
     */
    public DetalleBitacora recordDetailBinnacleUsersMassiveProfileDefault(String profile, String profileDontExist, String usercreate)
    {
        DetalleBitacora detail = new DetalleBitacora();
        detail.setDetalle_valor_fin("Se crea al usuario [" + usercreate + "] y se agrega el perfil: [" + profile + "]");
        detail.setDescripcion("Se agrega el elemento con el perfil por defecto, el perfil indicado [" + profileDontExist + "] no existia");
        return detail;
    }

    /**
     * Crea un detalle de bitacora de la creacion de usuarios masivos
     * para detallar que el usuarui ya existe
     *
     * @return
     */
    public DetalleBitacora recordDetailBinnacleUsersMassiveUserExist(String usercreate)
    {
        DetalleBitacora detail = new DetalleBitacora();
        detail.setDetalle_valor_fin("Se intenta crear al usuario [" + usercreate + "]");
        detail.setDescripcion("El usuario ya existe!.");
        return detail;
    }
}
