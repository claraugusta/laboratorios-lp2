package estudante;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import atividades.Atividade;

/**
 * A classe EstudanteController gerencia as operações relacionadas aos estudantes
 * no sistema ComplementACAO. Ela é responsável por criar estudantes, validar suas 
 * credenciais, manipular suas atividades complementares e gerar relatórios e históricos.
 * 
 * Funcionalidades principais:
 * <ul>
 *     <li>Adicionar, listar e alterar informações dos estudantes.</li>
 *     <li>Gerenciar atividades complementares dos estudantes.</li>
 *     <li>Gerar relatórios parciais e finais com base nas atividades realizadas.</li>
 * </ul>
 * 
 * Esta classe faz uso da classe {@link Estudante} para representar os estudantes
 * e da classe {@link Atividade} para as atividades complementares.
 * 
 *  @author Maria Clara Augusta Santos - 123210361
 */
public class EstudanteController {
	
	private Map<String, Estudante> estudantesMap;

	/**
	 * Construtor da classe EstudanteController.
	 * Inicializa o mapa de estudantes.
	 */
	public EstudanteController() {
		estudantesMap = new HashMap<>();
	}
	
	/**
	 * Cria um novo estudante no sistema.
	 * 
	 * @param nome O nome do estudante.
	 * @param cpf O CPF do estudante.
	 * @param senha A senha do estudante (mínimo 8 caracteres).
	 * @param matricula A matrícula do estudante.
	 * @return true se o estudante for criado com sucesso, false caso contrário.
	 */
	public boolean criaEstudante(String nome, String cpf, String senha, String matricula) {
		if(estudantesMap.containsKey(cpf) || senha.length() < 8) {
			return false;
		}
		Estudante estudante = new Estudante(nome, cpf, senha, matricula);
		estudantesMap.put(cpf, estudante);
		return true;
	}
	
	/**
	 * Verifica se o estudante existe no sistema.
	 * 
	 * @param cpf O CPF do estudante.
	 * @throws NullPointerException se o estudante não existir.
	 */
	private void verificaEstudante(String cpf) throws NullPointerException {
		if(!estudantesMap.containsKey(cpf)) {
			throw new NullPointerException("ESTUDANTE NÃO EXISTE");
		}
	}
	
	/**
	 * Retorna o estudante associado ao CPF.
	 * 
	 * @param cpf O CPF do estudante.
	 * @return O objeto Estudante.
	 */
	public Estudante pegaEstudante(String cpf) {
		verificaEstudante(cpf);
		return estudantesMap.get(cpf);
	}
	
	/**
	 * Valida a senha de um estudante.
	 * 
	 * @param cpf O CPF do estudante.
	 * @param senha A senha do estudante.
	 */
	public void validaSenha(String cpf, String senha) {
		pegaEstudante(cpf).validaSenha(senha);
	}
	
	/**
	 * Altera a senha de um estudante.
	 * 
	 * @param cpf O CPF do estudante.
	 * @param senhaAntiga A senha antiga do estudante.
	 * @param novaSenha A nova senha do estudante (mínimo 8 caracteres).
	 * @return true se a senha for alterada com sucesso, false caso contrário.
	 * @throws NullPointerException se a nova senha for nula.
	 */
	public boolean alteraSenhaEstudante(String cpf, String senhaAntiga, String novaSenha) throws NullPointerException {
		if(novaSenha == null) {
			throw new NullPointerException("SENHA NULA");
		}
		pegaEstudante(cpf).validaSenha(senhaAntiga);
		if(novaSenha.length() < 8 || novaSenha.equals(senhaAntiga)) {
			return false;
		}
		pegaEstudante(cpf).alteraSenha(novaSenha);
		return true;
	}
	
	/**
	 * Lista todos os estudantes do sistema.
	 * 
	 * @return Um array de strings contendo a exibição dos estudantes.
	 */
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
	
	/**
	 * Lista os estudantes ordenados por pontos de colaboração.
	 * 
	 * @return Um array de strings com o ranking dos estudantes.
	 */
	public String[] listarUsuariosRankingDicas() {
		List<Estudante> UsuariosList = new ArrayList<>(estudantesMap.values());
		String[] ranking = new String[UsuariosList.size()];
		int prox = 0;
		UsuariosList.sort(new ComparadorBonus());
		for(Estudante e : UsuariosList) {
			ranking[prox++] = e.toString();
		}
		return ranking;
	}
	
	/**
	 * Cria um código de atividade para um estudante.
	 * 
	 * @param cpf O CPF do estudante.
	 * @return O código gerado.
	 */
	public String criaCodigo(String cpf) {
		return pegaEstudante(cpf).criaCodigo();
	}
	
	/**
	 * Adiciona uma atividade a um estudante.
	 * 
	 * @param cpf O CPF do estudante.
	 * @param senha A senha do estudante.
	 * @param tipo O tipo da atividade.
	 * @param codigo O código da atividade.
	 * @param atividade A atividade a ser adicionada.
	 * @return O código da atividade.
	 */
	public String adicionaAtividadeEstudante(String cpf, String senha, String tipo, String codigo, Atividade atividade) {
		validaSenha(cpf, senha);
		pegaEstudante(cpf).setCreditoTotal(atividade.calculaCreditos(0));
		pegaEstudante(cpf).adicionaAtividade(tipo, codigo, atividade);
		return codigo;
	}
	
