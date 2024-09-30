package estudante;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.TreeMap;

import atividades.Atividade;


/**
 * Construtor da classe {@code Estudante}. Inicializa os atributos principais do estudante.
 * 
 * @param nome      O nome do estudante.
 * @param cpf       O CPF do estudante.
 * @param senha     A senha de acesso do estudante.
 * @param matricula A matrícula do estudante.
 * @throws NullPointerException     Se algum dos parâmetros for nulo.
 * @throws IllegalArgumentException Se algum dos parâmetros for vazio.
 * 
 * @author Maria Clara Augusta Santos - 123210361
 */
public class Estudante {
	
	private String nome;
	private String cpf;
	private String senha;
	private String matricula;
	private int bonificacao;
	private Map<String, Map<String, Atividade>> atividades;
	private Map<LocalDate, String> historico;
	private int atividadesCodigo;
	private int creditosTotal;
	private final Map<String, Integer> max;
	
	public Estudante(String nome, String cpf, String senha, String matricula) throws NullPointerException, IllegalArgumentException{
		if(nome == null) { 
            throw new NullPointerException("Nome Nulo");
        }
        if(nome.isEmpty()) { 
            throw new IllegalArgumentException("Nome Vazio");
        }
        if(senha == null) {
        	throw new NullPointerException("Senha Nula");
        }
        if(senha.isEmpty()) { 
            throw new IllegalArgumentException("Senha Vazia");
        }
        if(cpf == null) { 
            throw new NullPointerException("Cpf Nulo");
        }
        if(cpf.isEmpty()) { 
            throw new IllegalArgumentException("Cpf Vazio");
        }
        if(matricula == null) { 
            throw new NullPointerException("Matricula Nula");
        }
        if(matricula.isEmpty()) { 
            throw new IllegalArgumentException("Matricula Vazia");
        }
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.matricula = matricula;
		this.bonificacao = 0;
		this.atividadesCodigo = 1;
		this.creditosTotal = 0;
		this.historico = new HashMap<>();
		this.atividades = Map.of(
			    "ESTAGIO", new HashMap<>(),
			    "MONITORIA", new HashMap<>(),
			    "PESQUISA_EXTENSAO", new HashMap<>(),
			    "REPRESENTACAO_ESTUDANTIL", new HashMap<>()
			);
		this.max = Map.of(
			    "ESTAGIO", 18,
			    "MONITORIA", 16,
			    "PESQUISA_EXTENSAO", 18,
			    "REPRESENTACAO_ESTUDANTIL", 2
			);


	}
	
	public String getMatricula() {
		return matricula;
	}
	

    /**
     * Calcula o total de créditos para um tipo específico de atividade.
     * 
     * @param tipo O tipo de atividade (ex: "ESTAGIO", "MONITORIA").
     * @return O total de créditos acumulados para o tipo de atividade especificado.
     */
	public int calculaCreditoTipo(String tipo) {
		Map<String, Atividade> atvs = pegaMapaTipo(tipo);
		int creditos = 0;
		for(Atividade a : atvs.values()) {
			if(creditos == a.getMaxCreditos()) {
				break;
			}
			int creditoAtv = a.calculaCreditos(creditos);		
			creditos += creditoAtv;
		}
		return creditos;
	}
	
	public void setCreditoTotal(int credito) {
		creditosTotal += credito;
	}
	
	public int getCreditoTotal() {
		return creditosTotal;
	}
	
	/**
     * Gera um resumo dos créditos acumulados por tipo de atividade.
     * 
     * @return Uma string formatada com o resumo dos créditos por atividade.
     */
	public String geraMapaCreditos() {
		String out = String.format("CRÉDITOS POR ATIVIDADE\n"
    			+ "ESTAGIO: %d\n"
    			+ "MONITORIA: %d\n"
    			+ "PESQUISA_EXTENSAO: %d\n"
    			+ "REPRESENTACAO_ESTUDANTIL: %d\n", calculaCreditoTipo("ESTAGIO"), calculaCreditoTipo("MONITORIA"), 
    			calculaCreditoTipo("PESQUISA_EXTENSAO"), calculaCreditoTipo("REPRESENTACAO_ESTUDANTIL"));
		return out;
	}

