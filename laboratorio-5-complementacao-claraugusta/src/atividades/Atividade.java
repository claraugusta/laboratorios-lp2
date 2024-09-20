package atividades;

public abstract class Atividade {
	
	private String codigo;
	private String descricao;
	private String link;
	private int unidadeAcumulada;
	protected int creditos;

	
	public Atividade(String codigo, String descricao, String link, int unidadeAcumulada) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.link = link;
		this.unidadeAcumulada = unidadeAcumulada;
		this.creditos = 0;
	}
	
	public int getUnidadeAcumulada() {
		return unidadeAcumulada;
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
