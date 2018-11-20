package co.isoft.nnita.profile.configuration.spring.security;

import co.isoft.nnita.logger.util.ModulesIsoft;
import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.Perfiles;
import co.isoft.nnita.profile.api.models.Permisos;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.services.PerfilesYPermisosService;
import co.isoft.nnita.profile.api.services.UsuariosService;
import co.isoft.nnita.profile.impl.service.UsuariosServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase provider, para la consulta de usuarios del sistema
 * usada por spring para la seguridad del sistema
 *
 * @author Yaher Carrillo
 * @Date 25/08/2018
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsServiceCustomLogin

{

    /**
     * Objeto utilizado para el log
     */
    private static final Log logger = LogFactory.getLog(UsuariosServiceImpl.class);
    /**
     * Login de Usuario
     */
    private String loginUser;

    /**
     * Servicio de usuarios
     */
    @Autowired
    @Qualifier("proxyUsuariosService")
    private UsuariosService userService;

    /**
     * Servicio de permisologias del sistema
     */
    @Autowired
    private PerfilesYPermisosService perfilesYPermisosService;

    /**
     * Servicio de seguridad que identifica a los usuarios que intentan acceder
     * a la aplicacion, de ser valido se le garantizan los permisos de accesos.
     *
     * @param ssoId
     * @return
     * @throws UsernameNotFoundException
     */
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException
    {
        Usuarios user = null;
        try
        {
            user = userService.findUser(ssoId.toUpperCase());
        }
        catch (ServiceException e)
        {
            co.isoft.nnita.logger.util.Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), ssoId, "Error Provider del Usuarios de sistema", e);
            logger.error("Falla al tratar de autenticar al usuario [Usuario:" + ssoId + "]", e);
        }

        if (user == null)
        {
            throw new UsernameNotFoundException("Username no existe [" + ssoId + "]");
        }
        setLoginUser(ssoId.toUpperCase());
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getClave(), true, true, true, true, getGrantedAuthorities(user));
    }

    /**
     * Asignacion de permisos
     *
     * @param user usuario a dar permisos
     * @return
     */
    private List<GrantedAuthority> getGrantedAuthorities(Usuarios user)
    {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        List<Permisos> listaPermisos = findPermisionUserAuthenticated(user.getPerfilDefault().getNombre_perfil());

        if (listaPermisos != null && !listaPermisos.isEmpty())
        {
            for (Permisos permiso : listaPermisos)
            {
                logger.info("Asignando permiso" + permiso.getMenuItem().getRef_security());
                authorities.add(new SimpleGrantedAuthority(permiso.getMenuItem().getRef_security().toUpperCase()));
            }
            //authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }
        else
        {
            authorities.add(new SimpleGrantedAuthority("GUEST"));
        }

        return authorities;
    }

    /**
     * Busca los permisos de usuario
     *
     * @return
     */
    public List<Permisos> findPermisionUserAuthenticated(String codeperfil)
    {
        try
        {
            return perfilesYPermisosService.findGrantPermisions(codeperfil);
        }
        catch (ServiceException e)
        {
            co.isoft.nnita.logger.util.Log.getInstance().error(ModulesIsoft.ISOFT_PROFILE.getCodigo(), codeperfil, "Falla al tratar de buscar los permisos del perfil por defecto del usuario", e);
            logger.error("Falla al tratar de buscar los permisos del perfil por defecto del usuario", e);
        }
        return null;
    }

    @Override
    public String getLoginUser()
    {
        return this.loginUser;
    }

    @Override
    public void setLoginUser(String loginUser)
    {
        this.loginUser = loginUser;
    }
}
