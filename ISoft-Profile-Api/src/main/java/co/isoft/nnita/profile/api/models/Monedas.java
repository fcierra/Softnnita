package co.isoft.nnita.profile.api.models;

import co.isoft.nnita.profile.api.dao.BusinessClass;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Clase Modelo de la tabla ISOFT_MMONEDAS
 * Encargada de los proocesos de interaccion
 * con la tabla a traves de hibernate.
 *
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
@Entity
@SequenceGenerator(name = "monedas-gen", sequenceName = "ISOFT_MMONEDAS_SEQ", initialValue = 1, allocationSize = 1)
@Table(name = "ISOFT_MMONEDAS")
public class Monedas implements Serializable, BusinessClass
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "monedas-gen")
    @Column(name = "ID_MONEDA", nullable = false)
    private Long id;

    @Size(max = 20)
    @Column(name = "COD_MONEDA", nullable = false)
    private String codigo_moneda;

    @Size(max = 50)
    @Column(name = "NOMBRE_MONEDA", nullable = false)
    private String nombre_moneda;

    @Column(name = "HABILITADO", nullable = false)
    private int habilitado;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCodigo_moneda()
    {
        return codigo_moneda;
    }

    public void setCodigo_moneda(String codigo_moneda)
    {
        this.codigo_moneda = codigo_moneda;
    }

    public String getNombre_moneda()
    {
        return nombre_moneda;
    }

    public void setNombre_moneda(String nombre_moneda)
    {
        this.nombre_moneda = nombre_moneda;
    }

    public int getHabilitado()
    {
        return habilitado;
    }

    public void setHabilitado(int habilitado)
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
        if (!(obj instanceof Monedas))
            return false;
        Monedas other = (Monedas) obj;
        if (id != other.id)
            return false;
        if (codigo_moneda == null)
        {
            if (other.codigo_moneda != null)
                return false;
        }
        else if (!codigo_moneda.equals(other.codigo_moneda))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "Moneda [id=" + id + ", " + "Codigo=" + codigo_moneda + ", " + ", " + "Habilitado=" + (habilitado == 1 ? "true" : "false") + "]";
    }
}
