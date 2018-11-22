package co.isoft.nnita.profile.api.dto.input;

import co.isoft.nnita.profile.api.dto.output.UsersMassiveOutDTO;

import java.util.List;

/**
 * Modelo request, para la creacion de usuarios masivamente
 * ISoftProfile.
 *
 * @author Yaher Carrillo
 * @date 11/10/2018
 */
public class NewUsersMassiveInputDTO
{
    /**
     * Claves de usuarios
     */
    private String password;
    /**
     * Nombres de usuario
     */
    private List<UsersMassiveOutDTO> usuariosYPerfil;

    /**
     * Obtiene la clave a asignar a todos los usuarios
     * que van a ser creados de forma masiva.
     *
     * @return clave masiva
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Asigna un valor a la clave a asignar
     * de forma masiva
     *
     * @param password valor a asignar
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Lista de usuarios a crear de forma masiva
     *
     * @return listado de usuarios a crear
     */
    public List<UsersMassiveOutDTO> getUsuariosYPerfil()
    {
        return usuariosYPerfil;
    }

    /**
     * Asigna un valor al listado de usuarios a crear de forma masiva
     *
     * @param usuariosYPerfil valor a asignar
     */
    public void setUsuariosYPerfil(List<UsersMassiveOutDTO> usuariosYPerfil)
    {
        this.usuariosYPerfil = usuariosYPerfil;
    }
}