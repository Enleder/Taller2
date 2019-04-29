package Logic;

import java.io.Serializable;

public abstract class Participante implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombreArtistico;
	private String nombreYApellido;
	private String foto;
	private int edad;
	private String especialidadArtistica;
	private int puntajeTodasLasPerformances;
	private boolean tienePerformancePendiente;
	private boolean esFinalista;
	private int puntajeTotalPublico;
	private boolean esGanador;
	private Performances performances;

	public Participante() {
	}

	public Participante(String nombreArtistico, String nombreYApellido, String foto, int edad,
			String especialidadArtistica) {
		this.nombreArtistico = nombreArtistico;
		this.nombreYApellido = nombreYApellido;
		this.foto = foto;
		this.edad = edad;
		this.especialidadArtistica = especialidadArtistica;
		this.puntajeTodasLasPerformances = 0;
		this.tienePerformancePendiente = true;
		this.esFinalista = false;
		this.puntajeTotalPublico = 0;
		this.esGanador = false;
		this.performances = new Performances();
	}

	////////////////////////// CUANDO SE LEVANTA DESDE ARCHIVO
	public Participante(String nombreArtistico, String nombreYApellido, String foto, int edad,
			String especialidadArtistica, int puntajeTodasLasPerformances, boolean tienePerformancePendiente,
			boolean esFinalista, int puntajeTotalPublico, boolean esGanador, Performances performances) {

		this.nombreArtistico = nombreArtistico;
		this.nombreYApellido = nombreYApellido;
		this.foto = foto;
		this.edad = edad;
		this.especialidadArtistica = especialidadArtistica;
		this.puntajeTodasLasPerformances = puntajeTodasLasPerformances;
		this.tienePerformancePendiente = tienePerformancePendiente;
		this.esFinalista = esFinalista;
		this.puntajeTotalPublico = puntajeTotalPublico;
		this.esGanador = esGanador;

		this.performances = performances;

	}

	public String getNombreArtistico() {
		return nombreArtistico;
	}

	public String getNombreYApellido() {
		return nombreYApellido;
	}

	public String getFoto() {
		return foto;
	}

	public int getEdad() {
		return edad;
	}

	public String getEspecialidadArtistica() {
		return especialidadArtistica;
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

	public Performances getPerformances() {
		return performances;
	}

	public abstract TipoParticipante getTipoDeParticipante();

	public void setNombreArtistico(String nombreArtistico) {
		this.nombreArtistico = nombreArtistico;
	}

	public void setNombreYApellido(String nombreYApellido) {
		this.nombreYApellido = nombreYApellido;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public void setEspecialidadArtistica(String especialidadArtistica) {
		this.especialidadArtistica = especialidadArtistica;
	}

	public void setPuntajeTodasLasPerformances(int puntajeTodasLasPerformances) {
		this.puntajeTodasLasPerformances = puntajeTodasLasPerformances;
	}

	public void setTienePerformancePendiente(boolean tienePerformancePendiente) {
		this.tienePerformancePendiente = tienePerformancePendiente;
	}

	public void setEsFinalista(boolean esFinalista) {
		this.esFinalista = esFinalista;
	}

	public void setPuntajeTotalPublico(int puntajeTotalPublico) {
		this.puntajeTotalPublico = puntajeTotalPublico;
	}

	public void setEsGanador(boolean esGanador) {
		this.esGanador = esGanador;
	}

	public void setPerformances(Performances performances) {
		this.performances = performances;
	}

}
