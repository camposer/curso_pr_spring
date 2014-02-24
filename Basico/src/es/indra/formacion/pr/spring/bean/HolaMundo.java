package es.indra.formacion.pr.spring.bean;

public class HolaMundo {
	private Dato dato;
	
	public void imprimir() {
		System.out.println("Hola: " + dato.getContenido());
	}

	public void setDato(Dato dato) {
		this.dato = dato;
	}
}
