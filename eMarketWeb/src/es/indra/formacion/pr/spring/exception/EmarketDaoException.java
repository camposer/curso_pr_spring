package es.indra.formacion.pr.spring.exception;

@SuppressWarnings("serial")
public class EmarketDaoException extends EmarketException {
	public EmarketDaoException() { }
	
	public EmarketDaoException(String msg, Throwable e) {
		super(msg, e);
	}
}
