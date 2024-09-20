package atividades;

public class Estagio extends Atividade{
	private String disciplina;
	
	public Estagio(String codigo, String descricao, String link, int horas, String disciplina) {
		super(codigo, descricao, link, horas);
		this.disciplina = disciplina;
	}
	
	public boolean adicionaCreditos() {
		int calculo = (1/60) * getUnidadeAcumulada();
		if(creditos + calculo > 18) {
			calculo = 18;
		}
		creditos += calculo;
		return true;
	}
}
