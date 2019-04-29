package ValueObjects;

import java.io.Serializable;

import Logic.TipoParticipante;

public abstract class VOParticipanteDetallado implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombreArtistico;
	private String nombreYApellido;
	private int edad;
	private String especialidadArtistica;
	private String foto;
	private int puntajeTodasLasPerformances;
	private boolean tienePerformancePendiente;
	private boolean esFinalista;
	private int puntajeTotalPublico;
	private boolean esGanador;

	public VOParticipanteDetallado(String nombreArtistico, String nombreYApellido, String foto, int edad,
			String especialidadArtistica) {
		this.nombreArtistico = nombreArtistico;
		this.nombreYApellido = nombreYApellido;
		this.edad = edad;
		this.especialidadArtistica = especialidadArtistica;
		this.foto = foto;
		this.puntajeTodasLasPerformances = 0;
		this.tienePerformancePendiente = true;
		this.esFinalista = false;
		this.puntajeTotalPublico = 0;
		this.esGanador = false;
	}

	public VOParticipanteDetallado(String nombreArtistico, String nombreYApellido, String foto, int edad,
			String especialidadArtistica, int puntajeTodasLasPerformances, boolean tienePerformancePendiente,
			boolean esFinalista, int puntajeTotalPublico, boolean esGanador) {
		this.nombreArtistico = nombreArtistico;
		this.nombreYApellido = nombreYApellido;
		this.edad = edad;
		this.especialidadArtistica = especialidadArtistica;
		this.foto = foto;
		this.puntajeTodasLasPerformances = puntajeTodasLasPerformances;
		this.tienePerformancePendiente = tienePerformancePendiente;
		this.esFinalista = esFinalista;
		this.puntajeTotalPublico = puntajeTotalPublico;
		this.esGanador = esGanador;
	}

	public String getNombreArtistico() {
		return nombreArtistico;
	}

	public String getEspecialidadArtistica() {
		return especialidadArtistica;
	}

	public int getEdad() {
		return edad;
	}

	public String getNombreYApellido() {
		return nombreYApellido;
	}

	public String getFoto() {
		return foto;
	}

	public int getPuntajeTodasLasPerformances() {
		return puntajeTodasLasPerformances;
	}

	public boolean isTienePerformancePendiente() {
		return tienePerformancePendiente;
	}

	public boolean isEsFinalista() {
		return esFinalista;
	}

	public int getPuntajeTotalPublico() {
		return puntajeTotalPublico;
	}

	public boolean isEsGanador() {
		return esGanador;
	}

	public abstract TipoParticipante getTipoDeParticipante();

	@Override
	public String toString() {
		return "VOParticipanteDetallado [nombreYApellido=" + nombreYApellido + ", foto=" + foto
				+ ", puntajeTodasLasPerformances=" + puntajeTodasLasPerformances + ", tienePerformancePendiente="
				+ tienePerformancePendiente + ", esFinalista=" + esFinalista + ", puntajeTotalPublico="
				+ puntajeTotalPublico + ", esGanador=" + esGanador + "]";
	}
}
