package atividades;

/**
 * Representa uma atividade de pesquisa e extensão realizada por um estudante.
 * Esta classe é uma extensão da classe abstrata {@link Atividade} e inclui
 * um subtipo específico para categorizar a atividade de pesquisa e extensão.
 * @author Maria Clara Augusta Santos
 */
public class PesquisaExtensao extends Atividade {
    
    /**
     * O subtipo da atividade de pesquisa e extensão.
     */
    private String subtipo;

    /**
     * Construtor para a classe PesquisaExtensao.
     *
     * @param tipo     O tipo da atividade.
     * @param codigo   O código da atividade.
     * @param meses    O número de meses da atividade.
     * @param subtipo  O subtipo da atividade de pesquisa e extensão.
     */
    public PesquisaExtensao(String tipo, String codigo, int meses, String subtipo) {
        super(tipo, codigo, meses);
        this.subtipo = subtipo;    
        super.MAX_CREDITOS = 18;
    }

    /**
     * Calcula os créditos acumulados com base nas unidades acumuladas e nos créditos já existentes.
     *
     * @param creditoTipo O total de créditos já acumulados.
     * @return O número de créditos que podem ser acumulados com esta atividade.
     */
    public int calculaCreditos(int creditoTipo) {    
        int creditos = (int)(10.0 * getUnidadeAcumulada() / 12);
        if (creditoTipo + creditos > 18) {
            creditos = 18 - creditoTipo;
        }
        return creditos;
    }
}