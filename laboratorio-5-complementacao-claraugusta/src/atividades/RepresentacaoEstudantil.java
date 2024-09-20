package atividades;

public class RepresentacaoEstudantil extends Atividade {

	public RepresentacaoEstudantil(String codigo, String descricao, String link, int unidadeAcumulada) {
		super(codigo, descricao, link, unidadeAcumulada);
		// TODO Auto-generated constructor stub
	}
	public boolean adicionaCreditos() {
		int calculo = 2 * getUnidadeAcumulada();
		if(creditos + calculo > 2) {
			calculo = 2;
		}
		creditos += calculo;
		return true;
	}
}
