package Logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import ValueObjects.*;
import Exceptions.*;
import Logic.SystemProperties;
import Persistence.PersistenceStructuresManagement;
import Persistence.PersistenciaRecuperarRespaldar;

public class FachadaLogica extends UnicastRemoteObject implements IFachadaLogica {

	private static final long serialVersionUID = 1L;
	private static FachadaLogica instancia; // SINGLETON
	private static Concurso concursoActual;
	private static Participantes coleccionParticipantes;
	private static Finalistas coleccionFinalistas;
	private static Monitor monitor;
	private static SystemProperties properties;

	private FachadaLogica() throws FileNotFoundException, IOException {
		// System.out.println("Ctor FL");

		monitor = new Monitor();
		coleccionParticipantes = new Participantes();
		coleccionFinalistas = new Finalistas();
		properties = new SystemProperties();

		//// el concurso se instancia dentro para ver si como lo construimos dependiendo
		//// si ya hay datos o no guardados

		// System.out.println("FL------------------");
		try {
			CargarDatosDesdeArchivo();// Recuperar();
		} catch (Exception i) {
			// System.out.println("ERROR al cargar datos: " + i.getMessage());
		}

	}

	public static FachadaLogica getInstancia() throws FileNotFoundException, IOException, ParticipantesExceptions {
		if (instancia == null) {
			instancia = new FachadaLogica();
		}
		return instancia;
	}

