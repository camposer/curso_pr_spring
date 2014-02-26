package es.indra.formacion.pr.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.indra.formacion.pr.persistence.model.Ordenador;
import es.indra.formacion.pr.persistence.model.Persona;
import es.indra.formacion.pr.spring.dao.IOrdenadorDao;
import es.indra.formacion.pr.spring.dao.IPersonaDao;
import es.indra.formacion.pr.spring.transaction.TransactionManager;

public class PersonaService implements IPersonaService {
	@Autowired
	private IPersonaDao personaDao;
	@Autowired
	private IOrdenadorDao ordenadorDao;
	private TransactionManager tm =  null;

	public void init() {
		// TODO: Qué pasa con cuando la transacción es gestionada por el TransactionManager?
		tm = new TransactionManager();
		tm.join(personaDao);
		tm.join(ordenadorDao);
	}

	@Override
	public void agregarPersona(Persona p) {
		try {
			tm.begin();

			// Operaciones con persona
			personaDao.agregar(p);
			
			// Operaciones con ordenadores
			if (p.getOrdenadores() != null) for (Ordenador o : p.getOrdenadores()) {
				o.setPersona(p); // Ya fue persistido!!!
				ordenadorDao.agregar(o);
			}
			
			tm.commit();
		} catch (Exception e) {
			if (tm != null)
				tm.rollback();
		}
	}

	@Override
	public List<Persona> obtenerPersonas() {
		return personaDao.obtenerTodos();
	}

	@Override
	protected void finalize() throws Throwable {
		if (tm != null)
			tm.close(); // Cierra la sesión (EntityManager)
	}
}
