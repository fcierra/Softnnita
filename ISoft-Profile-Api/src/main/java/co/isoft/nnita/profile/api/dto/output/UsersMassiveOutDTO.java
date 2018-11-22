package co.isoft.nnita.profile.api.dto.output;

/**
 * Clase util para el manejo de usuarios y perfiles
 * de forma masiva.
 *
 * @author Yaher Carrillo
 * @date 11/10/2018
 */
public class UsersMassiveOutDTO
{
    /**
     * Login del usuario masivo a crear
     */
    private String loginname;
    /**
     * Nombres de usuario
     */
    private String names;
    /**
     * Apellidos de usuario
     */
    private String lastname;
    /**
     * Perfil del usuario masivo a crear
     */
    private String codeperfil;
    /**
     * Descripcion de las respuesta de response
     */
    private String description;
    /**
     * Codigo de la transaccion
     */
    private String codetransaction;

    /**
     * Constructor por defecto
     */
    public UsersMassiveOutDTO()
    {
    }

    /**
     * Constructor que inicializa el valor del login de usuario
     * @param loginname login de usuario
     */
    public UsersMassiveOutDTO(String loginname)
    {
        this.loginname = loginname;
    }

    /**
     * Obtienen el loginname del usuario masivo a crear
     *
     * @return loginname
     */
    public String getLoginname()
    {
        return loginname;
    }

    /**
     * Asigna un loginname del usuario masivo a crear
     *
     * @param loginname valor a asignar
     */
    public void setLoginname(String loginname)
    {
        this.loginname = loginname;
    }

    /**
     * Obtiene el codigo del perfil
     * a asignar a un usuario masivo
     *
     * @return codigo de perfil a asignar
     */
    public String getCodeperfil()
    {
        return codeperfil;
    }

    /**
     * Asigna un valor al codigo de perfil
     * a asignar a un usuario masivo.
     *
     * @param codeperfil
     */
    public void setCodeperfil(String codeperfil)
    {
        this.codeperfil = codeperfil;
    }

    /**
     * Obtiene los nombres del usuario
     *
     * @return
     */
    public String getNames()
    {
        return names;
    }

    /**
     * Asigna un valor a los nombres al item
     *
     * @param names valor a asignar
     */
    public void setNames(String names)
    {
        this.names = names;
    }

    /**
     * Obtiene el apellido del usuario
     *
     * @return apellido a usar
     */
    public String getLastname()
    {
        return lastname;
    }

    /**
     * Asigna un valor a los apellidos del usuario
     *
     * @param lastname valor a asignar
     */
    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    /**
     * Obtiene la descripcion de las respuestas
     * rest
     *
     * @return descripcion de las respuestas
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Asigna un valor a la descripcion
     *
     * @param description valor a asignar.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Obtiene el codigo de la transaccion del item
     *
     * @return valor de la transaccion
     */
    public String getCodetransaction()
    {
        return codetransaction;
    }

    /**
     * Asigna un valor al codigo de la transaccion
     *
     * @param codetransaction valor a asignar
     */
    public void setCodetransaction(String codetransaction)
    {
        this.codetransaction = codetransaction;
    }
}
