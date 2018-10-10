package co.isoft.nnita.profile.impl.dao.impl;

import co.isoft.nnita.profile.api.dao.JwtDao;
import co.isoft.nnita.profile.api.exceptions.JwtException;
import co.isoft.nnita.profile.api.util.JSonUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class JwtImpl implements JwtDao
{
    private static JwtImpl ourInstance = new JwtImpl();

    public static JwtImpl getInstance()
    {
        return ourInstance;
    }

    public String generarToken(Object entity, Map<String, Object> mapConfigurations)
            throws UnsupportedEncodingException
    {
        JSonUtil.toJson(entity);
        Map<String, Object> headerClaims = new HashMap();
        headerClaims.put("REQUEST", JSonUtil.toJson(entity));
        String token = "";
        try
        {
            String issuer = mapConfigurations.get("client").toString();
            String nbsp = mapConfigurations.get("access").toString();
            Algorithm algorithm = Algorithm.HMAC256(nbsp);

            token = JWT.create().withHeader(headerClaims).withIssuer(issuer).sign(algorithm);
        }
        catch (JWTCreationException|IllegalArgumentException |UnsupportedEncodingException e)
        {
            throw e;
        }
        return token;
    }

    public String generarToken(Object entity)
            throws UnsupportedEncodingException
    {
        JSonUtil.toJson(entity);
        Map<String, Object> headerClaims = new HashMap();
        headerClaims.put("REQUEST", JSonUtil.toJson(entity));
        String token = "";
        try
        {
            String issuer = "INFOTURNOS";
            String nbsp = "INFO-123456789";
            Algorithm algorithm = Algorithm.HMAC256(nbsp);

            token = JWT.create().withHeader(headerClaims).withIssuer(issuer).sign(algorithm);
        }
        catch (JWTCreationException|UnsupportedEncodingException |IllegalArgumentException e)
        {
            throw e;
        }
        return token;
    }

    public Object deGenerarToken(String token)
            throws UnsupportedEncodingException
    {
        DecodedJWT jwt = null;
        try
        {
            String issuer = "INFOTURNOS";
            String nbsp = "INFO-123456789";
            Algorithm algorithm = Algorithm.HMAC256(nbsp);

            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            verifier.verify(token);
            jwt = JWT.decode(token);
        }
        catch (JWTVerificationException|UnsupportedEncodingException |IllegalArgumentException e)
        {
            //DecodedJWT jwt;
            throw e;
        }
        catch (Exception e)
        {
            throw e;
        }

        Claim claim = jwt.getHeaderClaim("REQUEST");
        return claim.asString();
    }

    public Object deGenerarToken(String token, Map<String, Object> mapConfigurations)
            throws UnsupportedEncodingException
    {
        DecodedJWT jwt = null;
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(mapConfigurations.get("access").toString());

            JWTVerifier verifier = JWT.require(algorithm).withIssuer(mapConfigurations.get("client").toString()).build();
            verifier.verify(token);
            jwt = JWT.decode(token);
        }
        catch (JWTVerificationException|UnsupportedEncodingException e)
        {
            //DecodedJWT jwt;
            throw e;
        }
        catch (Exception e)
        {
            throw e;
        }
        //DecodedJWT jwt;
        Claim claim = jwt.getHeaderClaim("REQUEST");
        return claim.asString();
    }
}
