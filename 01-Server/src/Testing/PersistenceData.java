package Testing;

import java.io.Serializable;
import Logic.Concurso;
import Logic.Participante;

public class PersistenceData implements Serializable {

	private static final long serialVersionUID = 1L;

	private Concurso concurso;
	private Participante participante;

	public PersistenceData() {
	}

	public PersistenceData(Concurso c, Participante p) {

		concurso = c;
		participante = p;

	}

	public Concurso getConcurso() {
		return concurso;
	}

	public void setConcurso(Concurso concurso) {
		this.concurso = concurso;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
