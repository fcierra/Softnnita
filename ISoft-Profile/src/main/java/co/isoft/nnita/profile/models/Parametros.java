package co.isoft.nnita.profile.models;

import co.isoft.nnita.profile.dao.BusinessClass;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Clase Modelo de la tabla ISOFT_PARAMETROS
 * Encargada de almacenar la configuracion dinamica de la aplicacion
 * con la tabla a traves de hibernate.
 *
 * @author Yaher Carrillo
 * @Date 10/07/2018
 */
@Entity
@SequenceGenerator(name = "parametros-gen", sequenceName = "isoft_parametros_id_parametro_seq", initialValue = 1, allocationSize = 1)
@Table(name = "ISOFT_PARAMETROS")
public class Parametros implements Serializable, BusinessClass
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "parametros-gen")
    @Column(name = "ID_PARAMETRO", nullable = false)
    private Long id;

    @Size(max = 256)
    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;

    @Size(max = 256)
    @Column(name = "VALOR", nullable = false)
    private String valor;

    @Size(max = 64)
    @Column(name = "TIPO_DATO", nullable = false)
    private String tipo_dato;

    @Size(max = 128)
    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Size(max = 64)
    @Column(name = "GRUPO", nullable = false)
    private String grupo;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getValor()
    {
        return valor;
    }

    public void setValor(String valor)
    {
        this.valor = valor;
    }

    public String getTipo_dato()
    {
        return tipo_dato;
    }

    public void setTipo_dato(String tipo_dato)
    {
        this.tipo_dato = tipo_dato;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getGrupo()
    {
        return grupo;
    }

    public void setGrupo(String grupo)
    {
        this.grupo = grupo;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Parametros))
            return false;
        Parametros other = (Parametros) obj;
        if (id != other.id)
            return false;
        if (nombre == null)
        {
            if (other.nombre != null)
                return false;
        }
        else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "Parametro [id=" + id + ", " + "Nombre=" + nombre + ", " + "Tipo Dato: [" + tipo_dato + "]" + ", " + "Grupo=" + grupo + "]";
    }
}
