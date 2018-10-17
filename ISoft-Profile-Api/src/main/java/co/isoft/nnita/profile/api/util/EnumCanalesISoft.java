package co.isoft.nnita.profile.api.util;

/**
 * Enum de los canales del sistema.
 * @author Yaher Carrillo
 * @Date 02/10/2018
 */
public enum EnumCanalesISoft
{

    WEB(1,"WEB", "Consumos Web"),
    APP(2,"APP", "Apps's de Integracion"),;


    private int id;
    private String codigo;
    private String nombre;

    EnumCanalesISoft(int id,String codigo, String nombre)
    {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
    }

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

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Regresa un canal por el codigo
     * @param code codigo de la aplicacion
     * @return Canal encontrada o null.
     */
    public static EnumCanalesISoft valueOf(int code)
    {
        switch (code)
        {
            case 1:
                return WEB;
            case 2:
                return APP;
        }
        return null;
    }
}
