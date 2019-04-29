package Exceptions;

public class ParticipantesExceptions extends Exception {

	// Es necesario para la utilizacion de Exception
	private static final long serialVersionUID = 1L;

	public ParticipantesExceptions() {
		super();
	}

	public ParticipantesExceptions(String mensaje) {
		super(mensaje);
	}

}
