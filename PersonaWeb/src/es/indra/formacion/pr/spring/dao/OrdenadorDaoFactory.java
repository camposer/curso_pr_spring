package es.indra.formacion.pr.spring.dao;

public abstract class OrdenadorDaoFactory {
	public static IOrdenadorDao createOrdenadorDao() {
		return createOrdenadorDao(true);
	}

	public static IOrdenadorDao createOrdenadorDao(boolean autoCommit) {
		return new OrdenadorDao(autoCommit);
	}
}
