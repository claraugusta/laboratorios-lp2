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
	

	public String incluirTime(String id, String campeonato) {
		verificaTime(id);
		verificaCampeonato(campeonato);
		if(campeonatoMap.get(campeonato).limiteTimesAtingido()) {
			return "TODOS OS TIMES DESSE CAMPEONATO JÁ FORAM INCLUÍDOS!";
		}
		campeonatoMap.get(campeonato).insereTime(timesMap.get(id));
		timesMap.get(id).insereCampeonato(campeonato);
		return "ADICIONADO!";
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
		if(colocacao > camp.getQtdeParticipantes()) {
			return "APOSTA NÃO REGISTRADA!";
		}
		apostas.add(new Aposta(timesMap.get(id), camp, colocacao, valorAposta));
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
}
