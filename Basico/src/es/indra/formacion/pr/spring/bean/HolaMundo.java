package es.indra.formacion.pr.spring.bean;

public class HolaMundo implements IHolaMundo {
	private Dato dato;
	
	public HolaMundo() {
		System.out.println("Creando HolaMundo...");
	}
	
	@Override
	public void imprimir() {
		System.out.println("Hola " + dato.getContenido());
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
