package co.isoft.nnita.profile.api.gateways.models.request.users;

/**
 * Modelo de salida de respuesta de las modificaciones
 * de permisos sobre un perfil.
 * @author Yaher Carrillo
 * @date 21/11/2018
 */
public class PermissionGrants
{
    /**
     * Item alterado o modificado
     */
    private String item;
    /**
     * Codigo de la transaccion
     */
    private String code;
    /**
     * Status de la transaccion
     */
    private String status;
    /**
     * Descripcion de la ejecucion
     */
    private String description;

    /**
     * Constructor por defecto
     */
    public PermissionGrants()
    {
    }

    /**
     * Contructor que inicializa los valores del objeto
     *
     * @param item        item modificado
     * @param code        codigo de la transaccion
     * @param status      status de la transaccion
     * @param description descripcion de la transaccion
     */
    public PermissionGrants(String item, String code, String status, String description)
    {
        this.item = item;
        this.code = code;
        this.status = status;
        this.description = description;
    }

    /**
     * Obtiene el item modificado
     *
     * @return item modificado
     */
    public String getItem()
    {
        return item;
    }

    /**
     * Asigna un valor al item modificado
     *
     * @param item valor a asignar
     */
    public void setItem(String item)
    {
        this.item = item;
    }

    /**
     * Obtiene el valor del codigo de la transaccion
     *
     * @return codigo de la transaccion
     */
    public String getCode()
    {
        return code;
    }

    /**
     * Asigna un valor al codigo de la transaccion
     *
     * @param code valor a asignar
     */
    public void setCode(String code)
    {
        this.code = code;
    }

    /**
     * Obtiene el estatis de la transaccion
     *
     * @return status de la transaccion
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * Asigna un valor al status de la transaccion
     *
     * @param status valor a asignar
     */
    public void setStatus(String status)
    {
        this.status = status;
    }

    /**
     * Obtiene la descripcion de la transaccion
     *
     * @return descripcion de la transaccion
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Asigna un valor a la descripcion de la transaccion
     *
     * @param description valor a asignar
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
}
