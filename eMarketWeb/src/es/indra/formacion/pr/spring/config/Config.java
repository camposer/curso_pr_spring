package es.indra.formacion.pr.spring.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import es.indra.formacion.pr.spring.dao.IProductoDao;
import es.indra.formacion.pr.spring.dao.ProductoDaoJpa;
import es.indra.formacion.pr.spring.service.IProductoService;
import es.indra.formacion.pr.spring.service.ProductoService;

/**
 * La clase debe tener la anotación \@Configuration 
 */
@Configuration
// <tx:annotation-driven transaction-manager="transactionManager"/-->
@EnableTransactionManagement
public class Config {
	/**
	 * El nombre del método es el id del Bean 
	 */
	@Bean
	public IProductoDao productoDao() {
		return new ProductoDaoJpa();
	}
	
	@Bean
	public IProductoService productoService() {
		return new ProductoService();
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		return Persistence.createEntityManagerFactory("eMarketWeb");
	}
	
	@Bean 
	public EntityManager entityManager() {
		return entityManagerFactory()
				.createEntityManager();
	}
	
	@Bean 
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory());
	}
}
