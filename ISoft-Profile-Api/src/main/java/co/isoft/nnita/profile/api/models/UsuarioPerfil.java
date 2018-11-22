package co.isoft.nnita.profile.api.models;

import co.isoft.nnita.profile.api.dao.BusinessClass;
import sun.misc.Perf;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Clase Modelo de la tabla ISOFT_USUARIO_PERFIL
 * Encargada de los proocesos de interaccion
 * con la tabla a traves de hibernate.
 *
 * @author Yaher Carrillo
 * @Date 13/07/2018
 */
@NamedQueries({
        @NamedQuery(name = "buscarPerfilesUsuarios", query = "select usr.perfil from UsuarioPerfil usr where usr.usuario.id =:PARAM_USER"),
        @NamedQuery(name = "findProfilesUsers", query = "select new co.isoft.nnita.profile.api.dto.output.ProfilesToUserOutDTO(usr.perfil.nombre_perfil,usr.habilitado)from UsuarioPerfil usr where usr.usuario.id =:PARAM_USER")
})

@Entity
@SequenceGenerator(name = "usuarioperfil-gen", sequenceName = "ISOFT_USUARIO_PERFIL_SEQ", initialValue = 1, allocationSize = 1)
@Table(name = "ISOFT_USUARIO_PERFIL")
public class UsuarioPerfil implements Serializable, BusinessClass
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "usuarioperfil-gen")
    @Column(name = "ID_USUARIO_PERFIL", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuarios usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERFIL", nullable = false)
    private Perfiles perfil;

    @Column(name = "HABILITADO", nullable = false)
    private Long habilitado;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Usuarios getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Usuarios usuario)
    {
        this.usuario = usuario;
    }

    public Perfiles getPerfil()
    {
        return perfil;
    }

    public void setPerfil(Perfiles perfil)
    {
        this.perfil = perfil;
    }

    public Long getHabilitado()
    {
        return habilitado;
    }

    public void setHabilitado(Long habilitado)
    {
        this.habilitado = habilitado;
    }

    /**
     * Constructor por defecto
     */
    public UsuarioPerfil (){
    }

    /**
     * Constructor que inicializa los valores de parametros
     * @param usuario usuario
     * @param perfil perfil
     * @param habilitado registro habilitado
     */
    public UsuarioPerfil (Usuarios usuario, Perfiles perfil, Long habilitado){
        this.usuario = usuario;
        this.perfil = perfil;
        this.habilitado = habilitado;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof UsuarioPerfil))
            return false;
        UsuarioPerfil other = (UsuarioPerfil) obj;
        if (id != other.id)
            return false;
        if (usuario == null)
        {
            if (other.usuario != null)
                return false;
        }
        if (perfil == null)
        {
            if (other.perfil != null)
                return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Usuario_Perfil [id=" + id + ", " + "Usuario=" + usuario.toString() + ", " + "Perfil: [" + perfil.toString() + "]" + ", " + "Habilitado=" + (habilitado == 1 ? "true" : "false") + "]";
    }
}
