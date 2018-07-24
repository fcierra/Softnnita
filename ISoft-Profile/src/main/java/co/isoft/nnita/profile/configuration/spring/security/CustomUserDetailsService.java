package co.isoft.nnita.profile.configuration.spring.security;

import co.isoft.nnita.profile.api.exceptions.ServiceException;
import co.isoft.nnita.profile.api.models.Usuarios;
import co.isoft.nnita.profile.api.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsServiceCustomLogin
{

    private String loginUser;

    @Autowired
    @Qualifier("proxyUsuariosService")
    private UsuariosService userService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException
    {
        Usuarios user = null;
        try
        {
            user = userService.validateUser(ssoId);
        }
        catch (ServiceException e)
        {
            e.printStackTrace();
        }

        if (user == null)
        {

            throw new UsernameNotFoundException("Username not found");
        }
        setLoginUser(ssoId);
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getClave(), true, true, true, true, getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Usuarios user)
    {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		/*for(UserProfile userProfile : user.getUserProfiles()){
			logger.info("UserProfile : {}", userProfile);
			authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
		}*/

        return authorities;
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
