package co.isoft.nnita.profile.api.util;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * Response comun para el manejo de respuestas
 * REST, de los servicios gateway
 * @author Yaher Carrillo
 * @date 09/09/2018
 */
public class CommonsResponse
{
    /**
     * Estatus de la operacion
     */
    private String estatus;
    /**
     * Codigo resultante de la operacion
     */
    private String codigo;
    /**
     * Descripcion de la transaccion
     */
    private String descripcion;
    /**
     * Objeto de la respuesta del servicio
     */
    private Object response;
    /**
     * Recurso de texto que debe ser inyectado
     */
    private MessageSource messageSource;

    /**
     * Operacion comun de llenado de la informacion
     * para responses efectivos
     */
    public CommonsResponse toOk(){
        this.setCodigo(EstatusGenericos.INFO.getDescription());
        this.setEstatus(EstatusGenericos.INFO.getCode());
        this.setDescripcion(EstatusGenericos.INFO.getRefbundle());
        return this;
    }

    /**
     * Response comun para respuestas vacias
     * o sin datos
     * @return
     */
    public CommonsResponse toEmpty(){
        this.setCodigo(EstatusGenericos.INFO_EMPTY.getDescription());
        this.setEstatus(EstatusGenericos.INFO_EMPTY.getCode());
        this.setDescripcion(EstatusGenericos.INFO_EMPTY.getRefbundle());
        return this;
    }

    /**
     * Response comun para respuestas vacias
     * o sin datos
     * @return
     */
    public String toLicenceWarn(MessageSource messageSource ,String codeerror, String sharedkey){
        String message = messageSource.getMessage(codeerror, new Object[] { "App" }, Locale.getDefault());
        GatewayBaseBean.matchToResponses(this,codeerror, message, EstatusGenericos.WARN.getCode());
        this.setResponse(sharedkey);
        return message;
    }

    /**
     * Response comun para respuestas vacias
     * o sin datos
     * @return
     */
    public String toParamsWarn(MessageSource messageSource ,String codeerror){
        String message = messageSource.getMessage(codeerror, new Object[] { "App" }, Locale.getDefault());
        GatewayBaseBean.matchToResponses(this,codeerror, message, EstatusGenericos.WARN.getCode());
        return message;
    }


    /**
     * Obtiene el estatus de la operacion
     *
     * @return estatus de la operacion
     */
    public String getEstatus()
    {
        return estatus;
    }

    /**
     * Asigna un valor al estatus de la operacion
     *
     * @param estatus valor a asignar
     */
    public void setEstatus(String estatus)
    {
        this.estatus = estatus;
    }

    /**
     * Obtiene el codigo de la transaccion
     *
     * @return codigo de la transaccion
     */
    public String getCodigo()
    {
        return codigo;
    }

    /**
     * Asigna un valor al codigo de la transaccion
     *
     * @param codigo valor a asignar
     */
    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    /**
     * Obtiene la descripcion de la transaccion
     *
     * @return descripcion de la transaccion
     */
    public String getDescripcion()
    {
        return descripcion;
    }

    /**
     * Asigna un valor a la descripcion de la transaccion
     *
     * @param descripcion valor a asignar
     */
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    /**
     * Objeto de salida comun para respuestas
     *
     * @return objeto comun
     */
    public Object getResponse()
    {
        return response;
    }

    /**
     * Asigna un valor al objeto de salida de respuestas
     *
     * @param response valor a asignar
     */
    public void setResponse(Object response)
    {
        this.response = response;
    }
}
