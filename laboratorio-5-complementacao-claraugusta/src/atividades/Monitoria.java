package atividades;

public class Monitoria extends Atividade {
	
	private String disciplina;

	public Monitoria(String tipo, String codigo, int semestres, String disciplina)  {
		super(tipo, codigo, semestres);
		this.disciplina = disciplina;
	}

	public boolean adicionaCreditos() {
		if(creditos == 16) {
			return false;
		}
		int calculo = (4/6) * getUnidadeAcumulada();
		if(creditos + calculo > 16) {
			calculo = 16;
		}
		creditos += calculo;
		return true;
	}
}
