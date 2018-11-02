package co.isoft.nnita.profile.api.util;

/**
 * Enum que describe el manejo
 * de las licencias por parte del
 * sistema.
 * @author Yaher Carrillo
 * @Date 16/10/2018
 */
public enum EnumTypesLicense
{
    OPEN_ALL("open.all", "Abierta", "Licencia de alto consumo."),
    USERS_CONCURRENT("user.concurrent", "Abierta por usuarios", "Licencia de consumo por usuarios."),
    IPS("ip.users", "Reservada", "Licencia de consumo exclusivo"),;
    /**
     * Codigo de la licencia
     **/
    private String code;
    /**
     * Nombre de la licencia
     */
    private String name;
    /**
     * Descripcion de la licencia
     */
    private String description;

    /**
     * Constructor del Enum que inicializa valores
     *
     * @param code        identificador del elemento
     * @param description nombre del elemento
     * @param description descripcion del elemento
     */
    EnumTypesLicense(String code, String name, String description)
    {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    /**
     * Obtiene el codigo de busqueda del elemento
     *
     * @return codigo de error
     */
    public String getCode()
    {
        return code;
    }

    /**
     * Asigna el codigo a un elemento.
     *
     * @param code valor a asignar
     */
    public void setCode(String code)
    {
        this.code = code;
    }

    /**
     * Obtiene la desdcripcion del error
     *
     * @return
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Asigna una valor a la descripcion del error
     *
     * @param description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Obtiene el nombre de la licencia
     * @return nombre de la licencia
     */
    public String getName()
    {
        return name;
    }

    /**
     * Asigna un valor al nomrbe de la licencia
     * @param name valor a asignar
     */
    public void setName(String name)
    {
        this.name = name;
    }
}
