package ValueObjects;

import java.io.Serializable;

import Logic.TipoParticipante;

public class VOExtranjero extends VOParticipanteDetallado implements Serializable {

	private static final long serialVersionUID = 1L;
	private String paisOrigen;
	private int cantidadAniosEnUy;

	public VOExtranjero(String nombreArtistico, String nombreYApellido, String foto, int edad,
			String especialidadArtistica, String paisOrigen, int cantidadAniosEnUy) {

		super(nombreArtistico, nombreYApellido, foto, edad, especialidadArtistica);
		this.paisOrigen = paisOrigen;
		this.cantidadAniosEnUy = cantidadAniosEnUy;
	}

	public VOExtranjero(String nombreArtistico, String nombreYApellido, String foto, int edad,
			String especialidadArtistica, int puntajeTodasLasPerformances, boolean tienePerformancePendiente,
			boolean esFinalista, int puntajeTotalPublico, boolean esGanador, String paisOrigen, int cantidadAniosEnUy) {

		super(nombreArtistico, nombreYApellido, foto, edad, especialidadArtistica, puntajeTodasLasPerformances,
				tienePerformancePendiente, esFinalista, puntajeTotalPublico, esGanador);
		this.paisOrigen = paisOrigen;
		this.cantidadAniosEnUy = cantidadAniosEnUy;
	}

	public String getPaisOrigen() {
		return paisOrigen;
	}

	public int getCantidadAniosEnUy() {
		return cantidadAniosEnUy;
	}

	@Override
	public String toString() {
		return "VOExtranjero [paisOrigen=" + paisOrigen + ", cantidadAniosEnUy=" + cantidadAniosEnUy + "]";
	}

	@Override
	public TipoParticipante getTipoDeParticipante() {
		return TipoParticipante.EXTRANJERO;
	}
}
