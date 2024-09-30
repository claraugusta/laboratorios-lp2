package complementacao;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividades.AtividadeController;
import dica.DicaController;
import estudante.Estudante;
import estudante.EstudanteController;

class TestesDicas {

	private EstudanteController ec;
	private DicaController dc;

	@BeforeEach
	public void setUp() {
        ec = new EstudanteController();
        dc = new DicaController(ec);

        ec.criaEstudante("Emmily", "555555555-00", "senha123", "20210001");
        ec.criaEstudante("Luis", "777777777-00", "garfield444", "123210579");        
        dc.adicionaDica( "777777777-00", "garfield444", "Tema da Dica 1");
        
    }
	
	//verificar adição de elementos na dica 1, dos três tipos: texto, multimídia, referência. 
	//Também elementos de texto e multimídia na dica 2. Na dica 3, apenas um elemento de texto
	@Test
	public void testAdicaoElementos() {

        ec.criaEstudante("Eduardo", "333333333-00", "vaquejada","123210771");
        dc.adicionaDica("333333333-00", "vaquejada", "dica2");
        dc.adicionaDica("333333333-00", "vaquejada", "dica3");
        
		assertTrue(dc.adicionaElementoMultimidiaDica("777777777-00", "garfield444", 1, "link.com.br", "cabecalho", 120));
		assertTrue(dc.adicionaElementoTextoDica("777777777-00", "garfield444", 1, "texto lalalalalallala"));
		assertTrue(dc.adicionaElementoReferenciaDica("777777777-00", "garfield444", 1, "Regulamento estágio UASC", "Res 01/2020", 2024, 5, true));
		
		assertTrue(dc.adicionaElementoTextoDica("333333333-00", "vaquejada", 2, "texto lalalalalallala"));
		assertTrue(dc.adicionaElementoMultimidiaDica("333333333-00", "vaquejada", 1, "link.com.br", "cabecalho", 120));
		
		assertTrue(dc.adicionaElementoTextoDica("333333333-00", "vaquejada", 3, "texto lalalalalajdflkjfsllala"));
		
		// ranking
		String[] esperado = {"Nome: Luis\nCPF: 777777777-00\nMatrícula: 123210579\nBonificação: 37\n",
		    	"Nome: Eduardo\nCPF: 333333333-00\nMatrícula: 123210771\nBonificação: 4\n",
		    	"Nome: Emmily\nCPF: 555555555-00\nMatrícula: 20210001\nBonificação: 0\n"};

		assertArrayEquals(esperado, ec.listarUsuariosRankingDicas());
		
		// lista dica1 sem detalhes
		assertEquals(dc.listaDicaPosicao(1), "Luis - Tema da Dica 1\ncabecalho, link.com.br\ntexto lalalalalallala\n"
				+ "Referência: Regulamento estágio UASC, Res 01/2020, ano: 2024.cabecalho, link.com.br\n");
		
		// lista com detalhes
		assertEquals(dc.listaDicaDetalhadaPosicao(1),"Luis - Tema da Dica 1\ncabecalho, link.com.br, 120s. Valor = 10\ntexto lalalalalallala (21 caracteres). Valor = 2"
				+ "\nReferência: Regulamento estágio UASC, Res 01/2020, ano: 2024, importância: 5. Valor = 15\ncabecalho, link.com.br, 120s. Valor = 10\n");
		
		

	}
	
	@Test
	public void testExibeEstudante() {
		Estudante a = ec.pegaEstudante("777777777-00");
		assertEquals(a.toString(), "Nome: Luis\nCPF: 777777777-00\nMatrícula: 123210579\nBonificação: 0\n");
	}

    @Test
    public void testAdicionarDica() {
        int posicao = dc.adicionaDica("555555555-00", "senha123", "Tema da Dica");
        assertEquals(1, posicao, "A posição da primeira dica deve ser 0");
    }

    @Test
    public void testAdicionarElementoTextoDica() {
        boolean resultado = dc.adicionaElementoTextoDica("777777777-00", "garfield444", 1, "Novo texto na dica"); 
        assertTrue(resultado);
        assertEquals(1, ec.pegaEstudante("777777777-00").getBonificacao());
    }

