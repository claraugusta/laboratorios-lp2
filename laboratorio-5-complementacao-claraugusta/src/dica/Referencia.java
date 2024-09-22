package dica;

import java.util.List;

public class Referencia implements ElementoInterface {
	private boolean conferida;
	private int importancia;
	private String titulo;
	private String fonte;
	private int ano;
	
	public Referencia(String titulo, String fonte, int ano, int importancia, boolean conferida) throws NullPointerException, IllegalArgumentException {
		if (titulo == null || fonte == null) {
            throw new NullPointerException("titulo ou fonte nula");
        }
        if (titulo.isEmpty() || fonte.isEmpty()) {
            throw new IllegalArgumentException("titulo ou fonte vazia.");
        }
				
		this.titulo = titulo;
		this.fonte = fonte;
		this.ano = ano;
		this.conferida = conferida;
		this.importancia = importancia;
	}
	
	public int calculaBonus() {
		int bonus = 0;
		if(conferida) {
			bonus += 15;
		}
		if(bonus > 50) {
			bonus = 50;
		}
		return bonus;
	}
	
	public String exibeConteudo() {
		return String.format("Referência: %s, %s, ano: %d.", titulo, fonte, ano);
	}
	
	public String exibeConteudoDetalhado() {

		return String.format("Referência: %s, %s, ano: %d, importância: %d", titulo, fonte, ano, importancia);
	}
}
