package Controllers;

import java.rmi.RemoteException;
import java.util.List;
import Exceptions.ParticipantesExceptions;
import Logic.Mensajes;
import ValueObjects.VOParticipante;
import ValueObjects.VOParticipanteFinalista;

public class ControllerListarFinalistas extends ControllerContenedorPrincipal {

	public ControllerListarFinalistas() {
	}

	public List<VOParticipanteFinalista> ListarTodosLosFinalistas() throws ParticipantesExceptions {
		List<VOParticipanteFinalista> listaFinalistas = null;
		try {
			listaFinalistas = iFachada.R9ListFinalistasYGanador();
		} catch (Exception e) {
			throw new ParticipantesExceptions(Mensajes.M_ErrorAlIntentarConectarServer);
		}

		return listaFinalistas;

	}

	
	// EstadoVotacion = fachada.getEstadoVotacion();
	public boolean getEstadoVotacion() throws Exception {
		boolean getEstadoVotacion = false;
		try {
			getEstadoVotacion = iFachada.getEstadoVotacion();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return getEstadoVotacion;
	}

	// HayGanador = fachada.getHayGanador();
	public boolean getHayGanador() throws RemoteException, InterruptedException {
		boolean getHayGanador = false;
		getHayGanador = iFachada.getHayGanador();

		return getHayGanador;
	}


}
