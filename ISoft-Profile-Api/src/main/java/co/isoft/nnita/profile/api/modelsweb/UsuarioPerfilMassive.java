package co.isoft.nnita.profile.api.modelsweb;

/**
 * Clase util para el manejo de usuarios y perfiles
 * de forma masiva.
 *
 * @author Yaher Carrillo
 * @date 11/10/2018
 */
public class UsuarioPerfilMassive
{
    /**
     * Login del usuario masivo a crear
     */
    private String loginname;
    /**
     *Nombres de usuario
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
     * @return
     */
    public String getNames()
    {
        return names;
    }

    /**
     * Asigna un valor a los nombres al item
     * @param names valor a asignar
     */
    public void setNames(String names)
    {
        this.names = names;
    }

    /**
     * Obtiene el apellido del usuario
     * @return apellido a usar
     */
    public String getLastname()
    {
        return lastname;
    }

    /**
     * Asigna un valor a los apellidos del usuario
     * @param lastname valor a asignar
     */
    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }
}
