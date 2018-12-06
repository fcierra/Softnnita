package co.isoft.nnita.profile.impl.util;

import co.isoft.nnita.profile.api.dto.input.NewUserInputDTO;
import co.isoft.nnita.profile.api.dto.output.UsersMassiveOutDTO;
import co.isoft.nnita.profile.api.exceptions.ParamsException;
import co.isoft.nnita.profile.api.util.ConstantesBaseBean;
import co.isoft.nnita.profile.api.util.EmailValidator;
import co.isoft.nnita.profile.impl.service.UtilServices;

import java.util.regex.Pattern;

import static co.isoft.nnita.profile.api.util.EstatusGenericos.*;

/**
 * Objeto que permite validar la integridad de los objetos
 * referentes al modelo de usuario
 *
 * @author Yaher Carrillo
 * @date 27/11/2018
 */
public abstract class ValidationsBasicModelUsuarios extends UtilServices
{
    /**
     * Limite de texto de los login de usuarios.
     */
    private static int MIN_DATABASE_LOGIN_USER = 5;
    /**
     * lIMITE Minimo de texto de los login de usuarios.
     */
    private static int MAX_DATABASE_LOGIN_USER = 60;
    /**
     * Constante que indica el minimo soportado para claves
     */
    private static int MIN_DATABASE_PASSWORD_USER = 5;
    /**
     * Constante que indica el maximo soportado para claves
     */
    private static int MAX_DATABASE_PASSWORD_USER= 15;
    /**
     * Validador de emails
     */
    private static EmailValidator emailValidator = new EmailValidator();

    /**
     * Metodo que valida los limites de los login de usuarios
     *
     * @param usuario login de usuarios
     * @throws ParamsException Ocurre si la operacion no cumple.
     */
    public static void validateLoginUsers(String usuario) throws ParamsException
    {
        String regex = "^[A-Za-z1-9]{"+MIN_DATABASE_LOGIN_USER+","+MAX_DATABASE_LOGIN_USER+"}$";
        validarParametrosGenericos(usuario);
        if (!Pattern.matches(regex , usuario))
            throw new ParamsException(PROFILER_USER_NICKNAME_MAX_LENGTH.getDescription(), PROFILER_USER_NICKNAME_MAX_LENGTH.getCode(), PROFILER_USER_NICKNAME_MAX_LENGTH.getRefbundle());
    }

    /**
     * Validar los nombres y apellidos de un usuario
     * @param nombres
     * @throws ParamsException Ocurre si no es valido el parametro
     */
    public static void validateNamesUsers(String nombres) throws ParamsException
    {
        String regex = "[a-zA-z]+([ '-][a-zA-Z]+)*.{"+MIN_DATABASE_LOGIN_USER+","+MAX_DATABASE_LOGIN_USER+"}$";
        if (!Pattern.matches(regex , nombres))
            throw new ParamsException(PROFILER_USER_NAMES_MAX_LENGTH.getDescription(), PROFILER_USER_NAMES_MAX_LENGTH.getCode(), PROFILER_USER_NAMES_MAX_LENGTH.getRefbundle());
    }

    /**
     * Valida el formato de emails
     * @param param email a validar
     * @throws ParamsException
     */
    public static void validateEmailUsers(String param) throws ParamsException
    {
        if (param!=null && !param.trim().equals(""))
            if (!emailValidator.validateEmail(param))
                throw (new ParamsException(PROFILER_USER_EMAIL_MAX_LENGTH.getDescription(), PROFILER_USER_EMAIL_MAX_LENGTH.getCode(), PROFILER_USER_EMAIL_MAX_LENGTH.getRefbundle()));
    }

    /**
     * Valida la integridad de claves de usuarios
     * @param param clave de usuario
     * @throws ParamsException Ocurre si falla la operacion
     */
    public static void validatePasswordUsers(String param) throws ParamsException
    {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&+=])(?=\\S+$).{5,}$";
        if (!Pattern.matches(regex , param))
            throw new ParamsException(PROFILER_USER_PASS_MAX_LENGTH.getDescription(), PROFILER_USER_PASS_MAX_LENGTH.getCode(), PROFILER_USER_PASS_MAX_LENGTH.getRefbundle());
    }


    /**
     * Valida la integridad del objeto usuarios
     * para su creacion
     * @param user Objeto usuario
     * @throws ParamsException Ocurre si falla la operacion
     */
    public static void validateIntegriyObjectUsers(NewUserInputDTO user) throws ParamsException
    {
        validarParametrosGenericos(user.getUsuario(),user.getCorreo(),user.getClave());
        validateLoginUsers(user.getUsuario());
        validateEmailUsers(user.getCorreo());
        validateNamesUsers(user.getNombres());
        validateNamesUsers(user.getApellidos());
        validatePasswordUsers(user.getClave());
    }

    /**
     * Valida la integridad del objeto usuarios masivos
     * para su creacion
     * @param user Objeto usuario masivo
     * @throws ParamsException Ocurre si falla la operacion
     */
    public static void validateIntegriyObjectUsersMassive(UsersMassiveOutDTO user) throws ParamsException
    {
        ValidationsBasicModelUsuarios.validateLoginUsers(user.getUsuario());
        ValidationsBasicModelUsuarios.validateNamesUsers(user.getNombres() != null && !user.getNombres().trim().equals("") ? user.getNombres() : ConstantesBaseBean.EMPTY_NAMES);
        ValidationsBasicModelUsuarios.validateNamesUsers(user.getApellidos() != null && !user.getApellidos().trim().equals("") ? user.getApellidos() : ConstantesBaseBean.EMPTY_NAMES);
    }




}
