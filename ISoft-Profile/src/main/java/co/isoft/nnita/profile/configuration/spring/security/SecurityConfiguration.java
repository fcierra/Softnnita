package co.isoft.nnita.profile.configuration.spring.security;

import co.isoft.nnita.profile.api.models.Menus_Item;
import co.isoft.nnita.profile.api.models.Permisos;
import co.isoft.nnita.profile.api.services.PermisosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	private UserDetailsService userDetailsService;


	@Autowired
	private PermisosService permisosServices;


	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		List<Menus_Item> listaPermisos = permisosServices.findNavigationSystem();
		if (listaPermisos!=null && !listaPermisos.isEmpty()){
			for (Menus_Item permiso : listaPermisos){
				http.authorizeRequests().
						antMatchers(permiso.getMenu_link()+"*")
						.access("hasAnyAuthority('"+permiso.getRef_security().toUpperCase()+"')");
			}
		}

		/*http.authorizeRequests().
				antMatchers("/secure/profiler/profile_actividad.xhtml*")
				.access("hasAnyAuthority('PROFILEBITACORA')");

		*/

		http.authorizeRequests().
				and().formLogin().  //login configuration
				loginPage("/index.xhtml").
				loginProcessingUrl("/appLogin").
				usernameParameter("app_username").
				passwordParameter("app_password").
				defaultSuccessUrl("/secure/init/login.xhtml").
				and().exceptionHandling().accessDeniedPage("/secure/init/denied.xhtml").
				and().logout().    //logout configuration
				logoutUrl("/appLogout").
				invalidateHttpSession(true).
				logoutSuccessUrl("/index.xhtml");

		http
				.csrf().disable();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}

}
