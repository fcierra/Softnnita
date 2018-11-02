package co.isoft.nnita.profile.api.models;

import co.isoft.nnita.profile.api.dao.BusinessClass;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Clase Modelo de la tabla ISOFT_DETALLE_BITACORA
 * Encargada de los proocesos de interaccion
 * con la tabla a traves de hibernate.
 *
 * @author Yaher Carrillo
 * @Date 11/06/2018
 */
@Entity
@SequenceGenerator(name = "detalle-bitacora-gen", sequenceName = "ISOFT_DETALLE_BITACORA_SEQ", initialValue = 1, allocationSize = 1)
@Table(name = "ISOFT_DETALLE_BITACORA")
public class DetalleBitacora implements Serializable, BusinessClass
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "detalle-bitacora-gen")
    @Column(name = "ID_DETALLE_BITACORA", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_BITACORA", nullable = false)
    private Bitacora bitacora;

    @ManyToOne
    @JoinColumn(name = "ID_EVENTO", nullable = false)
    private Eventos evento;

    @Column(name = "HORA_INICIO")
    private Long hora_inicio;

    @Column(name = "HORA_FIN")
    private Long hora_fin;

    @Size(max = 260)
    @Column(name = "DETALLE_VALOR_INICIO")
    private String detalle_valor_inicio;

    @Size(max = 260)
    @Column(name = "DETALLE_VALOR_FIN")
    private String detalle_valor_fin;

    @Size(max = 260)
    @Column(name = "DESCRIPCION")
    private String descripcion;

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

    public Bitacora getBitacora()
    {
        return bitacora;
    }

    public void setBitacora(Bitacora bitacora)
    {
        this.bitacora = bitacora;
    }

    public Eventos getEvento()
    {
        return evento;
    }

    public void setEvento(Eventos evento)
    {
        this.evento = evento;
    }

    public Long getHora_inicio()
    {
        return hora_inicio;
    }

    public void setHora_inicio(Long hora_inicio)
    {
        this.hora_inicio = hora_inicio;
    }

    public Long getHora_fin()
    {
        return hora_fin;
    }

    public void setHora_fin(Long hora_fin)
    {
        this.hora_fin = hora_fin;
    }

    public String getDetalle_valor_inicio()
    {
        return detalle_valor_inicio;
    }

    public void setDetalle_valor_inicio(String detalle_valor_inicio)
    {
        this.detalle_valor_inicio = detalle_valor_inicio;
    }

    public String getDetalle_valor_fin()
    {
        return detalle_valor_fin;
    }

    public void setDetalle_valor_fin(String detalle_valor_fin)
    {
        this.detalle_valor_fin = detalle_valor_fin;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
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
        if (!(obj instanceof DetalleBitacora))
            return false;
        DetalleBitacora other = (DetalleBitacora) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "Detalle Bitacora [id=" + id + "]" + "Evento [nombre=" + evento.getNombre_evento() + "]";
    }
}
