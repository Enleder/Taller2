package Testing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import Exceptions.ParticipantesExceptions;
import Logic.Finalistas;
import Logic.Nacional;
import Logic.Participante;

public class TestFinalistas {

	public static void main(String[] args) throws FileNotFoundException, ParticipantesExceptions, IOException {

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

		listarFinalistas(finalistas.getLista());

	}

	private static void listarFinalistas(List<Participante> lista) {

		int cant_participntes = lista.size();

		Random random = new Random();
		int contador = 0;

		// Conjunto de números ya usados
		Set<Integer> numerosUtilizados = new HashSet<>();

		// Generamos 3 números aleatorios sin repetición
		while (numerosUtilizados.size() < 3) {

			// Número aleatorio entre 0 y la cantidad de participantes
			int randomNumber = random.nextInt(cant_participntes);

			// Si no lo hemos usado ya, lo metemos en el conjunto de usados.
			if (!numerosUtilizados.contains(randomNumber)) {
				System.out.println(
						"Finalista Nº " + (contador + 1) + " : " + lista.get(randomNumber).getNombreArtistico());
				numerosUtilizados.add(randomNumber);
				contador++;
			}
		}

	}

}
