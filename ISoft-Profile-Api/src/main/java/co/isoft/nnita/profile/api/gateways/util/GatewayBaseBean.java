package co.isoft.nnita.profile.api.gateways.util;

import co.isoft.nnita.profile.api.gateways.ParamsException;
import co.isoft.nnita.profile.api.gateways.models.CommonsResponse;
import co.isoft.nnita.profile.api.gateways.models.request.users.RequestNewUserISoftProfile;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.util.EstatusGenericos;

import java.util.Date;
import java.util.List;

import static co.isoft.nnita.profile.api.util.EstatusGenericos.PROFILER_GENERIC_ERROR_PARAMS;

public abstract class GatewayBaseBean
{

    /**
     * Validacions base a parametros de entrada, comparacion
     * distintas a nulls, espacios vacios y caracteres especiales
     * de SOAP.
     *
     * @param params listado de parametros de entrada
     */
    public static void validarParametrosGenericos(String... params) throws ParamsException
    {
        for (String parametro : params) {
            if (parametro == null || parametro.trim().equals("") || parametro.trim().equals("?"))
                throw (new ParamsException(PROFILER_GENERIC_ERROR_PARAMS.getDescription(),PROFILER_GENERIC_ERROR_PARAMS.getCode(), PROFILER_GENERIC_ERROR_PARAMS.getRefbundle()));
        }
    }

    /**
     * Validacions base a parametros de entrada, comparacion
     * distintas a nulls, espacios vacios y caracteres especiales
     * y ademas valida las listas vacias de datos
     * @param listParam lista de caracteres
     * @throws ParamsException
     */
    public static void validarParametrosGenericos(List<String>... listParam) throws ParamsException
    {
        for (List<String> item : listParam) {
            if (item == null || item.isEmpty())
                throw (new ParamsException(PROFILER_GENERIC_ERROR_PARAMS.getDescription(),PROFILER_GENERIC_ERROR_PARAMS.getCode(), PROFILER_GENERIC_ERROR_PARAMS.getRefbundle()));
        }
    }

    /**
     * Construccion de response por defecto para errrores generales
     * @param response estructura a mostrar
     */
    public static void matchToOk(CommonsResponse response) {
        response.setCodeTrasacction(PROFILER_GENERIC_ERROR_PARAMS.INFO.getCode());
        response.setStatus(PROFILER_GENERIC_ERROR_PARAMS.INFO.getCode());
        response.setDescriptionTransacction(PROFILER_GENERIC_ERROR_PARAMS.INFO.getDescription());
    }

    /**
     * Construccion de response por defecto para errrores generales
     * @param response estructura a mostrar
     */
    public static void matchToResponses(CommonsResponse response) {
        response.setCodeTrasacction(EstatusGenericos.PROFILER_GENERIC_ERROR.getCode());
        response.setStatus(PROFILER_GENERIC_ERROR_PARAMS.ERROR.getCode());
        response.setDescriptionTransacction(PROFILER_GENERIC_ERROR_PARAMS.ERROR.getDescription());
    }

    /**
     * Construccion de response por defecto para errrores generales
     * @param response estructura a mostrar
     * @param codError codigo de error a indicar
     */
    public static void matchToResponses(CommonsResponse response, String codError, String descripcion,String status) {
        response.setCodeTrasacction(codError);
        response.setDescriptionTransacction(descripcion);
        response.setStatus(status);
    }


    /**
     * clona la informacion de entrada respecto a usuarios
     * para trabajar con las instacias de la aplicacion.
     * @param request objeto con informacon
     * @return objeto usuario con la clonacion de datos
     */
    public static Usuarios clonUsersRequest(RequestNewUserISoftProfile request){
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
}
