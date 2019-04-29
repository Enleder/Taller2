package Testing;

import Logic.Participantes;
import Logic.Nacional;

import java.io.FileNotFoundException;
import java.io.IOException;

import Exceptions.ParticipantesExceptions;

public class TestParticipantesException {

	public static void main(String[] args) throws ParticipantesExceptions, FileNotFoundException, IOException {

		// public Nacional(String nombreArtistico, String nombreYApellido, String foto,
		// int edad, String especialidadArtistica, String departamento, String
		// localidad) {
		// super(nombreArtistico, nombreYApellido, foto, edad, especialidadArtistica);
		// this.departamento = departamento;
		// this.localidad = localidad;
		// }

		Nacional p = new Nacional("Salto", "Constitucion", "El zorro", 25, "Guitarrista", "Montevideo", "Montevideo");
		Participantes ppss = new Participantes();

		try {
			ppss.Insert(p);

		} catch (ParticipantesExceptions ex) {

			System.out.println(ex.getMessage());

		}

	}

}
