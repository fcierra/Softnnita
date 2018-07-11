package co.isoft.nnita.profile.api.models;

import co.isoft.nnita.profile.api.dao.BusinessClass;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Clase Modelo de la tabla ISOFT_BITACORA
 * Encargada de los proocesos de interaccion
 * con la tabla a traves de hibernate.
 *
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
@Entity
@SequenceGenerator(name = "bitacora-gen", sequenceName = "isoft_bitacora_id_bitacora_seq", initialValue = 1, allocationSize = 1)
@Table(name = "ISOFT_BITACORA")
public class Bitacora implements Serializable, BusinessClass
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "bitacora-gen")
    @Column(name = "ID_BITACORA", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_EVENTO", nullable = false)
    private Eventos evento;

    @ManyToOne
    @JoinColumn(name = "ID_CANAL", nullable = false)
    private Canales canal;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuarios usuario;


    @Column(name = "FECHA_REGISTRO")
    private Date fecha_registro;

    @Column(name = "FECHA_REGISTRO_SEGUNDOS")
    private Long fecha_registro_segundos;

    @Size(max = 50)
    @Column(name = "IP")
    private String ip;

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

    public Eventos getEvento()
    {
        return evento;
    }

    public void setEvento(Eventos evento)
    {
        this.evento = evento;
    }

    public Canales getCanal()
    {
        return canal;
    }

    public void setCanal(Canales canal)
    {
        this.canal = canal;
    }

    public Usuarios getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Usuarios usuario)
    {
        this.usuario = usuario;
    }

    public Date getFecha_registro()
    {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro)
    {
        this.fecha_registro = fecha_registro;
    }

    public Long getFecha_registro_segundos()
    {
        return fecha_registro_segundos;
    }

    public void setFecha_registro_segundos(Long fecha_registro_segundos)
    {
        this.fecha_registro_segundos = fecha_registro_segundos;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
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
        if (!(obj instanceof Bitacora))
            return false;
        Bitacora other = (Bitacora) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "Bitacora [id=" + id + "]" + "Usuario [login=" + usuario.getLogin() + "]";
    }
}
