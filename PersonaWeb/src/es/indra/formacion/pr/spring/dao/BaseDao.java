package es.indra.formacion.pr.spring.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class BaseDao<T, K> implements IDao<T, K> {
	@Autowired
	@Qualifier("entityManager") // Indica el id del bean para Spring
	@PersistenceContext // Le dice a Spring que gestione el EntityManager
	protected EntityManager em;
	protected Class<T> clase;
	protected boolean autoCommit;
	
	@SuppressWarnings("unchecked")
	public BaseDao(boolean autoCommit) {
		Type type = this.getClass().getGenericSuperclass();

	    if (type instanceof ParameterizedType) {
	        ParameterizedType pt = (ParameterizedType) type;
	        Type[] fieldArgTypes = pt.getActualTypeArguments();
	        clase = (Class<T>) fieldArgTypes[0];
	    }
	    
	    this.autoCommit = autoCommit;
	}
	
	public BaseDao() {
		this(true);
	}
	
	@Override
	protected void finalize() throws Throwable {
		if (em != null)
			em.close();
	}
	
	@Override
	public void agregar(T obj) {
		EntityTransaction et = null;
		try {
			if (autoCommit) {
				et = em.getTransaction();
				et.begin();
			}

			em.persist(obj);

			if (autoCommit)
				et.commit();
		} catch (PersistenceException e) {
			if (et != null && autoCommit)
				et.rollback();
			else if (!autoCommit)
				throw e;
		} 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> obtenerTodos() {
		Query q = em.createNamedQuery(clase.getSimpleName() + ".findAll");
		return q.getResultList();
	}

	@Override
	public void modificar(T obj) {
		EntityTransaction et = null;
		try {
			if (autoCommit) {
				et = em.getTransaction();
				et.begin();
			}

			em.merge(obj);

			if (autoCommit)
				et.commit();
		} catch (PersistenceException e) {
			if (et != null && autoCommit)
				et.rollback();
			else if (!autoCommit)
				throw e;
		} 
	}

	@Override
	public void eliminar(K clave) {
		EntityTransaction et = null;
		try {
			if (autoCommit) {
				et = em.getTransaction();
				et.begin();
			}

			Query q = em.createQuery("delete from " + clase.getSimpleName() + " where id = :id");
			q.setParameter("id", clave);
			q.executeUpdate();
			
			if (autoCommit)
				et.commit();
		} catch (PersistenceException e) {
			if (et != null && autoCommit)
				et.rollback();
			else if (!autoCommit)
				throw e;
		} 
	}

	@Override
	public T obtener(K clave) {
		return em.find(clase, clave);
	}
	
	@Override
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public static EntityManager createEntityManager() {
		return Persistence
				.createEntityManagerFactory("PersonaJpa")
				.createEntityManager();
	}

}
