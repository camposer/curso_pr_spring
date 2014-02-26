package es.indra.formacion.pr.spring.service;

public abstract class PersonaServiceFactory {
	public static IPersonaService createPersonaService() {
		return new PersonaService();
	}
}