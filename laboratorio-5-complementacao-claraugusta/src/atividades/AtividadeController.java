package atividades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import estudante.Estudante;
import estudante.EstudanteController;

public class AtividadeController {
	
	EstudanteController ec;
	private List<String> subtiposExtensaoPesquisa;
	private List<String> subtiposRepresentacaoEstudantil;
	
	public AtividadeController(EstudanteController ec){
		this.ec = ec;
		subtiposExtensaoPesquisa = new ArrayList<>(Arrays.asList("PET", "PIBIC", "PIVIC", "PIBITI", "PIVITI", "PROBEX", "PDI"));
		subtiposRepresentacaoEstudantil = new ArrayList<>(Arrays.asList("COMISSAO", "DIRETORIA"));
	}
	
	private Estudante pegaEstudante(String cpf) {
		ec.verificaEstudante(cpf);
		return ec.pegaEstudante(cpf);
	}
	
	private void validaSenha(String cpf, String senha) {
		pegaEstudante(cpf).validaSenha(senha);
	}
	
	private String criaCodigo(String cpf) {
		return cpf + "_" + pegaEstudante(cpf).getAtividadesCount();
	}
	
	public String criaAtividadeMonitoriaEmEstudante(String cpf, String senha, String tipo, int unidadeAcumulada, String disciplina) throws IllegalArgumentException{
		if(unidadeAcumulada < 1) {
			throw new IllegalArgumentException("Número de semestres inválido.");
		}
		validaSenha(cpf, senha);
		String codigo = criaCodigo(cpf);
		Atividade monitoria = new Monitoria(tipo, codigo, unidadeAcumulada, disciplina);
		pegaEstudante(cpf).setCreditos(monitoria.getCreditos());
		pegaEstudante(cpf).adicionaAtividade(criaCodigo(cpf), monitoria);
		return codigo;
	}
	
	public boolean alteraDescricaoAtividade(String cpf, String senha, String codigoAtividade, String descricao) {
		validaSenha(cpf, senha);
		pegaEstudante(cpf).pegaAtividade(codigoAtividade).setDescricao(descricao);
		return true;
	}
	
	public boolean alteraComprovacaoAtividade(String cpf, String senha, String codigoAtividade, String linkComprovacao) {
		validaSenha(cpf, senha);
		pegaEstudante(cpf).pegaAtividade(codigoAtividade).setComprovacao(linkComprovacao);
		return true;
	}
	

	public String criaAtividadePesquisaExtensaoEmEstudante(String cpf, String senha, String tipo, int unidadeAcumulada, String subtipo) {
		validaSenha(cpf, senha);
		if(!subtiposExtensaoPesquisa.contains(subtipo.toUpperCase())) {
			return "subtipo de pesquisa e extensão inválido";
		}
		String codigo = criaCodigo(cpf);
		Atividade pesquisaExtensao = new PesquisaExtensao(tipo, codigo, unidadeAcumulada, subtipo);
		pegaEstudante(cpf).setCreditos(pesquisaExtensao.getCreditos());
		pegaEstudante(cpf).adicionaAtividade(criaCodigo(cpf), pesquisaExtensao);
		return codigo;
	}
	
	public String criaAtividadeEstagioEmEstudante(String cpf, String senha, String tipo, int unidadeAcumulada, String nomeEmpresa) {
		validaSenha(cpf, senha);
		String codigo = criaCodigo(cpf);
		Atividade estagio = new Estagio(tipo, codigo, unidadeAcumulada, nomeEmpresa);
		pegaEstudante(cpf).setCreditos(estagio.getCreditos());
		pegaEstudante(cpf).adicionaAtividade(criaCodigo(cpf), estagio);
		return codigo;
	}
	
	public String criaAtividadeRepresentacaoEstudantilEmEstudante(String tipo, String cpf, String senha, int unidadeAcumulada, String subtipo) {
		validaSenha(cpf, senha);
		if(!subtiposRepresentacaoEstudantil.contains(subtipo.toUpperCase())) {
			return "subtipo de representação estudantil inválido";
		}
		String codigo = criaCodigo(cpf);
		Atividade representacaoEstudantil = new RepresentacaoEstudantil(tipo, codigo, unidadeAcumulada, subtipo);
		representacaoEstudantil.adicionaCreditos();
		pegaEstudante(cpf).setCreditos(representacaoEstudantil.getCreditos());
		pegaEstudante(cpf).adicionaAtividade(criaCodigo(cpf), representacaoEstudantil);
		return codigo;
	}
	
	public int creditosAtividade(String cpf, String senha, String tipo) {
		validaSenha(cpf, senha);
		return 0;
	}
	
	public String geraMapaCreditosAtividades(String cpf, String senha) {
		validaSenha(cpf, senha);
		return "";
	}
	
	public boolean verificaMetaAlcancada(String cpf, String senha) {
		validaSenha(cpf, senha);
		if(pegaEstudante(cpf).getCreditos() == 22) {
			return true;
		}
		return false;
	}

}
