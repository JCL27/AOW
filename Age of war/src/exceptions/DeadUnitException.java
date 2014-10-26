package exceptions;

import Units.Unit;

public class DeadUnitException extends Exception {
	
	private Unit unit;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DeadUnitException(Unit unit){
		super("Unit is dead");		
		this.unit = unit;
	}
	
	public Unit getUnit(){
		return this.unit;
	}
}
