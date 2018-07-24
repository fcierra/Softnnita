package co.isoft.nnita.profile.api.util;

/**
 * Enum que describe el manejo
 * de los sexos en el sistema.
 * @author Yaher Carrillo
 * @Date 20/07/2018
 */
public enum EnumSexConfig
{
    SEX_CONFIG_MAN("M", "Hombre", "config.sex.man"),
    SEX_CONFIG_WOMAN("F", "Mujer", "config.sex.woman");
    /**
     * Codigo del sexo
     **/
    private String code;
    /**
     * Descripcion del sexo
     */
    private String description;
    /**
     * Referencia de objetos de mensajes globales
     */
    private String refbundle;

    /**
     * Constructor del Enum que inicializa valores
     *
     * @param code        identificador del elemento
     * @param description descripcion del recurso
     * @param refbundle   referencia del archivo de recursos
     */
    EnumSexConfig(String code, String description, String refbundle)
    {
        this.code = code;
        this.description = description;
        this.refbundle = refbundle;
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
     * Obtiene la llave de referencia del archivo de recursos de texto
     *
     * @return
     */
    public String getRefbundle()
    {
        return refbundle;
    }

    /**
     * ASsigna un valor de la referencia de resursos de texto
     *
     * @param refbundle
     */
    public void setRefbundle(String refbundle)
    {
        this.refbundle = refbundle;
    }
}
