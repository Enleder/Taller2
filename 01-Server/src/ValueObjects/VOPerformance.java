package ValueObjects;

import java.io.Serializable;

public class VOPerformance implements Serializable {

	private static final long serialVersionUID = 1L;
	private int numero;
	private String descripcion;
	private int puntajeJuez1;
	private int puntajeJuez2;
	private int puntajeJuez3;

	public VOPerformance(int numero, String descripcion, int puntajeJuez1, int puntajeJuez2, int puntajeJuez3) {
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

	@Override
	public String toString() {
		return "VOPerformance [numero=" + numero + ", descripcion=" + descripcion + ", puntajeJuez1=" + puntajeJuez1
				+ ", puntajeJuez2=" + puntajeJuez2 + ", puntajeJuez3=" + puntajeJuez3 + "]";
	}
}
