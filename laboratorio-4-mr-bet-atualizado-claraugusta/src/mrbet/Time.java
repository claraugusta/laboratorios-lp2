package mrbet;

import java.util.ArrayList;
import java.util.Objects;

public class Time {
	private String id;
	private String nome;
	private String mascote;
	private ArrayList<String> campeonatos;
	private ArrayList<Aposta> apostas;
	
	public Time(String id, String nome, String mascote) {
		this.id = id;
		this.nome = nome;
		this.mascote = mascote;
		this.campeonatos = new ArrayList<String>();
		this.apostas = new ArrayList<>();
	}
	
	public void insereCampeonato(String campeonato) {
		campeonatos.add(campeonato);
	}
	
	public int getQtdeCampeonatos() {
		return campeonatos.size();
	}
	
	public void insereAposta(Aposta aposta) {
		apostas.add(aposta);
	}
	
	public int contaApostasEmPrimeiro() {
		int cont = 0;
		for(Aposta aposta : apostas) {
			if(aposta.getColocacao() == 1) {
				cont++;
			}
		}
		return cont;
	}
	
	public String getNome() {
		return nome;
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
