package co.isoft.nnita.profile.impl.util;

import co.isoft.nnita.profile.api.exceptions.ParamsException;
import co.isoft.nnita.profile.impl.service.UtilServices;

import static co.isoft.nnita.profile.api.util.EstatusGenericos.PROFILER_GENERIC_ERROR_PARAMS;

/**
 * Objeto que permite validar la integridad de los objetos
 * referentes al modelo de usuario
 * @author Yaher Carrillo
 * @date 27/11/2018
 */
public abstract class ValidationsBasicModelUsuarios extends UtilServices
{
    /**
     * Limite de texto de los login de usuarios.
     */
    private static int LIMIT_DATABASE_LOGIN_USER = 60;
    public static void validarLoginUsuarios(String usuario) throws ParamsException
    {
        validarParametrosGenericos(usuario);
        if (usuario.length()> LIMIT_DATABASE_LOGIN_USER)
            throw new ParamsException(PROFILER_GENERIC_ERROR_PARAMS.getDescription(),PROFILER_GENERIC_ERROR_PARAMS.getCode(),PROFILER_GENERIC_ERROR_PARAMS.getRefbundle());
    }
}
