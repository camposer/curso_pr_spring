package es.indra.formacion.pr.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.indra.formacion.pr.spring.dao.IProductoDao;
import es.indra.formacion.pr.spring.dao.ProductoDaoMockup;
import es.indra.formacion.pr.spring.service.IProductoService;
import es.indra.formacion.pr.spring.service.ProductoService;

/**
 * La clase debe tener la anotación \@Configuration 
 */
@Configuration
public class Config {
	/**
	 * El nombre del método es el id del Bean 
	 */
	@Bean
	public IProductoDao productoDao() {
		return new ProductoDaoMockup();
	}
	
	//@Bean(initMethod="init")
	@Bean
	//@Scope("prototype")
	//@Lazy
	public IProductoService productoService() {
		return new ProductoService();
	}
	
}
