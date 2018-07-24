package co.isoft.nnita.profile.configuration.spring.security;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailsServiceCustomLogin extends UserDetailsService
{
    public String getLoginUser();

    public void setLoginUser(String loginUser);

}
