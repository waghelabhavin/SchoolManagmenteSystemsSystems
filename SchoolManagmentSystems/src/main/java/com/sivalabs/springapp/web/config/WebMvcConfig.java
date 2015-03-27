/**
 * 
 */
package com.sivalabs.springapp.web.config;

import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

/**
 * @author Siva
 * 
 */
@Configuration
@ComponentScan(basePackages = { "com.sivalabs.springapp.web"}) 
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter
{
 
	/* without Apache tiles
	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		super.addViewControllers(registry);
		registry.addViewController("login/form").setViewName("loginNew");		
		registry.addViewController("welcome").setViewName("welcome");
		registry.addViewController("admin").setViewName("admin");
		registry.addViewController("advanced").setViewName("advanced");
	}

	@Bean
	public ViewResolver resolver()
	{

		InternalResourceViewResolver url = new InternalResourceViewResolver();
		url.setPrefix("/WEB-INF/jsp/");
		url.setSuffix(".jsp");
		return url;
	}
	*/
	
	 /* For apache tiles*/
	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		super.addViewControllers(registry);
		registry.addViewController("login/form").setViewName("loginNew");		
		registry.addViewController("welcome").setViewName("welcome");
//		registry.addViewController("admin").setViewName("admin");
//		registry.addViewController("advanced").setViewName("advanced");
	}
	
	@Bean
	public ViewResolver resolver()
	{
		UrlBasedViewResolver url = new UrlBasedViewResolver();
//		((UrlBasedViewResolver) url).setPrefix("/WEB-INF/jsp/");
//		((UrlBasedViewResolver) url).setSuffix(".jsp");
		((UrlBasedViewResolver) url).setViewClass(TilesView.class);
		return url;
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[]{"/WEB-INF/tiles-defs/tiles-defs.xml"});
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}
	

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
	{
		configurer.enable();
	}

	@Bean(name = "messageSource")
	public MessageSource configureMessageSource()
	{
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setCacheSeconds(5);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver()
	{
		SimpleMappingExceptionResolver b = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.put("org.springframework.dao.DataAccessException", "error");
		b.setExceptionMappings(mappings);
		return b;
	}
}