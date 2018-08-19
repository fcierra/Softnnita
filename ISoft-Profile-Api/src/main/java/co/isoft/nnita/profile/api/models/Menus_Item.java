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


    @Column(name = "ID_MENU_PADRE", nullable = true)
    private long menu_padre;

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
