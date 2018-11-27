package co.isoft.nnita.profile.impl.service;

import co.isoft.nnita.profile.api.dto.output.UserDTO;
import co.isoft.nnita.profile.api.exceptions.ParamsException;
import co.isoft.nnita.profile.api.models.DetalleBitacora;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.dto.output.UsersMassiveOutDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;

import static co.isoft.nnita.profile.api.util.EstatusGenericos.PROFILER_GENERIC_ERROR_PARAMS;

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
     * Crea un detalle de bitacora de la creacion de un perfil
     * para detallar lo ocurrido en su creacion
     *
     * @return Detalle de bitacora
     */
    public DetalleBitacora recordDetailBinnacleCreateProfileSucess(String profile, Long habilitado)
    {
        DetalleBitacora detail = new DetalleBitacora();
        detail.setDetalle_valor_fin("Se crea el perfil [" + profile + "] y se agrega [" + (habilitado.equals("1")?"habilitado":"deshabilitado") + "]");
        detail.setDescripcion("Se agrega el elemento satisfactoriamente");
        return detail;
    }

    /**
     * Crea un detalle de bitacora de la creacion de un perfil
     * para detallar lo ocurrido en su creacion
     *
     * @return Detalle de bitacora
     */
    public DetalleBitacora recordDetailBinnacleModifyProfileSucess(String profile_antes, Long habilitado_antes,String profile_despues, Long habilitado_despues)
    {
        DetalleBitacora detail = new DetalleBitacora();
        detail.setDetalle_valor_inicio("Se cambia el [attr]:"+"nombre"+", ["+profile_antes+"], [attr]:habilitado ["+habilitado_antes+"] ");
        detail.setDetalle_valor_fin("Se cambia el [attr]:"+"nombre"+", ["+profile_despues+"], [attr]:habilitado ["+habilitado_despues+"] ");
        detail.setDescripcion("Se modifica el perfil :[" + profile_antes + "], satisfactoriamente");
        return detail;
    }

    /**
     * Crea un detalle de bitacora para la modificacion de valores de un usuario
     * @param usercreate usuario a modificar
     * @param attr atributo modificado
     * @param antes valor antes de la modificacion
     * @param despues valor luego de la modificacion.
     * @return Detalle de bitacora.
     */
    public DetalleBitacora recordDetailBinnacleUsersModifySucess(String usercreate, String attr, String antes, String despues)
    {
        DetalleBitacora detail = new DetalleBitacora();
        detail.setDetalle_valor_inicio("Se cambia el [attr]:"+attr+", ["+antes+"]");
        detail.setDetalle_valor_fin("Se cambia el [attr]:"+attr+", ["+despues+"] ");
        detail.setDescripcion("Se modifica el usuario :[" + usercreate + "], satisfactoriamente");
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
    public UsersMassiveOutDTO construcObjectResponseAddProfileOk (Usuarios user, String codeprofile){
        UsersMassiveOutDTO usersCreate = new UsersMassiveOutDTO();
        usersCreate.setLoginname(user.getLogin());
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
    public UsersMassiveOutDTO construcObjectResponseUnAddProfileOk (Usuarios user, String codeprofile){
        UsersMassiveOutDTO usersCreate = new UsersMassiveOutDTO();
        usersCreate.setNames(user.getNombres());
        usersCreate.setLoginname(user.getLogin());
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
    public UsersMassiveOutDTO construcObjectResponseAddProfileFail (Usuarios user, String codeprofile){
        UsersMassiveOutDTO usersCreate = construcObjectResponseAddProfileOk(user,codeprofile);
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
    public UsersMassiveOutDTO construcObjectResponseUnAddProfileFail (Usuarios user, String codeprofile){
        UsersMassiveOutDTO usersCreate = construcObjectResponseAddProfileOk(user,codeprofile);
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

    /**
     * Realiza un detalle de bitacora abocado al cambio de estatus de usuarios-
     * @param loginuser usuario a detallar
     * @param status status asignado
     * @return Un detalle de bitacora
     */
    public DetalleBitacora recordDetailBinnacleStatusUsers(String loginuser, boolean status)
    {
        DetalleBitacora detail = new DetalleBitacora();
        String status_seg = status?"Habilidato":"Deshabilitado";
        detail.setDetalle_valor_fin("Se agrega el estatus : ["+status_seg+"] al usuario :[" + loginuser + "]");
        detail.setDescripcion("Se administrar el estado satisfactoriamente.");
        return detail;
    }

    /**
     * Crea un detalle de bitacora de la modificacion de un perfil
     * para detallar lo ocurrido en su creacion
     *
     * @return Detalle de bitacora
     */
    public DetalleBitacora recordDetailBinnacleModifyPermissionProfileSucess(String permiso, Long habilitado_antes,Long habilitado_despues)
    {
        DetalleBitacora detail = new DetalleBitacora();
        detail.setDetalle_valor_inicio("Antes, Se cambia el [attr]:"+"permiso"+", ["+permiso+"], [attr]:habilitado ["+(habilitado_antes.equals(1l)?"true":"false")+"] ");
        detail.setDetalle_valor_fin("Despues, Se cambia el [attr]:"+"permiso"+", ["+permiso+"], [attr]:habilitado ["+(habilitado_despues.equals(1l)?"true":"false")+"] ");
        detail.setDescripcion("Se modifica el permiso :[" + permiso + "], satisfactoriamente");
        return detail;
    }

    /**
     * Crea un detalle de bitacora abocado a la modificacion no exiostente de permisos
     * @param permiso permiso a intentar modificar
     * @return Detalle de bitacora
     */
    public DetalleBitacora recordDetailBinnacleModifyPermissionProfileDontExist(String permiso)
    {
        DetalleBitacora detail = new DetalleBitacora();
        detail.setDetalle_valor_inicio("El permiso no existe attr["+permiso+"] ");
        detail.setDescripcion("No se realiza, satisfactoriamente");
        return detail;
    }

    /**
     * Clona los datos del modelo de usuarios a un DTO
     * @param usuario usuario a clonar
     * @return Usuario DTO
     */
    public UserDTO cloneUser(Usuarios usuario)
    {
        UserDTO user = new UserDTO();
        user.setNombres(usuario.getNombres());
        user.setApellidos(usuario.getApellidos());
        user.setCorreo(usuario.getEmail());
        user.setEstado(usuario.getHabilitado().toString().equalsIgnoreCase("true")?true:false);
        user.setNombre_perfil_defecto(usuario.getPerfilDefault().getNombre_perfil());
        return user;
    }


    /**
     * Validacions base a parametros de entrada, comparacion
     * distintas a nulls, espacios vacios y caracteres especiales
     * de SOAP.
     *
     * @param params listado de parametros de entrada
     */
    public static void validarParametrosGenericos(String... params) throws ParamsException
    {
        for (String parametro : params)
        {
            if (parametro == null || parametro.trim().equals("") || parametro.trim().equals("?"))
                throw (new ParamsException(PROFILER_GENERIC_ERROR_PARAMS.getDescription(), PROFILER_GENERIC_ERROR_PARAMS.getCode(), PROFILER_GENERIC_ERROR_PARAMS.getRefbundle()));
        }
    }
}
