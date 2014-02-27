package es.indra.formacion.pr.spring.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import es.indra.formacion.pr.spring.dao.IProductoDao;
import es.indra.formacion.pr.spring.dao.ProductoDaoJpa;
import es.indra.formacion.pr.spring.model.Producto;

public class ProductoDaoTest {
	public static void main(String[] args) {
		EntityManager em = Persistence
				.createEntityManagerFactory("eMarketWeb")
				.createEntityManager();
		
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		// Agregando
		IProductoDao productoDao = new ProductoDaoJpa();
		((ProductoDaoJpa)productoDao).setEntityManager(em);
		
		productoDao.agregar(new Producto("Portátiles", 599f));
		productoDao.agregar(new Producto("Cámaras", 230f));
		productoDao.agregar(new Producto("DVD", 60f));
		productoDao.agregar(new Producto("Teléfono", 500f));
		
		// Consultando
		System.out.println(em.createQuery("from Producto").getResultList());
		
		et.commit();
	}
}
