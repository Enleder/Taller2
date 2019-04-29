package Testing;

import java.io.*;
import java.util.Vector;
import Logic.Mensajes;
import Logic.SystemProperties;
import Exceptions.PersistenciaException;

public class Persistencia {

	private SystemProperties sp;
	@SuppressWarnings("unused")
	private Mensajes msg;

	public Persistencia() throws FileNotFoundException, IOException {
		sp = new SystemProperties();
		msg = new Mensajes();

	}

	public void respaldar(Vector<PersistenceData> arre) throws PersistenciaException {
		try {

			// Abro el archivo y creo un flujo de comunicación hacia él ::: 25253354
			FileOutputStream f = new FileOutputStream(sp.getNombreArchivo());
			ObjectOutputStream o = new ObjectOutputStream(f);
			// Escribo el arreglo de vehículos en el archivo a través del flujo

			o.writeObject(arre);
			o.close();
			f.close();
		} catch (IOException e) {
			throw new PersistenciaException(Mensajes.M_PERSISTENCIA_RESPALDAR + " - Error detalle : " + e.getMessage());
		}
	}

	public Vector<PersistenceData> recuperar() throws PersistenciaException, ClassNotFoundException {

		try {
			// Abro el archivo y creo un flujo de comunicación hacia él
			FileInputStream f = new FileInputStream(sp.getNombreArchivo());
			ObjectInputStream o = new ObjectInputStream(f);
			// Leo el arreglo de vehículos desde el archivo a través del flujo
			@SuppressWarnings("unchecked")
			Vector<PersistenceData> arre = (Vector<PersistenceData>) o.readObject();
			o.close();
			f.close();
			return arre;

		} catch (IOException e) {
			throw new PersistenciaException(Mensajes.M_PERSISTENCIA_RECUPERAR);
		}
	}
}
