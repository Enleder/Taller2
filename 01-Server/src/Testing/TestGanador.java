package Testing;

import java.util.Iterator;
import java.util.List;
import Logic.Finalistas;
import Logic.Nacional;
import Logic.Participante;

public class TestGanador {

	public static void main(String[] args) {

		Finalistas finalistas = new Finalistas();

		Nacional n = new Nacional("Participante_1", "Ricardo Manuel Rodriguez", "foto2", 25, "Malabarista", "Salto",
				"Constitucion");

		Nacional n1 = new Nacional("Participante_2", "Ricardo Manuel Rodriguez", "foto2", 25, "Malabarista", "Salto",
				"Constitucion");

		Nacional n2 = new Nacional("Participante_3", "Ricardo Manuel Rodriguez", "foto2", 25, "Malabarista", "Salto",
				"Constitucion");

		Nacional n3 = new Nacional("Participante_4", "Ricardo Manuel Rodriguez", "foto2", 25, "Malabarista", "Salto",
				"Constitucion");

		Nacional n4 = new Nacional("Participante_5", "Ricardo Manuel Rodriguez", "foto2", 25, "Malabarista", "Salto",
				"Constitucion");

		Nacional n5 = new Nacional("Participante_6", "Ricardo Manuel Rodriguez", "foto2", 25, "Malabarista", "Salto",
				"Constitucion");

		Nacional n6 = new Nacional("Participante_7", "Ricardo Manuel Rodriguez", "foto2", 25, "Malabarista", "Salto",
				"Constitucion");

		Nacional n7 = new Nacional("Participante_8", "Ricardo Manuel Rodriguez", "foto2", 25, "Malabarista", "Salto",
				"Constitucion");

		Nacional n8 = new Nacional("Participante_9", "Ricardo Manuel Rodriguez", "foto2", 25, "Malabarista", "Salto",
				"Constitucion");

		Nacional n9 = new Nacional("Participante_10", "Ricardo Manuel Rodriguez", "foto2", 25, "Malabarista", "Salto",
				"Constitucion");

		Nacional n10 = new Nacional("Participante_11", "Ricardo Manuel Rodriguez", "foto2", 25, "Malabarista", "Salto",
				"Constitucion");

		Nacional n11 = new Nacional("Participante_12", "Ricardo Manuel Rodriguez", "foto2", 25, "Malabarista", "Salto",
				"Constitucion");

		Nacional n12 = new Nacional("Participante_13", "Ricardo Manuel Rodriguez", "foto2", 25, "Malabarista", "Salto",
				"Constitucion");

		Nacional n13 = new Nacional("Participante_14", "Ricardo Manuel Rodriguez", "foto2", 25, "Malabarista", "Salto",
				"Constitucion");

		Nacional n14 = new Nacional("Participante_15", "Ricardo Manuel Rodriguez", "foto2", 25, "Malabarista", "Salto",
				"Constitucion");

		Nacional n15 = new Nacional("Participante_16", "Ricardo Manuel Rodriguez", "foto2", 25, "Malabarista", "Salto",
				"Constitucion");

		n1.setPuntajeTotalPublico(20);
		n2.setPuntajeTotalPublico(30);
		n3.setPuntajeTotalPublico(90);
		n4.setPuntajeTotalPublico(50);
		n5.setPuntajeTotalPublico(60);

		finalistas.Insert(n);
		finalistas.Insert(n1);
		finalistas.Insert(n2);
		finalistas.Insert(n3);
		finalistas.Insert(n4);
		finalistas.Insert(n5);
		finalistas.Insert(n6);
		finalistas.Insert(n7);
		finalistas.Insert(n8);
		finalistas.Insert(n9);
		finalistas.Insert(n10);
		finalistas.Insert(n11);
		finalistas.Insert(n12);
		finalistas.Insert(n13);
		finalistas.Insert(n14);
		finalistas.Insert(n15);

		MostrarGanador(finalistas.getLista());

	}

	private static void MostrarGanador(List<Participante> lista) {

		Iterator<Participante> iter = lista.iterator();
		int mayor = 0;
		Participante pg = null;
		while (iter.hasNext()) {
			Participante p = iter.next();
			if (p.getPuntajeTotalPublico() > mayor) {
				mayor = p.getPuntajeTotalPublico();
				pg = p;
			}
		}

		System.out.println("El Participante Ganador es ::   " + pg.getNombreArtistico());

	}

}
