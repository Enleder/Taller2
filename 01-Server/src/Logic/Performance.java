package Logic;

import java.io.Serializable;

public class Performance implements Serializable {

	private static final long serialVersionUID = 1L;
	private int numero;
	private String descripcion;
	private int puntajeJuez1;
	private int puntajeJuez2;
	private int puntajeJuez3;

	public Performance() {
	}

	public Performance(int numero, String descripcion, int puntajeJuez1, int puntajeJuez2, int puntajeJuez3) {
		this.numero = numero;
		this.descripcion = descripcion;
		this.puntajeJuez1 = puntajeJuez1;
		this.puntajeJuez2 = puntajeJuez2;
		this.puntajeJuez3 = puntajeJuez3;
	}

	public int getNumero() {
		return numero;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getPuntajeJuez1() {
		return puntajeJuez1;
	}

	public int getPuntajeJuez2() {
		return puntajeJuez2;
	}

	public int getPuntajeJuez3() {
		return puntajeJuez3;
	}
}
