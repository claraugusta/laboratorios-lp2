package atividades;

public class RepresentacaoEstudantil extends Atividade {
	
	private String subtipo;
	
	public RepresentacaoEstudantil(String tipo, String codigo,  int unidadeAcumulada, String subtipo) {
		super(tipo, codigo, unidadeAcumulada);
		this.subtipo = subtipo;
	}
	
	public boolean adicionaCreditos() {
		if(creditos == 2) {
			return false;
		}
		int calculo = 2 * getUnidadeAcumulada();
		if(creditos + calculo > 2) {
			calculo = 2;
		}
		creditos += calculo;
		return true;
	}
}
