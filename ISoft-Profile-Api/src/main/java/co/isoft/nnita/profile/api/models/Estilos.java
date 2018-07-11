package co.isoft.nnita.profile.api.models;

import co.isoft.nnita.profile.api.dao.BusinessClass;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Clase Modelo de la tabla ISOFT_MESTILOS
 * Encargada de los proocesos de interaccion
 * con la tabla a traves de hibernate.
 *
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
@Entity
@SequenceGenerator(name = "estilos-gen", sequenceName = "isoft_mestilos_id_estilo_seq", initialValue = 1, allocationSize = 1)
@Table(name = "ISOFT_MESTILOS")
public class Estilos implements Serializable, BusinessClass
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "estilos-gen")
    @Column(name = "ID_ESTILO", nullable = false)
    private Long id;

    @Size(max = 20)
    @Column(name = "COD_ESTILO", nullable = false)
    private String codigo_estilo;

    @Size(max = 50)
    @Column(name = "NOMBRE_ESTILO")
    private String nombre_estilo;

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

    public String getCodigo_estilo()
    {
        return codigo_estilo;
    }

    public void setCodigo_estilo(String codigo_estilo)
    {
        this.codigo_estilo = codigo_estilo;
    }

    public String getNombre_estilo()
    {
        return nombre_estilo;
    }

    public void setNombre_estilo(String nombre_estilo)
    {
        this.nombre_estilo = nombre_estilo;
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
        if (!(obj instanceof Estilos))
            return false;
        Estilos other = (Estilos) obj;
        if (id != other.id)
            return false;
        if (codigo_estilo == null)
        {
            if (other.codigo_estilo != null)
                return false;
        }
        else if (!codigo_estilo.equals(other.codigo_estilo))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "Estilo [id=" + id + ", " + "Codigo=" + codigo_estilo + ", " + "Habilitado=" + (habilitado == 1 ? "true" : "false") + "]";
    }
}
