package co.isoft.nnita.profile.api.models;

import co.isoft.nnita.profile.api.dao.BusinessClass;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Clase Modelo de la tabla ISOFT_MENU
 * Encargada de los proocesos de interaccion
 * con la tabla a traves de hibernate.
 *
 * @author Yaher Carrillo
 * @Date 07/08/2018
 */
@Entity
@SequenceGenerator(name = "menus-gen", sequenceName = "isoft_menu_id_menu_seq", initialValue = 1, allocationSize = 1)
@Table(name = "ISOFT_MENU")
public class Menus implements Serializable, BusinessClass
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "menus-gen")
    @Column(name = "ID_MENU", nullable = false)
    private Long id;

    @Size(max = 30)
    @Column(name = "MENU_LABEL", nullable = false)
    private String menu_label;

    @Column(name = "ORDEN", nullable = true)
    private int orden;

    @Size(max = 220)
    @Column(name = "REF_SECURITY", nullable = true)
    private String ref_security;

    @Transient
    private List<Menus_Item> items;

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

    public String getMenu_label()
    {
        return menu_label;
    }

    public void setMenu_label(String menu_label)
    {
        this.menu_label = menu_label;
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

    public List<Menus_Item> getItems()
    {
        return items;
    }

    public void setItems(List<Menus_Item> items)
    {
        this.items = items;
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
        if (!(obj instanceof Menus))
            return false;
        Menus other = (Menus) obj;
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
        return "Menu [id=" + id + ", " + "Label=" + menu_label + ", Referencia Seguridad = " + ref_security + " " + "Orden=" + orden + ", " + "Habilitado=" + (habilitado == 1 ? "true" : "false") + "]";
    }
}
