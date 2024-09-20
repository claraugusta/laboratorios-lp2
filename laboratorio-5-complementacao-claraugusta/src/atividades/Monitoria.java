package atividades;

public class Monitoria extends Atividade {
	
	private String disciplina;
	
	public Monitoria(String codigo, String descricao, String link, int semestres, String disciplina)  {
		super(codigo, descricao, link, semestres);
		this.disciplina = disciplina;
	}

	public boolean adicionaCreditos() {
		int calculo = (4/6) * getUnidadeAcumulada();
		if(creditos + calculo > 16) {
			calculo = 16;
		}
		creditos += calculo;
		return true;
	}
}
