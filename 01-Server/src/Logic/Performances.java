package Logic;

import java.io.Serializable;
import java.util.ArrayList;

public class Performances implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Performance> arreTope = new ArrayList<Performance>();

	public Performances() {
		arreTope = new ArrayList<Performance>();
		// creo el arreglo con 15 espacios vacio: algo como... arreTope[15] new ... etc
	}

	public boolean esVacia() {

		return arreTope.isEmpty();
	}

	public Performance primero() {

		return arreTope.get(0);
	}

	public void pesto() {

	}

	public int largo() {

		return arreTope.size();
	}

	public Performance kEsimo(int i) {

		return arreTope.get(i);
	}

	public void AltaPerformace(Performance performance) {
		arreTope.add(performance);

	}

}
