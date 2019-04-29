package Servlets;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exceptions.ParticipantesExceptions;
import Logic.IFachadaLogica;
import ValueObjects.VOParticipanteFinalista;

/**
 * Servlet implementation class Finalistas
 */
@WebServlet("/Finalistas")
public class Finalistas extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public IFachadaLogica iFachada;
	public IFachadaLogica iFachadaLogica;
	public boolean estadoVotacion;
	public boolean hayGanador;
	public ArrayList<VOParticipanteFinalista> listaFinalistas;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Finalistas() {
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

			iFachadaLogica = (IFachadaLogica) Naming.lookup(ruta);

			estadoVotacion = iFachadaLogica.getEstadoVotacion();
			hayGanador = iFachadaLogica.getHayGanador();
	
			if (!estadoVotacion && !hayGanador) {
				// etapa = 1;
				// no hay finalistas -> lo redirecciono ahi
				request.getRequestDispatcher("NoHayFinalistas.jsp").forward(request, response);

			} else {

				// SI hay finalistas -> los cargo
				ArrayList<VOParticipanteFinalista> listaFinalistas = new ArrayList<>();
				listaFinalistas = iFachadaLogica.R9ListFinalistasYGanador();
				if (listaFinalistas.size() > 0) {
					System.out.println("TIENE ELEMENTOS: " + listaFinalistas.size());
				}
				request.setAttribute("ListaFinalistas", listaFinalistas);

				if (estadoVotacion && !hayGanador) {
					// etapa = 2;
					// Efectivamente SI hay finalistas, lo mando al panel con
					// botones que llaman al servlet.OtorgarVoto
					request.getRequestDispatcher("/Finalistas.jsp").forward(request, response);
				}
				if (!estadoVotacion && hayGanador) {
					// etapa = 3;
					// Efectivamente SI hay finalistas, lo mando al panel sin botones que muestra
					// resaltado al ganador
					request.getRequestDispatcher("/Ganador.jsp").forward(request, response);
				}
			}
		
		} catch (Exception e) // si la ruta no esta bien formada o el server caido
		{
			System.out.println("ERROR: " + e.getMessage());
			request.getRequestDispatcher("/ErrorServer.jsp").forward(request, response);
		}

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ipServidor = super.getInitParameter("ipServidor");
		String puerto = super.getInitParameter("puerto");
		String nombreAPublicar = super.getInitParameter("nombreAPublicar");
		String ruta = "//" + ipServidor + ":" + puerto + "/" + nombreAPublicar;

		try {
			iFachadaLogica = (IFachadaLogica) Naming.lookup(ruta);
			estadoVotacion = iFachadaLogica.getEstadoVotacion();
			hayGanador = iFachadaLogica.getHayGanador();
			
			if (!estadoVotacion && hayGanador) {

				request.getRequestDispatcher("/Ganador.jsp").forward(request, response);

			} else {

				String respuesta = "";
				listaFinalistas = iFachadaLogica.R9ListFinalistasYGanador();
				response.setContentType("text/html;charset=UTF-8");
				String idVotado = request.getParameter("votado");
				String refrescar = request.getParameter("refrescar");

				if (refrescar != null && refrescar.equals("refrescar")) {
					respuesta = crearJSON();
					refrescar = "";
				} else {
					iFachadaLogica.R10AcreditarVotoAFinalista(idVotado);
					respuesta = crearJSON();
				}
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(respuesta);
			}

		} catch (Exception e) {
			
			System.out.println("ERROR: " + e.getMessage());
			request.getRequestDispatcher("/ErrorServer.jsp").forward(request, response);
		}
	}

	private String crearJSON() {

		String respuesta = "{";
		for (int i = 0; i < listaFinalistas.size(); i++) {

			String finalista = listaFinalistas.get(i).getNombreArtistico();
			String puntaje = String.valueOf(listaFinalistas.get(i).getPuntajeTotalPublico());
			respuesta = respuesta + "\"finalista" + i + "\":\"" + finalista + "\",\"puntaje" + i + "\":\"" + puntaje
					+ "\",";

		}

		respuesta = respuesta.substring(0, respuesta.length() - 1);
		respuesta = respuesta + "}";

		return respuesta;
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
