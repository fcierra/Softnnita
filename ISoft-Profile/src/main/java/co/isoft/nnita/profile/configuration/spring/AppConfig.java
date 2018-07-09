package co.isoft.nnita.profile.configuration.spring;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Locale;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "co.isoft.nnita.profile")
public class AppConfig extends WebMvcConfigurerAdapter
{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/js/**").addResourceLocations("/ui/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("/ui/css/");
		registry.addResourceHandler("/img/**").addResourceLocations("/ui/img/");
		registry.addResourceHandler("/plugins/**").addResourceLocations("/ui/plugins/");

		registry.addResourceHandler("/js/**").addResourceLocations("/ui/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("/ui/css/");
		registry.addResourceHandler("/img/**").addResourceLocations("/ui/img/");
		registry.addResourceHandler("/plugins/**").addResourceLocations("/ui/plugins/");
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsf");
		return viewResolver;
	}



	@Bean
	public MessageSource messageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("messages");
		messageSource.setDefaultEncoding("ISO-8859-1");
		// # -1 : never reload, 0 always reload
		messageSource.setCacheSeconds(0);
	    return messageSource;
	}

	@Bean
	public CookieLocaleResolver localeResolver(){
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("es"));
		localeResolver.setCookieName("locale-cookie");
		localeResolver.setCookieMaxAge(3600);
		return localeResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeInterceptor(){
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		return interceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeInterceptor());
	}

}

