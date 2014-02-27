package es.indra.formacion.pr.spring.exception;

@SuppressWarnings("serial")
public class EmarketServiceException extends EmarketException {

	public EmarketServiceException() { }

	public EmarketServiceException(String msg, Throwable e) {
		super(msg, e);
	}
}
