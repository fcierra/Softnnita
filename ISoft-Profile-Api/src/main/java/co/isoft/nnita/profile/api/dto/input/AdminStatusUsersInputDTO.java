package co.isoft.nnita.profile.api.dto.input;

/**
 * Modelo request, para la creacion de usuarios
 * ISoftProfile
 *
 * @author Yaher Carrillo
 * @date 09/09/2018
 */
public class AdminStatusUsersInputDTO
{
    /**
     * Login de usuario a crear
     */
    private String loginname;
    /**
     * Nombres de usuario
     */
    private Long status;

    /**
     * Constructor por defecto
     */
    public AdminStatusUsersInputDTO()
    {
    }

    /**
     * Constructor que inicializa los valores del objeto.
     *
     * @param loginname login de usuario
     * @param status    status a asignar
     */
    public AdminStatusUsersInputDTO(String loginname, Long status)
    {
        this.loginname = loginname;
        this.status = status;
    }

    /**
     * Obtiene el login de usuario
     *
     * @return login de usuario
     */
    public String getLoginname()
    {
        return loginname;
    }

    /**
     * Asigna un valor al login de usuario
     *
     * @param loginname valor a asignar
     */
    public void setLoginname(String loginname)
    {
        this.loginname = loginname;
    }

    /**
     * Obtiene el status de asignacion
     *
     * @return estatus de asignacion.
     */
    public Long getStatus()
    {
        return status;
    }

    /**
     * Asigna un valor al status de asignacion del usuario
     *
     * @param status valor a asignar
     */
    public void setStatus(Long status)
    {
        this.status = status;
    }
}
