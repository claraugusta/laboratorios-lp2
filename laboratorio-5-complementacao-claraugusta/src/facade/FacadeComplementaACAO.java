package facade;

import java.util.List;

import atividades.AtividadeController;
import dica.DicaController;
import estudante.EstudanteController;

public class FacadeComplementaACAO {
	
	private EstudanteController estudanteC;
	private DicaController dicaC;
	private AtividadeController atividadeC;
	
	public FacadeComplementaACAO() {
		atividadeC = new AtividadeController(estudanteC);
		estudanteC = new EstudanteController();
		dicaC = new DicaController(estudanteC);	
	}
	
	public boolean criarEstudante(String nome, String cpf, String senha, String matricula) {
		return estudanteC.criaEstudante(nome, cpf, senha, matricula);
	}
	
	public String[] exibirEstudantes() {	
		return estudanteC.listaEstudantes();
	}
	
	public boolean alterarSenhaEstudante(String cpf, String senhaAntiga, String novaSenha) {
		return estudanteC.alteraSenhaEstudante(cpf, senhaAntiga, novaSenha);
	}
	
	public int adicionarDica(String cpf, String senha, String tema) {
		return dicaC.adicionaDica(senha, cpf, tema);
	}
	
	public boolean adicionarElementoTextoDica(String cpf, String senha, int posicao, String texto) {
		return dicaC.adicionaElementoTextoDica(cpf, senha, posicao, texto);
	}
	
	public boolean adicionarElementoMultimidiaDica(String cpf, String senha, int posicao, String link, String cabecalho, int tempo) {
		return dicaC.adicionaElementoMultimidiaDica(cpf, senha, posicao, link, cabecalho, tempo);
	}
	
	public boolean adicionarElementoReferenciaDica(String cpf, String senha, int posicao,  String titulo, String fonte, int ano, boolean conferida, int importancia) {
		return dicaC.adicionaElementoReferenciaDica(cpf, senha, posicao, titulo, fonte,ano , importancia, conferida);
	}
	
	public String[] listarDicas() {
		return dicaC.listaDicas();
	}
	
	public String [] listarDicasDetalhes() {
		return dicaC.listaDicasDetalhadas();
	}
	
	public String listarDica(int posicao) {
		return dicaC.listaDicaPosicao(posicao);
	}
	
	public String listarDicaDetalhes(int posicao) {
		return dicaC.listaDicaDetalhadaPosicao(posicao);
	}
	
	public String[] listarUsuariosRankingDicas() {
		return estudanteC.listarUsuariosRankingDicas();
	}

	public String criarAtividadeMonitoriaEmEstudante(String cpf, String senha, String tipo, int unidadeAcumulada, String disciplina) {
		return atividadeC.criaAtividadeMonitoriaEmEstudante(cpf, senha, tipo, unidadeAcumulada, disciplina);
	}
	
	public boolean alterarDescricaoAtividade(String cpf, String senha, String codigoAtividade, String descricao) {
		return estudanteC.alteraDescricaoAtividade(cpf, senha, codigoAtividade, descricao);
	}
	
	public boolean alterarComprovacaoAtividade(String cpf, String senha, String codigoAtividade, String linkComprovacao) {
		return estudanteC.alteraComprovacaoAtividade(cpf, senha, codigoAtividade, linkComprovacao);
	}
	
	public String criarAtividadePesquisaExtensaoEmEstudante(String cpf, String senha, String tipo, int unidadeAcumulada, String subtipo) {
		return atividadeC.criaAtividadePesquisaExtensaoEmEstudante(cpf, senha, tipo, unidadeAcumulada, subtipo);
	}
	
	public String criarAtividadeEstagioEmEstudante(String cpf, String senha, String tipo, int unidadeAcumulada, String nomeEmpresa) {
		return atividadeC.criaAtividadeEstagioEmEstudante(cpf, senha, tipo, unidadeAcumulada, nomeEmpresa);
	}
	
	public String criarAtividadeRepresentacaoEstudantil(String cpf, String senha, int unidadeAcumulada, String subtipo) {
		return atividadeC.criaAtividadeEstagioEmEstudante(cpf, senha, subtipo, unidadeAcumulada, subtipo);
	}
	
	public int creditosAtividade(String cpf, String senha, String tipo) {
		return estudanteC.creditosAtividade(cpf, senha, tipo);
	}
	
	public String gerarMapaCreditosAtividades(String cpf, String senha) {
		return estudanteC.geraMapaCreditosAtividades(cpf, senha);
	}
	
	public boolean verificarMetaAlcancada(String cpf, String senha) {
		return estudanteC.verificaMetaAlcancada(cpf, senha);
	}

	public String gerarRelatorioFinal(String cpf, String senha) {
		return estudanteC.gerarRelatorioFinal(cpf, senha);
	}
	
	public String gerarRelatorioFinalPorAtividade(String cpf, String senha, String tipoAtividade) {
		return estudanteC.gerarRelatorioFinalPorAtividade(cpf, senha, tipoAtividade);
	}
	
	public String gerarRelatorioParcial(String cpf, String senha, boolean salvar) {
		return estudanteC.gerarRelatorioParcial(cpf, senha, salvar);
	}
	
	public String gerarRelatorioParcialPorAtividade(String cpf, String senha, boolean salvar, String tipoAtividade) {
		return estudanteC.gerarRelatorioParcialPorAtividade(cpf, senha, salvar, tipoAtividade);
	}
	
	public String listarHistorico(String cpf, String senha) {
		return estudanteC.listarHistorico(cpf, senha);
	}
	
	public boolean excluirItemHistorico(String cpf, String senha, String data) {
		return estudanteC.excluirItemHistorico(cpf, senha, data);
	}


}
