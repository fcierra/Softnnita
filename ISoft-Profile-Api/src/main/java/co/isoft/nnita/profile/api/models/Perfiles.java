package co.isoft.nnita.profile.api.models;

import co.isoft.nnita.profile.api.dao.BusinessClass;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Clase Modelo de la tabla ISOFT_PERFILES
 * Encargada de los proocesos de interaccion
 * con la tabla a traves de hibernate.
 *
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
@NamedQueries({
                @NamedQuery(name = "findProfilesSystem", query = "from Perfiles perfil where perfil.habilitado =:PARAM_STATUS and perfil.administrador = 0 "),
            })
@Entity
@SequenceGenerator(name = "perfiles-gen", sequenceName = "isoft_perfiles_id_perfil_seq", initialValue = 1, allocationSize = 1)
@Table(name = "ISOFT_PERFILES")
public class Perfiles implements Serializable, BusinessClass
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "perfiles-gen")
    @Column(name = "ID_PERFIL", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "NOMBRE_PERFIL", nullable = false)
    private String nombre_perfil;

    @Column(name = "ADMINISTRADOR")
    private int administrador;

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

    public String getNombre_perfil()
    {
        return nombre_perfil;
    }

    public void setNombre_perfil(String nombre_perfil)
    {
        this.nombre_perfil = nombre_perfil;
    }

    public int getAdministrador()
    {
        return administrador;
    }

    public void setAdministrador(int administrador)
    {
        this.administrador = administrador;
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
        if (!(obj instanceof Perfiles))
            return false;
        Perfiles other = (Perfiles) obj;
        if (id != other.id)
            return false;
        if (nombre_perfil == null)
        {
            if (other.nombre_perfil != null)
                return false;
        }
        else if (!nombre_perfil.equals(other.nombre_perfil))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "Perfil [id=" + id + ", " + "Nombre=" + nombre_perfil + ", " + ", " + "Administrador=" + (habilitado == 1 ? "true" : "false") + ", " + "Habilitado=" + (habilitado == 1 ? "true" : "false") + "]";
    }
}
