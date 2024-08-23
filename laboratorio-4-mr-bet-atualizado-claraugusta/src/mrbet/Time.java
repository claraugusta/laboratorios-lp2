package mrbet;

import java.util.ArrayList;
import java.util.Objects;

public class Time {
	private String id;
	private String nome;
	private String mascote;
	private ArrayList<String> campeonatos;
	
	public Time(String id, String nome, String mascote) {
		this.id = id;
		this.nome = nome;
		this.mascote = mascote;
		this.campeonatos = new ArrayList<String>();
	}
	
	public void insereCampeonato(String campeonato) {
		campeonatos.add(campeonato);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Time other = (Time) obj;
		return Objects.equals(id, other.id);
	}
	
	public ArrayList<String> getCampeonatos() {
		return campeonatos;
	}
	
	public String getCampeonato(int indice) {
		return campeonatos.get(indice);
	}

	public String toString(){
		return String.format("[%s] %s / %s", id, nome, mascote);
	}
	
	
}
