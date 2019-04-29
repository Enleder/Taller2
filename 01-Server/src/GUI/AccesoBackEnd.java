package GUI;

import java.io.*;
import java.util.*;
import Exceptions.ParticipantesExceptions;
import Logic.FachadaLogica;
import Logic.SystemProperties;
import Logic.TipoParticipante;
import ValueObjects.VOExtranjero;
import ValueObjects.VONacional;
import ValueObjects.VOParticipante;
import ValueObjects.VOParticipanteDetallado;
import ValueObjects.VOParticipanteFinalista;
import ValueObjects.VOPerformance;

public class AccesoBackEnd {

	private static SystemProperties properties;
	private static boolean EstadoVotacion;
	private static boolean HayGanador;
	private static int CantParticipantes;
	private static boolean QuedaAlgunaPerformance;

	public static void main(String[] args)
			throws FileNotFoundException, IOException, ParticipantesExceptions, InterruptedException {
		FachadaLogica fachada = FachadaLogica.getInstancia();
		properties = new SystemProperties();

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String opcion = "99";
		boolean isNumero = false;

		int vuelta = 0;
		do {
			vuelta++;
			System.out.println(vuelta
					+ "------------------------Bienvenido a 'SPT - Sistema de Participantes de TalentoUruguayo' ------------------------------");
			System.out.println("Ingrese opcion:");
			System.out.println("1) Registro de un participante");
			System.out.println("2) Listado general de participantes");
			System.out.println("3) Listado detallado de un participante");
			System.out.println("4) Listado de performances de un participante");
			System.out.println("5) Cierre de performances y definición de finalistas");
			System.out.println("6) Ingreso de performance de un participante");
			System.out.println("7) Cierre de votación del público");
			// System.out.println("8) Respaldo de datos");
			System.out.println("9) Listado de finalistas y ganador del certamen");
			System.out.println("10) Votación de un ganador");
			System.out.println("11) Listado Detallado de Todos los Participantes");
			System.out.println("0) SALIR");
			System.out.println("Opcion: ");

			opcion = input.nextLine();

			try {
				EstadoVotacion = fachada.getEstadoVotacion();
				HayGanador = fachada.getHayGanador();
				CantParticipantes = fachada.getCantParticipantes();
				QuedaAlgunaPerformance = fachada.getQuedaAlgunaPerformance();
			} catch (Exception e) {
				// System.out.println(e.getMessage());
			}

			switch (opcion) {

			case "1":

				if (EstadoVotacion || HayGanador) {
					System.out.println(
							"ERROR: No se puede ejecutar esta opcion ya que la etapa de inscripcion y realizacion de performances termino (la votacion del publico esta abierta o ya hay un ganador)");
				} else {

					System.out.println("Entro a la opcion: 1) Registro de un participante");

					String nombreArtistico;
					String nombreYApellido;
					String foto;
					int edad = 0;
					String especialidadArtistica = "";
					String esNacional;

					System.out.println("Ingrese nombre artistico");
					nombreArtistico = input.nextLine();

					if (nombreArtistico.length() < Integer.parseInt(properties.getNomArtMin())) {
						System.out.println("Error, el nombre artistico debe ser mayor a " + properties.getNomArtMin()
								+ " caracteres, reintente");
					} else {

						System.out.println("Ingrese nombre y apellido");
						nombreYApellido = input.nextLine();
						System.out.println("foto");
						foto = input.nextLine();

						isNumero = false;
						while (!isNumero) {

							System.out.println("edad");
							try {
								edad = input.nextInt();
								input.nextLine();
								isNumero = true;
							} catch (Exception e) {// InputMismatchException
								// System.out.println(e.getMessage());
								isNumero = false;
								System.out.println("***Solo se permite ingreso de numeros, reintente");
								input.nextLine();
							}
						}

						System.out.println("Ingrese especialidad artistica");
						especialidadArtistica = input.nextLine();

						System.out.println(
								"Es Naciona? Ingrese 1 si es nacional, cualquier otro digito hara que se considere como extranjero");
						esNacional = input.nextLine();

						if (esNacional.equals("1")) {

							String departamento;
							String localidad;

							System.out.println("departamento");
							departamento = input.nextLine();
							System.out.println("localidad");
							localidad = input.nextLine();

							try {
								VOParticipanteDetallado voN = new VONacional(nombreArtistico, nombreYApellido, foto,
										edad, especialidadArtistica, departamento, localidad);
								fachada.R1AltaParticipante(voN);
								System.out.println("Registro Ingresado Correctamente !!");

							} catch (Exception e) {
								System.out.println(e.getMessage());
							}

						} else {

							String paisOrigen;
							int cantidadAniosEnUy = 0;

							System.out.println("paisOrigen");
							paisOrigen = input.nextLine();

							isNumero = false;
							while (!isNumero) {

								System.out.println("cantidadAniosEnUy");

								try {
									cantidadAniosEnUy = input.nextInt();
									input.nextLine();
									isNumero = true;
								} catch (Exception e) {// InputMismatchException
									// System.out.println(e.getMessage());
									isNumero = false;
									System.out.println("***Solo se permite ingreso de numeros, reintente");
									input.nextLine();
								}
							}

							try {
								VOParticipanteDetallado voE = new VOExtranjero(nombreArtistico, nombreYApellido, foto,
										edad, especialidadArtistica, paisOrigen, cantidadAniosEnUy);

								fachada.R1AltaParticipante(voE);
								System.out.println("Registro Ingresado Correctamente !!");

							} catch (Exception e) {
								System.out.println(e.getMessage());
							}

						}

					}
				}
				break;

			case "2":

				if (CantParticipantes == 0) {
					System.out.println(
							"ERROR: No se puede ejecutar esta opcion ya que no hay participantes registrados aun");
				} else {

					System.out.println("Entro a la opcion: 2) Listado general de participantes");
					System.out.println("======================================================");

					// ArrayList<VOParticipante> listadoSimple;

					List<VOParticipante> listadoSimple = new ArrayList<VOParticipante>();

					try {
						listadoSimple = fachada.R2ListParticipantes();
						Iterator<VOParticipante> iter = listadoSimple.iterator();
						while (iter.hasNext()) {
							VOParticipante par = iter.next();
							System.out.println(" =========================================== ");
							System.out.println(" Nombre Artistico  : " + par.getNombreArtistico());
							System.out.println(" Edad  : " + par.getEdad());
							System.out.println(" Especialidad  : " + par.getEspecialidadArtistica());
							System.out.println(" =========================================== ");
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				break;

			case "3":

				// Misma validacion del 2 pero no tiene sentido en WIN porque si no esta la
				// lista no puede acceder a esta oipcion
				System.out.println("Entro a la opcion: 3) Listado detallado de un participante");
				System.out.println("Ingrese nombre artistico para buscar y desplegar");
				String nombreArtistico = input.nextLine();

				VOParticipanteDetallado voDetallado;
				try {
					voDetallado = fachada.R3MostrarDetParticipante(nombreArtistico);
					System.out.println(
							" ===============DATOS DETALLADOS DE UN PARTICIPANTE============================ ");
					System.out.println(" Nombre Artistico  : " + voDetallado.getNombreArtistico());
					System.out.println(" Nombre y Apellido  : " + voDetallado.getNombreYApellido());
					System.out.println(" Edad  : " + voDetallado.getEdad());
					System.out.println(" Especialidad  : " + voDetallado.getEspecialidadArtistica());
					System.out
							.println(" puntajeTodasLasPerformances  : " + voDetallado.getPuntajeTodasLasPerformances());
					System.out.println(" tienePerformancePendiente  : " + voDetallado.isTienePerformancePendiente());
					System.out.println(" esFinalista  : " + voDetallado.isEsFinalista());
					System.out.println(" puntajeTotalPublico  : " + voDetallado.getPuntajeTotalPublico());
					System.out.println(" esGanador  : " + voDetallado.isEsGanador());
					if (voDetallado.getTipoDeParticipante() == TipoParticipante.NACIONAL) {
						System.out.println(" Departamento  : " + ((VONacional) voDetallado).getDepartamento());
						System.out.println(" Localidad  : " + ((VONacional) voDetallado).getLocalidad());
					} else {
						System.out.println(" País de Origen  : " + ((VOExtranjero) voDetallado).getPaisOrigen());
						System.out
								.println(" Años en el País  : " + ((VOExtranjero) voDetallado).getCantidadAniosEnUy());
					}
					System.out.println(" =========================================== ");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case "4":
				System.out.println("Entro a la opcion: 4) Listado de performances de un participante");
				System.out.println("Ingrese nombre artistico para buscar y desplegar sus performances");
				nombreArtistico = input.nextLine();

				ArrayList<VOPerformance> listadoPerformance;
				try {
					listadoPerformance = fachada.R4ListPerformancesUnParticipante(nombreArtistico);
					Iterator<VOPerformance> iter = listadoPerformance.iterator();
					if (!iter.hasNext()) {
						System.out.println("ERROR: " + nombreArtistico + " no tiene performances que listar");
					} else {

						System.out.println(
								" =========PERFORMANCES DE UN PARTICIPANTE================================== ");
						System.out.println(" Nombre Artistico  : " + nombreArtistico);
						while (iter.hasNext()) {
							VOPerformance per = iter.next();
							System.out.println("----------------------------------");
							System.out.println("num:" + per.getNumero());
							System.out.println("desc: " + per.getDescripcion());
							System.out.println("puntajeJuez1a: " + per.getPuntajeJuez1());
							System.out.println("puntajeJuez2a: " + per.getPuntajeJuez2());
							System.out.println("puntajeJuez3a: " + per.getPuntajeJuez3());
							System.out.println("----------------------------------");
						}
						System.out.println(" =========================================== ");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case "5":
				if (QuedaAlgunaPerformance) {
					System.out.println(
							"ERROR: No se puede ejecutar esta opcion ya que hay participantes que no compeltaron la totalidad de sus performances");
				} else {
					if (EstadoVotacion || HayGanador) {
						System.out.println(
								"ERROR: No se puede ejecutar esta opcion ya que la etapa realizacion de performances ya fue cerrada (la votacion del publico esta abierta o ya hay un ganador)");
					} else {

						System.out.println("Entro a la opcion: 5) Cierre de performances y definición de finalistas");
						try {
							fachada.R5CierrePerfsYDefinicionFinalistas();
							System.out.println(
									"Se cerro la etapa de performances y se defino los finalistas correctamente!!!");

						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}
				}
				break;
			case "6":
				if (EstadoVotacion || HayGanador) {
					System.out.println(
							"ERROR: No se puede ejecutar esta opcion ya que la etapa realizacion de performances ya fue cerrada (la votacion del publico esta abierta o ya hay un ganador)");
				} else {

					System.out.println("Entro a la opcion: 6) Ingreso de performance de un participante");
					System.out.println("Ingrese nombre artistico para agregar una performance");
					nombreArtistico = input.nextLine();
					try {
						// int cantPerf = 0;
						// try {
						// listadoPerformance =
						// fachada.R4ListPerformancesUnParticipante(nombreArtistico);
						// cantPerf = listadoPerformance.size();
						// } catch (Exception e) {
						// // handle exception
						// }
						// if (cantPerf == Integer.parseInt(properties.getCantidadPerformances())) {
						// System.out.println("No se puede ingresar una nueva performance a este
						// participante");
						// } else {
						// int numeroNuevaPerformance = cantPerf + 1;
						System.out.println("Ingrese descripcion de la performance");
						String descripcion = input.nextLine();
						int pto1 = 0, pto2 = 0, pto3 = 0;

						isNumero = false;
						while (!isNumero) {

							try {
								System.out.println("Ingrese puntaje jurado 1");
								pto1 = input.nextInt();
								input.nextLine();
								isNumero = true;
								isNumero = true;
							} catch (Exception e) {// InputMismatchException
								// System.out.println(e.getMessage());
								isNumero = false;
								System.out.println("***Solo se permite ingreso de numeros, reintente");
								input.nextLine();
							}
						}
						isNumero = false;
						while (!isNumero) {

							try {
								System.out.println("Ingrese puntaje jurado 2");
								pto2 = input.nextInt();
								input.nextLine();
								isNumero = true;
							} catch (Exception e) {// InputMismatchException
								// System.out.println(e.getMessage());
								isNumero = false;
								System.out.println("***Solo se permite ingreso de numeros, reintente");
								input.nextLine();
							}
						}
						isNumero = false;
						while (!isNumero) {

							try {
								System.out.println("Ingrese puntaje jurado 3");
								pto3 = input.nextInt();
								input.nextLine();
								isNumero = true;
							} catch (Exception e) {// InputMismatchException
								// System.out.println(e.getMessage());
								isNumero = false;
								System.out.println("***Solo se permite ingreso de numeros, reintente");
								input.nextLine();
							}
						}

						VOPerformance VOper = new VOPerformance(0, descripcion, pto1, pto2, pto3);
						try {
							fachada.R6AltaPerfUnParticipante(nombreArtistico, VOper);
							System.out.println("Performance Ingresada correctametne!!!");

						} catch (Exception e) {
							System.out.println(e.getMessage());
						}

						// }
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				break;
			case "7":
				if (!EstadoVotacion) {
					System.out.println(
							"ERROR: No se puede ejecutar esta opcion ya que la etapa de votación no esta abierta");
				} else {

					System.out.println("Entro a la opcion: 7) Cierre de votación del público");
					try {
						fachada.R7CierreVotacionPublico();
						System.out.println("Se cerro la votacón del público correctamente");

					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				break;
			case "9":
				if (HayGanador) {
					// como la antigua condicion logica no funcionaba y para no separar el
					// requerimiento en 2 parte
					// si hay ganador, la votacion estara cerrada, pero si esta cerrada no entrara
					// en el if de abajo,
					// entonces, si hay ganador, significa que la votacion no esta cerrada porque
					// recien empezo el concurso,
					// sino que esta cerrada por que ya se obtuvo el ganador entonces si yo aca
					// adentro engaño la logica
					// y le digo que esta abierta, no hace daño a otra condicion u opcion pues el
					// estado de votacion se carga cada vez que se itera este menu
					EstadoVotacion = true;
				}

				if (!EstadoVotacion) {// || !HayGanador) {
					System.out.println(
							"ERROR: No se puede ejecutar esta opcion ya que no hay finalistas o ganador definidos");
				} else {

					System.out.println("Entro a la opcion: 9) Listado de finalistas y ganador del certamen");
					ArrayList<VOParticipanteFinalista> VOFinalistasAListar;
					try {
						VOFinalistasAListar = fachada.R9ListFinalistasYGanador();

						Iterator<VOParticipanteFinalista> iter = VOFinalistasAListar.iterator();
						while (iter.hasNext()) {
							VOParticipanteFinalista pFina = iter.next();
							System.out.println(" ===============DATOS DETALLADOS DE UN FINALISTA=============== ");
							System.out.println(" Nombre Artistico  : " + pFina.getNombreArtistico());
							System.out.println(" Edad  : " + pFina.getEdad());
							System.out.println(" puntajeTotalPublico  : " + pFina.getPuntajeTotalPublico());
							if (!fachada.getEstadoVotacion()) {
								System.out.println(" esGanador  : " + pFina.isEsGanador());
							}
							System.out.println(" =========================================== ");
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				break;
			case "10":
				if (!EstadoVotacion) {
					System.out.println("ERROR: No se puede ejecutar esta opcion ya la votacion no esta abierta");
				} else {

					System.out.println("Entro a la opcion: 10) Votación de un ganador");
					System.out.println(
							"Ingrese nombre artistico de uno de los finalistas desplegados en la opcion 9 para agregar un voto");
					nombreArtistico = input.nextLine();
					try {
						fachada.R10AcreditarVotoAFinalista(nombreArtistico);
						System.out.println(
								"SU (1) VOTO FUE AGREDITADO CORRECTAMENTE AL FINALISTA: " + nombreArtistico + "!!!");
						System.out.println("Puede verificarlo con la opcion 9");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				break;
			case "11":
				if (CantParticipantes == 0) {
					System.out.println(
							"ERROR: No se puede ejecutar esta opcion ya que no hay participantes registrados aun");
				} else {

					System.out.println(
							"Entro a la opcion: 11) Listado completo detallado de participantes y perfromances");
					System.out.println("======================================================");

					// ArrayList<VOParticipante> listadoSimple;

					List<VOParticipante> listadoSimple = new ArrayList<VOParticipante>();

					try {
						listadoSimple = fachada.R2ListParticipantes();
						Iterator<VOParticipante> iter = listadoSimple.iterator();
						while (iter.hasNext()) {
							VOParticipante par = iter.next();
							System.out.println(" ");
							System.out.println("   ");
							System.out.println(
									" =========================================================================================== ");
							System.out.println(
									" =========================================================================================== ");
							System.out.println(
									" =========================================================================================== ");
							System.out.println(
									" =========================================================================================== ");
							System.out.println("   ");
							System.out.println("  ");
							nombreArtistico = par.getNombreArtistico();

							// VOParticipanteDetallado voDetallado;
							try {
								voDetallado = fachada.R3MostrarDetParticipante(nombreArtistico);
								System.out.println(
										" ===============DATOS DETALLADOS DE UN PARTICIPANTE============================ ");
								System.out.println(" Nombre Artistico  : " + voDetallado.getNombreArtistico());
								System.out.println(" Nombre y Apellido  : " + voDetallado.getNombreYApellido());
								System.out.println(" Edad  : " + voDetallado.getEdad());
								System.out.println(" Especialidad  : " + voDetallado.getEspecialidadArtistica());
								System.out.println(" puntajeTodasLasPerformances  : "
										+ voDetallado.getPuntajeTodasLasPerformances());
								System.out.println(
										" tienePerformancePendiente  : " + voDetallado.isTienePerformancePendiente());
								System.out.println(" esFinalista  : " + voDetallado.isEsFinalista());
								System.out.println(" puntajeTotalPublico  : " + voDetallado.getPuntajeTotalPublico());
								System.out.println(" esGanador  : " + voDetallado.isEsGanador());
								if (voDetallado.getTipoDeParticipante() == TipoParticipante.NACIONAL) {
									System.out.println(
											" Departamento  : " + ((VONacional) voDetallado).getDepartamento());
									System.out.println(" Localidad  : " + ((VONacional) voDetallado).getLocalidad());
								} else {
									System.out.println(
											" Pais de Origen  : " + ((VOExtranjero) voDetallado).getPaisOrigen());
									System.out.println(" Años en el País  : "
											+ ((VOExtranjero) voDetallado).getCantidadAniosEnUy());
								}
								System.out.println(" =========================================== ");
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}

							// ArrayList<VOPerformance> listadoPerformance;
							try {
								listadoPerformance = fachada.R4ListPerformancesUnParticipante(nombreArtistico);
								Iterator<VOPerformance> iter2 = listadoPerformance.iterator();
								if (!iter2.hasNext()) {
									System.out
											.println("ERROR: " + nombreArtistico + " no tiene performances que listar");
								} else {

									System.out.println(
											" =========PERFORMANCES DE UN PARTICIPANTE================================== ");
									System.out.println(" Nombre Artistico  : " + nombreArtistico);
									while (iter2.hasNext()) {
										VOPerformance per = iter2.next();
										System.out.println("----------------------------------");
										System.out.println("num:" + per.getNumero());
										System.out.println("desc: " + per.getDescripcion());
										System.out.println("puntajeJuez1a: " + per.getPuntajeJuez1());
										System.out.println("puntajeJuez2a: " + per.getPuntajeJuez2());
										System.out.println("puntajeJuez3a: " + per.getPuntajeJuez3());
										System.out.println("----------------------------------");
									}
									System.out.println(" =========================================== ");
								}
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}

						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				break;
			case "0":
				System.out.println("Entro a la opcion: 0) SALIR");
				break;
			default:
				System.out.println("*****************ERROR, Caracter incorrecto, reintente");
				break;
			}
			System.out.println("------------------------------------------------------------------------");
		} while (!opcion.equals("0"));
		System.out.println("---------------------------FIN DEL PROGRAMA---------------------------------------");
		System.out.println("*****************<<<______About this software______>>>****************************");
		System.out.println("UDE-TRABAJO TALLER 2 LIC.INFO.Nocturno2018/19-Tutor: Suarez, Gabriel              ");
		System.out.println("# proudlyDevelopedBy:                                                             ");
		System.out.println("# Pias, Richard        - 1.924.591-2                                              ");
		System.out.println("# Segovia, Joaquin     - 4.739.544-4                                              ");
		System.out.println("# Torres, Mathias      - 4.223.291-4                                              ");
		System.out.println("**********************************************************************************");
	}

}