package estudante;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import atividades.Atividade;


public class EstudanteController {
	
	private Map<String, Estudante> estudantesMap;

	
	public EstudanteController() {
		estudantesMap = new HashMap<>();

	}
	
	public boolean criaEstudante(String nome, String cpf, String senha, String matricula) {		
		if(estudantesMap.containsKey(cpf) || senha.length() < 8) {
			return false;
		}
		Estudante estudante = new Estudante(nome, cpf, senha, matricula);
		estudantesMap.put(cpf, estudante);
		return true;
	}
	
	public void verificaEstudante(String cpf) throws NullPointerException {
		if(!estudantesMap.containsKey(cpf)) {
			throw new NullPointerException("ESTUDANTE NÃO EXISTE");
		}
	}
	
	public Estudante pegaEstudante(String cpf) {
		verificaEstudante(cpf);
		return estudantesMap.get(cpf);
	}
	
	public boolean alteraSenhaEstudante(String cpf, String senhaAntiga, String novaSenha) throws NullPointerException {
		if(novaSenha == null) {
			throw new NullPointerException("SENHA NULA");
		}
		pegaEstudante(cpf).validaSenha(senhaAntiga);
		if(novaSenha == null || novaSenha.length() < 8 || novaSenha.equals(senhaAntiga)) {
			return false;
		}		
		pegaEstudante(cpf).alteraSenha(novaSenha);
		return true;
	}
	
	public String[] listaEstudantes() {
		int TAMANHO = estudantesMap.size();
		int proxPos = 0;
		String[] exibicao = new String[TAMANHO];	
		for(Estudante e : estudantesMap.values()) {
			exibicao[proxPos++] = e.exibeAluno();
		}
		Arrays.sort(exibicao);
		return exibicao;
	}
	
	public String[] listarUsuariosRankingDicas() {
		List<Estudante> UsuariosList = new ArrayList<>(estudantesMap.values());
		String[] ranking = new String[UsuariosList.size()];
		int prox = 0;
		UsuariosList.sort(new ComparadorBonus());	
		for(Estudante e : UsuariosList) {
			ranking[prox++] = e.toString() + "Bonificação: " + e.getBonificacao() + "\n";
		}
		return ranking;
	}
	
	public boolean adicionaAtividadeEstudante(Estudante estudante, String codigo,  Atividade atividade) {
		estudante.adicionaAtividade(codigo, atividade);
		return true;
	}
	

}
