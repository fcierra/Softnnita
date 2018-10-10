package co.isoft.nnita.profile.api.util;

/**
 * Enum de los canales del sistema.
 * @author Yaher Carrillo
 * @Date 02/10/2018
 */
public enum EnumCanalesISoft
{

    WEB("WEB", "Consumos Web"),
    APP("APP", "Apps's de Integracion"),;


    private String codigo;
    private String nombre;

    EnumCanalesISoft(String codigo, String nombre)
    {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo()
    {
        return codigo;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
}
