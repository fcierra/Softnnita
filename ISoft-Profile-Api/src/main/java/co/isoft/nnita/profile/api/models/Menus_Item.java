package co.isoft.nnita.profile.api.models;

import co.isoft.nnita.profile.api.dao.BusinessClass;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Clase Modelo de la tabla ISOFT_MENU_ITEM
 * Encargada de los proocesos de interaccion
 * con la tabla a traves de hibernate.
 *
 * @author Yaher Carrillo
 * @Date 07/08/2018
 */
@NamedQueries({ @NamedQuery(name = "buscarMenuItemsPorMenu", query = "from Menus_Item item where item.menu_padre.id =:PARAM_PADRE and item.habilitado = 1"),
                @NamedQuery(name = "buscarItemsDeSistema", query = "from Menus_Item item where menu_padre.habilitado = 1 and item.habilitado = 1") })
@Entity
@SequenceGenerator(name = "menu-item-gen", sequenceName = "isoft_menu_id_item_menu_item_seq", initialValue = 1, allocationSize = 1)
@Table(name = "ISOFT_MENU_ITEM")
public class Menus_Item implements Serializable, BusinessClass
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "menu-item-gen")
    @Column(name = "ID_MENU_ITEM", nullable = false)
    private Long id;

    @Size(max = 120)
    @Column(name = "MENU_LINK", nullable = false)
    private String menu_link;

    @Size(max = 30)
    @Column(name = "MENU_LABEL", nullable = false)
    private String menu_label;

    @ManyToOne
    @JoinColumn(name = "ID_MENU_PADRE", nullable = true)
    private Menus menu_padre;

    @Column(name = "ID_MENU_HIJO", nullable = true)
    private long menu_hijo;

    @Column(name = "ORDEN", nullable = true)
    private int orden;

    @Size(max = 220)
    @Column(name = "REF_SECURITY", nullable = true)
    private String ref_security;

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

    public String getMenu_link()
    {
        return menu_link;
    }

    public void setMenu_link(String menu_link)
    {
        this.menu_link = menu_link;
    }

    public String getMenu_label()
    {
        return menu_label;
    }

    public void setMenu_label(String menu_label)
    {
        this.menu_label = menu_label;
    }

    public Menus getMenu_padre()
    {
        return menu_padre;
    }

    public void setMenu_padre(Menus menu_padre)
    {
        this.menu_padre = menu_padre;
    }

    public long getMenu_hijo()
    {
        return menu_hijo;
    }

    public void setMenu_hijo(long menu_hijo)
    {
        this.menu_hijo = menu_hijo;
    }

    public int getOrden()
    {
        return orden;
    }

    public void setOrden(int orden)
    {
        this.orden = orden;
    }

    public String getRef_security()
    {
        return ref_security;
    }

    public void setRef_security(String ref_security)
    {
        this.ref_security = ref_security;
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
        if (!(obj instanceof Menus_Item))
            return false;
        Menus_Item other = (Menus_Item) obj;
        if (id != other.id)
            return false;
        if (menu_label == null)
        {
            if (other.menu_label != null)
                return false;
        }
        else if (!menu_label.equals(other.menu_label))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "Menu Item [id=" + id + ", " + "Label=" + menu_label + ", Referencia Seguridad = " + ref_security + " " + "Orden=" + orden + ", " + "Habilitado=" + (habilitado == 1 ? "true" : "false") + "]";
    }
}
