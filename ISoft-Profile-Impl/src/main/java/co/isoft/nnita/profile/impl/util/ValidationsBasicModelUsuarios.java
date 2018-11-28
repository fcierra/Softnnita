package co.isoft.nnita.profile.impl.util;

import co.isoft.nnita.profile.api.exceptions.ParamsException;
import co.isoft.nnita.profile.impl.service.UtilServices;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static co.isoft.nnita.profile.api.util.EstatusGenericos.PROFILER_GENERIC_ERROR_PARAMS;

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
     * Metodo que valida los limites de los login de usuarios
     *
     * @param usuario login de usuarios
     * @throws ParamsException Ocurre si la operacion no cumple.
     */
    public static void validarLoginUsuarios(String usuario) throws ParamsException
    {
        String regex = "^[A-Za-z1-9]{"+MIN_DATABASE_LOGIN_USER+","+MAX_DATABASE_LOGIN_USER+"}$";
        validarParametrosGenericos(usuario);
        if (!Pattern.matches(regex , usuario))
            throw new ParamsException(PROFILER_GENERIC_ERROR_PARAMS.getDescription(), PROFILER_GENERIC_ERROR_PARAMS.getCode(), PROFILER_GENERIC_ERROR_PARAMS.getRefbundle());
    }
}
