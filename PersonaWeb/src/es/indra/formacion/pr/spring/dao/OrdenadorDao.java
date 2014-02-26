package es.indra.formacion.pr.spring.dao;

import java.util.List;

import es.indra.formacion.pr.persistence.model.Ordenador;

public class OrdenadorDao extends BaseDao<Ordenador, Integer> implements IOrdenadorDao {
	public OrdenadorDao() {
		super();
	}

	public OrdenadorDao(boolean autoCommit) {
		super(autoCommit);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ordenador> obtenerTodosOrdenadosPorSerial() {
		return em.createNamedQuery("Ordenador.findAllOrderedBySerial").getResultList();
	}
}
