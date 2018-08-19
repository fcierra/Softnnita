package co.isoft.nnita.profile.api.models;

import co.isoft.nnita.profile.api.dao.BusinessClass;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Clase Modelo de la tabla ISOFT_PERMISOS
 * Encargada de almacenar la configuracion dinamica de la aplicacion
 * con la tabla a traves de hibernate.
 *
 * @author Yaher Carrillo
 * @Date 12/08/2018
 */
@NamedQueries({
        @NamedQuery(name = "buscarPermisosUsuario", query = "from Permisos permiso where permiso.perfil.id =:PARAM_PERFIL"),
        @NamedQuery(name = "buscarNavegacionesPerfiles", query = "Select permiso.menu from Permisos permiso where permiso.perfil.id =:PARAM_PERFIL")
})
@Entity
@SequenceGenerator(name = "permisos-gen", sequenceName = "isoft_permisos_id_permisos_seq", initialValue = 1, allocationSize = 1)
@Table(name = "ISOFT_PERMISOS")
public class Permisos implements Serializable, BusinessClass
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "permisos-gen")
    @Column(name = "ID_PERMISO", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_MENU", nullable = false)
    private Menus menu;

    @ManyToOne
    @JoinColumn(name = "ID_PERFIL", nullable = false)
    private Perfiles perfil;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Menus getMenu()
    {
        return menu;
    }

    public void setMenu(Menus menu)
    {
        this.menu = menu;
    }

    public Perfiles getPerfil()
    {
        return perfil;
    }

    public void setPerfil(Perfiles perfil)
    {
        this.perfil = perfil;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Permisos))
            return false;
        Permisos other = (Permisos) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "Permiso [id=" + id + ", " + "Menu=" + menu.getMenu_label() + ", " + "Perfil: [" + perfil.getNombre_perfil() + "]";
    }
}
