package es.indra.formacion.pr.spring.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import es.indra.formacion.pr.spring.model.Producto;

public class ProductoDaoTest {
	public static void main(String[] args) {
		EntityManager em = Persistence
				.createEntityManagerFactory("eMarketWeb")
				.createEntityManager();
		
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		// Agregando
		em.persist(new Producto("Portátiles", 599f));
		em.persist(new Producto("Cámaras", 230f));
		em.persist(new Producto("DVD", 60f));
		em.persist(new Producto("Teléfono", 500f));
		
		// Consultando
		System.out.println(em.createQuery("from Producto").getResultList());
		
		et.commit();
	}
}
