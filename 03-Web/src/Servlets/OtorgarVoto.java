package Servlets;

import java.io.IOException;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import Controllers.ControllerListarFinalistas;
import Exceptions.ParticipantesExceptions;
import Logic.IFachadaLogica;
import Logic.Mensajes;
import Logic.SystemProperties;
import ValueObjects.VOParticipanteFinalista;;

/**
 * Servlet implementation class OtorgarVoto
 */
@WebServlet("/OtorgarVoto")
public class OtorgarVoto extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public IFachadaLogica iFachada;
	public boolean estadoVotacion;
	public boolean hayGanador;
	public ArrayList<VOParticipanteFinalista> listaFinalistas;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OtorgarVoto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//// Parametros van locales a un serverlet en el web.xml
			String ipServidor = super.getInitParameter("ipServidor");
			String puerto = super.getInitParameter("puerto");
			String nombreAPublicar = super.getInitParameter("nombreAPublicar");
			String ruta = "//" + ipServidor + ":" + puerto + "/" + nombreAPublicar;
			// System.out.println("RUTA lookup: "+ ruta);
			IFachadaLogica iFachadaLogica = (IFachadaLogica) Naming.lookup(ruta);

			
			String idFinalista=request.getParameter("id0");
			
			System.out.println("doGet_ENTRO AL OTORGAR VOTO CON idFinalista: "+idFinalista);
			//iFachadaLogica.R10AcreditarVotoAFinalista(idFinalista);
			
			request.getRequestDispatcher("Finalistas.java").forward(request, response);
			