    @Test
    public void testAdicionarElementoMultimidiaDica() {
        boolean resultado = dc.adicionaElementoMultimidiaDica("777777777-00", "garfield444", 1, "www.link.com", "Cabeçalho", 120);   
        assertTrue(resultado);
        assertEquals(ec.pegaEstudante("777777777-00").getBonificacao(), 10);
    }
    
    public void testAdicionarElementoReferenciaDica() {
    	dc.adicionaElementoReferenciaDica("777777777-00", "garfield444", 1, "Regulamento estágio UASC", "Res 01/2020", 2024, 5, true);
    	assertEquals(ec.pegaEstudante("777777777-00").getBonificacao(), 15);
    }
    
   

    @Test
	// lista as informações de todas as dicas do sistema
	public void testListarDicas() {
	      dc.adicionaDica("777777777-00", "garfield444", "Tema da Dica 2");
	      dc.adicionaElementoMultimidiaDica("777777777-00", "garfield444", 1, "www.link.com", "Cabeçalho", 120);
	      String[] dicasListadas = dc.listaDicas();
	      assertEquals(dicasListadas[0],"Luis - Tema da Dica 1\nCabeçalho, www.link.com\n");
	      assertEquals(dicasListadas[1],"Luis - Tema da Dica 2\n");
	  }
	   
	  @Test
	  // lista as informações de uma dica especificada
	  public void testListarDicaEspecifica() {
	      dc.adicionaDica("777777777-00", "garfield444", "Tema da Dica 2");
	      dc.adicionaElementoMultimidiaDica("777777777-00", "garfield444", 1, "www.link.com", "Cabeçalho", 120);
	      dc.adicionaElementoReferenciaDica("777777777-00", "garfield444", 2, "Regulamento estágio UASC", "Res 01/2020", 2024, 5, true);
	      dc.adicionaElementoTextoDica("777777777-00", "garfield444", 1, "Novo texto na dica"); 
	      String dica = dc.listaDicaPosicao(1);
	      String dica2 = dc.listaDicaPosicao(2);
	      assertEquals(dica,"Luis - Tema da Dica 1\nCabeçalho, www.link.com\nNovo texto na dica\n");
	      assertEquals(dica2,"Luis - Tema da Dica 2\nReferência: Regulamento estágio UASC, Res 01/2020, ano: 2024.");
	  }
	  
	  @Test
	  // lista as informacoes detalhadas de todas as dicas do sistema
	  public void testeListarDicasDetalhada() {
	      dc.adicionaDica("555555555-00", "senha123", "Tema da Dica 2");
	      dc.adicionaElementoReferenciaDica("555555555-00", "senha123", 2, "Regulamento estágio UASC", "Res 01/2020", 2024, 5, true);
	      dc.adicionaElementoMultimidiaDica("777777777-00", "garfield444", 1, "www.link.com", "Cabeçalho", 120);
	      String[] dicasListadas = dc.listaDicasDetalhadas();
	      assertEquals(dicasListadas[0],"Luis - Tema da Dica 1\nCabeçalho, www.link.com, 120s. Valor = 10\n");
	      assertEquals(dicasListadas[1],"Emmily - Tema da Dica 2\nReferência: Regulamento estágio UASC, Res 01/2020, ano: 2024, importância: 5. Valor = 15\n");
	  }
	  
	
	  @Test
	// lista as informacoes detalhadas de uma dica especificada
	  public void testeListarDicasDetalhadaEspecifica() {
		  dc.adicionaDica("555555555-00", "senha123", "Tema da Dica 2");
	      dc.adicionaElementoReferenciaDica("555555555-00", "senha123", 2, "Regulamento estágio UASC", "Res 01/2020", 2024, 5, true);
	      dc.adicionaElementoMultimidiaDica("777777777-00", "garfield444", 1, "www.link.com", "Cabeçalho", 120);
	      String dica = dc.listaDicaDetalhadaPosicao(1);
	      String dica2 = dc.listaDicaDetalhadaPosicao(2);
	      assertEquals(dica,"Luis - Tema da Dica 1\nCabeçalho, www.link.com, 120s. Valor = 10\n");
	      assertEquals(dica2,"Emmily - Tema da Dica 2\nReferência: Regulamento estágio UASC, Res 01/2020, ano: 2024, importância: 5. Valor = 15\n");
	  }
	  


}
