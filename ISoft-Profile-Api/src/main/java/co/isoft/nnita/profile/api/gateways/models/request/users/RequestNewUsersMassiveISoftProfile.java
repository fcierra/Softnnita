package co.isoft.nnita.profile.api.gateways.models.request.users;

import co.isoft.nnita.profile.api.modelsweb.UsuarioPerfilMassive;

import java.util.List;

/**
 * Modelo request, para la creacion de usuarios masivamente
 * ISoftProfile.
 *
 * @author Yaher Carrillo
 * @date 11/10/2018
 */
public class RequestNewUsersMassiveISoftProfile
{
    /**
     * Usuarios coordinador que realiza la actividad
     */
    private String loginusertransaction;
    /**
     * Claves de usuarios
     */
    private String password;

    /**
     * Usuarios que realiza la transaccion
     *
     * @return login de usuarios
     */
    public String getLoginusertransaction()
    {
        return loginusertransaction;
    }

    /**
     * Asigna un valor al usuario que realiza la transaccion
     *
     * @param loginusertransaction valor a asignar
     */
    public void setLoginusertransaction(String loginusertransaction)
    {
        this.loginusertransaction = loginusertransaction;
    }

    /**
     * Nombres de usuario
     */
    private List<UsuarioPerfilMassive> usuariosYPerfil;

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
    public List<UsuarioPerfilMassive> getUsuariosYPerfil()
    {
        return usuariosYPerfil;
    }

    /**
     * Asigna un valor al listado de usuarios a crear de forma masiva
     *
     * @param usuariosYPerfil valor a asignar
     */
    public void setUsuariosYPerfil(List<UsuarioPerfilMassive> usuariosYPerfil)
    {
        this.usuariosYPerfil = usuariosYPerfil;
    }
}