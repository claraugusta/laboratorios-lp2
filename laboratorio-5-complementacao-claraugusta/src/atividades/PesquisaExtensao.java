package atividades;

public class PesquisaExtensao extends Atividade{
	private String subtipo;
	
	public PesquisaExtensao(String codigo, String descricao, String link, int meses, String subtipo) {
		super(codigo, descricao, link, meses);
		this.subtipo = subtipo;	
	}
	
	public boolean adicionaCreditos() {	
		int calculo = (10/12) * getUnidadeAcumulada();
		if(creditos + calculo > 18) {
			calculo = 18;
		}
		creditos += calculo;
		return true;
	}
}
