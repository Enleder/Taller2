package OldTestView;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import View.FotografiaTools.ImagenConvertidor;
import View.FotografiaTools.MiCanvas;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class VentanaFoto {

	private JFrame frmVentanaFoto;
	private MiCanvas canvas;
	// lienzo sobre el cual vamos a cargar una imagen desde disco

	public VentanaFoto() {
		initialize();
	}

	private void initialize() {
		frmVentanaFoto = new JFrame();
		frmVentanaFoto.setTitle("Ventana Foto");
		frmVentanaFoto.setResizable(false);
		frmVentanaFoto.setBounds(100, 100, 432, 499);
		frmVentanaFoto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVentanaFoto.getContentPane().setLayout(null);

		canvas = new MiCanvas();
		canvas.setBounds(10, 10, 410, 410);
		frmVentanaFoto.getContentPane().add(canvas);

		Button button = new Button("CARGAR FOTO");
		button.setBounds(138, 428, 152, 22);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// cuando el usuario aprieta el botón, le doy a elegir la foto a cargar
				try {
					// hago que la ventana que se abre para elegir la imagen desde disco luzca
					// como usualmente lucen las ventanas en windows
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

					// JFileChooser permite navegar en las carpetas del SO para buscar una imagen
					JFileChooser fc = new JFileChooser();
					fc.showOpenDialog(frmVentanaFoto);
					File file = fc.getSelectedFile();

					// Si el usuario no selecciono ninguna imagen, no hago nada
					if (file != null) {
						String nombreArchivo = file.getName();
						int index = nombreArchivo.indexOf(".");
						String extension = nombreArchivo.substring(index + 1, nombreArchivo.length());

						// Si el archivo seleccionado no es una imagen gif o jpg, no hago nada
						if (extension.equalsIgnoreCase("gif") || extension.equalsIgnoreCase("jpg")) {
							byte[] imagen = ImagenConvertidor.convertirImagen(file.getAbsolutePath());
							canvas.setImagen(imagen);
							canvas.repaint();
						}
					}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
			}
		});
		frmVentanaFoto.getContentPane().add(button);
	}

	public void visualizar() {
		frmVentanaFoto.setVisible(true);
	}
}
