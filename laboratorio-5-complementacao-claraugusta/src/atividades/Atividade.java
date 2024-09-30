package atividades;

/**
 * Representa uma atividade acadêmica que pode ser associada a um estudante.
 * 
 * A classe é abstrata e serve como base para atividades específicas, como monitoria,
 * pesquisa, estágio e representação estudantil. Cada atividade possui um tipo, código,
 * unidades acumuladas e a possibilidade de adicionar uma descrição e um link para
 * comprovação.
 */
public abstract class Atividade {
    
   
    private String tipo;
    private String descricao;
    private String link;
    private int unidadeAcumulada;
    protected int MAX_CREDITOS;
    private String codigo;

    /**
     * Constrói uma nova atividade com o tipo, código e unidades acumuladas especificadas.
     * 
     * @param tipo O tipo da atividade.
     * @param codigo O código da atividade.
     * @param unidadeAcumulada O número de unidades acumuladas para a atividade.
     * @throws NullPointerException Se o tipo for nulo.
     * @throws IllegalArgumentException Se o tipo for uma string vazia.
     */
    public Atividade(String tipo, String codigo, int unidadeAcumulada) throws NullPointerException, IllegalArgumentException {
        if (tipo == null) {
            throw new NullPointerException("Tipo nulo.");
        }
        if (tipo.isEmpty()) {
            throw new IllegalArgumentException("Tipo vazio.");
        }
        this.codigo = codigo;
        this.tipo = tipo;
        this.unidadeAcumulada = unidadeAcumulada;
        this.descricao = "";
        this.link = "";
    }

    /**
     * Define a descrição da atividade.
     * 
     * @param descricao A descrição a ser atribuída.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Define o link de comprovação da atividade.
     * 
     * @param link O link a ser atribuído.
     */
    public void setComprovacao(String link) {
        this.link = link;
    }

    public int getUnidadeAcumulada() {
        return unidadeAcumulada;
    }

    public int getMaxCreditos() {
        return MAX_CREDITOS;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    
    public String getLink() {
        return link;
    }

    /**
     * Método abstrato para calcular os créditos da atividade com base no tipo de crédito.
     * 
     * @param creditoTipo O tipo de crédito a ser considerado para o cálculo.
     * @return O número de créditos calculados.
     */
    public abstract int calculaCreditos(int creditoTipo);
}
