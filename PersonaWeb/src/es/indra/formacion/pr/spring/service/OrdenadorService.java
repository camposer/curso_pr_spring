package es.indra.formacion.pr.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.indra.formacion.pr.persistence.model.Ordenador;
import es.indra.formacion.pr.persistence.model.Persona;
import es.indra.formacion.pr.spring.dao.IOrdenadorDao;
import es.indra.formacion.pr.spring.dao.IPersonaDao;

@Transactional
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
		List<Ordenador> ordenadoresHib = ordenadorDao.obtenerTodos();
		List<Ordenador> ordenadores = new ArrayList<Ordenador>();
		
		if (ordenadoresHib != null) 
			for (Ordenador oHib : ordenadoresHib) {
				Persona pHib = oHib.getPersona();
				Ordenador o = new Ordenador();
				
				o.setId(oHib.getId());
				o.setNombre(oHib.getNombre());
				o.setSerial(oHib.getSerial());
				o.setPersona(new Persona(
						pHib.getNombre(), 
						pHib.getApellido(), 
						pHib.getFechaNacimiento(), 
						pHib.getAltura()
					));
				
				ordenadores.add(o);
			}
		
		System.out.println(ordenadores);
		return ordenadores;
	}

	@Override
	public List<Ordenador> obtenerOrdenadoresOrdenadosPorSerial() {
		return ordenadorDao.obtenerTodosOrdenadosPorSerial();
	}
}
