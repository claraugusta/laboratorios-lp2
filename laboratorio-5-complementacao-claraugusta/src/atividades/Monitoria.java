package atividades;


/**
 * Representa uma atividade de monitoria em que um estudante auxilia na
 * aprendizagem de uma disciplina.
 * 
 * <p>A classe estende a classe abstrata Atividade e define a disciplina
 * específica da monitoria, além de calcular os créditos acumulados com base
 * nas unidades acumuladas.</p>
 * @author Maria Clara Augusta Santos
 */
public class Monitoria extends Atividade {

    private String disciplina;

    /**
     * Construtor para criar uma nova atividade de monitoria.
     * 
     * @param tipo        O tipo da atividade (ex: "Monitoria").
     * @param codigo      O código identificador da atividade.
     * @param semestres   O número de semestres em que a atividade foi realizada.
     * @param disciplina  O nome da disciplina da monitoria.
     */
    public Monitoria(String tipo, String codigo, int semestres, String disciplina) {
        super(tipo, codigo, semestres);
        this.disciplina = disciplina;
        super.MAX_CREDITOS = 16;
    }

    /**
     * Calcula os créditos acumulados com base nas unidades acumuladas e no tipo
     * de crédito já acumulado.
     * 
     * @param creditoTipo O total de créditos já acumulados.
     * @return O número de créditos que podem ser adicionados.
     */
    public int calculaCreditos(int creditoTipo) {
        int creditos = 4 * getUnidadeAcumulada();
        if (creditoTipo + creditos > 16) {
            creditos = 16 - creditoTipo;
        }
        return creditos;
    }
}
