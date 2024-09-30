package atividades;

/**
 * A classe RepresentacaoEstudantil representa uma atividade de 
 * representação estudantil e herda da classe abstrata Atividade.
 * Esta atividade possui um subtipo e calcula créditos com base 
 * nas unidades acumuladas.
 * @author Maria Clara Augusta Santos
 */
public class RepresentacaoEstudantil extends Atividade {
    
    private String subtipo;
    
    /**
     * Construtor da classe RepresentacaoEstudantil.
     *
     * @param tipo            O tipo da atividade.
     * @param codigo          O código identificador da atividade.
     * @param unidadeAcumulada O número de unidades acumuladas.
     * @param subtipo         O subtipo da representação estudantil.
     */
    public RepresentacaoEstudantil(String tipo, String codigo, int unidadeAcumulada, String subtipo) {
        super(tipo, codigo, unidadeAcumulada);
        this.subtipo = subtipo;
        super.MAX_CREDITOS = 2;
    }
    
    /**
     * Calcula os créditos acumulados pela atividade.
     *
     * @param creditoTipo O número de créditos já acumulados.
     * @return O número de créditos que podem ser acumulados nesta atividade.
     */
    public int calculaCreditos(int creditoTipo) {
        int creditos = 2 * getUnidadeAcumulada();
        if (creditoTipo + creditos > 2) {
            creditos = 2 - creditoTipo;
        }
        return creditos;
    }
}