package Testing;

import Exceptions.ParticipantesExceptions;
import Exceptions.PersistenciaException;
import Logic.Concurso;
import Logic.Extranjero;
import Logic.Finalistas;
import Logic.Mensajes;
import Logic.Nacional;
import Logic.Participante;
import Logic.Participantes;
import Logic.Performance;
import Logic.Performances;
import Logic.TipoParticipante;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;
import Persistence.PersistenceStructuresManagement;
import Persistence.PersistenciaRecuperarRespaldar;

public class TestSistemaSegundaEntrega {

	private static Concurso concursoActual;
	private static Participantes coleccionParticipantes;
	private static Finalistas coleccionFinalistas;

	public static void main(String[] args)
			throws FileNotFoundException, IOException, ParticipantesExceptions, InterruptedException {
		// FachadaLogica fachada = FachadaLogica.getInstancia();
		coleccionParticipantes = new Participantes();
		coleccionFinalistas = new Finalistas();

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		@SuppressWarnings("unused")
		String enter = "";

		System.out.println("---------INICIO DEL TEST----------");

		System.out.println("=");
		System.out.println("=");
		System.out.println("=======================================================================");
		System.out.println("=======================================================================");
		System.out.println("=");
		System.out.println("=");

		System.out.println("------Inicio recuperar y mostrar datos si existe archivo y si tiene contenido----------");
		try {
			TEST_CargarDatosDesdeArchivo();// Recuperar();
		} catch (Exception i) {
			System.out.println("ERROR al cargar datos: " + i.getMessage());
		}
		System.out.println("------FIN de recuperar y mostrar datos si existe archivo y si tiene contenido----------");

		System.out.println(".................................................");
		System.out.println(".................................................");
		System.out.println("...................1)presione enter para continuar");
		enter = input.nextLine();

		System.out.println("=");
		System.out.println("=");
		System.out.println("=======================================================================");
		System.out.println("=======================================================================");
		System.out.println("=");
		System.out.println("=");

		System.out.println("------Inicio respaldo en disco con un primer contenido----------");
		try {
			TEST_R8RespaldoDatos1();// Respaldar();
		} catch (Exception e) {
			System.out.println("ERROR Respaldo:  " + e.getMessage());
		}
		System.out.println("------FIN de respaldo en disco con un primer contenido-------------");

		System.out.println(".................................................");
		System.out.println(".................................................");
		System.out.println("...................2)presione enter para continuar");
		enter = input.nextLine();

		System.out.println("=");
		System.out.println("=");
		System.out.println("=======================================================================");
		System.out.println("=======================================================================");
		System.out.println("=");
		System.out.println("=");

		System.out.println(
				"------Inicio SEGUNDA recuperacion y mostra de datos si existe archivo y si tiene contenido----------");
		try {
			TEST_CargarDatosDesdeArchivo();// Recuperar();
		} catch (Exception i) {
			System.out.println("ERROR al cargar datos: " + i.getMessage());
		}
		System.out.println(
				"------FIN de SEGUNDA recuperacion y mostra de datos si existe archivo y si tiene contenido----------");

		System.out.println(".................................................");
		System.out.println(".................................................");
		System.out.println("...................3)presione enter para continuar");
		enter = input.nextLine();

		System.out.println("=");
		System.out.println("=");
		System.out.println("=======================================================================");
		System.out.println("=======================================================================");
		System.out.println("=");
		System.out.println("=");

		System.out.println("------Inicio respaldo en disco con un SEGUNDO contenido----------");
		try {
			TEST_R8RespaldoDatos2();// Respaldar();
		} catch (Exception e) {
			System.out.println("ERROR Respaldo:  " + e.getMessage());
		}
		System.out.println("------FIN de respaldo en disco con un SEGUNDO contenido-------------");

		System.out.println(".................................................");
		System.out.println(".................................................");
		System.out.println("...................4)presione enter para continuar");
		enter = input.nextLine();

		System.out.println("=");
		System.out.println("=");
		System.out.println("=======================================================================");
		System.out.println("=======================================================================");
		System.out.println("=");
		System.out.println("=");

		System.out.println(
				"------Inicio TERCER recuperacion y mostra de datos si existe archivo y si tiene contenido----------");
		try {
			TEST_CargarDatosDesdeArchivo();// Recuperar();
		} catch (Exception i) {
			System.out.println("ERROR al cargar datos: " + i.getMessage());
		}
		System.out.println(
				"------FIN de TERCER recuperacion y mostra de datos si existe archivo y si tiene contenido----------");

		System.out.println("=");
		System.out.println("=");
		System.out.println("=======================================================================");
		System.out.println("=======================================================================");
		System.out.println("=");
		System.out.println("=");

		System.out.println("---------FIN DEL TEST----------");
	}

