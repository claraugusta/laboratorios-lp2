package dica;

public class Texto implements ElementoInterface{
	
	private String texto;
	
	public Texto(String texto) throws IllegalArgumentException{
		if(contaCaracteres(texto) > 500) {
			throw new IllegalArgumentException("Limite de caracteres Atingido.");
		}
		this.texto = texto;
	}
	
	public int calculaBonus() {
		int bonus = texto.length() / 10;
		if(bonus > 50) {
			bonus = 50;
		}
		return bonus;
	}
	
	private int contaCaracteres(String texto) {
		return texto.length();
	}
	
	public String exibeConteudo() {
		return String.format("%s\n", texto);
	}
	
	public String exibeConteudoDetalhado() {
		return String.format("%s\n %d caracteres.\n", texto, contaCaracteres(texto));
	}
	
}
