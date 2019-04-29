package Exceptions;

public class PersistenciaException extends Exception {

	// Es necesario para la utilizacion de Exception
	private static final long serialVersionUID = 1L;

	public PersistenciaException() {
		super();
	}

	public PersistenciaException(String mensaje) {
		super(mensaje);
	}
}