	/**
	 * Altera a descrição de uma atividade.
	 * 
	 * @param cpf O CPF do estudante.
	 * @param senha A senha do estudante.
	 * @param codigoAtividade O código da atividade.
	 * @param descricao A nova descrição da atividade.
	 * @return true se a descrição for alterada com sucesso, false caso contrário.
	 */
	public boolean alteraDescricaoAtividade(String cpf, String senha, String codigoAtividade, String descricao) {
		validaSenha(cpf, senha);
		Atividade atv = pegaEstudante(cpf).pegaAtividade(codigoAtividade);
		if(atv == null) {
			return false;
		}
		atv.setDescricao(descricao);
		return true;
	}
	
	/**
     * Altera o link de comprovação de uma atividade.
     * 
     * @param cpf O CPF do estudante.
     * @param senha A senha do estudante.
     * @param codigoAtividade O código da atividade.
     * @param linkComprovacao O novo link de comprovação.
     * @return true se o link for alterado com sucesso, false caso contrário.
     */
    public boolean alteraComprovacaoAtividade(String cpf, String senha, String codigoAtividade, String linkComprovacao) {
        validaSenha(cpf, senha);
        Atividade atv = pegaEstudante(cpf).pegaAtividade(codigoAtividade);
        if (atv == null) {
            return false;
        }
        atv.setComprovacao(linkComprovacao);
        return true;
    }
    
    /**
     * Calcula os créditos de um determinado tipo de atividade.
     * 
     * @param cpf O CPF do estudante.
     * @param senha A senha do estudante.
     * @param tipo O tipo de atividade.
     * @return O número de créditos calculados.
     */
    public int creditosAtividade(String cpf, String senha, String tipo) {
        validaSenha(cpf, senha);
        return pegaEstudante(cpf).calculaCreditoTipo(tipo);
    }
    
    /**
     * Gera um mapa de créditos de atividades realizadas por um estudante.
     * 
     * @param cpf O CPF do estudante.
     * @param senha A senha do estudante.
     * @return Uma string com o mapa de créditos.
     */
    public String geraMapaCreditosAtividades(String cpf, String senha) {
        validaSenha(cpf, senha);
        return pegaEstudante(cpf).geraMapaCreditos();
    }
    
    /**
     * Verifica se um estudante atingiu a meta de créditos.
     * 
     * @param cpf O CPF do estudante.
     * @param senha A senha do estudante.
     * @return true se a meta foi alcançada, false caso contrário.
     */
    public boolean verificaMetaAlcancada(String cpf, String senha) {
        validaSenha(cpf, senha);
        return (pegaEstudante(cpf).getCreditoTotal() >= 22);
    }
    
    /**
     * Gera o relatório final de atividades de um estudante.
     * 
     * @param cpf O CPF do estudante.
     * @param senha A senha do estudante.
     * @return Uma string contendo o relatório final.
     */
    public String gerarRelatorioFinal(String cpf, String senha) {
        validaSenha(cpf, senha);
        return pegaEstudante(cpf).relatorioFinal();
    }
    
    /**
     * Gera o relatório final de atividades de um determinado tipo.
     * 
     * @param cpf O CPF do estudante.
     * @param senha A senha do estudante.
     * @param tipoAtividade O tipo de atividade.
     * @return Uma string contendo o relatório final para o tipo de atividade.
     */
    public String gerarRelatorioFinalPorAtividade(String cpf, String senha, String tipoAtividade) {
        validaSenha(cpf, senha);
        return pegaEstudante(cpf).relatorioFinalTipo(tipoAtividade);
    }
    
    /**
     * Gera um relatório parcial de atividades realizadas por um estudante.
     * 
     * @param cpf O CPF do estudante.
     * @param senha A senha do estudante.
     * @param salvar Indica se o relatório deve ser salvo.
     * @return Uma string com o relatório parcial.
     */
    public String gerarRelatorioParcial(String cpf, String senha, boolean salvar) {
        validaSenha(cpf, senha);
        LocalDate data = LocalDate.now();
        return pegaEstudante(cpf).relatorioParcial(data, salvar);
    }
    
    /**
     * Gera um relatório parcial de um tipo específico de atividade.
     * 
     * @param cpf O CPF do estudante.
     * @param senha A senha do estudante.
     * @param salvar Indica se o relatório deve ser salvo.
     * @param tipoAtividade O tipo de atividade para o qual o relatório será gerado.
     * @return Uma string com o relatório parcial para o tipo de atividade.
     */
    public String gerarRelatorioParcialPorAtividade(String cpf, String senha, boolean salvar, String tipoAtividade) {
        validaSenha(cpf, senha);
        LocalDate data = LocalDate.now();
        return pegaEstudante(cpf).relatorioParcialTipo(data, salvar, tipoAtividade);
    }
    
    /**
     * Lista o histórico de atividades realizadas por um estudante.
     * 
     * @param cpf O CPF do estudante.
     * @param senha A senha do estudante.
     * @return Uma string contendo o histórico de atividades.
     */
    public String listarHistorico(String cpf, String senha) {
        validaSenha(cpf, senha);
        return pegaEstudante(cpf).listarHistorico();
    }
    
    /**
     * Exclui um item do histórico de atividades de um estudante.
     * 
     * @param cpf O CPF do estudante.
     * @param senha A senha do estudante.
     * @param data A data da atividade a ser excluída.
     * @return true se o item for excluído com sucesso, false caso contrário.
     */
    public boolean excluirItemHistorico(String cpf, String senha, String data) {
        validaSenha(cpf, senha);
        return pegaEstudante(cpf).excluirItemHistorico(data);
    }


}
