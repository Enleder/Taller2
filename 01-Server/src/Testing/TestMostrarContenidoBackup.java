package Testing;

import java.util.Iterator;
import java.util.Vector;

import Logic.Concurso;
import Logic.Extranjero;
import Logic.Mensajes;
import Logic.Nacional;
import Logic.Participante;
import Logic.Participantes;
import Logic.TipoParticipante;
import Persistence.PersistenceStructuresManagement;
import Persistence.PersistenciaRecuperarRespaldar;

public class TestMostrarContenidoBackup {

	public static void main(String[] args) {

		Recuperar();

	}

	private static void Recuperar() {

		try {

			PersistenciaRecuperarRespaldar p = new PersistenciaRecuperarRespaldar();
			Vector<PersistenceStructuresManagement> arre;
			arre = p.recuperar();

			for (int i = 0; i < arre.size(); i++) {

				PersistenceStructuresManagement aux = arre.get(i);

				Concurso con = aux.getConcurso();
				System.out.println(" ==============CONCURSO RECUPERADO========== ");
				System.out.println(" FECHA: " + con.getFechaCierreVotacion());
				if (con.isHayGanador() == true) {
					System.out.println("CON: si hay g ");
				}
				System.out.println(" ===con.isEstadoVotacionActivo()::== ");
				System.out.println(con.isEstadoVotacionActivo());

				Participantes parts = aux.getParticipantes();

				Iterator<Participante> itera = parts.getAbb().values().iterator();

				while (itera.hasNext()) {
					Participante par = itera.next();

					System.out.println(" =========================================== ");
					System.out.println(" Nombre Artistico  : " + par.getNombreArtistico());
					System.out.println(" Nombre y Apellido  : " + par.getNombreYApellido());
					System.out.println(" Edad  : " + par.getEdad());
					System.out.println(" Especialidad  : " + par.getEspecialidadArtistica());
					System.out.println(" Ganador?  : " + par.isEsGanador());
					if (par.getTipoDeParticipante() == TipoParticipante.NACIONAL) {
						System.out.println(" Departamento  : " + ((Nacional) par).getDepartamento());
						System.out.println(" Localidad  : " + ((Nacional) par).getLocalidad());

						if (par.getPerformances().largo() > 0) {

							for (int y = 0; y < par.getPerformances().largo(); y++) {

								int num = par.getPerformances().kEsimo(y).getNumero();
								String desc = par.getPerformances().kEsimo(y).getDescripcion();
								int puntajeJuez1a = par.getPerformances().kEsimo(y).getPuntajeJuez1();
								int puntajeJuez2a = par.getPerformances().kEsimo(y).getPuntajeJuez2();
								int puntajeJuez3a = par.getPerformances().kEsimo(y).getPuntajeJuez3();

								System.out.println("------PERFORMANCE---------");
								System.out.println("num:" + num);
								System.out.println("desc: " + desc);
								System.out.println("puntajeJuez1a: " + puntajeJuez1a);
								System.out.println("puntajeJuez2a: " + puntajeJuez2a);
								System.out.println("puntajeJuez3a: " + puntajeJuez3a);
								System.out.println("--------------------------");

							}

						}

					} else {

						System.out.println(" Pais de Origen  : " + ((Extranjero) par).getPaisOrigen());
						System.out.println(" Años en el País  : " + ((Extranjero) par).getCantidadAniosEnUy());

					}

					if (par.isEsFinalista()) {
						System.out.println(" SI es FINALISTA ");
					} else {
						System.out.println(" ***NO es FINALISTA ");
					}
					System.out.println(" =========================================== ");
				}

			}

			System.out.println(Mensajes.M_PERSISTENCIA_RECUPERAR_EXITO);

		} catch (Exception e) {
			System.out.println("ERROR 4:  " + e.getMessage());
		}

	}

}
