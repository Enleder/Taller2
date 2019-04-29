package Logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import Exceptions.*;
import ValueObjects.VOParticipante;
import ValueObjects.VOParticipanteDetallado;
import ValueObjects.VOParticipanteFinalista;
import ValueObjects.VOPerformance;

public interface IFachadaLogica extends Remote {

	//// INICIO REQUERIMIENTO 1+++++++++++++++++++++++++++++++++++++++++++++++++++++
	//// PRECONDICION: La votacion no debe estar abierta y ademas no debe haber
	//// ganador (usar en la interface los ultimos metodos de la fachada)
	public void R1AltaParticipante(VOParticipanteDetallado DatosParticipante)
			throws RemoteException, ParticipantesExceptions, FileNotFoundException, IOException, InterruptedException;

	//// INICIO REQUERIMIENTO 2+++++++++++++++++++++++++++++++++++++++++++++++++++++
	public ArrayList<VOParticipante> R2ListParticipantes()
			throws RemoteException, ParticipantesExceptions, InterruptedException;

	//// INICIO REQUERIMIENTO 3+++++++++++++++++++++++++++++++++++++++++++++++++++++
	public VOParticipanteDetallado R3MostrarDetParticipante(String idParticipante)
			throws RemoteException, ParticipantesExceptions, InterruptedException;

	//// INICIO REQUERIMIENTO 4+++++++++++++++++++++++++++++++++++++++++++++++++++++
	public ArrayList<VOPerformance> R4ListPerformancesUnParticipante(String idParticipante)
			throws RemoteException, ParticipantesExceptions, InterruptedException;

	//// INICIO REQUERIMIENTO 5+++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void R5CierrePerfsYDefinicionFinalistas()
			throws RemoteException, ParticipantesExceptions, InterruptedException, FileNotFoundException, IOException;

	//// INICIO REQUERIMIENTO 6+++++++++++++++++++++++++++++++++++++++++++++++++++++
	//// PRECONDICION: La votacion no debe estar abierta y ademas no debe haber
	//// ganador (usar en la interface los ultimos metodos de la fachada)
	public void R6AltaPerfUnParticipante(String idParticipante, VOPerformance datosNuevaPerformance)
			throws RemoteException, ParticipantesExceptions, InterruptedException;

	//// INICIO REQUERIMIENTO 7+++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void R7CierreVotacionPublico()
			throws RemoteException, ParticipantesExceptions, InterruptedException, FileNotFoundException, IOException;

	//// INICIO REQUERIMIENTO 9+++++++++++++++++++++++++++++++++++++++++++++++++++++
	public ArrayList<VOParticipanteFinalista> R9ListFinalistasYGanador()
			throws RemoteException, ParticipantesExceptions, InterruptedException;

	//// INICIO REQUERIMIENTO
	//// 10+++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void R10AcreditarVotoAFinalista(String idFinalista)
			throws RemoteException, ParticipantesExceptions, InterruptedException;

	//// INICIO metodos auxiliares +++++++++++++++++++++++++++++++++++++++++++++++++
	// uso interno, (show/hide) de componentes de interfaces(consoltaTest, WIN, WEB)

	//// Obtiene el estado de la votacion del concursoActual que esta correindo
	public boolean getEstadoVotacion() throws RemoteException, InterruptedException;

	//// Obtiene el estado de la flag del concurso para saber si ya hay definido un
	//// ganador
	public boolean getHayGanador() throws RemoteException, InterruptedException;

	//// Obtiene la cantidad de Participante en la coleccion de Participantes
	public int getCantParticipantes() throws RemoteException, InterruptedException;

	//// Recorre la coleccion de Participantes y valida si queda algun participante
	public boolean getQuedaAlgunaPerformance() throws RemoteException, InterruptedException;

	// DEMO DE RMI
	public String TestPersistenceRMI() throws RemoteException;

	//
}