	/**
     * Altera a senha do estudante.
     * 
     * @param novaSenha A nova senha do estudante.
     */
    public void alteraSenha(String novaSenha) {
        senha = novaSenha;
    }

    /**
     * Valida a senha informada com a senha do estudante.
     * 
     * @param senha A senha a ser validada.
     * @throws IllegalArgumentException Se a senha for inválida.
     */
    public void validaSenha(String senha) throws IllegalArgumentException {
        if (!this.senha.equals(senha)) {
            throw new IllegalArgumentException("SENHA INVÁLIDA!");
        }
    }

    /**
     * Adiciona um bônus à bonificação do estudante.
     * 
     * @param bonus A quantidade de bônus a ser adicionada.
     */
    public void adicionaBonus(int bonus) {
        this.bonificacao += bonus;
    }
	
	public int getBonificacao() {
		return bonificacao;
	}
	
	public int getAtividadesCount() {
		return atividadesCodigo;
	}
	
	/**
     * Gera um código único para a atividade do estudante.
     * 
     * @return O código único da atividade.
     */
    public String criaCodigo() {
        return cpf + "_" + atividadesCodigo++;
    }

    /**
     * Adiciona uma nova atividade ao mapa de atividades do estudante.
     * 
     * @param tipo     O tipo da atividade (ex: "ESTAGIO").
     * @param codigo   O código da atividade.
     * @param atividade A atividade a ser adicionada.
     */
    public void adicionaAtividade(String tipo, String codigo, Atividade atividade) {
        pegaMapaTipo(tipo).put(codigo, atividade);
    }

    /**
     * Retorna o mapa de atividades de um tipo específico.
     * 
     * @param tipo O tipo de atividade (ex: "ESTAGIO").
     * @return O mapa de atividades do tipo especificado.
     */
    public Map<String, Atividade> pegaMapaTipo(String tipo) {
        return atividades.get(tipo.toUpperCase());
    }

    /**
     * Recupera uma atividade específica pelo código.
     * 
     * @param codigo O código da atividade.
     * @return A atividade correspondente ao código ou {@code null} se não for encontrada.
     */
    public Atividade pegaAtividade(String codigo) {
        for (Map<String, Atividade> m : atividades.values()) {
            if (m.containsKey(codigo)) {
                return m.get(codigo);
            }
        }
        return null;
    }

    /**
     * Gera o relatório final do estudante com os créditos por atividade.
     * 
     * @return O relatório final ou uma mensagem indicando que o relatório não pode ser gerado.
     */
    public String relatorioFinal() {
        if (creditosTotal >= 22) {
            return String.format(
                "NOME: %s\nCPF: %s\nMATRÍCULA: %s\n" +
                "ESTAGIO: %d / 18\nMONITORIA: %d / 16\nREPRESENTACAO ESTUDANTIL: %d / 2\nPESQUISA DE EXTENSAO: %d / 18\n" +
                "TOTAL ACUMULADO: %d / 22",
                nome, cpf, matricula,
                calculaCreditoTipo("ESTAGIO"),
                calculaCreditoTipo("MONITORIA"),
                calculaCreditoTipo("REPRESENTACAO_ESTUDANTIL"),
                calculaCreditoTipo("PESQUISA_EXTENSAO"),
                creditosTotal
            );
        }
        return "IMPOSSÍVEL GERAR RELATÓRIO";
    }
    /**
     * Retorna o valor máximo de créditos permitido para um determinado tipo de atividade.
     * 
     * @param tipo O tipo de atividade (ex: "ESTAGIO").
     * @return O valor máximo de créditos permitido para o tipo especificado.
     */
    private int pegaMax(String tipo) {
        return max.get(tipo.toUpperCase());
    }

