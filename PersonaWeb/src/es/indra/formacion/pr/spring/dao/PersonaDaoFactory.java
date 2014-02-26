package es.indra.formacion.pr.spring.dao;

public abstract class PersonaDaoFactory {
	public static IPersonaDao createPersonaDao() {
		return createPersonaDao(true);
	}
	public static IPersonaDao createPersonaDao(boolean autoCommit) {
		return new PersonaDao(autoCommit);
	}
}
