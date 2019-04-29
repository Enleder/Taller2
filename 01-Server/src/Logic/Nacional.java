package Logic;

import java.io.Serializable;

public class Nacional extends Participante implements Serializable {

	private static final long serialVersionUID = 1L;
	private String departamento;
	private String localidad;

	public Nacional() {
		super();
	}

	public Nacional(String nombreArtistico, String nombreYApellido, String foto, int edad, String especialidadArtistica,
			String departamento, String localidad) {
		super(nombreArtistico, nombreYApellido, foto, edad, especialidadArtistica);
		this.departamento = departamento;
		this.localidad = localidad;
	}

	////////////////////////// CUANDO SE LEVANTA DESDE ARCHIVO
	public Nacional(String nombreArtistico, String nombreYApellido, String foto, int edad, String especialidadArtistica,
			int puntajeTodasLasPerformances, boolean tienePerformancePendiente, boolean esFinalista,
			int puntajeTotalPublico, boolean esGanador, Performances performances, String departamento,
			String localidad) {
		super(nombreArtistico, nombreYApellido, foto, edad, especialidadArtistica, puntajeTodasLasPerformances,
				tienePerformancePendiente, esFinalista, puntajeTotalPublico, esGanador, performances);
		this.departamento = departamento;
		this.localidad = localidad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public TipoParticipante getTipoDeParticipante() {
		return TipoParticipante.NACIONAL;
	}

}