//			///VUELVO A SIMULAR FINALISTAS.java
//			
//			estadoVotacion = iFachadaLogica.getEstadoVotacion();
//			hayGanador = iFachadaLogica.getHayGanador();
//			// HAY 3 ETAPAS
//			// 1)Etapa de perfromances: estado de votacion = false; no hay finalistas; y hay
//			// ganador = false
//			// 2)Etapa de votacion: estado de votacion = true; SI hay finalistas; y hay
//			// ganador = false
//			// 3)Estapa de ganador: estado de votacion = false; SI hay finalistas; y hay
//			// ganador = true
//
//			if (!estadoVotacion && !hayGanador) {
//				// etapa = 1;
//				// no hay finalistas -> lo redirecciono ahi
//				request.getRequestDispatcher("NoHayFinalistas.jsp").forward(request, response);
//			} else {
//				// SI hay finalistas -> los cargo
//				ArrayList<VOParticipanteFinalista> listaFinalistas = new ArrayList<>();
//				listaFinalistas = iFachadaLogica.R9ListFinalistasYGanador();
//				if(listaFinalistas.size() > 0) {
//					System.out.println("TIENE ELEMENTOS: "+listaFinalistas.size());
//				}
//				request.setAttribute("ListaFinalistas", listaFinalistas);
//								
//				String nombre1 = listaFinalistas.get(0).getNombreArtistico();
//				String foto1 = listaFinalistas.get(0).getFoto();
//				int edad1 = listaFinalistas.get(0).getEdad();
//				int puntajePublico1 = listaFinalistas.get(0).getPuntajeTotalPublico();
//				boolean esGanador1 =listaFinalistas.get(0).isEsGanador();
//				if (listaFinalistas.get(0).isEsGanador()) {
//					request.setAttribute("ganador", 1);
//				}
//				request.setAttribute("nombre1", nombre1);
//				request.setAttribute("foto1", foto1);
//				request.setAttribute("edad1", edad1);
//				request.setAttribute("puntajePublico1", puntajePublico1);
//				request.setAttribute("esGanador1", esGanador1);
//		
//				
//				String nombre2 = listaFinalistas.get(1).getNombreArtistico();
//				String foto2 = listaFinalistas.get(1).getFoto();
//				int edad2 = listaFinalistas.get(1).getEdad();
//				int puntajePublico2 = listaFinalistas.get(1).getPuntajeTotalPublico();
//				boolean esGanador2 =listaFinalistas.get(1).isEsGanador();
//				if (listaFinalistas.get(1).isEsGanador()) {
//					request.setAttribute("ganador", 2);
//				}
//				request.setAttribute("nombre2", nombre2);
//				request.setAttribute("foto2", foto2);
//				request.setAttribute("edad2", edad2);
//				request.setAttribute("puntajePublico2", puntajePublico2);
//				request.setAttribute("esGanador2", esGanador2);
//				
//				
//				String nombre3 = listaFinalistas.get(2).getNombreArtistico();
//				String foto3 = listaFinalistas.get(2).getFoto();
//				int edad3 = listaFinalistas.get(2).getEdad();
//				int puntajePublico3 = listaFinalistas.get(2).getPuntajeTotalPublico();
//				boolean esGanador3 =listaFinalistas.get(2).isEsGanador();
//				if (listaFinalistas.get(2).isEsGanador()) {
//					request.setAttribute("ganador", 3);
//				}
//				request.setAttribute("nombre3", nombre3);
//				request.setAttribute("foto3", foto3);
//				request.setAttribute("edad3", edad3);
//				request.setAttribute("puntajePublico3", puntajePublico3);
//				request.setAttribute("esGanador3", esGanador3);
//				
//				
//				
//				
//				if (estadoVotacion && !hayGanador) {
//					// etapa = 2;
//					//Efectivamente SI hay finalistas, lo mando al panel con botones que llaman al servlet.OtorgarVoto
//					request.getRequestDispatcher("Finalistas.jsp").forward(request, response);
//				}
//				if (!estadoVotacion && hayGanador) {
//					// etapa = 3;
//					//Efectivamente SI hay finalistas, lo mando al panel sin botones que muestra resaltado al ganador
//					request.getRequestDispatcher("Ganador.jsp").forward(request, response);
//				}
//			}
//			//otherwise: si me falto otra logica que lo mande a la pantalla de mantenimiento y listo
//			request.getRequestDispatcher("ErrorServer.jsp").forward(request, response);
		} catch (Exception e) // si la ruta no esta bien formada o el server caido
		{
			request.getRequestDispatcher("ErrorServer.jsp").forward(request, response);
		}

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String idFinalista=request.getParameter("id0");
		
		System.out.println("doPost_ENTRO AL OTORGAR VOTO CON idFinalista: "+idFinalista);
		
		
		// obtengo los datos del Request
		// String mensaje = request.getParameter("mensaje");
		//
		// // obtengo el nombre del autor de la sesi�n
		// HttpSession session = request.getSession();
		//
		// synchronized (session) {
		// listaFinalistas = (List<VOParticipanteFinalista>)
		// session.getAttribute("datosFinalistas");
		// }
		//
		// // guardo el mensaje en el contexto
		// ServletContext context = super.getServletContext();
		// synchronized (context) {
		// // String autor = datosPer.getNombre();
		// // DataMensaje datosMens = new DataMensaje(autor,mensaje);
		// // @SuppressWarnings("unchecked")
		// //
		// // listaFinalistas = (List<VOParticipanteFinalista>)
		// // context.getAttribute("datosFinalistas");
		// //
		// // ArrayList<DataMensaje> mensajes = (ArrayList <DataMensaje>)
		// // context.getAttribute("mensajes");
		// //
		// // if (mensajes == null)
		// // mensajes = new ArrayList<DataMensaje>();
		// // mensajes.add(datosMens);
		// // context.setAttribute("mensajes",mensajes);
		// }
		//
		// // forwardeo a la p�gina de resultados
		// RequestDispatcher rd;
		// rd = request.getRequestDispatcher("index.jsp");
		// rd.forward(request, response);

	}

	public List<VOParticipanteFinalista> getListaFinalistas() {
		return listaFinalistas;
	}

	public void setListaFinalistas(ArrayList<VOParticipanteFinalista> listaFinalistas) {
		this.listaFinalistas = listaFinalistas;
	}

	public IFachadaLogica getiFachada() {
		return iFachada;
	}

	public void setiFachada(IFachadaLogica iFachada) {
		this.iFachada = iFachada;
	}

}
