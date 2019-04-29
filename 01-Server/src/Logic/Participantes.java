package Logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.TreeMap;

import Exceptions.ParticipantesExceptions;

public class Participantes implements Serializable {

	private static final long serialVersionUID = 1L;
	private TreeMap<String, Participante> abb;

	public Participantes() {
		abb = new TreeMap<String, Participante>();
	}

	public void Insert(Participante p) throws ParticipantesExceptions, FileNotFoundException, IOException {
		SystemProperties sp = new SystemProperties();

		if (p.getNombreArtistico().length() < Integer.parseInt(sp.getNomArtMin()))
			throw new ParticipantesExceptions(Mensajes.M_PARTICIPANTES_NOM_ART_CORTO);
		else if (p.getNombreArtistico().length() > Integer.parseInt(sp.getNomArtMax()))
			throw new ParticipantesExceptions(Mensajes.M_PARTICIPANTES_NOM_ART_LARGO);
		else if (p.getNombreArtistico().isEmpty())
			throw new ParticipantesExceptions(Mensajes.M_PARTICIPANTES_NOM_ART_VACIO);
		else

			abb.put(p.getNombreArtistico(), p);
		// this.getAbb().put(p.getNombreArtistico(), p);
	}

	public boolean Member(String id) {
		return abb.containsKey(id);
	}

	public void Modify(Participante p) {

		abb.replace(p.getNombreArtistico(), p);

	}

	public void Delete(String id) {

		abb.remove(id);

	}

	public Participante Find(String id) {

		Participante p;
		p = abb.get(id);
		return p;
	}

	public boolean HayAlgunParticipante() {
		// return abb.isEmpty();
		boolean HayAlgunParticipante = false;
		if (abb.isEmpty()) {
			HayAlgunParticipante = false;
		} else {
			HayAlgunParticipante = true;
		}
		return HayAlgunParticipante;
	}

	public TreeMap<String, Participante> getAbb() {
		return abb;
	}

	public void setAbb(TreeMap<String, Participante> abb) {
		this.abb = abb;
	}

}
