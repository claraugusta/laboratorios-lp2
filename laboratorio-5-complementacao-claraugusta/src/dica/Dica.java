package dica;

import java.util.ArrayList;
import java.util.List;

import estudante.Estudante;

public class Dica {
	
	private Estudante autor;
	private String tema;
	private int id;
	private ArrayList<ElementoInterface> elementos;
	private int bonus;
	
	
	public Dica(Estudante autor, String tema) {
		
		elementos = new ArrayList<>();
		this.autor = autor;
		this.tema = tema; 
		this.id = 0;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	public Estudante pegaAutor() {
		return autor;
	}
	
	public String getTema() {
		return tema;
	}	
	
	public boolean adicionaElementoTexto(String texto) {	
		ElementoInterface elemento = new Texto(texto);
		elementos.add(elemento);
		return true;
	}
	
	public boolean adicionaElementoReferencia(List<String> referencias, boolean conferida) {	
		ElementoInterface elemento = new Referencia(referencias, conferida);
		elementos.add(elemento);
		return true;
	}
	
	public boolean adicionaElementoMultimidia(String link, String cabecalho, int tempo) {	
		ElementoInterface elemento = new Multimidia(link, cabecalho, tempo);
		elementos.add(elemento);
		return true;
	}
	
	public int calculaBonificacao() {
		bonus = 0;
		for(ElementoInterface e : elementos) {
			bonus += e.calculaBonus();
		}
		return bonus;
	}
	
	public String listaDica() {
		String out = autor.getNome() + "\n";
		for(ElementoInterface e : elementos) {
			out += e.exibeConteudo();
		}
		return out;
	}
	
	public String listaDicaDetalhada() {
		String out = autor.getNome() + "\n";
		for(ElementoInterface e : elementos) {
			out += e.exibeConteudoDetalhado();
		}
		return out;
	}
	
	
}
