package co.isoft.nnita.profile.api.util;

/**
 * Enum de las funcionalidades del sistema.
 * @author Yaher Carrillo
 * @Date 02/10/2018
 */
public enum EnumFuncionalityISoft
{

    FUNCIONALIDAD_CREAR_USUARIO("crear.usuarios", "Creacion de Usuarios ISoft"),;


    private String codigo;
    private String nombre;

    EnumFuncionalityISoft(String codigo, String nombre)
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
