package View.FotografiaTools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ImagenConvertidor {
	// metodo que convierte una imagen en un arreglo de bytes a partir de la ruta a
	// la imagen
	public static byte[] convertirImagen(String path) {
		try {
			// cargo la imagen desde un archivo a partir de su ruta
			FileInputStream is = new FileInputStream(path);

			// voy leyendo la imagen byte a byte
			int b = -1;
			ArrayList<Byte> bytes = new ArrayList<Byte>();
			b = is.read();
			while (b != -1) {
				bytes.add((byte) b);
				b = is.read();
			}
			is.close();

			// pongo los bytes leidos en un arreglo
			byte[] resu = new byte[bytes.size()];
			for (int i = 0; i < resu.length; i++) {
				resu[i] = bytes.get(i);
			}

			// retorno la imagen en forma de arreglo de bytes
			return resu;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new byte[0];
		} catch (IOException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}
}
