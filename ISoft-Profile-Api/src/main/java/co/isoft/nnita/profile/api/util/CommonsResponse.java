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
    private String status;
    /**
     * Codigo resultante de la operacion
     */
    private String codeTrasacction;
    /**
     * Descripcion de la transaccion
     */
    private String descriptionTransacction;
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
        this.setCodeTrasacction(EstatusGenericos.INFO.getDescription());
        this.setStatus(EstatusGenericos.INFO.getCode());
        this.setDescriptionTransacction(EstatusGenericos.INFO.getRefbundle());
        return this;
    }

    /**
     * Response comun para respuestas vacias
     * o sin datos
     * @return
     */
    public CommonsResponse toEmpty(){
        this.setCodeTrasacction(EstatusGenericos.INFO_EMPTY.getDescription());
        this.setStatus(EstatusGenericos.INFO_EMPTY.getCode());
        this.setDescriptionTransacction(EstatusGenericos.INFO_EMPTY.getRefbundle());
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
    public String getStatus()
    {
        return status;
    }

    /**
     * Asigna un valor al estatus de la operacion
     *
     * @param status valor a asignar
     */
    public void setStatus(String status)
    {
        this.status = status;
    }

    /**
     * Obtiene el codigo de la transaccion
     *
     * @return codigo de la transaccion
     */
    public String getCodeTrasacction()
    {
        return codeTrasacction;
    }

    /**
     * Asigna un valor al codigo de la transaccion
     *
     * @param codeTrasacction valor a asignar
     */
    public void setCodeTrasacction(String codeTrasacction)
    {
        this.codeTrasacction = codeTrasacction;
    }

    /**
     * Obtiene la descripcion de la transaccion
     *
     * @return descripcion de la transaccion
     */
    public String getDescriptionTransacction()
    {
        return descriptionTransacction;
    }

    /**
     * Asigna un valor a la descripcion de la transaccion
     *
     * @param descriptionTransacction valor a asignar
     */
    public void setDescriptionTransacction(String descriptionTransacction)
    {
        this.descriptionTransacction = descriptionTransacction;
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
