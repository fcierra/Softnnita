package co.isoft.nnita.profile.configuration.references;

import co.isoft.nnita.profile.service.UsuariosService;
import co.isoft.nnita.profile.service.UsuariosServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que sirve para mapear
 * las multiples implementaciones que puedan tener los servicios
 * del sistema. En ella se deben llenar los mapas de implementaciones
 * posibles, para ser usados por distintos clientes finales,
 * es importante que el parametro sea igual a lo definido
 * @author Yaher Carrillo
 * @Date 10/07/2018
 */
@Service("referencesServiceUsers")
public class ReferencesServiceUsers
{
    /**
     * Mapa de las multiples implementaciones de servicios de usuarios
     */
    private Map<String, String> mapServicesUsers = new HashMap<>();

    /**
     * Referencias de mapeo para el servicio de consulta
     * de datos de usuarios
     */
    public ReferencesServiceUsers()
    {
        mapServicesUsers.put("SERVICIO_NNITA", "usuariosServiceImpl");
        mapServicesUsers.put("SERVICIO_LDAP", "usuariosServiceLdapImpl");
    }

    public Map<String, String> getMapServicesUsers()
    {
        return mapServicesUsers;
    }

    public void setMapServicesUsers(Map<String, String> mapServicesUsers)
    {
        this.mapServicesUsers = mapServicesUsers;
    }
}
