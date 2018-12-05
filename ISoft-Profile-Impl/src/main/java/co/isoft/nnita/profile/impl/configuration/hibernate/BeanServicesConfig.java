package co.isoft.nnita.profile.impl.configuration.hibernate;

import co.isoft.nnita.profile.api.services.BitacoraService;
import co.isoft.nnita.profile.api.services.PerfilesYPermisosService;
import co.isoft.nnita.profile.api.services.UsuariosService;
import co.isoft.nnita.profile.impl.proxys.ProxyUsuariosServiceImpl;
import co.isoft.nnita.profile.impl.service.BitacoraServiceImpl;
import co.isoft.nnita.profile.impl.service.PerfilesYPermisosServiceImpl;
import co.isoft.nnita.profile.impl.service.UsuariosServiceImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * En esta clase se definen los bean de servicios
 * cuando sean multiples implementaciones, en pro
 * de diferenciarlos por su nombre y retornar segun sea necesario.
 * Para esto es importante la implementacion de proxys donde
 * a traves de un criterio, se implementen los requeridos.
 *
 * @author Yaher Carrillo
 * @Date 10/07/2018
 */
@Configuration
public class BeanServicesConfig
{
    /**
     * Bean que define el proxy de operaciones de usuarios
     *
     * @return Bean de servicio de usuarios segun el proxy
     */
    @Bean
    @Primary
    public UsuariosService proxyUsuariosServiceImpl()
    {
        return new ProxyUsuariosServiceImpl();
    }

    /**
     * Bean de configuracion de servicios del sistema de auditorias
     *
     * @return
     */
    @Bean
    public BitacoraService bitacoraServiceImpl()
    {
        return new BitacoraServiceImpl();
    }

    /**
     * Bean que las operaciones principales de usuarios.
     *
     * @return Bean original de implementacion
     */
    @Bean
    public UsuariosService usuariosServiceImpl()
    {
        return new UsuariosServiceImpl(bitacoraServiceImpl());
    }

    /**
     * Bean de configuracion de servicios del sistema
     *
     * @return
     */
    @Bean
    public PerfilesYPermisosService perfilesYPermisosServiceImpl()
    {
        return new PerfilesYPermisosServiceImpl(bitacoraServiceImpl());
    }

    /**
     * Recursos de texto del sistema
     * @return
     */
    @Bean
    public MessageSource messageSource()
    {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    /**
     * Bean con el listado de mapeo de los servicio de referencias de usuarios
     *
     * @return
     */
    @Bean
    public ServicesReferencesMapping beansUsersHandlers()
    {
        return new ServicesReferencesMapping();
    }
}
