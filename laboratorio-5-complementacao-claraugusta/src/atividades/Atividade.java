package atividades;

public abstract class Atividade {
	
	private String tipo;
	private String codigo;
	private String descricao;
	private String link;
	private int unidadeAcumulada;
	protected int creditos;

	
	public Atividade(String tipo, String codigo, int unidadeAcumulada) {
		if(tipo == null) {
			throw new NullPointerException("Tipo nulo.");
		}		
		if(codigo == null) {
			throw new NullPointerException("Código nulo.");
		}
		if(descricao == null) {
			throw new NullPointerException("Descrição nula.");
		}
		if(link == null) {
			throw new NullPointerException("Link nulo.");
		}
		if(tipo.isEmpty()) {
			throw new IllegalArgumentException("Tipo vazio.");
		}
		if(codigo.isEmpty()) {
			throw new IllegalArgumentException("Código vazio.");
		}
		if(descricao.isEmpty()) {
			throw new IllegalArgumentException("Descrição vazia.");
		}
		if(link.isEmpty()) {
			throw new IllegalArgumentException("Link vazio.");
		}
				
		this.tipo = tipo;
		this.codigo = codigo;
		this.unidadeAcumulada = unidadeAcumulada;
		this.creditos = 0;
		adicionaCreditos();
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setComprovacao(String link) {
		this.link = link;
	}

	public int getUnidadeAcumulada() {
		return unidadeAcumulada;
	}
	
	public String getTipo() {
		return tipo;
	}

	public int getCreditos() {
		return creditos;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getLink() {
		return link;
	}
	
	public abstract boolean adicionaCreditos();
}
