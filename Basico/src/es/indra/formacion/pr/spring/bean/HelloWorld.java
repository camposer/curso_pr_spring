package es.indra.formacion.pr.spring.bean;

public class HelloWorld implements IHolaMundo {
	private Dato dato;
	
	public HelloWorld() {
		System.out.println("Creating HelloWold...");
	}
	
	@Override
	public void imprimir() {
		System.out.println("Hello " + dato.getContenido());
	}

	@Override
	public void setDato(Dato dato) {
		this.dato = dato;
	}

	@Override
	public Dato getDato() {
		return dato;
	}

}
