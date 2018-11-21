package co.isoft.nnita.profile.api.models;

import co.isoft.nnita.profile.api.dao.BusinessClass;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Clase Modelo de la tabla ISOFT_USUARIOS
 * Encargada de los proocesos de interaccion
 * con la tabla a traves de hibernate.
 *
 * @author Yaher Carrillo
 * @Date 01/06/2018
 */
@NamedQueries({ @NamedQuery(name = "buscarUsuarioPorLogin", query = "select new co.isoft.nnita.profile.api.models.Usuarios(usuario.id,usuario.login,usuario.clave,usuario.fecha_ultima_visita,perfil.nombre_perfil) from Usuarios usuario INNER JOIN usuario.perfilDefault perfil where usuario.login =:PARAM_LOGIN  and usuario.habilitado = 1"),
        @NamedQuery(name = "buscarUsuarioPorCorreo", query = "from Usuarios usuario where usuario.email =:PARAM_CORREO and usuario.habilitado = 1"),
        @NamedQuery(name = "buscarUsuarioUltimoMesActivo", query = "from Usuarios usuario where usuario.fecha_ultima_visita between :PARAM_FINICIO and :PARAM_FFIN"),
        @NamedQuery(name = "buscarUsuarioActivosPorFecha", query = "from Usuarios usuario where usuario.fecha_ultima_visita between :PARAM_FINICIO and :PARAM_FFIN"),
        @NamedQuery(name = "buscarUsuarioPorEstado", query = "from Usuarios usuario where usuario.habilitado =:PARAM_ESTADO"),
        @NamedQuery(name = "buscarTodosLosUsuarios", query = "select new co.isoft.nnita.profile.api.gateways.models.request.users.UsuariosTodos(" + " usuario.login,usuario.nombres,usuario.apellidos,usuario.email,usuario.sexo,usuario.fecha_ultima_visita,usuario.habilitado"
                + ")from Usuarios usuario") })
@Entity
@SequenceGenerator(name = "usuarios-gen", sequenceName = "ISOFT_USUARIOS_SEQ", initialValue = 1, allocationSize = 1)
@Table(name = "ISOFT_USUARIOS")
public class Usuarios implements Serializable, BusinessClass
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "usuarios-gen")
    @Column(name = "ID_USUARIO", nullable = false)
    private Long id;

    @Size(max = 30)
    @Column(name = "LOGIN", nullable = false)
    private String login;

    @Size(max = 256)
    @Column(name = "CLAVE", nullable = false)
    private String clave;

    @Size(max = 50)
    @Column(name = "NOMBRE_USUARIO", nullable = false)
    private String nombres;

    @Size(max = 50)
    @Column(name = "APELLIDO_USUARIO", nullable = false)
    private String apellidos;

    @Size(max = 30)
    @Column(name = "EMAIL")
    private String email;

    @Size(max = 1)
    @Column(name = "SEXO")
    private String sexo;

    @Column(name = "FECHA_REGISTRO")
    private Date fecha_registro;

    @Column(name = "FECHA_ULTIMA_VIS")
    private Date fecha_ultima_visita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERFIL_DEFAULT")
    private Perfiles perfilDefault;

    @Column(name = "HABILITADO", nullable = false)
    private Long habilitado;

    /**
     * Constructor por defecto
     */
    public Usuarios()
    {
    }

    /**
     * Constructor que inicializa los atributos del objeto
     *
     * @param id               identificador unico de usuario
     * @param login               login de usuario
     * @param clave               clave de acceso
     * @param fecha_ultima_visita fecha de ultima visita
     * @param codePerfil       codigo de perfil por defecto
     */
    public Usuarios(Long id,String login, String clave, Date fecha_ultima_visita, String codePerfil)
    {
        this.id = id;
        this.login = login;
        this.clave = clave;
        this.fecha_ultima_visita = fecha_ultima_visita;

        Perfiles perfil = new Perfiles();
        perfil.setNombre_perfil(codePerfil);
        this.perfilDefault = perfil;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getClave()
    {
        return clave;
    }

    public void setClave(String clave)
    {
        this.clave = clave;
    }

    public String getNombres()
    {
        return nombres;
    }

    public void setNombres(String nombres)
    {
        this.nombres = nombres;
    }

    public String getApellidos()
    {
        return apellidos;
    }

    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getSexo()
    {
        return sexo;
    }

    public void setSexo(String sexo)
    {
        this.sexo = sexo;
    }

    public Date getFecha_registro()
    {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro)
    {
        this.fecha_registro = fecha_registro;
    }

    public Date getFecha_ultima_visita()
    {
        return fecha_ultima_visita;
    }

    public void setFecha_ultima_visita(Date fecha_ultima_visita)
    {
        this.fecha_ultima_visita = fecha_ultima_visita;
    }

    public Perfiles getPerfilDefault()
    {
        return perfilDefault;
    }

    public void setPerfilDefault(Perfiles perfilDefault)
    {
        this.perfilDefault = perfilDefault;
    }

    public Long getHabilitado()
    {
        return habilitado;
    }

    public void setHabilitado(Long habilitado)
    {
        this.habilitado = habilitado;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Usuarios))
            return false;
        Usuarios other = (Usuarios) obj;
        if (id != other.id)
            return false;
        if (login == null)
        {
            if (other.login != null)
                return false;
        }
        else if (!login.equals(other.login))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "Usuario [id=" + id + ", " + "Nombres=" + nombres + ", " + "Apellidos=" + apellidos + ", " + "Habilitado=" + ((habilitado != null ? habilitado : 1l) == 1 ? "true" : "false") + "]";
    }
}
