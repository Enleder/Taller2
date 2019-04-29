package View.FotografiaTools;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

// Clase que extiende una clase predefinida llamada Canvas
// Sirve como lienzo sobre el cual se carga una imagen dentro de una ventana
public class MiCanvas extends Canvas {
	private static final long serialVersionUID = 1L;
	private byte[] imagen;

	public MiCanvas() {
		// constructor por defecto
		imagen = new byte[0];
	}

	public void setImagen(byte[] imagen) {
		// guardo la imagen que me pasan en formato arreglo de bytes
		this.imagen = imagen;
	}

	public void paint(Graphics g) {
		super.paint(g);

		// creo la imagen a partir del arreglo de bytes
		Image img1 = Toolkit.getDefaultToolkit().createImage(imagen);

		// escalo la imagen, llevándola al tamaño deseado usando un algortimo de
		// escalado
		Image img2 = img1.getScaledInstance(146, 190, Image.SCALE_SMOOTH);
		// Image img2 = img1.getScaledInstance(400, 400,Image.SCALE_SMOOTH);
		if (img2 != null) {
			// pongo al MediaTracker a esperar que cargue la imagen
			// el MediaTracker se asegura de que la imagen este cargada a memoria antes de
			// mostrarla
			MediaTracker md = new MediaTracker(this);
			md.addImage(img2, 1);
			try {
				md.waitForAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// una vez cargada, dibujo la imagen sobre el canvas
			g.drawImage(img2, 0, 0, this);
		}
	}

	public void paint(Graphics g, int ancho, int alto) {
		super.paint(g);

		// creo la imagen a partir del arreglo de bytes
		Image img1 = Toolkit.getDefaultToolkit().createImage(imagen);

		// escalo la imagen, llevándola al tamaño deseado usando un algortimo de
		// escalado
		Image img2 = img1.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		// Image img2 = img1.getScaledInstance(146, 190,Image.SCALE_SMOOTH);
		// Image img2 = img1.getScaledInstance(400, 400,Image.SCALE_SMOOTH);
		if (img2 != null) {
			// pongo al MediaTracker a esperar que cargue la imagen
			// el MediaTracker se asegura de que la imagen este cargada a memoria antes de
			// mostrarla
			MediaTracker md = new MediaTracker(this);
			md.addImage(img2, 1);
			try {
				md.waitForAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// una vez cargada, dibujo la imagen sobre el canvas
			g.drawImage(img2, 0, 0, this);
		}
	}
}
