package co.isoft.nnita.profile.api.util;

/**
 * Enum de las funcionalidades del sistema.
 * @author Yaher Carrillo
 * @Date 02/10/2018
 */
public enum EnumFuncionalityISoft
{

    FUNCIONALIDAD_CREAR_USUARIO("crear.usuarios", "Creacion de Usuarios ISoft"),
    FUNCIONALIDAD_CREAR_USUARIOS_MASIVOS("crear.usuarios.masivos", "Creacion de Usuarios ISoft Masivos"),
    FUNCIONALIDAD_ACTUALIZAR_USUARIO("actualizar.usuario", "Actualizacion de Usuarios ISoft Masivos"),
    FUNCIONALIDAD_ASOCIAR_PERFIL("asociar.perfil", "Asociar Perfiles"),
    FUNCIONALIDAD_DESASOCIAR_PERFIL("desasociar.perfil", "Desasociar Perfiles"),
    FUNCIONALIDAD_CONSULTAR_PERFILES("consultar.perfiles", "Consulta de Perfiles"),
    FUNCIONALIDAD_CONSULTAR_PERFILES_USUARIO("consultar.perfiles.usuario", "Consulta de Perfiles de Usuario"),
    FUNCIONALIDAD_ADMINISTRAR_STATUS_USUARIOS("administrar.status.usuarios", "Administracion de Estatus de Usuarios"),
    FUNCIONALIDAD_CREAR_PERFILES("crear.perfil", "Creacion de perfiles de sistema"),
    FUNCIONALIDAD_MODIFICAR_PERFILES("modificar.perfil", "Modificacion de perfiles de sistema"),;


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
