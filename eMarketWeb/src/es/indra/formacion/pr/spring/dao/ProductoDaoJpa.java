package es.indra.formacion.pr.spring.dao;

import javax.persistence.EntityManager;

import es.indra.formacion.pr.spring.model.Producto;

public class ProductoDaoJpa extends BaseDao<Producto, Integer> implements
		IProductoDao {

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
}
