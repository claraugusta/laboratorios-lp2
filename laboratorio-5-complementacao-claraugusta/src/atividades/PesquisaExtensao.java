package atividades;

public class PesquisaExtensao extends Atividade{
	private String subtipo;
	
	public PesquisaExtensao(String tipo, String codigo, int meses, String subtipo) {
		super(tipo, codigo,  meses);
		this.subtipo = subtipo;	
	}
	
	public boolean adicionaCreditos() {	
		if(creditos == 18) {
			return false;
		}
		int calculo = (10/12) * getUnidadeAcumulada();
		if(creditos + calculo > 18) {
			calculo = 18;
		}
		creditos += calculo;
		return true;
	}
}
