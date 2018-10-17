package co.isoft.nnita.profile.api.gateways.util;

import co.isoft.nnita.profile.api.dao.JwtDao;
import co.isoft.nnita.profile.api.exceptions.JwtException;
import co.isoft.nnita.profile.api.exceptions.LicenseException;
import co.isoft.nnita.profile.api.exceptions.ParamsException;
import co.isoft.nnita.profile.api.gateways.models.CommonsResponse;
import co.isoft.nnita.profile.api.gateways.models.request.users.RequestNewUserISoftProfile;
import co.isoft.nnita.profile.api.gateways.models.response.ResponseFindProfiles;
import co.isoft.nnita.profile.api.models.Perfiles;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.modelsweb.DatosLicencia;
import co.isoft.nnita.profile.api.util.EstatusGenericos;
import co.isoft.nnita.profile.api.util.JSonUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.io.UnsupportedEncodingException;
import java.util.*;

import static co.isoft.nnita.profile.api.util.EstatusGenericos.PROFILER_GENERIC_ERROR_PARAMS;

public abstract class GatewayBaseBean
{
    /**
     * Servicios JWT
     */
    private static JwtDao jwtDao;
    /**
     * Constantes Mapa de transacciones de la llave de usuario.
     */
    public static String MAP_USER_TRANSACTION = "usertransaction";
    /**
     * Constantes Mapa de transacciones de la llave del canal de transaccion.
     */
    public static String MAP_CANAL_TRANSACTION = "canaltransaction";

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

    /**
     * Valida la licencia que intenta interactuar con el
     * sistema.
     *
     * @param key Llave de sistema
     * @throws LicenseException ocurre si falla la operacion.
     */
    public static Map<String, String> validateLicence(String key) throws LicenseException
    {
        Map<String, String> mapConfigurationLicence = new HashMap<>();
        try
        {
            Map<String, Object> mapConfiguration = new HashMap<>();
            mapConfiguration.put("client", "ISOFT");
            mapConfiguration.put("access", "Prueba12$");
            String generate = (String) jwtDao.deGenerarToken(key, mapConfiguration);
            DatosLicencia quote = (DatosLicencia) JSonUtil.fromJson(generate, DatosLicencia.class);
            //Se llena el mapa de configuracion
            mapConfigurationLicence.put(MAP_USER_TRANSACTION, quote.getClienteISoft());
            mapConfigurationLicence.put(MAP_CANAL_TRANSACTION, String.valueOf(quote.getCanal()));
        }
        catch (JWTVerificationException e)
        {
            throw new LicenseException(e.getMessage(), EstatusGenericos.PROFILER_GENERIC_LICENSE.getCode(), "Falla la verificacion JWT");
        }
        catch (UnsupportedEncodingException e)
        {
            throw new LicenseException(e.getMessage(), EstatusGenericos.PROFILER_GENERIC_LICENSE.getCode(), "Falla encoding JWT");
        }
        catch (JwtException e)
        {
            throw new LicenseException(e.getMessage(), EstatusGenericos.PROFILER_GENERIC_LICENSE.getCode(), "Falla general JWT");
        }
        finally
        {
            return mapConfigurationLicence;
        }
    }

    /**
     * Validacions base a parametros de entrada, comparacion
     * distintas a nulls, espacios vacios y caracteres especiales
     * y ademas valida las listas vacias de datos
     *
     * @param listParam lista de caracteres
     * @throws ParamsException
     */
    public static void validarParametrosGenericos(List<String>... listParam) throws ParamsException
    {
        for (List<String> item : listParam)
        {
            if (item == null || item.isEmpty())
                throw (new ParamsException(PROFILER_GENERIC_ERROR_PARAMS.getDescription(), PROFILER_GENERIC_ERROR_PARAMS.getCode(), PROFILER_GENERIC_ERROR_PARAMS.getRefbundle()));
        }
    }

    /**
     * Construccion de response por defecto para errrores generales
     *
     * @param response estructura a mostrar
     */
    public static void matchToOk(CommonsResponse response)
    {
        response.setCodeTrasacction(PROFILER_GENERIC_ERROR_PARAMS.INFO.getCode());
        response.setStatus(PROFILER_GENERIC_ERROR_PARAMS.INFO.getCode());
        response.setDescriptionTransacction(PROFILER_GENERIC_ERROR_PARAMS.INFO.getDescription());
    }

    /**
     * Construccion de response por defecto para errrores generales
     *
     * @param response estructura a mostrar
     */
    public static void matchToResponses(CommonsResponse response)
    {
        response.setCodeTrasacction(EstatusGenericos.PROFILER_GENERIC_ERROR.getCode());
        response.setStatus(PROFILER_GENERIC_ERROR_PARAMS.ERROR.getCode());
        response.setDescriptionTransacction(PROFILER_GENERIC_ERROR_PARAMS.ERROR.getDescription());
    }

    /**
     * Construccion de response por defecto para errrores generales
     *
     * @param response estructura a mostrar
     * @param codError codigo de error a indicar
     */
    public static void matchToResponses(CommonsResponse response, String codError, String descripcion, String status)
    {
        response.setCodeTrasacction(codError);
        response.setDescriptionTransacction(descripcion);
        response.setStatus(status);
    }

    /**
     * clona la informacion de entrada respecto a usuarios
     * para trabajar con las instacias de la aplicacion.
     *
     * @param request objeto con informacon
     * @return objeto usuario con la clonacion de datos
     */
    public static Usuarios clonUsersRequest(RequestNewUserISoftProfile request)
    {
        Usuarios user = new Usuarios();
        user.setLogin(request.getLoginname());
        user.setNombres(request.getNombres());
        user.setApellidos(request.getApellidos());
        user.setEmail(request.getEmail());
        user.setClave(request.getClave());
        user.setHabilitado(new Long("1"));
        user.setFecha_registro(new Date());
        return user;
    }

    /**
     * Clona la informacion de entrada respecto a perfiles
     * para trabajar con las instacias de la aplicacion.
     *
     * @param list objeto con informacon
     * @return objeto listado de perfiles de respuestas clonados
     */
    public static List<ResponseFindProfiles> clonProfileResponse(List<Perfiles> list)
    {
        List<ResponseFindProfiles> listClone = new ArrayList<>();
        if (list!=null && !list.isEmpty()){
            for (Perfiles perfil: list){
                ResponseFindProfiles item = new ResponseFindProfiles();
                item.setId(perfil.getId().toString());
                item.setNombre(perfil.getNombre_perfil());
                listClone.add(item);
            }
        }
        return listClone;
    }

    /**
     * Obtiene el servicio de JWT
     *
     * @return servicio jwt
     */
    public JwtDao getJwtDao()
    {
        return jwtDao;
    }

    /**
     * Asigna un valor al servicio de JWT
     *
     * @param jwtDao valor a asignar
     */
    public static void setJwtDao(JwtDao jwtDao)
    {
        GatewayBaseBean.jwtDao = jwtDao;
    }
}
