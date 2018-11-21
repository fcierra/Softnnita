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
@NamedQueries({ @NamedQuery(name = "buscarPermisosUsuario", query = "from Permisos permiso where permiso.perfil.nombre_perfil =:PARAM_PERFIL and permiso.perfil.habilitado = 1"),
        @NamedQuery(name = "buscarItemsNavegacionDisponiblesPerfil", query = "Select permiso.menuItem from Permisos permiso INNER JOIN permiso.menuItem item where permiso.perfil.id =:PARAM_PERFIL and item.habilitado = 1 and item.menu_padre.id=:PARAM_MENU_PADRE"),
        @NamedQuery(name = "buscarPermisosPerfil", query = "Select new co.isoft.nnita.profile.api.gateways.models.request.users.PermisosDTO(permiso.id,item.menu_label) from Permisos permiso INNER JOIN permiso.menuItem item INNER JOIN permiso.perfil perfil where perfil.nombre_perfil =:PARAM_PERFIL and permiso.habilitado = 1 and item.habilitado = 1"),
})
@Entity
@SequenceGenerator(name = "permisos-gen", sequenceName = "ISOFT_PERMISOS_SEQ", initialValue = 1, allocationSize = 1)
@Table(name = "ISOFT_PERMISOS")
public class Permisos implements Serializable, BusinessClass
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "permisos-gen")
    @Column(name = "ID_PERMISO", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MENU_ITEM", nullable = false)
    private Menus_Item menuItem;

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