	private static void TEST_CargarDatosDesdeArchivo() throws IOException, PersistenciaException {
		try {
			boolean existeElArchivoYestaConDatos = false;
			// VALIDO EL QUE ARCHIVO EXISTA Y Q ESTE CON DATOS::
			PersistenciaRecuperarRespaldar cargaDatos = new PersistenciaRecuperarRespaldar();
			existeElArchivoYestaConDatos = cargaDatos.verificarExistenciaYContenidoArchivo();
			if (!existeElArchivoYestaConDatos) {
				// SI no existe el archivo o esta vacio, creo el concurso por primera vez
				concursoActual = new Concurso();
			} else {
				// de lo contrario, si existe y tiene datos::
				// LEVANTO todos LOS DATOS DEL ARCHIVO LLAMANDO A LOS CONSTRUCTORES COMPLETOS DE
				// CONCURSO y PARTICIPANTE N E para cargar participantes y finalistas
				Vector<PersistenceStructuresManagement> arre;
				arre = cargaDatos.recuperar();
				for (int i = 0; i < arre.size(); i++) {
					PersistenceStructuresManagement aux = arre.get(i);
					Concurso con = aux.getConcurso();
					boolean estadoVotacionActivo = con.isEstadoVotacionActivo();
					String fechaCierreVotacionTexto = con.getFechaCierreVotacion();
					boolean hayGanador = con.isHayGanador();
					concursoActual = new Concurso(estadoVotacionActivo, fechaCierreVotacionTexto, hayGanador);

					Participantes parts = aux.getParticipantes();
					Iterator<Participante> itera = parts.getAbb().values().iterator();
					while (itera.hasNext()) {
						Participante par = itera.next();
						String nombreArtistico = par.getNombreArtistico();
						String nombreYApellido = par.getNombreYApellido();
						String foto = par.getFoto();
						int edad = par.getEdad();
						String especialidadArtistica = par.getEspecialidadArtistica();
						int puntajeTodasLasPerformances = par.getPuntajeTodasLasPerformances();
						boolean tienePerformancePendiente = par.isTienePerformancePendiente();
						boolean esFinalista = par.isEsFinalista();
						int puntajeTotalPublico = par.getPuntajeTotalPublico();
						boolean esGanador = par.isEsGanador();
						Performances performances = par.getPerformances();
						if (par.getTipoDeParticipante() == TipoParticipante.NACIONAL) {
							String departamento = ((Nacional) par).getDepartamento();
							String localidad = ((Nacional) par).getLocalidad();
							Participante nuevoParticipante = new Nacional(nombreArtistico, nombreYApellido, foto, edad,
									especialidadArtistica, puntajeTodasLasPerformances, tienePerformancePendiente,
									esFinalista, puntajeTotalPublico, esGanador, performances, departamento, localidad);
							coleccionParticipantes.Insert(nuevoParticipante);
							if (par.isEsFinalista()) {
								coleccionFinalistas.Insert(nuevoParticipante);
							}
						} else {
							String paisOrigen = ((Extranjero) par).getPaisOrigen();
							int cantidadAniosEnUy = ((Extranjero) par).getCantidadAniosEnUy();
							Participante nuevoParticipante = new Extranjero(nombreArtistico, nombreYApellido, foto,
									edad, especialidadArtistica, puntajeTodasLasPerformances, tienePerformancePendiente,
									esFinalista, puntajeTotalPublico, esGanador, performances, paisOrigen,
									cantidadAniosEnUy);
							coleccionParticipantes.Insert(nuevoParticipante);
							if (par.isEsFinalista()) {
								coleccionFinalistas.Insert(nuevoParticipante);
							}
						}

						////// termina next

						// if (par.getPerformances().largo() > 0) {
						// for (int y = 0; y < par.getPerformances().largo(); y++) {
						// int num = par.getPerformances().kEsimo(y).getNumero();
						// String desc = par.getPerformances().kEsimo(y).getDescripcion();
						// int puntajeJuez1a = par.getPerformances().kEsimo(y).getPuntajeJuez1();
						// int puntajeJuez2a = par.getPerformances().kEsimo(y).getPuntajeJuez2();
						// int puntajeJuez3a = par.getPerformances().kEsimo(y).getPuntajeJuez3();
						// System.out.println("------PERFORMANCE---------");
						// System.out.println("num:" + num);
						// System.out.println("desc: " + desc);
						// System.out.println("puntajeJuez1a: " + puntajeJuez1a);
						// System.out.println("puntajeJuez2a: " + puntajeJuez2a);
						// System.out.println("puntajeJuez3a: " + puntajeJuez3a);
						// System.out.println("--------------------------");
						// }
						// }

					} //// while (itera.hasNext())
					if (coleccionFinalistas.getLista().size() > 3) {
						System.out
								.println("-----Warning: la colecicon finalistas quedo con mas de 3 elementos---------");
						System.out.println("-----Largo: " + coleccionFinalistas.getLista().size());
					}
					////////////////////////////////////////////////////////////////////////////////////////////////////
					/////////////////////////////////////////////////////////////////
					//////////// INCIO test//////////////////////////////////////////////////////
					///////////////////////////////////////////////////////////////

					System.out.println(" ==============CONCURSO RECUPERADO========== ");
					System.out.println(" FECHA: " + con.getFechaCierreVotacion());
					System.out.println(" ===con.isEstadoVotacionActivo()::== ");
					System.out.println(con.isEstadoVotacionActivo());
					System.out.println(" ===con.isHayGanador()::== ");
					System.out.println(con.isHayGanador());
					System.out.println(" =========================================== ");

					// Participantes parts2 = aux.getParticipantes();
					Iterator<Participante> itera2 = parts.getAbb().values().iterator();
					while (itera2.hasNext()) {
						Participante par = itera2.next();

						System.out.println(" =========================================== ");
						System.out.println(" Nombre Artistico  : " + par.getNombreArtistico());
						System.out.println(" Nombre y Apellido  : " + par.getNombreYApellido());
						System.out.println(" Edad  : " + par.getEdad());
						System.out.println(" Especialidad  : " + par.getEspecialidadArtistica());
						System.out.println(" puntajeTodasLasPerformances  : " + par.getPuntajeTodasLasPerformances());
						System.out.println(" tienePerformancePendiente  : " + par.isTienePerformancePendiente());
						System.out.println(" esFinalista  : " + par.isEsFinalista());
						System.out.println(" puntajeTotalPublico  : " + par.getPuntajeTotalPublico());
						System.out.println(" esGanador  : " + par.isEsGanador());

						if (par.getTipoDeParticipante() == TipoParticipante.NACIONAL) {

							System.out.println(" Departamento  : " + ((Nacional) par).getDepartamento());
							System.out.println(" Localidad  : " + ((Nacional) par).getLocalidad());

						} else {

							System.out.println(" Pais de Origen  : " + ((Extranjero) par).getPaisOrigen());
							System.out.println(" Años en el País  : " + ((Extranjero) par).getCantidadAniosEnUy());
						}

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

						////// termina next

						// if (par.getPerformances().largo() > 0) {
						// for (int y = 0; y < par.getPerformances().largo(); y++) {
						// int num = par.getPerformances().kEsimo(y).getNumero();
						// String desc = par.getPerformances().kEsimo(y).getDescripcion();
						// int puntajeJuez1a = par.getPerformances().kEsimo(y).getPuntajeJuez1();
						// int puntajeJuez2a = par.getPerformances().kEsimo(y).getPuntajeJuez2();
						// int puntajeJuez3a = par.getPerformances().kEsimo(y).getPuntajeJuez3();
						// System.out.println("------PERFORMANCE---------");
						// System.out.println("num:" + num);
						// System.out.println("desc: " + desc);
						// System.out.println("puntajeJuez1a: " + puntajeJuez1a);
						// System.out.println("puntajeJuez2a: " + puntajeJuez2a);
						// System.out.println("puntajeJuez3a: " + puntajeJuez3a);
						// System.out.println("--------------------------");
						// }
						// }

					} //// while (itera.hasNext())

					////////////////////////////////////////////////////////////////////////////////////////////////////
					/////////////////////////////////////////////////////////////////
					//////////// FIN test//////////////////////////////////////////////////////
					///////////////////////////////////////////////////////////////
				}
				System.out.println(Mensajes.M_PERSISTENCIA_RECUPERAR_EXITO);

			} //// else 65

		} catch (Exception e) {
			System.out.println("ERROR Recuperando Datos: " + e.getMessage());
			throw new PersistenciaException(Mensajes.M_FACHADALOGICA_RECUPERAR_ERROR);
		}

	}

