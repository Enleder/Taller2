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
 * Servlet implementation class TestingRichard
 */
@WebServlet("/TestingRichard")
public class TestingRichard extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public IFachadaLogica iFachada;
//	private List<VOParticipanteFinalista> listaFinalistas;
	public ArrayList<VOParticipanteFinalista> listaFinalistas;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestingRichard() {
		super();
		// TODO Auto-generated constructor stub
		
	}
	
	
	public void init() {

//		ControllerListarFinalistas cP = new ControllerListarFinalistas();
//		try {
//			listaFinalistas = cP.ListarTodosLosFinalistas();
//		} catch (ParticipantesExceptions e) {
//
//			e.printStackTrace();
//		}
//		
	}
	
	public ArrayList<VOParticipanteFinalista> ListarTodosLosFinalistas() throws ParticipantesExceptions {
		
		ArrayList<VOParticipanteFinalista> listaFinalistas = new ArrayList<>();
		try {
			listaFinalistas = iFachada.R9ListFinalistasYGanador();
		} catch (Exception e) {
			throw new ParticipantesExceptions(Mensajes.M_ErrorAlIntentarConectarServer);
		}
		return listaFinalistas;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
		try {
			
			//SystemProperties sp = new SystemProperties();
			String ip = "localhost";
			String puerto = "1099";
			String nombreAPublicar = "concursotalentouruguayo";
			String ruta = "//" + ip + ":" + puerto + "/" + nombreAPublicar;

			
			IFachadaLogica iff = (IFachadaLogica) Naming.lookup(ruta);
			// accedo remotamente a la cuenta bancaria publicada en el servidor
		//	setiFachada((IFachadaLogica) Naming.lookup(ruta));
			
			listaFinalistas = iff.R9ListFinalistasYGanador();

		} catch (Exception e) // si la ruta no esta bien formada
		{
			JOptionPane.showMessageDialog(null, Mensajes.M_ErrorAlIntentarConectarServer, "ERROR",
				 JOptionPane.ERROR_MESSAGE);
		}
		
		VOParticipanteFinalista p1 = new VOParticipanteFinalista("MABEL", "", 1, 1, true);

		ArrayList<VOParticipanteFinalista> listaFinalistasTest = new ArrayList<VOParticipanteFinalista>();
		listaFinalistasTest.add(p1);
		int nombreF2 = listaFinalistasTest.size();
		//String test = listaFinalistasTest.get(0).getNombreArtistico();
		//String test = listaFinalistasTest.get(0).getNombreArtistico();
		
		if(listaFinalistas.size() > 0) {
			System.out.println("TIENE ELEMENTOSSSSSSSSSSs");

		}
		String test = listaFinalistas.get(0).getNombreArtistico();
		System.out.println(test);

		String nombreF1 = "RICHARD";
		request.setAttribute("test", test);
		request.setAttribute("nombreF1", nombreF1);
		request.setAttribute("nombreF2", nombreF2);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		
		// obtengo los datos del Request
//		String mensaje = request.getParameter("mensaje");
//
//		// obtengo el nombre del autor de la sesi�n
//		HttpSession session = request.getSession();
//
//		synchronized (session) {
//			listaFinalistas = (List<VOParticipanteFinalista>) session.getAttribute("datosFinalistas");
//		}
//
//		// guardo el mensaje en el contexto
//		ServletContext context = super.getServletContext();
//		synchronized (context) {
//			// String autor = datosPer.getNombre();
//			// DataMensaje datosMens = new DataMensaje(autor,mensaje);
//			// @SuppressWarnings("unchecked")
//			//
//			// listaFinalistas = (List<VOParticipanteFinalista>)
//			// context.getAttribute("datosFinalistas");
//			//
//			// ArrayList<DataMensaje> mensajes = (ArrayList <DataMensaje>)
//			// context.getAttribute("mensajes");
//			//
//			// if (mensajes == null)
//			// mensajes = new ArrayList<DataMensaje>();
//			// mensajes.add(datosMens);
//			// context.setAttribute("mensajes",mensajes);
//		}
//
//		// forwardeo a la p�gina de resultados
//		RequestDispatcher rd;
//		rd = request.getRequestDispatcher("index.jsp");
//		rd.forward(request, response);

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
