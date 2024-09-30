package dica;

import java.util.ArrayList;
import java.util.List;

import estudante.Estudante;

/**
 * A classe Dica representa uma dica criada por um estudante, composta por elementos
 * como textos, referências e mídias. Cada dica possui um autor, um tema, e uma lista
 * de elementos que podem ser adicionados.
 * @author Maria Clara Augusta Santos - 123210361
 */
public class Dica {
	
	private Estudante autor;
	private String tema;
	private int id;
	private List<ElementoInterface> elementos;
	private int bonus;
	
	
	/**
     * Construtor da classe Dica.
     *
     * @param autor O autor da dica.
     * @param tema O tema da dica.
     */
    public Dica(Estudante autor, String tema) {
        elementos = new ArrayList<>();
        this.autor = autor;
        this.tema = tema; 
        this.id = 0;
    }

    /**
     * Define o ID da dica.
     *
     * @param id O identificador único da dica.
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * Retorna o ID da dica.
     *
     * @return O ID da dica.
     */
    public int getID() {
        return id;
    }

    /**
     * Retorna o autor da dica.
     *
     * @return O autor da dica.
     */
    public Estudante pegaAutor() {
        return autor;
    }

    /**
     * Retorna o tema da dica.
     *
     * @return O tema da dica.
     */
    public String getTema() {
        return tema;
    }

    /**
     * Adiciona um elemento de texto à dica.
     *
     * @param texto O conteúdo textual a ser adicionado.
     * @return true se o elemento foi adicionado com sucesso.
     */
    public boolean adicionaElementoTexto(String texto) {
        ElementoInterface elemento = new Texto(texto);
        elementos.add(elemento);
        autor.adicionaBonus(elemento.calculaBonus());
        return true;
    }

    /**
     * Adiciona um elemento de referência à dica.
     *
     * @param titulo O título da referência.
     * @param fonte A fonte da referência.
     * @param ano O ano da referência.
     * @param importancia O nível de importância da referência.
     * @param conferida Se a referência foi conferida ou não.
     * @return true se o elemento foi adicionado com sucesso.
     */
    public boolean adicionaElementoReferencia(String titulo, String fonte, int ano, int importancia, boolean conferida) {
        ElementoInterface elemento = new Referencia(titulo, fonte, ano, importancia, conferida);
        elementos.add(elemento);
        autor.adicionaBonus(elemento.calculaBonus());
        return true;
    }

    /**
     * Adiciona um elemento de multimídia à dica.
     *
     * @param link O link para o conteúdo multimídia.
     * @param cabecalho O cabeçalho da mídia.
     * @param tempo O tempo de duração da mídia.
     * @return true se o elemento foi adicionado com sucesso.
     */
    public boolean adicionaElementoMultimidia(String link, String cabecalho, int tempo) {
        ElementoInterface elemento = new Multimidia(link, cabecalho, tempo);
        elementos.add(elemento);
        autor.adicionaBonus(elemento.calculaBonus());
        return true;
    }

    /**
     * Calcula a bonificação total da dica com base nos elementos adicionados.
     *
     * @return O total da bonificação.
     */
    public int calculaBonificacao() {
        bonus = 0;
        for(ElementoInterface e : elementos) {
            bonus += e.calculaBonus();
        }
        return bonus;
    }

    /**
     * Retorna a representação da dica com os elementos contidos.
     *
     * @return Uma String que representa a dica e seus elementos.
     */
    public String listaDica() {
        String out = autor.getNome() + " - " + tema + "\n";
        for(ElementoInterface e : elementos) {
            out += e.exibeConteudo();
        }
        return out;
    }

    /**
     * Retorna a representação detalhada da dica com os elementos contidos.
     *
     * @return Uma String que representa a dica de forma detalhada.
     */
    public String listaDicaDetalhada() {
        String out = autor.getNome() + " - " + tema + "\n";
        for(ElementoInterface e : elementos) {
            out += e.exibeConteudoDetalhado();
        }
        return out;
    }
	
	
}
