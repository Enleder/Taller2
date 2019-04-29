package Exceptions;

public class FinalistasExceptions extends Exception {

	// Es necesario para la utilizacion de Exception
	private static final long serialVersionUID = 1L;

	public FinalistasExceptions() {
		super();
	}

	public FinalistasExceptions(String mensaje) {
		super(mensaje);
	}
}
