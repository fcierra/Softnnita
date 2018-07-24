package co.isoft.nnita.profile.api.util;

/**
 * Enum que describe el manejo
 * de los sexos en el sistema.
 * @author Yaher Carrillo
 * @Date 20/07/2018
 */
public enum EnumServicesIsoftConfig
{
    SERVICES_USERS("servicio.usuarios", "usuariosServiceImpl"),
    SERVICES_USERS_PROXY("servicio.usuarios.proxy", "proxyUsuariosService"),
    SERVICES_DEFAILT("servicio.default", "usuariosServiceDefault");
    /**
     * Codigo del sexo
     **/
    private String code;
    /**
     * nombre del servicio expuesto por spring
     */
    private String nameService;

    /**
     * Constructor del Enum que inicializa valores
     *
     * @param code        identificador del elemento
     * @param nameService descripcion del recurso
     */
    EnumServicesIsoftConfig(String code, String nameService)
    {
        this.code = code;
        this.nameService = nameService;
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
    public String getNameService()
    {
        return nameService;
    }

    /**
     * Asigna una valor a la descripcion del error
     *
     * @param nameService
     */
    public void setNameService(String nameService)
    {
        this.nameService = nameService;
    }
}
