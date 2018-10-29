package co.isoft.nnita.profile.api.modelsweb;

import java.util.Date;

/**
 * Modelo no relacional
 * que ejemplifica los datos de todos los
 * usuarios del sistema para su consulta
 *
 * @author Yaher Carrillo
 * @since 26/10/2018
 */
public class UsuariosTodos
{
    /**
     * login de acceso de usaurio
     */
    private String loginname;

    /**
     * Nombres del usuario
     */
    private String nombres;

    /**
     * Apellidos del usuario
     */
    private String apellidos;

    /**
     * Correo del usuario
     */
    private String email;

    /**
     * Sexo del usuario
     */
    private String sexo;

    /**
     * Fecha de ultima visita al sistema
     */
    private Date fecha_ultima_visita;

    /**
     * Indica el status del usuario
     */
    private Long habilitado;

    /**
     * Constructor por defecto
     */
    public UsuariosTodos()
    {
    }

    /**
     * Constructor que inicializa los valores del objeto
     *
     * @param loginname           login del usuario
     * @param nombres             nombres del usuario
     * @param apellidos           apellidos del usuario
     * @param email               correo del usuario
     * @param sexo                sexo del usuario
     * @param fecha_ultima_visita fecha de ultima visita del usuario
     * @param habilitado          status del usuario
     */
    public UsuariosTodos(String loginname, String nombres, String apellidos, String email, String sexo, Date fecha_ultima_visita, Long habilitado)
    {
        this.loginname = loginname;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.sexo = sexo;
        this.fecha_ultima_visita = fecha_ultima_visita;
        this.habilitado = habilitado;
    }

    /**
     * Obtiene el login del usuario
     *
     * @return login del usuario
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
     * Obtiene los nombres del usuario
     *
     * @return nombres del usuario
     */
    public String getNombres()
    {
        return nombres;
    }

    /**
     * Asigna un valor a los nombres del usuario
     *
     * @param nombres valor a asignar
     */
    public void setNombres(String nombres)
    {
        this.nombres = nombres;
    }

    /**
     * Obtiene los apellidos del usuario
     *
     * @return apellidos
     */
    public String getApellidos()
    {
        return apellidos;
    }

    /**
     * Asigna un valor a los apellidos del usuario
     *
     * @param apellidos valor a asignar
     */
    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el correo del usuario
     *
     * @return
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Asigna un valor al correo del usuario
     *
     * @param email valor a asignar
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Asigna un valor al sexo del usuario
     *
     * @return sexo del usuario
     */
    public String getSexo()
    {
        return sexo;
    }

    /**
     * Asigna el sexo del usuario
     *
     * @param sexo valor a asignar
     */
    public void setSexo(String sexo)
    {
        this.sexo = sexo;
    }

    /**
     * Obtiene la fecha de ultima visita.
     *
     * @return fecha de ultima visita
     */
    public Date getFecha_ultima_visita()
    {
        return fecha_ultima_visita;
    }

    /**
     * Asigna un valor de fecha de ultima visita
     *
     * @param fecha_ultima_visita valor a asignar
     */
    public void setFecha_ultima_visita(Date fecha_ultima_visita)
    {
        this.fecha_ultima_visita = fecha_ultima_visita;
    }

    /**
     * Obtiene el status del usuario
     *
     * @return estatus
     */
    public Long getHabilitado()
    {
        return habilitado;
    }

    /**
     * Asigna un valor al estatus del usuario
     *
     * @param habilitado valor a asignar
     */
    public void setHabilitado(Long habilitado)
    {
        this.habilitado = habilitado;
    }
}