	//////////////////////// INICIO REQUERIMIENTO
	//////////////////////// 8/////////////////////////////////////////////////////////////////////////////////////////////

	// NO UTILIZA MONITOR EN NINGUN MOMENTO PUES SE EJECUTA "DENTRO" de un monitor
	public static void TEST_R8RespaldoDatos1()
			throws FileNotFoundException, IOException, ParticipantesExceptions, PersistenciaException {
		// nunca llama al monitor al igual que el cargar datos...este solo try guardar
		// los datos y es llamados desde el monitor

		try {
			//// aqui no controlo que este el archivo como en la carga pues, si no esta o si
			//// ya esta lo pasa por arriba y listo
			PersistenciaRecuperarRespaldar respaldoDatos = new PersistenciaRecuperarRespaldar();
			PersistenceStructuresManagement persistenciaAllData;

			Vector<PersistenceStructuresManagement> arre = new Vector<PersistenceStructuresManagement>();
			////////////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////
			//////////// INICIO test//////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////

			concursoActual = new Concurso();
			coleccionParticipantes = new Participantes();
			coleccionFinalistas = new Finalistas();

			concursoActual.setHayGanador(true);

			String nombreArtistico = "R8) 1) El ZORRO N";
			String nombreYApellido = "Maria Pereyra";
			String foto = "foto1";
			int edad = 32;
			String especialidadArtistica = "Guitarrista";
			String departamento = "Montevideo";
			String localidad = "Montevideo";
			Nacional n = new Nacional(nombreArtistico, nombreYApellido, foto, edad, especialidadArtistica, departamento,
					localidad);
			int pnumero = 1;
			String pdescripcion = "R8) 1) PrimerPErformance del loquito";
			int ppuntajeJuez1 = 1;
			int ppuntajeJuez2 = 2;
			int ppuntajeJuez3 = 3;
			Performance newPerf = new Performance(pnumero, pdescripcion, ppuntajeJuez1, ppuntajeJuez2, ppuntajeJuez3);
			n.getPerformances().AltaPerformace(newPerf);
			Performance newPerf2 = new Performance(2, pdescripcion, ppuntajeJuez1, ppuntajeJuez2, ppuntajeJuez3);
			n.getPerformances().AltaPerformace(newPerf2);

			Nacional n1 = new Nacional("R8) 1) El Gato MONTis", "Ricardo Manuel Rodriguez", "foto2", 25, "Malabarista",
					"Salto", "Constitucion");
			coleccionParticipantes.Insert(n);
			coleccionParticipantes.Insert(n1);
			coleccionFinalistas.Insert(n);
			coleccionFinalistas.Insert(n1);

			Iterator<Participante> iter = coleccionFinalistas.getLista().iterator();
			while (iter.hasNext()) {
				Participante partF = iter.next();
				if (partF.getNombreArtistico().equals("R8) 1) El ZORRO N")) {
					partF.setEsFinalista(true);
				} else {
					partF.setEsGanador(true);
				}
			}
			Extranjero e1 = new Extranjero("R8) 1) El Malo", "Evaristo Gonzalez", "foto5", 29, "Payador", "Argentino",
					10);
			coleccionParticipantes.Insert(e1);

			Extranjero e2 = new Extranjero("R8) 1) O Malevo", "Valdir Miranda", "foto5", 39, "Cantor", "Brasileiro",
					10);
			coleccionParticipantes.Insert(e2);
			////////////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////
			//////////// FIN test//////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////

			persistenciaAllData = new PersistenceStructuresManagement(concursoActual, coleccionParticipantes);

			arre.add(persistenciaAllData);
			respaldoDatos.respaldar(arre);
			System.out.println(Mensajes.M_PERSISTENCIA_RESPALDAR_EXITO);

		} catch (PersistenciaException e) {
			System.out.println("ERROR Respaldo:  " + e.getMessage());
			throw new PersistenciaException(Mensajes.M_FACHADALOGICA_RESPALDAR_ERROR);
		}

	}

