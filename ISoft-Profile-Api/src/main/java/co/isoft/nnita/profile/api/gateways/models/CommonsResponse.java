package co.isoft.nnita.profile.api.gateways.models;

import co.isoft.nnita.profile.api.util.EstatusGenericos;

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
