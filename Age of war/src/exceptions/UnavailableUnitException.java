package exceptions;

public class UnavailableUnitException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public UnavailableUnitException(){
		super("La unidad no esta habilitada, falta algun upgrade");
	}
}
