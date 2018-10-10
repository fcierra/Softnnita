package co.isoft.nnita.profile.api.dao;

import co.isoft.nnita.profile.api.exceptions.JwtException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Clase para el manejo de token's bajo la tecnologia JWT
 * Created by Yaher Carrillo on 26/10/2017.
 */
public interface JwtDao
{
    public abstract String generarToken(Object paramObject, Map<String, Object> paramMap)
            throws UnsupportedEncodingException, JwtException;

    public abstract Object deGenerarToken(String paramString, Map<String, Object> paramMap)
            throws UnsupportedEncodingException, JwtException;
}
