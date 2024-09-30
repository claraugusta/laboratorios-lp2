package complementacao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividades.AtividadeController;
import estudante.EstudanteController;

class TestesRelatorio {
	
	private EstudanteController ec;
	private AtividadeController ac;

	@BeforeEach
	void setUp() {
        ec = new EstudanteController();
        ac = new AtividadeController(ec);

        ec.criaEstudante("Clara", "123", "senha123", "20210001");
        ec.criaEstudante("Luis", "124", "senha123", "20210002");      
        ac.criaAtividadeMonitoriaEmEstudante("123", "senha123", "Monitoria", 2, "P2");
    }
	
	@Test
	void testGeraRelatorioFinalSenhaInvalida() {
		ac.criaAtividadeEstagioEmEstudante("123", "senha123", "Estagio", 600, "cagepa");
    	ac.criaAtividadePesquisaExtensaoEmEstudante("123", "senha123", "PESQUISA_EXTENSAO", 6, "PIBIC");
    	try{
    		ec.gerarRelatorioFinal("123", "qwert5444");
    	} catch(IllegalArgumentException iae) {
    		assertEquals(iae.getMessage(), "SENHA INVÁLIDA!");
    	}
	}
	
	@Test
    void relatorioFinalTipoSenhaInvalida() {
    	ac.criaAtividadeEstagioEmEstudante("123", "senha123", "Estagio", 600, "cagepa");
    	ac.criaAtividadePesquisaExtensaoEmEstudante("123", "senha123", "PESQUISA_EXTENSAO", 6, "PIBIC");
    	try {
    		ec.gerarRelatorioFinalPorAtividade("123", "qwert555", "MONITORIA");
    	} catch(IllegalArgumentException iae) {
    		assertEquals(iae.getMessage(), "SENHA INVÁLIDA!");
    	}
    }
	
	@Test
    void relatorioParcialSenhaInvalida() {
		try {
			ec.gerarRelatorioParcial("124", "qwerty555", true);
		} catch(IllegalArgumentException iae) {
			assertEquals(iae.getMessage(), "SENHA INVÁLIDA!");
		}
    	
    }
	
	 @Test
	    void relatorioParcialTipoSenhaInvalida() {
	    	ac.criaAtividadeMonitoriaEmEstudante("124", "senha123", "Monitoria", 2, "LP2");
	    	try{
	    		ec.gerarRelatorioParcialPorAtividade("124", "qwerty555", false, "MONITORIA");
	    	} catch(IllegalArgumentException iae) {
	    		assertEquals(iae.getMessage(), "SENHA INVÁLIDA!");
	    	}
	    }
	 
	 @Test
		void testGerarRelatorioFinalImpossivel() {
	    	assertEquals(ec.gerarRelatorioFinal("123", "senha123"), "IMPOSSÍVEL GERAR RELATÓRIO");
		}
	 
	 @Test
		void testGerarRelatorioFinalTipoImpossivel() {
		 try {
			 ec.gerarRelatorioFinalPorAtividade("123", "senha123", "monitoria");
		 } catch(NoSuchElementException nse) {
			 assertEquals(nse.getMessage(), "O ESTUDANTE NÃO POSSUI ATIVIDADES DO TIPO MONITORIA");
		 }
		}
	 
	 @Test
	 void testGerarRelatorioFinal() {
    	ac.criaAtividadeEstagioEmEstudante("123", "senha123", "Estagio", 600, "cagepa");
    	ac.criaAtividadePesquisaExtensaoEmEstudante("123", "senha123", "PESQUISA_EXTENSAO", 6, "PIBIC");
    	assertEquals(ec.gerarRelatorioFinal("123", "senha123"), "NOME: Clara\n"
    			+ "CPF: 123\n"
    			+ "MATRÍCULA: 20210001\n"
    			+ "ESTAGIO: 10 / 18\n"
    			+ "MONITORIA: 8 / 16\n"
    			+ "REPRESENTACAO ESTUDANTIL: 0 / 2\n"
    			+ "PESQUISA DE EXTENSAO: 5 / 18\n"
    			+ "TOTAL ACUMULADO: 23 / 22");
	 }
	
