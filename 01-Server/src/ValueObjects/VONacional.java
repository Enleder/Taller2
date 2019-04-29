package ValueObjects;

import java.io.Serializable;

import Logic.TipoParticipante;

public class VONacional extends VOParticipanteDetallado implements Serializable {

	private static final long serialVersionUID = 1L;
	private String departamento;
	private String localidad;

	public VONacional(String nombreArtistico, String nombreYApellido, String foto, int edad,
			String especialidadArtistica, String departamento, String localidad) {

		super(nombreArtistico, nombreYApellido, foto, edad, especialidadArtistica);
		this.departamento = departamento;
		this.localidad = localidad;
	}

	public VONacional(String nombreArtistico, String nombreYApellido, String foto, int edad,
			String especialidadArtistica, int puntajeTodasLasPerformances, boolean tienePerformancePendiente,
			boolean esFinalista, int puntajeTotalPublico, boolean esGanador, String departamento, String localidad) {

		super(nombreArtistico, nombreYApellido, foto, edad, especialidadArtistica, puntajeTodasLasPerformances,
				tienePerformancePendiente, esFinalista, puntajeTotalPublico, esGanador);
		this.departamento = departamento;
		this.localidad = localidad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public String getLocalidad() {
		return localidad;
	}

	@Override
	public String toString() {
		return "VONacional [departamento=" + departamento + ", localidad=" + localidad + "]";
	}

	@Override
	public TipoParticipante getTipoDeParticipante() {
		return TipoParticipante.NACIONAL;
	}
}
