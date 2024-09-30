package dica;

/**
 * A classe Texto representa um elemento de texto que pode ser adicionado a uma dica.
 * Ela implementa a interface ElementoInterface, fornecendo métodos para calcular bônus 
 * e exibir o conteúdo de forma simples ou detalhada.
 * @author Maria Clara Augusta Santos
 */
public class Texto implements ElementoInterface {

    private String texto;

    /**
     * Construtor da classe Texto.
     *
     * @param texto O conteúdo do texto a ser armazenado.
     * @throws IllegalArgumentException Se o texto tiver mais de 500 caracteres.
     */
    public Texto(String texto) throws IllegalArgumentException {
        if (contaCaracteres(texto) > 500) {
            throw new IllegalArgumentException("Limite de caracteres Atingido.");
        }
        this.texto = texto;
    }

    /**
     * Calcula o bônus com base no comprimento do texto.
     *
     * O bônus é calculado como o número de caracteres dividido por 10, 
     * com um máximo de 50.
     *
     * @return O bônus calculado.
     */
    public int calculaBonus() {
        int bonus = texto.length() / 10;
        if (bonus > 50) {
            bonus = 50;
        }
        return bonus;
    }

    /**
     * Conta o número de caracteres em um texto.
     *
     * @param texto O texto a ser contado.
     * @return O número de caracteres no texto.
     */
    private int contaCaracteres(String texto) {
        return texto.length();
    }

    /**
     * Exibe o conteúdo do texto em uma representação simples.
     *
     * @return O texto armazenado como uma String.
     */
    public String exibeConteudo() {
        return String.format("%s\n", texto);
    }

    /**
     * Exibe o conteúdo do texto em uma representação detalhada.
     *
     * @return O texto, o número de caracteres e o bônus calculado, formatados como uma String.
     */
    public String exibeConteudoDetalhado() {
        return String.format("%s (%d caracteres). Valor = %d\n", texto, contaCaracteres(texto), calculaBonus());
    }
}

