package exceptions;

public class UnavailableUpgradeException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public UnavailableUpgradeException(){
		super("Requiere algun upgrade anterior");
	}
}
