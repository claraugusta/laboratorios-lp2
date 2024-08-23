package mrbet;

import java.util.Objects;

public class Campeonato {
	private String nome;
	private Time[] times;
	private int proxTime;
	private int qtdeParticipantes;
	
	public Campeonato(String nome, int qtdeParticipantes){
		this.nome = nome;
		this.times = new Time[qtdeParticipantes];
		this.qtdeParticipantes = qtdeParticipantes;
		proxTime = 0;
	}
	
	public void insereTime(Time time) {
		if(proxTime < times.length) {
			times[proxTime++] = time;
		}
	}
	
	public int contaTimes() {
		int cont = 0;
		for(Time t : times) {
			if(t != null) {
				cont++;
			}
		}
		return cont;
	}
	
	public boolean limiteTimesAtingido() {
		if(contaTimes() == qtdeParticipantes) {
			return true;
		}
		return false;
	}
	
	public boolean temTime(Time timeBuscado) {
		for(Time time : times) {
			if(time != null && time.equals(timeBuscado)) {
				return true;
			}
		}
		return false;
	}

	
	public String getNome() {
		return nome;
	}

	public int getQtdeParticipantes() {
		return qtdeParticipantes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campeonato other = (Campeonato) obj;
		return Objects.equals(nome, other.nome);
	}
	
	public String toString() {
		return String.format("* %s - %d/%d", nome, contaTimes(), qtdeParticipantes);
	}
	
}
