package ar.edu.itba.game.backend.exceptions;

public class NullElementException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NullElementException(){
		super("El elemento no esta instanciado");
	}
}
