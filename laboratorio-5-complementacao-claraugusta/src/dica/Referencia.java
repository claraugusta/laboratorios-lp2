package dica;

import java.util.List;

public class Referencia implements ElementoInterface {
	private List<String> referencias;
	private boolean conferida;
	private String flag;
	
	
	public Referencia(List<String> referencias, boolean conferida) throws NullPointerException, IllegalArgumentException {
		if (referencias == null) {
            throw new NullPointerException("lista de referências nula.");
        }
        if (referencias.isEmpty()) {
            throw new IllegalArgumentException("lista de referências vazia.");
        }
		for(String dado : referencias) {
			if(dado == null) {
				throw new NullPointerException("ITEM NULO");
			}
			if(dado.isEmpty()) {
				throw new IllegalArgumentException("ITEM VAZIO");
			}
		}
				
		this.referencias = referencias;
		this.conferida = conferida;
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
		String out = "";
		for(String r : referencias) {
			out += r.substring(0,r.length()-14) + "\n";
		}
		return out;
	}
	
	public String exibeConteudoDetalhado() {
		String out = "";
		for(String r : referencias) {
			out += r + "\n";
		}
		return out;
	}
}
