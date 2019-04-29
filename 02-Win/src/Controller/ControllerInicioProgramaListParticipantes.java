package Controller;

import java.rmi.RemoteException;
import java.util.List;
import Exceptions.ParticipantesExceptions;
import Logic.Mensajes;
import ValueObjects.VOParticipante;

public class ControllerInicioProgramaListParticipantes extends ControllerContenedorPrincipal {

	public ControllerInicioProgramaListParticipantes() {
	}

	public List<VOParticipante> ListarTodosLosParticipantes() throws ParticipantesExceptions {
		List<VOParticipante> ListarTodosLosParticipantesOUT = null;
		try {
			ListarTodosLosParticipantesOUT = iFachada.R2ListParticipantes();
		} catch (Exception e) {
			throw new ParticipantesExceptions(Mensajes.M_ErrorAlIntentarConectarServer);
		}

		return ListarTodosLosParticipantesOUT;

	}

	public void R5CierrePerfsYDefinicionFinalistas() throws Exception {
		try {
			iFachada.R5CierrePerfsYDefinicionFinalistas();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void R7CierreVotacionPublico() throws Exception {
		try {
			iFachada.R7CierreVotacionPublico();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	// CantParticipantes = fachada.getCantParticipantes();
	public int CuantosParticipantesHay() throws ParticipantesExceptions {
		int cantidadParticipantesRegitrados = 0;
		try {
			cantidadParticipantesRegitrados = iFachada.getCantParticipantes();
		} catch (Exception e) {
			throw new ParticipantesExceptions(Mensajes.M_ErrorAlIntentarConectarServer);
		}

		return cantidadParticipantesRegitrados;
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

	// QuedaAlgunaPerformance = fachada.getQuedaAlgunaPerformance();
	public boolean getQuedaAlgunaPerformance() throws RemoteException, InterruptedException {
		boolean getQuedaAlgunaPerformance = false;
		getQuedaAlgunaPerformance = iFachada.getQuedaAlgunaPerformance();

		return getQuedaAlgunaPerformance;
	}

}
