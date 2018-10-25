package co.isoft.nnita.profile.impl.service;

import co.isoft.nnita.profile.api.models.DetalleBitacora;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.modelsweb.UsuarioPerfilMassive;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;

/**
 * Implementacion Original de consulta de usuarios Isoftnnita
 *
 * @author Yaher Carrillo
 * @Date 10/07/2018
 */
public abstract class UtilServices
{

    /**
     * Objeto utilizado para el log
     */
    private static final Log logger = LogFactory.getLog(UtilServices.class);

    /**
     * Constantes Mapa de transacciones de la llave de usuario.
     */
    public static String MAP_USER_TRANSACTION = "usertransaction";
    /**
     * Constantes Mapa de transacciones de la llave del canal de transaccion.
     */
    public static String MAP_CANAL_TRANSACTION = "canaltransaction";

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

    /**
     * Realiza un detalle de bitacora de adicion
     * de perfile a un determinado usuario.
     * @return
     */
    public DetalleBitacora recordDetailBinnacleUsersAddProfileOk(String loginuser, String profile)
    {
        DetalleBitacora detail = new DetalleBitacora();
        detail.setDetalle_valor_fin("Se agrega el perfil: ["+profile+"] al usuario :[" + loginuser + "]");
        detail.setDescripcion("Se agrega satisfactoriamente el perfil.");
        return detail;
    }

    /**
     * Realiza un detalle de bitacora de adicion
     * de perfile a un determinado usuario.
     * @return
     */
    public DetalleBitacora recordDetailBinnacleUsersAddProfileFail(String loginuser, String profile)
    {
        DetalleBitacora detail = new DetalleBitacora();
        detail.setDetalle_valor_fin("No se agrega el perfil: ["+profile+"] al usuario :[" + loginuser + "]");
        detail.setDescripcion("No se registra la operacion, el perfil indicado no existe.");
        return detail;
    }

    /**
     * Realiza un detalle de bitacora de adicion
     * de perfile a un determinado usuario.
     * @return
     */
    public DetalleBitacora recordDetailBinnacleUsersUnAddProfileOk(String loginuser, String profile)
    {
        DetalleBitacora detail = new DetalleBitacora();
        detail.setDetalle_valor_fin("Se elimina el perfil: ["+profile+"] al usuario :[" + loginuser + "]");
        detail.setDescripcion("Se Elimina satisfactoriamente el perfil.");
        return detail;
    }

    public DetalleBitacora recordDetailBinnacleUsersUnAddProfileFail(String loginuser, String profile){
        DetalleBitacora detail = recordDetailBinnacleUsersUnAddProfileOk(loginuser,profile);
        detail.setDetalle_valor_fin("No se elimina el perfil: ["+profile+"] al usuario :[" + loginuser + "]");
        detail.setDescripcion("El perfil indicado a eliminar no existe o no esta asociado al usuario.");
        return detail;
    }

    /**
     * Construye el response de la transaccion de adicion de perfiles a }
     * un usuario.
     * @param user usuario a agregar perfiles.
     * @param codeprofile codigo del perfil.
     * @return Objeto usuario.
     */
    public UsuarioPerfilMassive construcObjectResponseAddProfileOk (Usuarios user, String codeprofile){
        UsuarioPerfilMassive usersCreate = new UsuarioPerfilMassive();
        usersCreate.setNames(user.getNombres());
        usersCreate.setLastname(user.getApellidos());
        usersCreate.setCodeperfil(codeprofile);
        usersCreate.setDescription("Se agrega satisfactoriamente el perfil.");
        usersCreate.setCodetransaction("200");
        return usersCreate;
    }

    /**
     * Construye el response de la transaccion de eliminacion de perfiles a
     * un usuario.
     * @param user usuario a agregar perfiles.
     * @param codeprofile codigo del perfil.
     * @return Objeto usuario.
     */
    public UsuarioPerfilMassive construcObjectResponseUnAddProfileOk (Usuarios user, String codeprofile){
        UsuarioPerfilMassive usersCreate = new UsuarioPerfilMassive();
        usersCreate.setNames(user.getNombres());
        usersCreate.setLastname(user.getApellidos());
        usersCreate.setCodeperfil(codeprofile);
        usersCreate.setDescription("Se desasocia satisfactoriamente el perfil.");
        usersCreate.setCodetransaction("200");
        return usersCreate;
    }

    /**
     * Construye el response de la transaccion de adicion de perfiles a
     * un usuario por un perfil no existente.
     * @param user usuario a agregar perfiles.
     * @param codeprofile codigo del perfil.
     * @return Objeto usuario.
     */
    public UsuarioPerfilMassive construcObjectResponseAddProfileFail (Usuarios user, String codeprofile){
        UsuarioPerfilMassive usersCreate = construcObjectResponseAddProfileOk(user,codeprofile);
        usersCreate.setDescription("No se registra la operacion, el perfil indicado no existe.");
        usersCreate.setCodetransaction("404");
        return usersCreate;
    }

    /**
     * Construye un response de la transaccion de eliminar un perfil no existente
     * de un determinado usuario
     * @param user usuario a indicar
     * @param codeprofile codigo del perfil.
     * @return objeto de respuesta
     */
    public UsuarioPerfilMassive construcObjectResponseUnAddProfileFail (Usuarios user, String codeprofile){
        UsuarioPerfilMassive usersCreate = construcObjectResponseAddProfileOk(user,codeprofile);
        usersCreate.setDescription("No se registra la operacion, el perfil indicado no existe o no esta asociado al usuario.");
        usersCreate.setCodetransaction("404");
        return usersCreate;
    }

    /**
     * Realiza un detalle de bitacora de consulta de perfiles de un usuario.
     * de perfile a un determinado usuario.
     * @return
     */
    public DetalleBitacora recordDetailBinnacleUsersFindProfileOk(String loginuser, String profile)
    {
        DetalleBitacora detail = new DetalleBitacora();
        detail.setDetalle_valor_fin("Se consulta el perfil: ["+profile+"] al usuario :[" + loginuser + "]");
        detail.setDescripcion("Consulta Satisfactoria.");
        return detail;
    }
}
