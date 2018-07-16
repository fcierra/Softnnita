/*
 * ESTE COMPONENTE FUE REALIZADO BAJO LA METODOLOGIA DE DESARROLLO DE
 * INFORMATICA & TECNOLOGIA Y SE ENCUENTRA PROTEGIDO POR LAS LEYES DE
 * DERECHOS DE AUTOR.
 */
package co.isoft.nnita.profile.api.modelsweb;

import co.isoft.nnita.profile.api.models.Perfiles;
import co.isoft.nnita.profile.api.models.Usuarios;

import java.io.Serializable;
import java.util.List;

/**
 * Objeto con los datos necesarios para iniciar una sesion en ISoftProfile.
 *
 * @author Yaher Carrillo
 * @date 12/07/2018
 */
public class DatosSesionUsuario implements Serializable
{
    /**
     * Version de la clase
     */
    private static final long serialVersionUID = 1L;
    /**
     * Del usuario en la sesion
     */
    private Long id;
    /**
     * Usuario que inicia la sesion
     */
    private Usuarios usuario;
    /**
     * Listado de perfiles.
     */
    private List<Perfiles> perfiles;
    /**
     * ID de la Sesion de la aplicacion WEB
     */
    private String idSesionWeb;

    /**
     * Constructor por defecto
     */
    public DatosSesionUsuario()
    {
    }

    /**
     * Constructor de la clase que inicializa los atributos
     *
     * @param id          identificador de la sesion de usuario
     * @param usuario     usuario
     * @param idSesionWeb identificacion web de la sesion iniciada
     */
    public DatosSesionUsuario(Long id, Usuarios usuario, String idSesionWeb)
    {
        this.id = id;
        this.usuario = usuario;
        this.idSesionWeb = idSesionWeb;
    }

    /**
     * Obtiene la identificacion de sesion
     *
     * @return identificador
     */
    public Long getId()
    {
        return id;
    }

    /**
     * Asigna la identificacion de sesion
     *
     * @param id
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Obtiene la entidad usuario
     *
     * @return Usuario almacenado
     */
    public Usuarios getUsuario()
    {
        return usuario;
    }

    /**
     * Asigna la identificacion de usuario
     *
     * @param usuario usuario a asignar
     */
    public void setUsuario(Usuarios usuario)
    {
        this.usuario = usuario;
    }

    /**
     * Obtiene la identificacion web de la sesion
     *
     * @return idSesionWeb
     */
    public String getIdSesionWeb()
    {
        return idSesionWeb;
    }

    /**
     * Asigna la identificacion web a la sesion
     *
     * @param idSesionWeb identificador web
     */
    public void setIdSesionWeb(String idSesionWeb)
    {
        this.idSesionWeb = idSesionWeb;
    }

    /**
     * Obtiene el listado de perfiles
     *
     * @return listado
     */
    public List<Perfiles> getPerfiles()
    {
        return perfiles;
    }

    /**
     * Asigna un valor al listado de perfiles.
     *
     * @param perfiles valor a asignar
     */
    public void setPerfiles(List<Perfiles> perfiles)
    {
        this.perfiles = perfiles;
    }
}
