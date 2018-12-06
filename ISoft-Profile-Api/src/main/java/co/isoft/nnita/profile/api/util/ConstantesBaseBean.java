package co.isoft.nnita.profile.api.util;

/**
 * Clase Abstracta para el manejo de constantes del sistema
 *
 * @author Yaher Carrillo
 * @Date 11/10/2018
 */
public abstract class ConstantesBaseBean
{
    /**
     * Constante para la creacion de usuarios masivos, esto
     * indica la clave por defecto de todos los usuarios creados.
     */
    public static String PASS_DEFAULT_USER = "Prueba12$";
    /**
     * Valor vacios para cadenas de datos incompletas
     */
    public static String EMPTY= "EMPTY";
    /**
     * Valor vacios para cadenas de datos incompletos sobre los nombres
     */
    public static String EMPTY_NAMES= "EMPTY_NAME";
    /**
     * Perfil por defecto de la aplicacion.
     */
    public static String GUEST= "GUEST";

    /**
     * Constantes Mapa de transacciones de la llave de usuario.
     */
    public static String MAP_USER_TRANSACTION = "usertransaction";
    /**
     * Constantes Mapa de transacciones de la llave del canal de transaccion.
     */
    public static String MAP_CANAL_TRANSACTION = "canaltransaction";
    /**
     * IP desde donde se origina la transaccion
     */
    public static String MAP_IP_TRANSACTION = "iptransaction";

    /**
     * Llave para unica para la busqueda de errores.
     */
    public static String KEY_ERRORS_GENERIC = "profile.ws.usuarios.error.";
    /**
     * Llave unica para la busqueda de errores referentes al modulo de perfiles.
     */
    public static String KEY_ERRORS_PROFILER_GENERIC = "profile.ws.usuarios.error.";
    /**
     * Formato de fechas
     */
    public static String FORMAT_DATES = "yyyy-MM-dd";
    /**
     * Usuario de encriptacion de JWT
     */
    public static String USER_JWT = "ISOFT";
    /**
     * Clave de encriptacion de JWT
     */
    public static String PASS_JWT = "Prueba12$";
}
