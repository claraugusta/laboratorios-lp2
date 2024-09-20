package dica;

public class Multimidia implements ElementoInterface {
	
	private String link;
	private String cabecalho;
	private int tempo;
	
	public Multimidia(String link, String cabecalho, int tempo) {
		this.link = link;
		this.cabecalho = cabecalho;
		this.tempo = tempo;
	}
	
	public int calculaBonus() {
		int bonus = (tempo / 60) * 5;
		if(bonus > 50) {
			bonus = 50;
		}
		return bonus;
	}
	
	public String exibeConteudo() {
		return String.format("%s, %s\n", cabecalho, link);
	}
	public String exibeConteudoDetalhado() {
		return String.format("%s, %s, %ds.\n", cabecalho, link, tempo);
	}
}
