package co.isoft.nnita.profile.api.gateways.models.request.profile;

import java.util.List;

/**
 * Modelo request, para la asociacion
 * de perfiles a un usuario isoft
 *
 * @author Yaher Carrillo
 * @date 09/10/2018
 */
public class RequestAddProfileUser
{
    /**
     * Login de usuario a crear
     */
    private String loginname;
    /**
     * Listado de los perfiles a asociar
     */
    private List<String> codesProfiles;

    /**
     * Obtiene el loginname del usuario
     *
     * @return loginname
     */
    public String getLoginname()
    {
        return loginname;
    }

    /**
     * Asigna un valor al loginname del usuario
     *
     * @param loginname valor a asignar
     */
    public void setLoginname(String loginname)
    {
        this.loginname = loginname;
    }

    /**
     * Listado de codigos de los perfiles a asociar
     *
     * @return listado a asociar
     */
    public List<String> getCodesProfiles()
    {
        return codesProfiles;
    }

    /**
     * Asigna un valor al listado de codigos
     *
     * @param codesProfiles valor a asignar
     */
    public void setCodesProfiles(List<String> codesProfiles)
    {
        this.codesProfiles = codesProfiles;
    }
}
