package Testing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;
import Exceptions.PersistenciaException;
import Logic.Concurso;
import Logic.Mensajes;
import Logic.Nacional;

public class TestPersistencia {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		Respaldar();
		Recuperar();

	}

	private static void Respaldar() throws FileNotFoundException, IOException {

		try {
			Persistencia p = new Persistencia();
			PersistenceData pd;
			PersistenceData pd1;
			Nacional n;
			Nacional n1;
			Concurso c;
			Concurso c1;
			Vector<PersistenceData> arre = new Vector<PersistenceData>();

			c = new Concurso();
			c1 = new Concurso();

			n = new Nacional("El Zorro", "Maria Pereyra", "foto1", 32, "Guitarrista", "Montevideo", "Montevideo");
			pd = new PersistenceData(c, n);
			arre.add(pd);

			n1 = new Nacional("El Gato", "Ricardo Manuel Rodriguez", "foto2", 25, "Malabarista", "Salto",
					"Constitucion");
			pd1 = new PersistenceData(c1, n1);
			arre.add(pd1);

			p.respaldar(arre);

			System.out.println(Mensajes.M_PERSISTENCIA_RESPALDAR_EXITO);

		} catch (PersistenciaException e) {
			System.out.println("ERROR :  " + e.getMessage());
		}

	}

	private static void Recuperar() {

		try {

			Persistencia p = new Persistencia();
			Vector<PersistenceData> arre;
			arre = p.recuperar();

			for (int i = 0; i < arre.size(); i++) {

				PersistenceData aux = arre.get(i);
				Nacional n = (Nacional) aux.getParticipante();

				System.out.println(" =========================================== ");
				System.out.println(" Nombre Artistico  : " + n.getNombreArtistico());
				System.out.println(" Nombre y Apellido  : " + n.getNombreYApellido());
				System.out.println(" Edad  : " + n.getEdad());
				System.out.println(" Especialidad  : " + n.getEspecialidadArtistica());
				System.out.println(" Departamento  : " + n.getDepartamento());
				System.out.println(" Localidad  : " + n.getLocalidad());
				System.out.println(" =========================================== ");
			}

			System.out.println(Mensajes.M_PERSISTENCIA_RECUPERAR_EXITO);

		} catch (Exception e) {
			System.out.println("ERROR :  " + e.getMessage());
		}

	}

}