    /**
     * Gera o relatório final do estudante para um tipo específico de atividade.
     * 
     * @param tipo O tipo de atividade (ex: "ESTAGIO").
     * @return O relatório final do tipo de atividade especificado, ou uma mensagem indicando que o relatório não pode ser gerado.
     * @throws NoSuchElementException Se o tipo de atividade não for válido.
     */
    public String relatorioFinalTipo(String tipo) throws NoSuchElementException {
        int max = pegaMax(tipo);
        if (creditosTotal >= 22) {
            return String.format(
                "NOME: %s\nCPF: %s\nMATRÍCULA: %s\n%s: %d / %d\nTOTAL ACUMULADO: %d / 22",
                nome, cpf, matricula, tipo, calculaCreditoTipo(tipo), max, creditosTotal
            );
        }
        return "IMPOSSÍVEL GERAR RELATÓRIO";
    }

    /**
     * Gera um relatório parcial do estudante até uma data específica. 
     * Caso seja solicitado, o relatório também é salvo no histórico do estudante.
     * 
     * @param data   A data de geração do relatório.
     * @param salvar Indica se o relatório deve ser salvo no histórico.
     * @return O relatório parcial gerado.
     */
    public String relatorioParcial(LocalDate data, boolean salvar) {
        String relatorio = String.format(
            "RELATORIO PARCIAL\nNOME: %s\nCPF: %s\nMATRÍCULA: %s\n" +
            "ESTAGIO: %d / 18\nMONITORIA: %d / 16\nREPRESENTACAO ESTUDANTIL: %d / 2\nPESQUISA DE EXTENSAO: %d / 18\n" +
            "TOTAL ACUMULADO: %d / 22",
            nome, cpf, matricula,
            calculaCreditoTipo("ESTAGIO"),
            calculaCreditoTipo("MONITORIA"),
            calculaCreditoTipo("REPRESENTACAO_ESTUDANTIL"),
            calculaCreditoTipo("PESQUISA_EXTENSAO"),
            creditosTotal
        );
        if (salvar) {
            historico.put(data, relatorio);
        }
        return relatorio;
    }

    /**
     * Gera um relatório parcial para um tipo específico de atividade até uma data informada. 
     * Se solicitado, o relatório será salvo no histórico.
     * 
     * @param data   A data de geração do relatório.
     * @param salvar Indica se o relatório deve ser salvo no histórico.
     * @param tipo   O tipo de atividade (ex: "ESTAGIO").
     * @return O relatório parcial do tipo de atividade especificado.
     */
    public String relatorioParcialTipo(LocalDate data, boolean salvar, String tipo) {
        int max = pegaMax(tipo);
        String relatorio = String.format(
            "RELATORIO PARCIAL DE %s\nNOME: %s\nCPF: %s\nMATRÍCULA: %s\n%s: %d / %d\nTOTAL ACUMULADO: %d / 22",
            tipo, nome, cpf, matricula, tipo, calculaCreditoTipo(tipo), max, creditosTotal
        );
        if (salvar) {
            historico.put(data, relatorio);
        }
        return relatorio;
    }

    /**
     * Lista o histórico de relatórios salvos do estudante.
     * 
     * @return Uma string contendo todos os relatórios salvos no histórico, organizados por data.
     */
    public String listarHistorico() {
        StringBuilder out = new StringBuilder();
        for (Map.Entry<LocalDate, String> h : historico.entrySet()) {
            out.append(String.format("%s\n%s\n", h.getKey(), h.getValue()));
        }
        return out.toString();
    }

    /**
     * Exclui um relatório do histórico do estudante com base em uma data específica.
     * 
     * @param data A data do relatório a ser excluído (no formato "yyyy-MM-dd").
     * @return {@code true} se o relatório foi encontrado e excluído, {@code false} caso contrário.
     */
    public boolean excluirItemHistorico(String data) {
        LocalDate dataFormatada = LocalDate.parse(data);
        if (historico.containsKey(dataFormatada)) {
            historico.remove(dataFormatada);
            return true;
        }
        return false;
    }

	
	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudante other = (Estudante) obj;
		return Objects.equals(cpf, other.cpf);
	}
	public String exibeAluno() {
		return String.format("Nome: %s\nMatrícula: %s\n", nome, matricula);
	}
	public String toString() {
		return String.format("Nome: %s\nCPF: %s\nMatrícula: %s\nBonificação: %d\n", nome, cpf, matricula, bonificacao);
	}

	public String getNome() {
		return nome;
	}

}
