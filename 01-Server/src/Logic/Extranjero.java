package Logic;

public class Extranjero extends Participante {

	private static final long serialVersionUID = 1L;
	private String paisOrigen;
	private int cantidadAniosEnUy;

	public Extranjero() {
		super();
	}

	public Extranjero(String nombreArtistico, String nombreYApellido, String foto, int edad,
			String especialidadArtistica, String paisOrigen, int cantidadAniosEnUy) {
		super(nombreArtistico, nombreYApellido, foto, edad, especialidadArtistica);
		this.paisOrigen = paisOrigen;
		this.cantidadAniosEnUy = cantidadAniosEnUy;
	}

	////////////////////////// CUANDO SE LEVANTA DESDE ARCHIVO
	public Extranjero(String nombreArtistico, String nombreYApellido, String foto, int edad,
			String especialidadArtistica, int puntajeTodasLasPerformances, boolean tienePerformancePendiente,
			boolean esFinalista, int puntajeTotalPublico, boolean esGanador, Performances performances,
			String paisOrigen, int cantidadAniosEnUy) {
		super(nombreArtistico, nombreYApellido, foto, edad, especialidadArtistica, puntajeTodasLasPerformances,
				tienePerformancePendiente, esFinalista, puntajeTotalPublico, esGanador, performances);
		this.paisOrigen = paisOrigen;
		this.cantidadAniosEnUy = cantidadAniosEnUy;
	}

	public String getPaisOrigen() {
		return paisOrigen;
	}

	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	public int getCantidadAniosEnUy() {
		return cantidadAniosEnUy;
	}

	public void setCantidadAniosEnUy(int cantidadAniosEnUy) {
		this.cantidadAniosEnUy = cantidadAniosEnUy;
	}

	public TipoParticipante getTipoDeParticipante() {
		return TipoParticipante.EXTRANJERO;
	}

}
