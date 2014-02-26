package es.indra.formacion.pr.spring.bean;

public class Servicio {
	private DaoFactory daoFactory;

	public String getContenido() {
		Dao dao = daoFactory.createDao();
		Dato dato = dao.getDato();
		String contenido = dato.getContenido();
		
		return contenido;
	}
	
	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
}
