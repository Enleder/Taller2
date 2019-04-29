package Controller;

import java.util.List;
import ValueObjects.VOParticipanteDetallado;
import ValueObjects.VOPerformance;

public class ControllerAltaPerformance extends ControllerContenedorPrincipal {

	public ControllerAltaPerformance() {
	}

	// Agregar un participante.
	public void AltaPerformance(String idParticipante, VOPerformance datosNuevaPerformance) throws Exception {

		try {
			iFachada.R6AltaPerfUnParticipante(idParticipante, datosNuevaPerformance);
		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}

	// Retorna un participante detallado
	public VOParticipanteDetallado ListarParticipanteDetallado(String idParticipante) throws Exception {

		VOParticipanteDetallado ListarParticipanteDetallado;
		try {
			ListarParticipanteDetallado = iFachada.R3MostrarDetParticipante(idParticipante);

		} catch (Exception e) {

			throw new Exception(e.getMessage());

		}
		return ListarParticipanteDetallado;
	}

	// Retorna una list con todas las performances de un participante.
	public List<VOPerformance> ListarTodosLasPerformances(String idParticipante) throws Exception {

		List<VOPerformance> ListarTodosLasPerformances;
		try {
			ListarTodosLasPerformances = iFachada.R4ListPerformancesUnParticipante(idParticipante);

		} catch (Exception e) {

			throw new Exception(e.getMessage());

		}
		return ListarTodosLasPerformances;
	}

}
