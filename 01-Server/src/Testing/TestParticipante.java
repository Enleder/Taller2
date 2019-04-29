package Testing;

import java.util.*;
import Logic.*;

public class TestParticipante {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner inputText = new Scanner(System.in);

		String nombreArtistico;
		String nombreYApellido;
		String foto;
		int edad;
		String especialidadArtistica;
		int puntajeTodasLasPerformances;
		boolean tienePerformancePendiente;
		boolean esFinalista;
		int puntajeTotalPublico;
		boolean esGanador;
		Performances p = new Performances();
		String departamento;
		String localidad;
		String paisOrigen;
		int cantidadAniosEnUy;

		Nacional N;
		Extranjero E;

		System.out.println("---------Test Nacional-----");
		System.out.println("Ingrese nombre artistico");
		nombreArtistico = inputText.nextLine();
		System.out.println("Ingrese nombre y apellido");
		nombreYApellido = inputText.nextLine();
		System.out.println("foto");
		foto = inputText.nextLine();
		System.out.println("edad");
		edad = inputText.nextInt();
		System.out.println("Ingrese especialidad artistica");
		especialidadArtistica = inputText.nextLine();
		System.out.println("Puntaje todas las performances");
		puntajeTodasLasPerformances = inputText.nextInt();
		System.out.println("Tiene performances pentiendes?");
		tienePerformancePendiente = inputText.nextBoolean();
		System.out.println("Es finalista?");
		esFinalista = inputText.nextBoolean();
		System.out.println("Puntaje total publico");
		puntajeTotalPublico = inputText.nextInt();
		System.out.println("Es ganador?");
		esGanador = inputText.nextBoolean();
		System.out.println("departamento");
		departamento = inputText.nextLine();
		System.out.println("localidad");
		localidad = inputText.nextLine();

		System.out.println("paisOrigen");
		paisOrigen = inputText.nextLine();
		System.out.println("cantidadAniosEnUy");
		cantidadAniosEnUy = inputText.nextInt();

		N = new Nacional(nombreArtistico, nombreYApellido, foto, edad, especialidadArtistica, departamento, localidad);
		E = new Extranjero(nombreArtistico, nombreYApellido, foto, edad, especialidadArtistica, paisOrigen,
				cantidadAniosEnUy);

	}
}
