package atividades;

/**
 * A classe Estagio representa uma atividade de estágio realizada por um estudante.
 * Ela herda da classe abstrata Atividade e fornece uma implementação específica para 
 * o cálculo de créditos baseado nas horas acumuladas.
 * @author Maria Clara Augusta Santos
 */
public class Estagio extends Atividade {
    private String empresa;

    /**
     * Constrói uma nova instância da classe Estagio.
     *
     * @param tipo           O tipo da atividade.
     * @param codigo         O código da atividade.
     * @param horas          O número de horas acumuladas na atividade.
     * @param empresa        O nome da empresa onde o estágio foi realizado.
     */
    public Estagio(String tipo, String codigo, int horas, String empresa) {
        super(tipo, codigo, horas);
        this.empresa = empresa;
        super.MAX_CREDITOS = 18;
    }

    /**
     * Calcula os créditos obtidos com a atividade de estágio.
     *
     * @param creditoTipo    O total de créditos já acumulados.
     * @return O número de créditos que podem ser adicionados com esta atividade.
     */
    public int calculaCreditos(int creditoTipo) {
        int creditos = getUnidadeAcumulada() / 60;
        if (creditoTipo + creditos > 18) {
            creditos = 18 - creditoTipo;
        }
        return creditos;
    }
}