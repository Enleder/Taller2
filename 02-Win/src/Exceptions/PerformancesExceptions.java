package Exceptions;

public class PerformancesExceptions extends Exception {

	// Es necesario para la utilizacion de Exception
	private static final long serialVersionUID = 1L;

	public PerformancesExceptions() {
		super();
	}

	public PerformancesExceptions(String mensaje) {
		super(mensaje);
	}

}
