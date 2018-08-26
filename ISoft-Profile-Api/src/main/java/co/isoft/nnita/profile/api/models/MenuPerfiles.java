package co.isoft.nnita.profile.api.models;

import co.isoft.nnita.profile.api.dao.BusinessClass;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Clase Modelo de la tabla ISOFT_MENUS_PERFIL
 * Encargada de almacenar la configuracion dinamica de la aplicacion
 * con la tabla a traves de hibernate.
 *
 * @author Yaher Carrillo
 * @Date 12/08/2018
 */

@NamedQueries({
        @NamedQuery(name = "buscarNavegacionesPerfiles", query = "Select menuperfil.menu from MenuPerfiles menuperfil INNER JOIN menuperfil.menu where menuperfil.perfil.id =:PARAM_PERFIL AND menuperfil.menu.habilitado = 1")
})
@Entity
@SequenceGenerator(name = "menu-perfil-gen", sequenceName = "isoft_menu_perfil_id_menu_perfil_seq", initialValue = 1, allocationSize = 1)
@Table(name = "ISOFT_MENUS_PERFIL")
public class MenuPerfiles implements Serializable, BusinessClass
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "menu-perfil-gen")
    @Column(name = "ID_MENU_PERFIL", nullable = false)
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
        if (!(obj instanceof MenuPerfiles))
            return false;
        MenuPerfiles other = (MenuPerfiles) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "Permiso [id=" + id + ", " + "Menu Item=" + menu.getMenu_label() + ", " + "Perfil: [" + perfil.getNombre_perfil() + "]";
    }
}
