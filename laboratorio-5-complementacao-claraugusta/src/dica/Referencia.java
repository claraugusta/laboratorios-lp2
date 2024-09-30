package dica;

import java.util.List;

/**
 * Representa uma referência utilizada em uma dica.
 * Uma referência contém informações sobre o título, fonte, ano, 
 * importância e se foi conferida.
 * @author Maria Clara Augusta Santos
 */
public class Referencia implements ElementoInterface {
	private boolean conferida;
	private int importancia;
	private String titulo;
	private String fonte;
	private int ano;
	
	/**
     * Constrói uma nova referência com os dados especificados.
     *
     * @param titulo      O título da referência.
     * @param fonte       A fonte da referência.
     * @param ano         O ano da referência.
     * @param importancia  A importância da referência.
     * @param conferida    Indica se a referência foi conferida.
     * @throws NullPointerException     Se o título ou a fonte forem nulos.
     * @throws IllegalArgumentException  Se o título ou a fonte forem vazios.
     */
    public Referencia(String titulo, String fonte, int ano, int importancia, boolean conferida) 
            throws NullPointerException, IllegalArgumentException {
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

    /**
     * Calcula o bônus associado à referência.
     * Se a referência foi conferida, o bônus é 15.
     *
     * @return O valor do bônus calculado.
     */
    public int calculaBonus() {
        int bonus = 0;
        if (conferida) {
            bonus = 15;
        }
        return bonus;
    }

    /**
     * Exibe o conteúdo da referência em formato simples.
     *
     * @return A representação da referência.
     */
    public String exibeConteudo() {
        return String.format("Referência: %s, %s, ano: %d.", titulo, fonte, ano);
    }

    /**
     * Exibe o conteúdo da referência em formato detalhado.
     *
     * @return A representação detalhada da referência, incluindo importância e bônus.
     */
    public String exibeConteudoDetalhado() {
        return String.format("Referência: %s, %s, ano: %d, importância: %d. Valor = %d\n", 
                             titulo, fonte, ano, importancia, calculaBonus());
    }
}
