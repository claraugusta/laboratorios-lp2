package atividades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import estudante.EstudanteController;
/**
 * Classe responsável por gerenciar a criação de atividades para estudantes.
 * Possui métodos para cadastrar diferentes tipos de atividades, como monitoria,
 * pesquisa e estágio, além de representar atividades estudantis.
 * @author Maria Clara Augusta Santos
 */
public class AtividadeController {
    
    private EstudanteController ec; 
    private List<String> subtiposExtensaoPesquisa; 
    private List<String> subtiposRepresentacaoEstudantil;
    
    /**
     * Construtor da classe AtividadeController.
     * 
     * @param ec Instância do controlador de estudantes.
     */
    public AtividadeController(EstudanteController ec) {
        this.ec = ec;
        subtiposExtensaoPesquisa = new ArrayList<>(Arrays.asList("PET", "PIBIC", "PIVIC", "PIBITI", "PIVITI", "PROBEX", "PDI"));
        subtiposRepresentacaoEstudantil = new ArrayList<>(Arrays.asList("COMISSAO", "DIRETORIA"));
    }        

    /**
     * Cria uma atividade de monitoria para um estudante.
     * 
     * @param cpf CPF do estudante.
     * @param senha Senha do estudante.
     * @param tipo Tipo da atividade.
     * @param unidadeAcumulada Unidades acumuladas da atividade.
     * @param disciplina Disciplina associada à atividade de monitoria.
     * @return Código da atividade ou mensagem de erro.
     */
    public String criaAtividadeMonitoriaEmEstudante(String cpf, String senha, String tipo, int unidadeAcumulada, String disciplina) {
        if (unidadeAcumulada < 1) {
            return "ATIVIDADE NÃO CADASTRADA";
        }
        String codigo = ec.criaCodigo(cpf);
        Atividade monitoria = new Monitoria(tipo, codigo, unidadeAcumulada, disciplina);
        return ec.adicionaAtividadeEstudante(cpf, senha, tipo, codigo, monitoria);
    }

    /**
     * Cria uma atividade de pesquisa ou extensão para um estudante.
     * 
     * @param cpf CPF do estudante.
     * @param senha Senha do estudante.
     * @param tipo Tipo da atividade.
     * @param unidadeAcumulada Unidades acumuladas da atividade.
     * @param subtipo Subtipo da atividade de pesquisa ou extensão.
     * @return Código da atividade ou mensagem de erro.
     */
    public String criaAtividadePesquisaExtensaoEmEstudante(String cpf, String senha, String tipo, int unidadeAcumulada, String subtipo) {
        if (!subtiposExtensaoPesquisa.contains(subtipo.toUpperCase())) {
            return "ATIVIDADE NÃO CADASTRADA";
        }
        String codigo = ec.criaCodigo(cpf);
        Atividade pesquisaExtensao = new PesquisaExtensao(tipo, codigo, unidadeAcumulada, subtipo);
        return ec.adicionaAtividadeEstudante(cpf, senha, tipo, codigo, pesquisaExtensao);
    }

    /**
     * Cria uma atividade de estágio para um estudante.
     * 
     * @param cpf CPF do estudante.
     * @param senha Senha do estudante.
     * @param tipo Tipo da atividade.
     * @param unidadeAcumulada Unidades acumuladas da atividade.
     * @param nomeEmpresa Nome da empresa onde o estágio é realizado.
     * @return Código da atividade ou mensagem de erro.
     */
    public String criaAtividadeEstagioEmEstudante(String cpf, String senha, String tipo, int unidadeAcumulada, String nomeEmpresa) {
        if (unidadeAcumulada < 300) {
            return "ATIVIDADE NÃO CADASTRADA";
        }
        String codigo = ec.criaCodigo(cpf);
        Atividade estagio = new Estagio(tipo, codigo, unidadeAcumulada, nomeEmpresa);
        return ec.adicionaAtividadeEstudante(cpf, senha, tipo, codigo, estagio);
    }

    /**
     * Cria uma atividade de representação estudantil para um estudante.
     * 
     * @param cpf CPF do estudante.
     * @param senha Senha do estudante.
     * @param tipo Tipo da atividade.
     * @param unidadeAcumulada Unidades acumuladas da atividade.
     * @param subtipo Subtipo da atividade de representação estudantil.
     * @return Código da atividade ou mensagem de erro.
     */
    public String criaAtividadeRepresentacaoEstudantilEmEstudante(String cpf, String senha, String tipo, int unidadeAcumulada, String subtipo) {
        if (!subtiposRepresentacaoEstudantil.contains(subtipo.toUpperCase())) {
            return "ATIVIDADE NÃO CADASTRADA";
        }
        String codigo = ec.criaCodigo(cpf);
        Atividade representacaoEstudantil = new RepresentacaoEstudantil(tipo, codigo, unidadeAcumulada, subtipo);
        return ec.adicionaAtividadeEstudante(cpf, senha, tipo, codigo, representacaoEstudantil);
    }
}