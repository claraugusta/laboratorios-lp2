package mrbet;

import java.util.ArrayList;
import java.util.HashMap;

public class MrBet {
	
	HashMap<String, Time> timesMap;
	HashMap<String, Campeonato> campeonatoMap;
	ArrayList<Aposta> apostas;
	
	public MrBet() {
		timesMap = new HashMap<>();
		campeonatoMap = new HashMap<>();
		apostas = new ArrayList<>();
	}
	
	public String cadastraTime(String id, String nome, String mascote) {	
		Time time = new Time(id, nome, mascote);
		if(timesMap.get(id) != null) {
			return "TIME JÁ EXISTE!";
		}
		timesMap.put(id, time);
		return "INCLUSÃO REALIZADA!";
	}
	
	private void verificaTime(String id) throws NullPointerException {
		if(timesMap.get(id) == null) {
			throw new NullPointerException("TIME NÃO EXISTE!");
		}
	}
	
	private void verificaCampeonato(String campeonato) throws NullPointerException {
		if(campeonatoMap.get(campeonato) == null) {
			throw new NullPointerException("CAMPEONATO NÃO EXISTE!");
		}
	}
	
	public String mostraTime(String id) {
		verificaTime(id);
		return timesMap.get(id).toString();
	}
	
	public String cadastraCampeonato(String campeonato, int participantes) {
		if(campeonatoMap.get(campeonato) != null) {
			return "CAMPEONATO JÁ EXISTE!";
		}
		campeonatoMap.put(campeonato, new Campeonato(campeonato, participantes));
		return "CAMPEONATO ADICIONADO!";
	}
	
	public Campeonato pegaCampeonato(String campeonato) {
		return campeonatoMap.get(campeonato);
	}

	public String incluirTime(String id, String campeonato) {
		verificaTime(id);
		verificaCampeonato(campeonato);
		if(campeonatoMap.get(campeonato).limiteTimesAtingido()) {
			return "TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!";
		}
		campeonatoMap.get(campeonato).insereTime(timesMap.get(id));
		timesMap.get(id).insereCampeonato(campeonato);
		return "TIME INCLUÍDO NO CAMPEONATO!";
	}
	
	public String verificaTimeCampeonato(String id, String campeonato) {
		if(timesMap.get(id) == null) {
			return "O TIME NÃO EXISTE!";
		}
		if(campeonatoMap.get(campeonato) == null) {
			return "CAMPEONATO NÃO EXISTE!";
		}
		if(campeonatoMap.get(campeonato).temTime(timesMap.get(id))) {
			return "O TIME ESTÁ NO CAMPEONATO!";
		}else {
			return "O TIME NÃO ESTÁ NO CAMPEONATO!";
		}
	}
	
	public String exibeCampeonatos(String id) {
		Time time = timesMap.get(id);
		String campeonatosTime = "";
		for(int i = 0; i < time.getCampeonatos().size(); i++) {
			campeonatosTime += "\n" + campeonatoMap.get(time.getCampeonato(i)).toString();
		}
		return campeonatosTime;
	}
	
	public String insereAposta(String id, String campeonato, int colocacao, double valorAposta) {
		Campeonato camp =  campeonatoMap.get(campeonato);
		Time time = timesMap.get(id);
		if(colocacao > camp.getQtdeParticipantes()) {
			return "APOSTA NÃO REGISTRADA!";
		}
		Aposta novaAposta = new Aposta(time, camp, colocacao, valorAposta);
		apostas.add(novaAposta);
		time.insereAposta(novaAposta);
		return "APOSTA REGISTRADA!";
	}
	
	public String listaApostas() {
		String out = "Apostas:\n\n";
		for(int i = 0; i < apostas.size(); i++) {
			out += (i+1) + ". " + apostas.get(i).toString() + "\n\n";
		}
		out = out.substring(0, out.length() - 1);
		return out;
	}
	
	private String listaTimesMaisCompetitivos() {
		String out = "";
		int max = 0;
		for(Time time : timesMap.values()) {
			if(time.getQtdeCampeonatos() > max) {
				max = time.getQtdeCampeonatos();
			}
		}
		for(Time time : timesMap.values()) {
			if(time.getQtdeCampeonatos() >= max) {
				out += time.toString() + " " + max + "\n";
			}
		}
		return out;
	}
	
	private String listaTimesZeroCompeticoes() {
		String out = "";
		for(Time time : timesMap.values()) {
			if(time.getQtdeCampeonatos() == 0) {
				out += time.toString() + "\n";
			}
		}
		return out;
	}
	
	private String listaTimesMaisPopulares() {
		String out = "";
		for(Time time : timesMap.values()) {
			if(time.contaApostasEmPrimeiro() > 0) {
				out += String.format("%s / %d\n", time.getNome(), time.contaApostasEmPrimeiro());
			}
		}
		return out;
	}
	
	public String historico() {
		String out = String.format("Participação mais frequente em campeonatos\n%s\nAinda não participou de campeonato\n%s\nPopularidade em apostas\n%s", listaTimesMaisCompetitivos(), listaTimesZeroCompeticoes(), listaTimesMaisPopulares());
		return out;
	}
}
