package Logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SystemProperties {

	private String ipServidor;
	private String puertoServidor;
	private String nombreArchivo;
	private String nombreAPublicar;
	private String cantidadPerformances;
	private String nomArtMin;
	private String nomArtMax;
	private String cantidadFinalistas;

	public SystemProperties() throws FileNotFoundException, IOException {

		Properties p = new Properties();
		String nomArchivoProperty = "config/server.properties";
		p.load(new FileInputStream(nomArchivoProperty));

		ipServidor = p.getProperty("ipServidor");
		puertoServidor = p.getProperty("puertoServidor");
		nombreArchivo = p.getProperty("nombreArchivo");
		nombreAPublicar = p.getProperty("nombreAPublicar");
		cantidadPerformances = p.getProperty("cantidadPerformances");
		nomArtMin = p.getProperty("nomArtMin");
		nomArtMax = p.getProperty("nomArtMax");
		cantidadFinalistas = p.getProperty("cantidadFinalistas");

	}

	public String getNomArtMin() {
		return nomArtMin;
	}

	public String getNomArtMax() {
		return nomArtMax;
	}

	public String getIpServidor() {
		return ipServidor;
	}

	public String getPuertoServidor() {
		return puertoServidor;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public String getNombreAPublicar() {
		return nombreAPublicar;
	}

	public String getCantidadPerformances() {
		return cantidadPerformances;
	}

	public String getCantidadFinalistas() {
		return cantidadFinalistas;
	}

}
