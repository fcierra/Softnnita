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
    FUNCIONALIDAD_MODIFICAR_PERFILES("modificar.perfil", "Modificacion de perfiles de sistema"),
    FUNCIONALIDAD_MODIFICAR_PERMISOS_PERFIL("modificar.permisos.perfil", "Modificacion de permisos de perfil"),;

    /**
     * Codigo de la funcionalidad
     */
    private String codigo;
    /**
     * Nombre de la funcionalidad
     */
    private String nombre;

    /**
     * Constructor que inicializa los atributos del enum
     *
     * @param codigo codigo de la funcionalidad
     * @param nombre nombre de la funcionalidad
     */
    EnumFuncionalityISoft(String codigo, String nombre)
    {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    /**
     * Obtiene el codigo de la funcionalidad
     *
     * @return
     */
    public String getCodigo()
    {
        return codigo;
    }

    /**
     * Asigna el codigo de la funcionalidad
     *
     * @param codigo valor a asignar
     */
    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    /**
     * Obtiene el nombre de la funcionalidad
     *
     * @return nombre de la funcionalidad
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Asigna un valor al nombre de la funcionalidad
     *
     * @param nombre valor a asignar
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
}