	//// Recupera los datos desde el archivo, "opuesto" del R8
	private void CargarDatosDesdeArchivo() throws IOException, PersistenciaException {
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
				// LEVANTO TODO LOS DATOS DEL ARCHIVO LLAMANDO A LOS CONSTRUCTORES COMPLETOS DE
				// CONCURSO y PARTICIPANTE N E para cargar participantes y finalistas
				Vector<PersistenceStructuresManagement> arre;
				arre = cargaDatos.recuperar();
				for (int i = 0; i < arre.size(); i++) {
					PersistenceStructuresManagement aux = arre.get(i);
					Concurso con = aux.getConcurso();
					boolean estadoVotacionActivo = con.isEstadoVotacionActivo();
					String fechaCierreVotacionTexto = con.getFechaCierreVotacion();
					boolean hayGanadores = con.isHayGanador();
					concursoActual = new Concurso(estadoVotacionActivo, fechaCierreVotacionTexto, hayGanadores);

					Participantes parts = aux.getParticipantes();
					Iterator<Participante> itera = parts.getAbb().values().iterator();

					coleccionFinalistas = new Finalistas();

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

					} //// while (itera.hasNext())
					if (coleccionFinalistas.getLista().size() > 3) {
						System.out
								.println("-----Warning: la colecicon finalistas quedo con mas de 3 elementos---------");
						System.out.println("-----Largo: " + coleccionFinalistas.getLista().size());
					}

				}
				// System.out.println(Mensajes.M_PERSISTENCIA_RECUPERAR_EXITO);

			} //// else 62

		} catch (Exception e) {
			System.out.println("ERROR Recuperando Datos: " + e.getMessage());
			throw new PersistenciaException(Mensajes.M_FACHADALOGICA_RECUPERAR_ERROR);
		}

	}

	//// INICIO REQUERIMIENTO 1+++++++++++++++++++++++++++++++++++++++++++++++++++++
	//// PRECONDICION: La votacion no debe estar abierta y ademas no debe haber
	//// ganador (usar en la interface los ultimos metodos de la fachada)
	public void R1AltaParticipante(VOParticipanteDetallado DatosParticipante)
			throws RemoteException, ParticipantesExceptions, FileNotFoundException, IOException, InterruptedException {
		try {
			monitor.comienzoEscritura();
		} catch (InterruptedException e) {
			monitor.terminoEscritura();
			throw new InterruptedException(Mensajes.M_FACHADALOGICA_ESCRITURA_ERROR);
		}
		try {
			CargarDatosDesdeArchivo();// Recuperar();
		} catch (Exception i) {
			// System.out.println("ERROR al cargar datos: " + i.getMessage());
		}

		String idABuscar = DatosParticipante.getNombreArtistico().trim();

		if (coleccionParticipantes.Member(idABuscar)) {
			monitor.terminoEscritura();
			throw new ParticipantesExceptions(Mensajes.M_PARTICIPANTES_YAEXISTE);
		} else {
			String nombreArtistico = DatosParticipante.getNombreArtistico().trim();
			String nombreYApellido = DatosParticipante.getNombreYApellido();
			String foto = DatosParticipante.getFoto();
			int edad = DatosParticipante.getEdad();
			String especialidadArtistica = DatosParticipante.getEspecialidadArtistica();
			if (DatosParticipante.getTipoDeParticipante() == TipoParticipante.NACIONAL) {
				String departamento = ((VONacional) DatosParticipante).getDepartamento();
				String localidad = ((VONacional) DatosParticipante).getLocalidad();
				Participante nuevoParticipante = new Nacional(nombreArtistico, nombreYApellido, foto, edad,
						especialidadArtistica, departamento, localidad);
				try {
					coleccionParticipantes.Insert(nuevoParticipante);
					try {
						R8RespaldoDatos();// Respaldar();
					} catch (Exception e) {
						// System.out.println("ERROR Respaldo: " + e.getMessage());
					}
				} catch (ParticipantesExceptions ep) {
					monitor.terminoEscritura();
					throw new ParticipantesExceptions(ep.getMessage());
				} catch (FileNotFoundException ef) {
					monitor.terminoEscritura();
					throw new ParticipantesExceptions(Mensajes.M_FACHADALOGICA_INTERNAL_ERROR);
				} catch (IOException eio) {
					monitor.terminoEscritura();
					throw new ParticipantesExceptions(Mensajes.M_FACHADALOGICA_INTERNAL_ERROR);
				}
			} else {
				String paisOrigen = ((VOExtranjero) DatosParticipante).getPaisOrigen();
				int cantidadAniosEnUy = ((VOExtranjero) DatosParticipante).getCantidadAniosEnUy();
				Participante nuevoParticipante = new Extranjero(nombreArtistico, nombreYApellido, foto, edad,
						especialidadArtistica, paisOrigen, cantidadAniosEnUy);
				try {
					coleccionParticipantes.Insert(nuevoParticipante);
					try {
						R8RespaldoDatos();// Respaldar();
					} catch (Exception e) {
						// System.out.println("ERROR Respaldo: " + e.getMessage());
					}
				} catch (ParticipantesExceptions ep) {
					monitor.terminoEscritura();
					throw new ParticipantesExceptions(ep.getMessage());
				} catch (FileNotFoundException ef) {
					monitor.terminoEscritura();
					throw new ParticipantesExceptions(Mensajes.M_FACHADALOGICA_INTERNAL_ERROR);
				} catch (IOException eio) {
					monitor.terminoEscritura();
					throw new ParticipantesExceptions(Mensajes.M_FACHADALOGICA_INTERNAL_ERROR);
				}
			}
			// ojo que si se saca el cierre de monitor de aca hay que ponerlo debajo de los
			// dos insert que hay
			monitor.terminoEscritura();
		}
	}
	//// FIN REQUERIMIENTO 1----------------------------------------------------

	//// INICIO REQUERIMIENTO 2+++++++++++++++++++++++++++++++++++++++++++++++++++++
	public ArrayList<VOParticipante> R2ListParticipantes()
			throws RemoteException, ParticipantesExceptions, InterruptedException {
		try {
			monitor.comienzoLectura();
		} catch (InterruptedException e1) {
			monitor.terminoLectura();
			throw new InterruptedException(Mensajes.M_FACHADALOGICA_LECTURA_ERROR);
		}

		try {
			CargarDatosDesdeArchivo();// Recuperar();
		} catch (Exception i) {
			// System.out.println("ERROR al cargar datos: " + i.getMessage());
		}

		if (!coleccionParticipantes.HayAlgunParticipante()) {
			monitor.terminoLectura();
			throw new ParticipantesExceptions(Mensajes.M_PARTICIPANTES_NOHAYNINGUNO);
		} else {
			ArrayList<VOParticipante> listadoSimple = new ArrayList<VOParticipante>();
			Iterator<Participante> iter = coleccionParticipantes.getAbb().values().iterator();
			while (iter.hasNext()) {
				Participante par = iter.next();
				String nombreArtistico = par.getNombreArtistico();
				int edad = par.getEdad();
				String especialidadArtistica = par.getEspecialidadArtistica();
				VOParticipante datosUnPar = new VOParticipante(nombreArtistico, edad, especialidadArtistica);
				listadoSimple.add(datosUnPar);
			}
			monitor.terminoLectura();
			return listadoSimple;
		}

	}

	//// FIN REQUERIMIENTO 2----------------------------------------------------

	//// INICIO REQUERIMIENTO 3+++++++++++++++++++++++++++++++++++++++++++++++++++++
	public VOParticipanteDetallado R3MostrarDetParticipante(String idParticipante)
			throws RemoteException, ParticipantesExceptions, InterruptedException {
		try {
			monitor.comienzoLectura();
		} catch (InterruptedException e) {
			monitor.terminoLectura();
			throw new InterruptedException(Mensajes.M_FACHADALOGICA_LECTURA_ERROR);
		}
		try {
			CargarDatosDesdeArchivo();// Recuperar();
		} catch (Exception i) {
			// System.out.println("ERROR al cargar datos: " + i.getMessage());
		}

		if (idParticipante == " ") {
			monitor.terminoLectura();
			throw new ParticipantesExceptions(Mensajes.M_PARTICIPANTES_NOEXISTE);
		} else {
			if (coleccionParticipantes.Member(idParticipante) == false) {
				monitor.terminoLectura();
				throw new ParticipantesExceptions(Mensajes.M_PARTICIPANTES_NOEXISTE);
			} else {

				Participante p = coleccionParticipantes.Find(idParticipante);

				String nombreArtistico = p.getNombreArtistico();
				String nombreYApellido = p.getNombreYApellido();
				int edad = p.getEdad();
				String especialidadArtistica = p.getEspecialidadArtistica();
				String foto = p.getFoto();
				int puntajeTodasLasPerformances = p.getPuntajeTodasLasPerformances();
				boolean tienePerformancePendiente = p.isTienePerformancePendiente();
				boolean esFinalista = p.isEsFinalista();
				int puntajeTotalPublico = p.getPuntajeTotalPublico();
				boolean esGanador = p.isEsGanador();

				if (p.getTipoDeParticipante() == TipoParticipante.NACIONAL) {

					String departamento = ((Nacional) p).getDepartamento();
					String localidad = ((Nacional) p).getLocalidad();

					VOParticipanteDetallado pNacional = new VONacional(nombreArtistico, nombreYApellido, foto, edad,
							especialidadArtistica, puntajeTodasLasPerformances, tienePerformancePendiente, esFinalista,
							puntajeTotalPublico, esGanador, departamento, localidad);
					monitor.terminoLectura();
					return pNacional;

				} else {

					Extranjero ex = (Extranjero) p;

					String paisOrigen = ex.getPaisOrigen();
					int cantidadAniosEnUy = ex.getCantidadAniosEnUy();

					VOParticipanteDetallado pExtranjero = new VOExtranjero(nombreArtistico, nombreYApellido, foto, edad,
							especialidadArtistica, puntajeTodasLasPerformances, tienePerformancePendiente, esFinalista,
							puntajeTotalPublico, esGanador, paisOrigen, cantidadAniosEnUy);

					monitor.terminoLectura();
					return pExtranjero;
				}
			}
		}
	}
	//// FIN REQUERIMIENTO 3----------------------------------------------------

	//// INICIO REQUERIMIENTO 4+++++++++++++++++++++++++++++++++++++++++++++++++++++
	public ArrayList<VOPerformance> R4ListPerformancesUnParticipante(String idParticipante)
			throws RemoteException, ParticipantesExceptions, InterruptedException {
		try {
			monitor.comienzoLectura();
		} catch (InterruptedException e) {
			monitor.terminoLectura();
			throw new InterruptedException(Mensajes.M_FACHADALOGICA_LECTURA_ERROR);
		}
		try {
			CargarDatosDesdeArchivo();// Recuperar();
		} catch (Exception i) {
			// System.out.println("ERROR al cargar datos: " + i.getMessage());
		}

		if (coleccionParticipantes.Member(idParticipante) == false) {
			monitor.terminoLectura();
			throw new ParticipantesExceptions(Mensajes.M_PARTICIPANTES_NOEXISTE_R4);
		} else {
			ArrayList<VOPerformance> listadoPerformance;
			listadoPerformance = new ArrayList<VOPerformance>();

			for (int i = 0; i < coleccionParticipantes.Find(idParticipante).getPerformances().largo(); i++) {

				int numero = coleccionParticipantes.Find(idParticipante).getPerformances().kEsimo(i).getNumero();
				String descripcion = coleccionParticipantes.Find(idParticipante).getPerformances().kEsimo(i)
						.getDescripcion();
				int puntajeJuez1 = coleccionParticipantes.Find(idParticipante).getPerformances().kEsimo(i)
						.getPuntajeJuez1();
				int puntajeJuez2 = coleccionParticipantes.Find(idParticipante).getPerformances().kEsimo(i)
						.getPuntajeJuez2();
				int puntajeJuez3 = coleccionParticipantes.Find(idParticipante).getPerformances().kEsimo(i)
						.getPuntajeJuez3();

				VOPerformance vop = new VOPerformance(numero, descripcion, puntajeJuez1, puntajeJuez2, puntajeJuez3);

				listadoPerformance.add(i, vop);
			}
			monitor.terminoLectura();
			return listadoPerformance;
		}
	}
	//// FIN REQUERIMIENTO 4----------------------------------------------------

	//// INICIO REQUERIMIENTO 5+++++++++++++++++++++++++++++++++++++++++++++++++++++
	//// PRECONDICION: Todos los participantes deben haber realizado sus total de
	//// performances.
	//// La votacion no debe estar abierta y ademas no debe haber
	//// ganador (usar en la interface los ultimos metodos de la fachada)
	public void R5CierrePerfsYDefinicionFinalistas()
			throws RemoteException, ParticipantesExceptions, InterruptedException, FileNotFoundException, IOException {
		int cantidadDeParticipantesDelArbol = 0;
		try {
			cantidadDeParticipantesDelArbol = getCantParticipantes();
		} catch (Exception e) {
			throw new InterruptedException(e.getMessage());
		}
		try {
			monitor.comienzoEscritura();
		} catch (InterruptedException e) {
			monitor.terminoEscritura();
			throw new InterruptedException(Mensajes.M_FACHADALOGICA_ESCRITURA_ERROR);
		}
		try {
			CargarDatosDesdeArchivo();// Recuperar();
		} catch (Exception i) {
			// System.out.println("ERROR al cargar datos: " + i.getMessage());
		}
		if (coleccionParticipantes.HayAlgunParticipante() == false) {
			monitor.terminoEscritura();
			throw new ParticipantesExceptions(Mensajes.M_PARTICIPANTES_NOHAYNINGUNO);
		} else {
			boolean faltanPerformances = false;
			Iterator<Participante> iter = coleccionParticipantes.getAbb().values().iterator();
			while (iter.hasNext() && !faltanPerformances) {
				Participante p = iter.next();
				if (p.getPerformances().largo() < Integer.parseInt(properties.getCantidadPerformances())) {
					faltanPerformances = true;
				}

			}
			if (faltanPerformances == true) {
				monitor.terminoEscritura();
				throw new ParticipantesExceptions(Mensajes.M_PARTICIPANTES_FALTANPERFORMANCES);
			} else {
				if (cantidadDeParticipantesDelArbol < Integer.parseInt(properties.getCantidadFinalistas())) {
					monitor.terminoEscritura();
					throw new ParticipantesExceptions(Mensajes.M_PARTICIPANTES_NO_HAY_SUFICIENTES_PARTICIPANTES);
				} else {
					Iterator<Participante> iter2 = coleccionParticipantes.getAbb().values().iterator();
					while (iter2.hasNext()) {
						Participante p2 = iter2.next();
						coleccionFinalistas.Insert(p2);
					}
					coleccionFinalistas.SetFinalistas();
					concursoActual.setEstadoVotacionActivo(true);
					try {
						R8RespaldoDatos();// Respaldar();
					} catch (Exception e) {
						// System.out.println("ERROR Respaldo: " + e.getMessage());
					}
					monitor.terminoEscritura();
				}
			}
		}
	}
	//// FIN REQUERIMIENTO 5----------------------------------------------------

	//// INICIO REQUERIMIENTO 6+++++++++++++++++++++++++++++++++++++++++++++++++++++
	//// PRECONDICION: La votacion no debe estar abierta y ademas no debe haber
	//// ganador (usar en la interface los ultimos metodos de la fachada)
	public void R6AltaPerfUnParticipante(String idParticipante, VOPerformance datosNuevaPerformance)
			throws RemoteException, ParticipantesExceptions, InterruptedException {
		try {
			monitor.comienzoEscritura();
		} catch (InterruptedException e) {
			monitor.terminoEscritura();
			throw new InterruptedException(Mensajes.M_FACHADALOGICA_ESCRITURA_ERROR);
		}
		try {
			CargarDatosDesdeArchivo();// Recuperar();
		} catch (Exception i) {
			// System.out.println("ERROR al cargar datos: " + i.getMessage());
		}
		if (!coleccionParticipantes.Member(idParticipante)) {
			monitor.terminoEscritura();
			throw new ParticipantesExceptions(Mensajes.M_PARTICIPANTES_NOEXISTE_R6);
		} else {
			Participante p = coleccionParticipantes.Find(idParticipante);
			if (p.getPerformances().largo() == Integer.parseInt(properties.getCantidadPerformances())) {
				monitor.terminoEscritura();
				throw new ParticipantesExceptions(Mensajes.M_PARTICIPANTES_TOPEDEPERFORMANCES);
			} else {
				int numero = p.getPerformances().largo() + 1;
				if (numero == Integer.parseInt(properties.getCantidadPerformances())) {
					p.setTienePerformancePendiente(false);
				}
				String descripcion = datosNuevaPerformance.getDescripcion();
				int puntajeJuez1 = datosNuevaPerformance.getPuntajeJuez1();
				int puntajeJuez2 = datosNuevaPerformance.getPuntajeJuez2();
				int puntajeJuez3 = datosNuevaPerformance.getPuntajeJuez3();
				Performance nuevaPerformance = new Performance(numero, descripcion, puntajeJuez1, puntajeJuez2,
						puntajeJuez3);
				p.getPerformances().AltaPerformace(nuevaPerformance);
				int nuevoTotalPtosAllPerformances = p.getPuntajeTodasLasPerformances() + puntajeJuez1 + puntajeJuez2
						+ puntajeJuez3;
				p.setPuntajeTodasLasPerformances(nuevoTotalPtosAllPerformances);
				try {
					R8RespaldoDatos();// Respaldar();
				} catch (Exception e) {
					// System.out.println("ERROR Respaldo: " + e.getMessage());
				}
				monitor.terminoEscritura();
			}

		}
	}
	//// FIN REQUERIMIENTO 6----------------------------------------------------

	//// INICIO REQUERIMIENTO 7+++++++++++++++++++++++++++++++++++++++++++++++++++++
	//// PRECONDICION: La votacion SI debe estar abierta (usar en la interface los
	//// ultimos metodos de la fachada)
	public void R7CierreVotacionPublico()
			throws ParticipantesExceptions, InterruptedException, FileNotFoundException, IOException {
		try {
			monitor.comienzoEscritura();
		} catch (InterruptedException e) {
			monitor.terminoEscritura();
			throw new InterruptedException(Mensajes.M_FACHADALOGICA_ESCRITURA_ERROR);
		}
		try {
			CargarDatosDesdeArchivo();// Recuperar();
		} catch (Exception i) {
			// System.out.println("ERROR al cargar datos: " + i.getMessage());
		}
		if (!concursoActual.isEstadoVotacionActivo()) {
			monitor.terminoEscritura();
			throw new ParticipantesExceptions(Mensajes.M_FACHADALOGICA_ESTADOINACTIVO_ERROR);
		} else {
			// aqui no se controla si hay finalistas porque si se analiza la linea de tiempo
			// del concurso, para que esto se ejecute, el estado de votacion tiene que estar
			// activo (true), por ende cuando se ejecuta este R7 la coleccion de finalistas
			// tiene 3 elems
			concursoActual.setEstadoVotacionActivo(false);

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String fechaCierreVotacionTexto = (String) (dtf.format(now));
			concursoActual.setFechaCierreVotacion(fechaCierreVotacionTexto);

			String id = coleccionFinalistas.SetGanadorString();
			
			Iterator<Participante> iter2 = coleccionParticipantes.getAbb().values().iterator();
			while (iter2.hasNext()) {
				Participante pf = iter2.next();
				if (pf.getNombreArtistico().equals(id)) {
					pf.setEsGanador(true);
				}
			}
			
			concursoActual.setHayGanador(true);
			try {
				R8RespaldoDatos();// Respaldar();
			} catch (Exception e) {
				// System.out.println("ERROR Respaldo: " + e.getMessage());
			}
			monitor.terminoEscritura();
		}
	}
	//// FIN REQUERIMIENTO 7----------------------------------------------------

	//// INICIO REQUERIMIENTO 8+++++++++++++++++++++++++++++++++++++++++++++++++++++
	// NO NECESITA MONITOR EN NINGUN MOMENTO PUES SE EJECUTA "DENTRO" de un monitor
	private void R8RespaldoDatos()
			throws FileNotFoundException, IOException, ParticipantesExceptions, PersistenciaException {
		// nunca llama al monitor al igual que el cargar datos...este solo try guardar
		// los datos

		try {
			//// aqui no controlo que este el archivo como en la carga pues, si no esta o si
			//// ya esta lo pasa por arriba y listo
			PersistenciaRecuperarRespaldar respaldoDatos = new PersistenciaRecuperarRespaldar();
			PersistenceStructuresManagement persistenciaAllData;

			Vector<PersistenceStructuresManagement> arre = new Vector<PersistenceStructuresManagement>();

			persistenciaAllData = new PersistenceStructuresManagement(concursoActual, coleccionParticipantes);

			arre.add(persistenciaAllData);
			respaldoDatos.respaldar(arre);
			// System.out.println(Mensajes.M_PERSISTENCIA_RESPALDAR_EXITO);

		} catch (PersistenciaException e) {
			// System.out.println("ERROR Respaldo: " + e.getMessage());
			throw new PersistenciaException(Mensajes.M_FACHADALOGICA_RESPALDAR_ERROR);
		}

	}
	//// FIN REQUERIMIENTO 8----------------------------------------------------

	//// INICIO REQUERIMIENTO 9+++++++++++++++++++++++++++++++++++++++++++++++++++++
	//// PRECONDICION: La votacion debe estar abierta o haber un ganador
	public ArrayList<VOParticipanteFinalista> R9ListFinalistasYGanador()
			throws RemoteException, ParticipantesExceptions, InterruptedException {
		try {
			monitor.comienzoLectura();
		} catch (InterruptedException e) {
			monitor.terminoLectura();
			throw new InterruptedException(Mensajes.M_FACHADALOGICA_LECTURA_ERROR);
		}
		try {
			CargarDatosDesdeArchivo();// Recuperar();
		} catch (Exception i) {
			// System.out.println("ERROR al cargar datos: " + i.getMessage());
		}
		if (coleccionFinalistas.getLista().size() < Integer.parseInt(properties.getCantidadFinalistas())) {
			monitor.terminoLectura();
			throw new ParticipantesExceptions(Mensajes.M_FACHADALOGICA_INDET_FINALISTAS_ERROR);
		} else {
			ArrayList<VOParticipanteFinalista> VOFinalistasAListar = new ArrayList<VOParticipanteFinalista>();
			for (int indice = 0; indice < coleccionFinalistas.getLista().size(); indice++) {
				String nombreArtistico = coleccionFinalistas.getLista().get(indice).getNombreArtistico();
				String foto = coleccionFinalistas.getLista().get(indice).getFoto();
				int edad = coleccionFinalistas.getLista().get(indice).getEdad();
				int puntajeTotalPublico = coleccionFinalistas.getLista().get(indice).getPuntajeTotalPublico();
				boolean esGanador = coleccionFinalistas.getLista().get(indice).isEsGanador();
				VOParticipanteFinalista pv = new VOParticipanteFinalista(nombreArtistico, foto, edad,
						puntajeTotalPublico, esGanador);
				VOFinalistasAListar.add(pv);
			}
			monitor.terminoLectura();

			// VOFinalistasAListar.sort(Comparator.comparing(VOParticipanteFinalista::getPuntajeTotalPublico).reversed());
			return VOFinalistasAListar;

		}
	}
	//// FIN REQUERIMIENTO 9----------------------------------------------------

	//// INICIO REQUERIMIENTO
	//// 10+++++++++++++++++++++++++++++++++++++++++++++++++++++
	//// PRECONDICION: La votacion debe estar abierta
	public void R10AcreditarVotoAFinalista(String idFinalista)
			throws RemoteException, ParticipantesExceptions, InterruptedException {
		boolean getEstadoVotacion = false;
		try {
			getEstadoVotacion = getEstadoVotacion();
		} catch (Exception e) {
			throw new InterruptedException(e.getMessage());
		}
		try {
			monitor.comienzoEscritura();
		} catch (InterruptedException e) {
			monitor.terminoEscritura();
			throw new InterruptedException(Mensajes.M_FACHADALOGICA_ESCRITURA_ERROR);
		}
		try {
			CargarDatosDesdeArchivo();// Recuperar();
		} catch (Exception i) {
			// System.out.println("ERROR al cargar datos: " + i.getMessage());
		}
		// controla la existencia del id en la coleccion de finalistas para que no
		// intente dar un voto a alguien que no es finalista
		boolean existeElFinalistaAVotar = false;
		Iterator<Participante> iter = coleccionFinalistas.getLista().iterator();
		while (iter.hasNext() && !existeElFinalistaAVotar) {
			Participante p = iter.next();
			if (p.getNombreArtistico().equals(idFinalista)) {
				existeElFinalistaAVotar = true;
			}
		}
		if (!existeElFinalistaAVotar) {
			monitor.terminoEscritura();
			throw new ParticipantesExceptions(Mensajes.M_FINALISTAS_EL_ID_NOES_FINALISTA);
		} else {
			if (!getEstadoVotacion) {
				monitor.terminoEscritura();
				throw new ParticipantesExceptions(Mensajes.M_FACHADALOGICA_VOTACION_INACTIVA);
			} else {
				coleccionFinalistas.AumentarVotacion(idFinalista);
				try {
					R8RespaldoDatos();// Respaldar();
				} catch (Exception e) {
					// System.out.println("ERROR Respaldo: " + e.getMessage());
				}
				monitor.terminoEscritura();
			}
		}
	}
	//// FIN REQUERIMIENTO 10----------------------------------------------------

	//// INICIO metodos auxiliares +++++++++++++++++++++++++++++++++++++++++++++++++
	// uso interno, (show/hide) de componentes de interfaces(consoltaTest, WIN, WEB)

	//// Obtiene el estado de la votacion del concursoActual que esta correindo
	public boolean getEstadoVotacion() throws RemoteException, InterruptedException {
		try {
			monitor.comienzoLectura();
		} catch (InterruptedException e) {
			monitor.terminoLectura();
			throw new InterruptedException(Mensajes.M_FACHADALOGICA_LECTURA_ERROR);
		}
		try {
			CargarDatosDesdeArchivo();// Recuperar();
		} catch (Exception i) {
			// System.out.println("ERROR al cargar datos: " + i.getMessage());
		}
		boolean isEstadoVotacionActivo = concursoActual.isEstadoVotacionActivo();
		monitor.terminoLectura();
		return isEstadoVotacionActivo;
	}

	//// Obtiene el estado de la flag del concurso para saber si ya hay definido un
	//// ganador
	public boolean getHayGanador() throws RemoteException, InterruptedException {
		try {
			monitor.comienzoLectura();
		} catch (InterruptedException e) {
			monitor.terminoLectura();
			throw new InterruptedException(Mensajes.M_FACHADALOGICA_LECTURA_ERROR);
		}
		try {
			CargarDatosDesdeArchivo();// Recuperar();
		} catch (Exception i) {
			// System.out.println("ERROR al cargar datos: " + i.getMessage());
		}
		boolean isHayGanador = concursoActual.isHayGanador();
		monitor.terminoLectura();
		return isHayGanador;
	}

	//// Obtiene la cantidad de Participante en la coleccion de Participantes
	public int getCantParticipantes() throws RemoteException, InterruptedException {
		try {
			monitor.comienzoLectura();
		} catch (InterruptedException e) {
			monitor.terminoLectura();
			throw new InterruptedException(Mensajes.M_FACHADALOGICA_LECTURA_ERROR);
		}
		try {
			CargarDatosDesdeArchivo();// Recuperar();
		} catch (Exception i) {
			// System.out.println("ERROR al cargar datos: " + i.getMessage());
		}
		int cantidadParticipantes = 0;
		Iterator<Participante> iter = coleccionParticipantes.getAbb().values().iterator();
		while (iter.hasNext()) {
			@SuppressWarnings("unused")
			Participante p = iter.next();
			cantidadParticipantes++;
		}
		monitor.terminoLectura();
		return cantidadParticipantes;
	}

	//// Recorre la coleccion de Participantes y valida si queda algun participante
	public boolean getQuedaAlgunaPerformance() throws RemoteException, InterruptedException {
		try {
			monitor.comienzoLectura();
		} catch (InterruptedException e) {
			monitor.terminoLectura();
			throw new InterruptedException(Mensajes.M_FACHADALOGICA_LECTURA_ERROR);
		}
		try {
			CargarDatosDesdeArchivo();// Recuperar();
		} catch (Exception i) {
			// System.out.println("ERROR al cargar datos: " + i.getMessage());
		}
		boolean QuedaAlgunaPerformance = false;
		// int cantidadPerformancesParametro =
		// Integer.parseInt(properties.getCantidadPerformances());
		Iterator<Participante> iter = coleccionParticipantes.getAbb().values().iterator();
		while (iter.hasNext() && !QuedaAlgunaPerformance) {
			Participante p = iter.next();
			// se podria usar el get del att (leFaltaAlgunaPerformance)
			if (p.isTienePerformancePendiente()) {
				QuedaAlgunaPerformance = true;
			}
			// System.out.println("p.getPerformances().largo(): " +
			// p.getPerformances().largo());
			// if (p.getPerformances().largo() <= cantidadPerformancesParametro) {
			// System.out.println("2true");
			// QuedaAlgunaPerformance = true;
			// }
		}
		monitor.terminoLectura();
		return QuedaAlgunaPerformance;
		// boolean FaltanPerformances = false;
		// int cantidadPerformancesParametro =
		// Integer.parseInt(properties.getCantidadPerformances());
		// Iterator<Participante> iter =
		// coleccionParticipantes.getAbb().values().iterator();
		// while (iter.hasNext() && !FaltanPerformances) {
		// Participante p = iter.next();
		// // se podria usar el get del att (leFaltaAlgunaPerformance)
		// if (p.getPerformances().largo() < cantidadPerformancesParametro) {
		// FaltanPerformances = true;
		// }
		// }
		// monitor.terminoLectura();
		// return FaltanPerformances;
	}

	// DEMO DE RMI
	public String TestPersistenceRMI() throws RemoteException {

		return "TestRMI: HolaMundo desde proyecto Server, FachadaLogica";

	}

}
