package co.isoft.nnita.profile.api.dto.input;

/**
 * Modelo para la creacion de perfiles
 *
 * @author Yaher Carrillo
 * @date 13/11/2018
 */
public class NewProfileInputDTO
{
    /**
     * Nombre del perfil a ser modificado
     */
    private String nombreperfil;

    /**
     * nombre del perfil modificado
     */
    private String nombre_perfil_despues;
    /**
     * Estatus del perfil
     */
    private Long habilitado;

    /**
     * Constructor por defecto del objeto
     */
    public NewProfileInputDTO()
    {
    }

    /**
     * Constructor que inicaliza los valores del objeto
     *
     * @param nombreperfil   nombre del perfil
     * @param nombre_perfil_despues nombre del perfil a ser modificado
     * @param habilitado            status del perfil
     */
    public NewProfileInputDTO(String nombreperfil, String nombre_perfil_despues, Long habilitado)
    {
        this.nombreperfil = nombreperfil;
        this.nombre_perfil_despues = nombre_perfil_despues;
        this.habilitado = habilitado;
    }

    /**
     * Obtiene el nombre del perfil
     *
     * @return nombre del perfil
     */
    public String getNombreperfil()
    {
        return nombreperfil;
    }

    /**
     * Asigna un valor al nombre del perfil
     *
     * @param nombreperfil valor a asignar
     */
    public void setNombreperfil(String nombreperfil)
    {
        this.nombreperfil = nombreperfil;
    }

    /**
     * Obtienen el nombre del perfil una vez modificado
     *
     * @return nombre de perfil modificado
     */
    public String getNombre_perfil_despues()
    {
        return nombre_perfil_despues;
    }

    /**
     * Asigna un valor al perfil a ser modificado
     *
     * @param nombre_perfil_despues valor a asignar
     */
    public void setNombre_perfil_despues(String nombre_perfil_despues)
    {
        this.nombre_perfil_despues = nombre_perfil_despues;
    }

    /**
     * Obtiene el status del perfil
     *
     * @return status del perfil
     */
    public Long getHabilitado()
    {
        return habilitado;
    }

    /**
     * Asigna un valor al status del perfil
     *
     * @param habilitado valor a asignar
     */
    public void setHabilitado(Long habilitado)
    {
        this.habilitado = habilitado;
    }
}
