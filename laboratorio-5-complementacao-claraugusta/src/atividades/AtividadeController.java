package atividades;

import estudante.Estudante;
import estudante.EstudanteController;

public class AtividadeController {
	
	EstudanteController ec;
	
	
	public AtividadeController(EstudanteController ec){
		this.ec = ec;
	}
	
	private Estudante pegaEstudante(String cpf) {
		ec.verificaEstudante(cpf);
		return ec.pegaEstudante(cpf);
	}
	
	public String criaAtividadeMonitoriaEmEstudante(String cpf, String senha, String tipo, int unidadeAcumulada, String disciplina) throws IllegalArgumentException{
		if(unidadeAcumulada < 1) {
			throw new IllegalArgumentException("Número de semestres inválido.");
		}
		pegaEstudante(cpf).validaSenha(senha);
		String codigo = cpf + "_" + pegaEstudante(cpf).getAtividadesCount();

		return "";
	}
	
	public boolean alteraDescricaoAtividade(String cpf, String senha, String codigoAtividade, String descricao) {
		return false;
	}
	
	public boolean alteraComprovacaoAtividade(String cpf, String senha, String codigoAtividade, String linkComprovacao) {
		return false;
	}
	
	public String criaAtividadePesquisaExtensaoEmEstudante(String cpf, String senha, String tipo, int unidadeAcumulada, String subtipo) {
		return "";
	}
	
	public String criaAtividadeEstagioEmEstudante(String cpf, String senha, String tipo, int unidadeAcumulada, String nomeEmpresa) {
		return "";
	}
	
	public String criaAtividadeRepresentacaoEstudantil(String cpf, String senha, int unidadeAcumulada, String subtipo) {
		return "";
	}
	
	public int creditosAtividade(String cpf, String senha, String tipo) {
		return 0;
	}
	
	public String geraMapaCreditosAtividades(String cpf, String senha) {
		return "";
	}
	
	public boolean verificaMetaAlcancada(String cpf, String senha) {
		return false;
	}

}
