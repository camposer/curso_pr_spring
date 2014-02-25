package es.indra.formacion.pr.spring.exception;

@SuppressWarnings("serial")
public abstract class EmarketException extends Exception {
	public EmarketException() {}
	
	public EmarketException(String msg, Throwable e) {
		super(msg, e);
	}
}
