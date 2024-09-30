package complementacao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividades.AtividadeController;
import estudante.EstudanteController;

class TestesAtividades2 {

	private EstudanteController ec;
	private AtividadeController ac;

	@BeforeEach
	public void setUp() {
        ec = new EstudanteController();
        ac = new AtividadeController(ec);

        ec.criaEstudante("Emmily", "555555555-00", "senha123", "20210001");
        ec.criaEstudante("Luis", "777777777-00", "garfield444", "20210002");        
        ac.criaAtividadeMonitoriaEmEstudante("777777777-00", "garfield444", "Monitoria", 2, "LP2");
    }

    @Test
    public void testcriaAtividadeEstagioEmEstudante() {
    	assertEquals(ac.criaAtividadeEstagioEmEstudante("555555555-00", "senha123", "Estagio", 500, "Cagepa"), "555555555-00_1");
    	assertEquals(ac.criaAtividadeEstagioEmEstudante("555555555-00", "senha123", "Estagio", 500, "Nubank"), "555555555-00_2");
    }
    
    @Test
    public void testErrorCriaAtividadeEstagioEmEstudante() {
    	// unidade acumulada menor que 300
    	assertEquals(ac.criaAtividadeEstagioEmEstudante("555555555-00", "senha123", "Estagio", 200, "Cagepa"), "ATIVIDADE NÃO CADASTRADA");
  	
    }
    
    @Test
    public void testcriaAtividadeMonitoriaEmEstudante() {
    	assertEquals(ac.criaAtividadeMonitoriaEmEstudante("555555555-00", "senha123", "monitoria", 30, "LP2"), "555555555-00_1");
    }
    
    @Test
    public void testErrorCriaAtividadeMonitoriaEmEstudante() {
    	// unidade acumulada menor que 1
    	assertEquals(ac.criaAtividadeMonitoriaEmEstudante("555555555-00", "senha123", "monitoria", 0, "LP2"), "ATIVIDADE NÃO CADASTRADA");
    }
    
    @Test
    public void testcriaAtividadeRepresentacaoEstudantilEmEstudante() {
    	assertEquals(ac.criaAtividadeRepresentacaoEstudantilEmEstudante("777777777-00", "garfield444", "representacao_estudantil", 20, "comissao"),
    			"777777777-00_2");
    }
    
    @Test
    public void testErrorCriaAtividadeRepresentacaoEstudantilEmEstudante() {
    	assertEquals(ac.criaAtividadeRepresentacaoEstudantilEmEstudante("777777777-00", "garfield444", "representacao_estudantil", 20, "lalal"),
    			"ATIVIDADE NÃO CADASTRADA");
    }
    
    @Test
    public void testcriaAtividadeExtensaoEmEstudante() {
    	assertEquals(ac.criaAtividadePesquisaExtensaoEmEstudante("777777777-00", "garfield444", "pesquisa_extensao", 40, "pet"),
    			"777777777-00_2");
    }
    
    @Test
    public void testErrorCriaAtividadeExtensaoEmEstudante() {
    	assertEquals(ac.criaAtividadePesquisaExtensaoEmEstudante("777777777-00", "garfield444", "pesquisa_extensao", 600, "fjlkf"),
    			"ATIVIDADE NÃO CADASTRADA");
    }
    
    @Test
    public void testAlteraComprovacaoAtividade() {
    	assertTrue(ec.alteraComprovacaoAtividade("777777777-00", "garfield444", "777777777-00_1", "https://canvas.instructure."
    			+ "com/courses/9741408/grades"));
    	String comprovacao = ec.pegaEstudante("777777777-00").pegaAtividade("777777777-00_1").getLink();
    	assertEquals(comprovacao, "https://canvas.instructure.com/courses/9741408/grades");
    }
    
    @Test
    public void testAlteraComprovacaoAtividadeInexistente() {
    	assertFalse(ec.alteraComprovacaoAtividade("777777777-00", "garfield444", "777777777-00_6", "https://canvas.instructure.com/courses/9741408/grades"));
    }
    
    @Test
    public void testAlteraDescricaoAtividade() {
    	assertTrue(ec.alteraDescricaoAtividade("777777777-00", "garfield444", "777777777-00_1", "monitoria de lp2"));
    	String descricao = ec.pegaEstudante("777777777-00").pegaAtividade("777777777-00_1").getDescricao();
    	assertEquals(descricao, "monitoria de lp2");
    }
    
