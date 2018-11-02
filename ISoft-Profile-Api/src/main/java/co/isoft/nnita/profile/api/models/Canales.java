package co.isoft.nnita.profile.api.models;

import co.isoft.nnita.profile.api.dao.BusinessClass;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Clase Modelo de la tabla ISOFT_MCANALES
 * Encargada de los proocesos de interaccion
 * con la tabla a traves de hibernate.
 *
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
@Entity
@SequenceGenerator(name = "canales-gen", sequenceName = "ISOFT_MCANALES_SEQ", initialValue = 1, allocationSize = 1)
@Table(name = "ISOFT_MCANALES")
public class Canales implements Serializable, BusinessClass
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "canales-gen")
    @Column(name = "ID_CANAL", nullable = false)
    private Long id;

    @Size(max = 20)
    @Column(name = "COD_CANAL", nullable = false)
    private String codigo_canal;

    @Size(max = 50)
    @Column(name = "NOMBRE_CANAL")
    private String nombre_canal;

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

    public String getCodigo_canal()
    {
        return codigo_canal;
    }

    public void setCodigo_canal(String codigo_canal)
    {
        this.codigo_canal = codigo_canal;
    }

    public String getNombre_canal()
    {
        return nombre_canal;
    }

    public void setNombre_canal(String nombre_canal)
    {
        this.nombre_canal = nombre_canal;
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
        if (!(obj instanceof Canales))
            return false;
        Canales other = (Canales) obj;
        if (id != other.id)
            return false;
        if (codigo_canal == null)
        {
            if (other.codigo_canal != null)
                return false;
        }
        else if (!codigo_canal.equals(other.codigo_canal))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "Canal [id=" + id + ", " + "Codigo=" + codigo_canal + ", " + "Habilitado=" + (habilitado == 1 ? "true" : "false") + "]";
    }
}