	 @Test
	 void relatorioFinalTipo() {
    	ac.criaAtividadeEstagioEmEstudante("123", "senha123", "Estagio", 600, "cagepa");
    	ac.criaAtividadePesquisaExtensaoEmEstudante("123", "senha123", "PESQUISA_EXTENSAO", 6, "PIBIC");
    	assertEquals(ec.gerarRelatorioFinalPorAtividade("123", "senha123", "MONITORIA"), "NOME: Clara\n"
    			+ "CPF: 123\n"
    			+ "MATRÍCULA: 20210001\n"
    			+ "MONITORIA: 8 / 16\n"
    			+ "TOTAL ACUMULADO: 23 / 22");
	 }
    
	 @Test
	 void relatorioParcial() {
    	assertEquals(ec.gerarRelatorioParcial("124", "senha123",true), "RELATORIO PARCIAL\n"
    			+ "NOME: Luis\n"
    			+ "CPF: 124\n"
    			+ "MATRÍCULA: 20210002\n"
    			+ "ESTAGIO: 0 / 18\n"
    			+ "MONITORIA: 0 / 16\n"
    			+ "REPRESENTACAO ESTUDANTIL: 0 / 2\n"
    			+ "PESQUISA DE EXTENSAO: 0 / 18\n"
    			+ "TOTAL ACUMULADO: 0 / 22");
	 }
	 @Test
	 void relatorioParcialTipo() {
    	ac.criaAtividadeMonitoriaEmEstudante("124", "senha123", "Monitoria", 2, "LP2");
    	assertEquals(ec.gerarRelatorioParcialPorAtividade("124", "senha123", false, "MONITORIA"), "RELATORIO PARCIAL DE MONITORIA\n"
    			+ "NOME: Luis\n"
    			+ "CPF: 124\n"
    			+ "MATRÍCULA: 20210002\n"
    			+ "MONITORIA: 8 / 16\n"
    			+ "TOTAL ACUMULADO: 8 / 22");
	 }
    
	 @Test
	 void listarHistorico() {
		 // testar o metodo do controller diretamente quando você corrigir da erro por causa do localDate mudar todo dia, mas o de baixo prova que é válido 
    	
		ec.pegaEstudante("123").relatorioParcialTipo(LocalDate.of(2024, 9, 27), true, "MONITORIA");
    	ec.pegaEstudante("123").relatorioParcial(LocalDate.of(2024, 9, 29), true);
    	assertEquals(ec.listarHistorico("123", "senha123"), "2024-09-29\nRELATORIO PARCIAL\nNOME: Clara\nCPF: 123\nMATRÍCULA: 20210001\n"
    			+ "ESTAGIO: 0 / 18\nMONITORIA: 8 / 16\nREPRESENTACAO ESTUDANTIL: 0 / 2\nPESQUISA DE EXTENSAO: 0 / 18\nTOTAL ACUMULADO: 8 / 22\n"+
    			"2024-09-27\nRELATORIO PARCIAL DE MONITORIA\nNOME: Clara\nCPF: 123\n"
    			+ "MATRÍCULA: 20210001\nMONITORIA: 8 / 16\nTOTAL ACUMULADO: 8 / 22\n");
	 }
	 
	 @Test
	 void listarHistoricoVazio() {
		 assertEquals(ec.listarHistorico("123", "senha123"), "");
	 }
	 @Test
	 void removerItemHistorico() {
    	ec.gerarRelatorioParcial("123", "senha123",true);
    	ec.pegaEstudante("123").relatorioParcialTipo(LocalDate.of(2024, 9, 27), true, "MONITORIA");
    	ec.pegaEstudante("123").excluirItemHistorico(LocalDate.now().toString());
    	assertEquals(ec.listarHistorico("123", "senha123"), "2024-09-27\nRELATORIO PARCIAL DE MONITORIA\nNOME: Clara\nCPF: 123\n"
    			+ "MATRÍCULA: 20210001\nMONITORIA: 8 / 16\nTOTAL ACUMULADO: 8 / 22\n");
	 }
	
}
