package co.isoft.nnita.profile.api.gateways.util;

import co.isoft.nnita.profile.api.dao.JwtDao;
import co.isoft.nnita.profile.api.exceptions.JwtException;
import co.isoft.nnita.profile.api.exceptions.LicenseException;
import co.isoft.nnita.profile.api.exceptions.ParamsException;
import co.isoft.nnita.profile.api.gateways.models.CommonsResponse;
import co.isoft.nnita.profile.api.gateways.models.request.users.RequestNewUserISoftProfile;
import co.isoft.nnita.profile.api.gateways.models.response.ResponseFindProfiles;
import co.isoft.nnita.profile.api.gateways.models.response.ResponseFindProfilesUsers;
import co.isoft.nnita.profile.api.models.Perfiles;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.modelsweb.DatosLicencia;
import co.isoft.nnita.profile.api.util.EstatusGenericos;
import co.isoft.nnita.profile.api.util.JSonUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static co.isoft.nnita.profile.api.util.ConstantesBaseBean.*;
import static co.isoft.nnita.profile.api.util.EstatusGenericos.PROFILER_GENERIC_ERROR_PARAMS;

public abstract class GatewayBaseBean
{
    /**
     * Servicios JWT
     */
    private static JwtDao jwtDao;
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
    public static Map<String, String> validateLicenceToWS(String key,String ip) throws LicenseException
    {
        Map<String, String> mapConfigurationLicence = new HashMap<>();
        try
        {
            Map<String, Object> mapConfiguration = new HashMap<>();
            mapConfiguration.put("client", USER_JWT);
            mapConfiguration.put("access", PASS_JWT);
            String generate = (String) jwtDao.deGenerarToken(key, mapConfiguration);
            DatosLicencia quote = (DatosLicencia) JSonUtil.fromJson(generate, DatosLicencia.class);

            //Se evaluan las fecha de consumo
            SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATES);
            String fecha_actual_String = format.format(new Date());
            Date fecha_actual = format.parse(fecha_actual_String);

            if (!compareDatesRange(quote.getFechaInicio(),quote.getFechaFin(),fecha_actual))
                throw new LicenseException("Las Fechas de Licencia no Coinciden.",EstatusGenericos.PROFILER_GENERIC_LICENSE.getCode(),"Las Fechas de Licencia no Coinciden.");

            //Se llena el mapa de configuracion
            mapConfigurationLicence.put(MAP_USER_TRANSACTION, quote.getClienteISoft());
            mapConfigurationLicence.put(MAP_CANAL_TRANSACTION, String.valueOf(quote.getCanal()));
            mapConfigurationLicence.put(MAP_IP_TRANSACTION, ip);
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
        catch (ParseException e)
        {
            throw new LicenseException(e.getMessage(),EstatusGenericos.PROFILER_GENERIC_LICENSE.getCode(),"Las Fechas de Licencia no Coinciden.");
        }

        return mapConfigurationLicence;

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
     * Clona la informacion de entrada respecto a perfiles
     * para trabajar con las instacias de la aplicacion basados
     * en la consulta de perfiles de usuarios
     *
     * @param list objeto con informacion
     * @return objeto listado de perfiles de respuestas clonados
     */
    public static List<ResponseFindProfiles> clonProfileUsersResponse(List<Perfiles> list)
    {
        List<ResponseFindProfiles> listClone = new ArrayList<>();
        if (list!=null && !list.isEmpty()){
            for (Perfiles perfil: list){
                ResponseFindProfilesUsers item = new ResponseFindProfilesUsers();
                item.setId(perfil.getId().toString());
                item.setNombre(perfil.getNombre_perfil());
                item.setHabilitado(perfil.getHabilitado());
                listClone.add(item);
            }
        }
        return listClone;
    }

    /**
     * Metodo que compara una fecha entre rangos
     * @return logico
     */
    public static boolean compareDatesRange(Date finit, Date fend, Date question)
    {
        //compare no inclusive
        //return a.compareTo(d) * d.compareTo(b) > 0;
        //Compare inclusive
        return finit.compareTo(question) * question.compareTo(fend) >= 0;
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