    @Test
    public void testAlteraDescricaoAtividadeInexistente() {
    	assertFalse(ec.alteraDescricaoAtividade("777777777-00", "garfield444", "777777777-00_6", "monitoria de lp2"));
    }
    
    @Test
    public void testCalculaCreditosAtvNaoCadastrada() {	
    	assertEquals(ec.creditosAtividade("777777777-00", "garfield444", "PESQUISA_EXTENSAO"), 0);
    }
    
    @Test
    public void testCalculaCreditosMonitoria() {
    	assertEquals(ec.creditosAtividade("777777777-00", "garfield444", "MONITORIA"), 8);
    	ac.criaAtividadeMonitoriaEmEstudante("777777777-00", "garfield444", "monitoria", 3, "LP2");
    	assertEquals(ec.creditosAtividade("777777777-00", "garfield444", "MONITORIA"), 16);
    }
    
    @Test
    public void testCalculaCreditosEstagio() {
    	ac.criaAtividadeEstagioEmEstudante("555555555-00", "senha123", "Estagio", 5000, "Cagepa");
    	assertEquals(ec.creditosAtividade("555555555-00", "senha123", "estagio"), 18);
    }
    
    
    @Test
    public void testCalculaCreditosPesquisaExtensao() {	
    	ac.criaAtividadePesquisaExtensaoEmEstudante("777777777-00", "garfield444", "PESQUISA_EXTENSAO", 5, "pibic");
    	assertEquals(ec.creditosAtividade("777777777-00", "garfield444", "PESQUISA_EXTENSAO"), 4);
    }
    
    @Test
    public void testCalculaCreditosPesquisaExtensaoTruncado() {	
    	ac.criaAtividadePesquisaExtensaoEmEstudante("777777777-00", "garfield444", "PESQUISA_EXTENSAO", 50, "pibic");
    	assertEquals(ec.creditosAtividade("777777777-00", "garfield444", "PESQUISA_EXTENSAO"), 18);
    }
    
    @Test
    public void testCalculaCreditosRepresentacaoEstudantil() {
    	ac.criaAtividadeRepresentacaoEstudantilEmEstudante("777777777-00", "garfield444", "REPRESENTACAO_ESTUDANTIL", 5, "COMISSAO");
    	assertEquals(ec.creditosAtividade("777777777-00", "garfield444", "REPRESENTACAO_ESTUDANTIL"), 2);
    }
    
    @Test
    public void testGeraMapaCreditosAtividades() {
    	ac.criaAtividadeEstagioEmEstudante("777777777-00", "garfield444", "Estagio", 600, "cagepa");
    	ac.criaAtividadePesquisaExtensaoEmEstudante("777777777-00", "garfield444", "PESQUISA_EXTENSAO", 20, "pibic");
    	ac.criaAtividadeRepresentacaoEstudantilEmEstudante("777777777-00", "garfield444", "REPRESENTACAO_ESTUDANTIL", 2, "COMISSAO");
    	assertEquals(ec.geraMapaCreditosAtividades("777777777-00", "garfield444"), "CRÉDITOS POR ATIVIDADE\n"
    			+ "ESTAGIO: 10\n"
    			+ "MONITORIA: 8\n"
    			+ "PESQUISA_EXTENSAO: 16\n"
    			+ "REPRESENTACAO_ESTUDANTIL: 2\n");	
    }
    
    @Test
    public void testVerificaMetaAlcancada() {
    	ac.criaAtividadePesquisaExtensaoEmEstudante("555555555-00", "senha123", "PESQUISA_EXTENSAO", 50, "pibic");
    	assertFalse(ec.verificaMetaAlcancada("555555555-00", "senha123"));
    	// o total é 18
    	ac.criaAtividadeEstagioEmEstudante("555555555-00", "senha123", "Estagio", 600, "cagepa");
    	// o total é 28 ( maior que 22)
    	assertTrue(ec.verificaMetaAlcancada("555555555-00", "senha123"));
    }
    
    @Test
    public void testVerificaMetaNaoAlcancada() {
    	ac.criaAtividadePesquisaExtensaoEmEstudante("555555555-00", "senha123", "PESQUISA_EXTENSAO", 50, "pibic");
    	assertFalse(ec.verificaMetaAlcancada("555555555-00", "senha123"));
    }
}
