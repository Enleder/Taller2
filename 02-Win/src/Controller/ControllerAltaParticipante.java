package Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import Exceptions.ParticipantesExceptions;
import ValueObjects.VOParticipanteDetallado;

public class ControllerAltaParticipante extends ControllerContenedorPrincipal {

	public ControllerAltaParticipante() {
	}

	public void R1AltaParticipante(VOParticipanteDetallado DatosParticipante)
			throws ParticipantesExceptions, InterruptedException, FileNotFoundException, IOException {

		iFachada.R1AltaParticipante(DatosParticipante);
	}

}
