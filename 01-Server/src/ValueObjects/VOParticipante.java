package ValueObjects;

import java.io.Serializable;

public class VOParticipante implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombreArtistico;
	private int edad;
	private String especialidadArtistica;

	public VOParticipante(String nombreArtistico, int edad, String especialidadArtistica) {
		super();
		this.nombreArtistico = nombreArtistico;
		this.edad = edad;
		this.especialidadArtistica = especialidadArtistica;
	}

	public String getNombreArtistico() {
		return nombreArtistico;
	}

	public int getEdad() {
		return edad;
	}

	public String getEspecialidadArtistica() {
		return especialidadArtistica;
	}

	@Override
	public String toString() {
		return "VOParticipante [nombreArtistico=" + nombreArtistico + ", edad=" + edad + ", especialidadArtistica="
				+ especialidadArtistica + "]";
	}
}
