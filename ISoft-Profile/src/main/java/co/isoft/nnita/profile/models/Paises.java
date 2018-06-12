package co.isoft.nnita.profile.models;

import co.isoft.nnita.profile.dao.BusinessClass;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Clase Modelo de la tabla ISOFT_MPAISES
 * Encargada de los proocesos de interaccion
 * con la tabla a traves de hibernate.
 *
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
@Entity
@SequenceGenerator(name = "paises-gen", sequenceName = "isoft_mpaises_id_pais_seq", initialValue = 1, allocationSize = 1)
@Table(name = "ISOFT_MPAISES")
public class Paises implements Serializable, BusinessClass
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "paises-gen")
    @Column(name = "ID_PAIS", nullable = false)
    private Long id;

    @Size(max = 20)
    @Column(name = "COD_PAIS", nullable = false)
    private String codigo_pais;

    @Size(max = 50)
    @Column(name = "NOMBRE_PAIS")
    private String nombre_pais;

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

    public String getCodigo_pais()
    {
        return codigo_pais;
    }

    public void setCodigo_pais(String codigo_pais)
    {
        this.codigo_pais = codigo_pais;
    }

    public String getNombre_pais()
    {
        return nombre_pais;
    }

    public void setNombre_pais(String nombre_pais)
    {
        this.nombre_pais = nombre_pais;
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
        if (!(obj instanceof Paises))
            return false;
        Paises other = (Paises) obj;
        if (id != other.id)
            return false;
        if (codigo_pais == null)
        {
            if (other.codigo_pais != null)
                return false;
        }
        else if (!codigo_pais.equals(other.codigo_pais))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "Pais [id=" + id + ", " + "Codigo=" + codigo_pais + ", " + "Habilitado=" + (habilitado == 1 ? "true" : "false") + "]";
    }
}
