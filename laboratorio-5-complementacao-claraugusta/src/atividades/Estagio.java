package atividades;

public class Estagio extends Atividade{
	private String empresa;
	
	public Estagio(String tipo, String codigo, int horas, String empresa) {
		super(tipo, codigo, horas);
		this.empresa = empresa;
	}
	
	public boolean adicionaCreditos() {
		if(creditos == 18) {
			return false;
		}
		int calculo = (1/60) * getUnidadeAcumulada();
		if(creditos + calculo > 18) {
			calculo = 18;
		}
		creditos += calculo;
		return true;
	}
}
