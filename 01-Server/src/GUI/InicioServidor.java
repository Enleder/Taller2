package GUI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import Exceptions.ParticipantesExceptions;
import Logic.FachadaLogica;
import Logic.SystemProperties;

public class InicioServidor {

	public static void main(String[] args) throws ParticipantesExceptions {

		try {
			SystemProperties sp = new SystemProperties();
			String ip = sp.getIpServidor();
			String puerto = sp.getPuertoServidor();
			String nombreAPublicar = sp.getNombreAPublicar();
			int port = Integer.parseInt(puerto);

			try {
				LocateRegistry.createRegistry(port);
			} catch (Exception e) {
				// e.printStackTrace();
				LocateRegistry.getRegistry(port);
			}

			// publico el objeto remoto en dicha ip y puerto
			String ruta = "//" + ip + ":" + puerto + "/" + nombreAPublicar;
			FachadaLogica fachada = FachadaLogica.getInstancia();

			System.out.println("Antes de publicar");
			Naming.rebind(ruta, fachada);
			System.out.println(
					"Server Corriendo en la IP ::  " + sp.getIpServidor() + " Puerto :: " + sp.getPuertoServidor());
		} catch (RemoteException e) // si ocurre cualquier problema de red
		{
			e.printStackTrace();
		} catch (MalformedURLException e) // si la ruta no esta bien formada
		{
			e.printStackTrace();
		} catch (FileNotFoundException e) // si no encuentra el archivo de configuracion
		{
			e.printStackTrace();
		} catch (IOException e) // si ocurre cualquier otro error de E/S
		{
			e.printStackTrace();
		}
	}

}
