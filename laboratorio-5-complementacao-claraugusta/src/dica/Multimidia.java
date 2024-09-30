package dica;

/**
 * A classe Multimidia representa um elemento multimídia em uma dica.
 * Ela implementa a interface ElementoInterface, permitindo que
 * seja adicionada a uma dica e calcule seu bônus.
 * @author Maria Clara Augusta Santos
 */
public class Multimidia implements ElementoInterface {
	
	private String link;
	private String cabecalho;
	private int tempo;
	
	/**
	 * Constrói uma nova instância de Multimidia.
	 *
	 * @param link      O link do conteúdo multimídia.
	 * @param cabecalho O cabeçalho do conteúdo multimídia.
	 * @param tempo     O tempo de duração do conteúdo multimídia em segundos.
	 */
	public Multimidia(String link, String cabecalho, int tempo) {
		this.link = link;
		this.cabecalho = cabecalho;
		this.tempo = tempo;
	}
	
	/**
	 * Calcula o bônus associado ao conteúdo multimídia.
	 * O bônus é calculado como 5 pontos por cada 60 segundos
	 * de duração, com um limite máximo de 50 pontos.
	 *
	 * @return O bônus calculado.
	 */
	public int calculaBonus() {
		int bonus = (tempo / 60) * 5;
		if(bonus > 50) {
			bonus = 50;
		}
		return bonus;
	}
	
	/**
	 * Exibe o conteúdo multimídia em formato simples.
	 *
	 * @return Uma string representando o conteúdo multimídia.
	 */
	public String exibeConteudo() {
		return String.format("%s, %s\n", cabecalho, link);
	}
	
	/**
	 * Exibe o conteúdo multimídia em formato detalhado.
	 *
	 * @return Uma string representando o conteúdo multimídia com detalhes
	 *         como duração e valor do bônus.
	 */
	public String exibeConteudoDetalhado() {
		return String.format("%s, %s, %ds. Valor = %d\n", cabecalho, link, tempo, calculaBonus());
	}
}