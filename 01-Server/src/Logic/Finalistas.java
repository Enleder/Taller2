package Logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Finalistas {

	private List<Participante> lista;

	public Finalistas() {

		lista = new ArrayList<Participante>();
	}

	public void Insert(Participante p) {
		lista.add(p);
	}

	public void Delete(String id) {
		Iterator<Participante> iter = lista.iterator();
		while (iter.hasNext()) {
			Participante p = iter.next();
			if (p.getNombreArtistico().equals(id))
				lista.remove(p);
		}
	}

	public List<Participante> getLista() {
		return lista;
	}

	public void setLista(List<Participante> lista) {
		this.lista = lista;
	}

	public void SetFinalistas() throws FileNotFoundException, IOException {
		// 1.- En el Singleton la lista esta cargada (controlar antes de ... )
		// 2.- Elijo 3 (random) de la lista y elimino el resto
		// 3.- Verificar Casos borde (ejemplo solo tengo 1 o 2 participnates)
		// 4.- Setear en TRUE el parametro EsFinalista de la clase Participante de cada
		// finalista
		// 5.- Si queda 1 setearlo commo gannador
		SystemProperties sp = new SystemProperties();
		if (lista.size() == Integer.parseInt(sp.getCantidadFinalistas())) { // 2
			// seteo los 3 como finalistas
			Iterator<Participante> iter = lista.iterator();
			while (iter.hasNext()) {
				Participante partF = iter.next();
				partF.setEsFinalista(true);
			}
		} else {
			int cant_participntes = lista.size();
			Random random = new Random();
			@SuppressWarnings("unused")
			int contador = 0;
			// Conjunto de números ya usados
			Set<Integer> numerosUtilizados = new HashSet<>();
			// Generamos 3 números aleatorios sin repetición
			while (numerosUtilizados.size() < Integer.parseInt(sp.getCantidadFinalistas())) {
				// Número aleatorio entre 0 y la cantidad de participantes
				int randomNumber = random.nextInt(cant_participntes);
				// Si no lo hemos usado ya, lo metemos en el conjunto de usados.
				if (!numerosUtilizados.contains(randomNumber)) {
					// System.out.println(
					// "Finalista Nº " + (contador + 1) + " : " +
					// lista.get(randomNumber).getNombreArtistico());
					Participante pf = lista.get(randomNumber);
					pf.setEsFinalista(true);
					numerosUtilizados.add(randomNumber);
					contador++;
				}
			}
		}
	}

	public void SetGanador() {

		// recorre toda la lista de 3 finalistas,
		// ve cual tiene mas votos del publico
		// si hay un solo con mas votos, le setea su att esGanador en true
		// sino, si 2 o mas tienen la misma cantidad de votos del publico, corro un
		// random y seteo solo uno como ganador

		Iterator<Participante> iter = lista.iterator();
		int mayor = 0;
		String id = "";
		while (iter.hasNext()) {
			Participante p = iter.next();
			if (p.getPuntajeTotalPublico() > mayor) {
				mayor = p.getPuntajeTotalPublico();
				id = p.getNombreArtistico();
			}
		}

		Iterator<Participante> iter2 = lista.iterator();
		while (iter2.hasNext()) {
			Participante p = iter2.next();
			if (p.getNombreArtistico().equals(id)) {
				p.setEsGanador(true);
			}
		}

	}
	
	
	public String SetGanadorString() {

		// recorre toda la lista de 3 finalistas,
		// ve cual tiene mas votos del publico
		// si hay un solo con mas votos, le setea su att esGanador en true
		// sino, si 2 o mas tienen la misma cantidad de votos del publico, corro un
		// random y seteo solo uno como ganador

		Iterator<Participante> iter = lista.iterator();
		int mayor = 0;
		String id = "";
		while (iter.hasNext()) {
			Participante p = iter.next();
			if (p.getPuntajeTotalPublico() >= mayor) {
				mayor = p.getPuntajeTotalPublico();
				id = p.getNombreArtistico();
			}
		}

		return id;

	}

	public void AumentarVotacion(String idFinalistaParaAcreditarVoto) {

		Iterator<Participante> iter = lista.iterator();

		while (iter.hasNext()) {
			Participante p = iter.next();
			if (p.getNombreArtistico().equals(idFinalistaParaAcreditarVoto)) {
				p.setPuntajeTotalPublico((p.getPuntajeTotalPublico() + 1));
			}
		}

	}
}
