package Logic;

public class Monitor {

	private static int cantLectores; // especifica numero de lectores leyendo
	private static boolean escribiendo; // especifica si alguien esta wscribiendo

	public Monitor() {
		cantLectores = 0;
		escribiendo = false;
	}

	public synchronized void comienzoLectura() throws InterruptedException {
		while (escribiendo == true) {
			wait(); // wait(10000);
		}
		cantLectores++;
	}

	public synchronized void terminoLectura() {
		cantLectores--;
		notifyAll();
	}

	public synchronized void comienzoEscritura() throws InterruptedException {
		while ((cantLectores > 0) || (escribiendo == true)) {
			wait();
		}
		escribiendo = true;
	}

	public synchronized void terminoEscritura() throws InterruptedException {

		escribiendo = false;
		notifyAll();
	}

}
