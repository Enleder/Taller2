package Logic;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Concurso implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean estadoVotacionActivo;
	// private Date fechaCierreVotacion;
	private String fechaCierreVotacionTexto;
	private boolean hayGanador;

	public Concurso() {
		estadoVotacionActivo = false;

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		fechaCierreVotacionTexto = (String) (dtf.format(now));

		hayGanador = false;
	}

	////////////////////////// CUANDO SE LEVANTA DESDE ARCHIVO
	public Concurso(boolean EstadoVotacionActivo, String fechaCierreVotacionTexto, boolean hayGanador) {
		this.estadoVotacionActivo = EstadoVotacionActivo;
		this.fechaCierreVotacionTexto = fechaCierreVotacionTexto;
		this.hayGanador = hayGanador;
	}

	public boolean isEstadoVotacionActivo() {
		return estadoVotacionActivo;
	}

	public String getFechaCierreVotacion() {
		return fechaCierreVotacionTexto;
	}

	public boolean isHayGanador() {
		return hayGanador;
	}

	public void setEstadoVotacionActivo(boolean estadoVotacionActivo) {
		this.estadoVotacionActivo = estadoVotacionActivo;
	}

	public void setFechaCierreVotacion(String fechaCierreVotacionTexto) {
		this.fechaCierreVotacionTexto = fechaCierreVotacionTexto;
	}

	public void setHayGanador(boolean hayGanador) {
		this.hayGanador = hayGanador;
	}

}
