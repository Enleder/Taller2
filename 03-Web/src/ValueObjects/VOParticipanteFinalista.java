package ValueObjects;

import java.io.Serializable;

public class VOParticipanteFinalista implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombreArtistico;
	private String foto;
	private int edad;
	private int puntajeTotalPublico;
	private boolean esGanador;

	public VOParticipanteFinalista(String nombreArtistico, String foto, int edad, int puntajeTotalPublico,
			boolean esGanador) {
		super();
		this.nombreArtistico = nombreArtistico;
		this.edad = edad;
		this.puntajeTotalPublico = puntajeTotalPublico;
		this.foto = foto;
		this.esGanador = esGanador;
	}

	public String getNombreArtistico() {
		return nombreArtistico;
	}

	public int getEdad() {
		return edad;
	}

	public int getPuntajeTotalPublico() {
		return puntajeTotalPublico;
	}

	public String getFoto() {
		return foto;
	}

	public boolean isEsGanador() {
		return esGanador;
	}

	@Override
	public String toString() {
		return "VOParticipanteFinalista [nombreArtistico=" + nombreArtistico + ", edad=" + edad
				+ ", puntajeTotalPublico=" + puntajeTotalPublico + ", foto=" + foto + ", esGanador=" + esGanador + "]";
	}

}
