package co.isoft.nnita.profile.configuration.navigation;

/**
 * Enum que describe las navegaciones de los objetos
 * que interactuen con la capa web. Este enum
 * describe los elementos navegables por la aplicacion
 * y permite tener a traves de un objeto la navegacion de
 * toda la capa web, evitando colocar quemado valores y reusar la c
 * configuracion.
 *
 * @author Yaher Carrillo
 * @Date 12/07/2018
 */
public enum EnumNavigationConfig
{
    LOGIN_PAGE("0", "index?faces-redirect=true", "/views/index.xhtml"),
    WELCOME_PAGE("1", "welcome", "/views/sec/welcome.xhtml"),
    DONT_ACCESS("2", null, "");
    /**
     * Codigo de la navegacion
     **/
    private String id;
    /**
     * nombre del elemento para su busqueda comun
     **/
    private String name;
    /**
     * Path del recurso
     */
    private String path;

    /**
     * Constructor del Enum que inicializa valores
     *
     * @param id   identificador del elemento
     * @param name nombre de navegacion del elemento
     * @param path ruta del recurso
     */
    EnumNavigationConfig(String id, String name, String path)
    {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    /**
     * Obtiene el id del elemento
     *
     * @return id
     */
    public String getId()
    {
        return id;
    }

    /**
     * Asigna un valor al elemento id
     *
     * @param id valor a asignar
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * Obtiene el nombre del item a navegar
     *
     * @return nombre de redireccion
     */
    public String getName()
    {
        return name;
    }

    /**
     * Asigna un valor al nombre de navegacion de un item
     *
     * @param name valor a asignar
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Obtiene el path del recurso al que se navega
     *
     * @return path de navegacion
     */
    public String getPath()
    {
        return path;
    }

    /**
     * Asigna el valor de ruta del archivo fisico de navegacion
     *
     * @param path valor a asignar
     */
    public void setPath(String path)
    {
        this.path = path;
    }
}
