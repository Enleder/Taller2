package Controller;

import java.util.List;
import ValueObjects.*;

public class ControllerParticipanteListPerformances extends ControllerContenedorPrincipal {

	public ControllerParticipanteListPerformances() {
	}

	public List<VOPerformance> ListarTodosLasPerformances(String idParticipante) throws Exception {

		List<VOPerformance> ListarTodosLasPerformances;
		try {
			ListarTodosLasPerformances = iFachada.R4ListPerformancesUnParticipante(idParticipante);

		} catch (Exception e) {

			throw new Exception(e.getMessage());

		}
		return ListarTodosLasPerformances;
	}

	public VOParticipanteDetallado ListarParticipanteDetallado(String idParticipante) throws Exception {

		VOParticipanteDetallado ListarParticipanteDetallado;
		try {
			ListarParticipanteDetallado = iFachada.R3MostrarDetParticipante(idParticipante);

		} catch (Exception e) {

			throw new Exception(e.getMessage());

		}
		return ListarParticipanteDetallado;
	}

}
