package es.indra.formacion.pr.spring.service;

public abstract class OrdenadorServiceFactory {
	public static IOrdenadorService createOrdenadorService() {
		return new OrdenadorService();
	}
}
