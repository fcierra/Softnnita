package co.isoft.nnita.profile.api.models;

import co.isoft.nnita.profile.api.dao.BusinessClass;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Clase Modelo de la tabla ISOFT_MEVENTOS
 * Encargada de los proocesos de interaccion
 * con la tabla a traves de hibernate.
 *
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
@Entity
@SequenceGenerator(name = "eventos-gen", sequenceName = "isoft_meventos_id_evento_seq", initialValue = 1, allocationSize = 1)
@Table(name = "ISOFT_MEVENTOS")
public class Eventos implements Serializable, BusinessClass
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "eventos-gen")
    @Column(name = "ID_EVENTO", nullable = false)
    private Long id;

    @Size(max = 20)
    @Column(name = "COD_EVENTO", nullable = false)
    private String codigo_evento;

    @Size(max = 50)
    @Column(name = "NOMBRE_EVENTO")
    private String nombre_evento;

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

    public String getCodigo_evento()
    {
        return codigo_evento;
    }

    public void setCodigo_evento(String codigo_evento)
    {
        this.codigo_evento = codigo_evento;
    }

    public String getNombre_evento()
    {
        return nombre_evento;
    }

    public void setNombre_evento(String nombre_evento)
    {
        this.nombre_evento = nombre_evento;
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
        if (!(obj instanceof Eventos))
            return false;
        Eventos other = (Eventos) obj;
        if (id != other.id)
            return false;
        if (codigo_evento == null)
        {
            if (other.codigo_evento != null)
                return false;
        }
        else if (!codigo_evento.equals(other.codigo_evento))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "Evento [id=" + id + ", " + "Codigo=" + codigo_evento + ", " + "Habilitado=" + (habilitado == 1 ? "true" : "false") + "]";
    }
}
