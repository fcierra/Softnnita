package co.isoft.nnita.profile.models;

import co.isoft.nnita.profile.dao.BusinessClass;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Clase Modelo de la tabla ISOFT_MBANCOS
 * Encargada de los proocesos de interaccion
 * con la tabla a traves de hibernate.
 *
 * @author Yaher Carrillo
 * @Date 06/06/2018
 */
@Entity
@SequenceGenerator(name = "bancos-gen", sequenceName = "isoft_mbancos_id_banco_seq", initialValue = 1, allocationSize = 1)
@Table(name = "ISOFT_MBANCOS")
public class Bancos implements Serializable, BusinessClass
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "bancos-gen")
    @Column(name = "ID_BANCO", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_PAIS", nullable = false)
    private Paises pais;

    @Size(max = 20)
    @Column(name = "COD_BANCO", nullable = false)
    private String codigo_banco;

    @Size(max = 50)
    @Column(name = "NOMBRE_BANCO")
    private String nombre_banco;

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

    public Paises getPais()
    {
        return pais;
    }

    public void setPais(Paises pais)
    {
        this.pais = pais;
    }

    public String getCodigo_banco()
    {
        return codigo_banco;
    }

    public void setCodigo_banco(String codigo_banco)
    {
        this.codigo_banco = codigo_banco;
    }

    public String getNombre_banco()
    {
        return nombre_banco;
    }

    public void setNombre_banco(String nombre_banco)
    {
        this.nombre_banco = nombre_banco;
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
        if (!(obj instanceof Bancos))
            return false;
        Bancos other = (Bancos) obj;
        if (id != other.id)
            return false;
        if (codigo_banco == null)
        {
            if (other.codigo_banco != null)
                return false;
        }
        else if (!codigo_banco.equals(other.codigo_banco))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "Banco [id=" + id + ", " + "Codigo=" + codigo_banco + ", " + "Pais: [" + pais.getCodigo_pais() + "]" + ", " + "Habilitado=" + (habilitado == 1 ? "true" : "false") + "]";
    }
}
