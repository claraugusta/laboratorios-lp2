package dica;

/**
 * A interface ElementoInterface define os métodos que qualquer elemento de dica deve implementar.
 * Um elemento pode ser texto, referência ou multimídia, e cada um tem a capacidade de calcular
 * uma bonificação e exibir seu conteúdo de maneira simples ou detalhada.
 * @author Maria Clara Augusta Santos
 */
public interface ElementoInterface {
 	
	/**
     * Calcula o valor de bonificação associado ao elemento.
     * Cada tipo de elemento pode ter uma bonificação diferente.
     *
     * @return o valor de bonificação do elemento.
     */
    int calculaBonus();

    /**
     * Exibe o conteúdo básico do elemento.
     * Este método deve retornar uma representação do conteúdo de forma simples.
     *
     * @return uma String representando o conteúdo básico do elemento.
     */
    String exibeConteudo();

    /**
     * Exibe o conteúdo detalhado do elemento.
     * Este método deve retornar uma representação do conteúdo com detalhes adicionais.
     *
     * @return uma String representando o conteúdo detalhado do elemento.
     */
    String exibeConteudoDetalhado();
}
