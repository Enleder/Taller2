package Testing;

import Logic.Concurso;

public class TestConcurso {

	public static void main(String[] args) {

		Concurso c = new Concurso();
		String estado = "", ganador = "";
		if (c.isEstadoVotacionActivo())
			estado = "TRUE";
		else
			estado = "FALSE";
		if (c.isHayGanador())
			ganador = "TRUE";
		else
			ganador = "FALSE";

		System.out.println("Fecha Cierre Votaci�n :: " + c.getFechaCierreVotacion());
		System.out.println("Estado Votaci�n :: " + estado);
		System.out.println("Hay Ganadores :: " + ganador);

	}

}
