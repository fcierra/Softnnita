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
        @NamedQuery(name = "buscarPermisosUsuario", query = "from Permisos permiso where permiso.perfil.id =:PARAM_PERFIL and permiso.perfil.habilitado = 1"),
        @NamedQuery(name = "buscarItemsNavegacionDisponiblesPerfil", query = "Select permiso.menuItem from Permisos permiso INNER JOIN permiso.menuItem item where permiso.perfil.id =:PARAM_PERFIL and item.habilitado = 1 and item.menu_padre.id=:PARAM_MENU_PADRE"),
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
    @JoinColumn(name = "ID_MENU_ITEM", nullable = false)
    private Menus_Item menuItem;

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

    public Menus_Item getMenuItem()
    {
        return menuItem;
    }

    public void setMenuItem(Menus_Item menuItem)
    {
        this.menuItem = menuItem;
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
        return "Permiso [id=" + id + ", " + "Menu Item=" + menuItem.getMenu_label() + ", " + "Perfil: [" + perfil.getNombre_perfil() + "]";
    }
}