	//////////////////////// FIN REQUERIMIENTO
	//////////////////////// 8/////////////////////////////////////////////////////////////////////////////////////////////

	//////////////////////// INICIO REQUERIMIENTO
	//////////////////////// 8/////////////////////////////////////////////////////////////////////////////////////////////

	// NO UTILIZA MONITOR EN NINGUN MOMENTO PUES SE EJECUTA "DENTRO" de un monitor
	public static void TEST_R8RespaldoDatos2()
			throws FileNotFoundException, IOException, ParticipantesExceptions, PersistenciaException {
		// nunca llama al monitor al igual que el cargar datos...este solo try guardar
		// los datos y es llamados desde el monitor

		try {
			//// aqui no controlo que este el archivo como en la carga pues, si no esta o si
			//// ya esta lo pasa por arriba y listo
			PersistenciaRecuperarRespaldar respaldoDatos = new PersistenciaRecuperarRespaldar();
			PersistenceStructuresManagement persistenciaAllData;

			Vector<PersistenceStructuresManagement> arre = new Vector<PersistenceStructuresManagement>();
			////////////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////
			//////////// INICIO test//////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////

			concursoActual = new Concurso();
			coleccionParticipantes = new Participantes();
			coleccionFinalistas = new Finalistas();

			concursoActual.setHayGanador(false);

			String nombreArtistico = "R8 2) El ZORRO N";
			String nombreYApellido = "Maria Pereyra";
			String foto = "foto1";
			int edad = 32;
			String especialidadArtistica = "Guitarrista";
			String departamento = "Montevideo";
			String localidad = "Montevideo";
			Nacional n = new Nacional(nombreArtistico, nombreYApellido, foto, edad, especialidadArtistica, departamento,
					localidad);
			int pnumero = 1;
			String pdescripcion = "R8 2) PrimerPErformance del loquito";
			int ppuntajeJuez1 = 1;
			int ppuntajeJuez2 = 2;
			int ppuntajeJuez3 = 3;
			Performance newPerf = new Performance(pnumero, pdescripcion, ppuntajeJuez1, ppuntajeJuez2, ppuntajeJuez3);
			n.getPerformances().AltaPerformace(newPerf);
			Performance newPerf2 = new Performance(2, pdescripcion, ppuntajeJuez1, ppuntajeJuez2, ppuntajeJuez3);
			n.getPerformances().AltaPerformace(newPerf2);

			Nacional n1 = new Nacional("R8 2) El Gato MONTis", "Ricardo Manuel Rodriguez", "foto2", 25, "Malabarista",
					"Salto", "Constitucion");
			coleccionParticipantes.Insert(n);
			coleccionParticipantes.Insert(n1);
			coleccionFinalistas.Insert(n);
			coleccionFinalistas.Insert(n1);

			Iterator<Participante> iter = coleccionFinalistas.getLista().iterator();
			while (iter.hasNext()) {
				Participante partF = iter.next();
				if (partF.getNombreArtistico().equals("R8 2) El ZORRO N")) {
					partF.setEsFinalista(true);
				} else {
					partF.setEsGanador(true);
				}
			}
			Extranjero e1 = new Extranjero("R8 2) El Malo", "Evaristo Gonzalez", "foto5", 29, "Payador", "Argentino",
					10);
			coleccionParticipantes.Insert(e1);

			Extranjero e2 = new Extranjero("R8 2) O Malevo", "Valdir Miranda", "foto5", 39, "Cantor", "Brasileiro", 10);
			coleccionParticipantes.Insert(e2);
			////////////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////
			//////////// FIN test//////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////

			persistenciaAllData = new PersistenceStructuresManagement(concursoActual, coleccionParticipantes);

			arre.add(persistenciaAllData);
			respaldoDatos.respaldar(arre);
			System.out.println(Mensajes.M_PERSISTENCIA_RESPALDAR_EXITO);

		} catch (PersistenciaException e) {
			System.out.println("ERROR Respaldo:  " + e.getMessage());
			throw new PersistenciaException(Mensajes.M_FACHADALOGICA_RESPALDAR_ERROR);
		}

	}

	//////////////////////// FIN REQUERIMIENTO
	//////////////////////// 8/////////////////////////////////////////////////////////////////////////////////////////////

}