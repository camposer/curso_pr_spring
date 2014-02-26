package es.indra.formacion.pr.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.indra.formacion.pr.persistence.model.Ordenador;
import es.indra.formacion.pr.persistence.model.Persona;
import es.indra.formacion.pr.spring.dao.IOrdenadorDao;
import es.indra.formacion.pr.spring.dao.IPersonaDao;

public class OrdenadorService implements IOrdenadorService {
	@Autowired
	private IPersonaDao personaDao;
	@Autowired
	private IOrdenadorDao ordenadorDao;
	
	@Override
	public void agregarOrdenador(Ordenador o) {
		Persona p = personaDao.obtener(o.getPersona().getId());
		o.setPersona(p);
		
		ordenadorDao.agregar(o); // Ahora sí funciona!!
	}

	@Override
	public List<Ordenador> obtenerOrdenadores() {
		return ordenadorDao.obtenerTodos();
	}

	@Override
	public List<Ordenador> obtenerOrdenadoresOrdenadosPorSerial() {
		return ordenadorDao.obtenerTodosOrdenadosPorSerial();
	}
}
