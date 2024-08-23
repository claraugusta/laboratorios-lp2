package mrbet;

public class Aposta {
	
	private Time time;
	private Campeonato campeonato;
	private int colocacao;
	private double valorAposta;
	
	public Aposta(Time time, Campeonato campeonato, int colocacao, double valorAposta) {
		this.time = time;
		this.campeonato = campeonato;
		this.colocacao = colocacao;
		this.valorAposta = valorAposta;
	}
	
	public int getColocacao() {
		return colocacao;
	}

	public String toString() {
		return String.format("%s\n%s\n%d/%d\nR$ %.2f", time.toString(), campeonato.getNome(), colocacao,
				campeonato.getQtdeParticipantes(), valorAposta);
	}
	
}
