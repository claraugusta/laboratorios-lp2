package complementacao;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividades.AtividadeController;
import dica.DicaController;
import estudante.Estudante;
import estudante.EstudanteController;

class TestesEstudante {
	
	EstudanteController ec;
	DicaController dc;
	
	@BeforeEach
	public void setUp() {
		ec = new EstudanteController();
		dc = new DicaController(ec);
		ec.criaEstudante("Clara", "111111111-77", "oimundoo", "123210361");
		ec.criaEstudante("Emmily", "222222222-45", "amoclara", "123210718");
		ec.criaEstudante("Danilo", "444444444-99", "amolucas", "123210295");
		ec.criaEstudante("Luis", "111111111-00", "gatinhos123", "123210765");	
		ec.criaEstudante("Eduardo", "888888888-77", "vaqueijada", "123210289");
	}

	@Test
	public void testCriaEstudante() {	
		assertTrue(ec.criaEstudante("Lucas", "123456789-00", "senha123", "mat001"));	
	}

    @Test
    public void testCriaEstudanteNomeNulo() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new Estudante(null, "111111111-77", "oimundo", "mat001");
        });
        assertEquals("Nome Nulo", exception.getMessage());
    }

    @Test
    public void testCriaEstudanteNomeVazio() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Estudante("", "111111111-77", "senha123", "mat001");
        });
        assertEquals("Nome Vazio", exception.getMessage());
    }

    @Test
    public void testCriaEstudanteCpfNulo() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new Estudante("Clara", null, "senha123", "mat001");
        });
        assertEquals("Cpf Nulo", exception.getMessage());
    }

    @Test
    public void testCriaEstudanteCpfVazio() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Estudante("Clara", "", "senha123", "mat001");
        });
        assertEquals("Cpf Vazio", exception.getMessage());
    }

    @Test
    public void testCriaEstudanteSenhaNula() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new Estudante("Clara", "111111111-77", null, "mat001");
        });
        assertEquals("Senha Nula", exception.getMessage());
    }

    @Test
    public void testCriaEstudanteSenhaVazia() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Estudante("Clara", "111111111-77", "", "mat001");
        });
        assertEquals("Senha Vazia", exception.getMessage());
    }

    @Test
    public void testCriaEstudanteMatriculaNula() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new Estudante("Clara", "111111111-77", "senha123", null);
        });
        assertEquals("Matricula Nula", exception.getMessage());
    }

    @Test
    public void testCriaEstudanteMatriculaVazia() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Estudante("Clara", "111111111-77", "senha123", "");
        });
        assertEquals("Matricula Vazia", exception.getMessage());
    }
    
	@Test 
	public void testCriaEstudanteDuplicado() {
		assertFalse(ec.criaEstudante("Eduardo", "888888888-77", "vaqueijada", "123210289"));
	}
	
	@Test
	public void testListaEstudantes() {		
		String[] listaEsperada = {
	            "Nome: Clara\nMatrícula: 123210361\n",
	            "Nome: Danilo\nMatrícula: 123210295\n",
	            "Nome: Eduardo\nMatrícula: 123210289\n",
	            "Nome: Emmily\nMatrícula: 123210718\n",
	            "Nome: Luis\nMatrícula: 123210765\n"
	        };
		assertArrayEquals(ec.listaEstudantes(), listaEsperada);
	}
	
	@Test
	public void testAlteraSenhaSucesso() {	
		assertTrue(ec.alteraSenhaEstudante("111111111-77", "oimundoo", "987654778"));
	}
	
	@Test
	public void testAlteraSenhaMenosCaracteres() {
		// nova senha com tamanho menor que 8
		assertFalse(ec.alteraSenhaEstudante("111111111-77", "oimundoo", "987654"));	
	}
	
	@Test
	public void testAlteraSenhaInvalida() {
		try {
			ec.alteraSenhaEstudante("111111111-77", "qwertyuiop", "9485908903");
		} catch(IllegalArgumentException iae) {
			assertEquals(iae.getMessage(), "SENHA INVÁLIDA!");
		}
	}
	@Test
	public void testAlteraSenhaNula() {
		try {
			ec.alteraSenhaEstudante("111111111-77", "oimundoo", null);
		} catch(NullPointerException npe) {
			assertEquals(npe.getMessage(), "SENHA NULA");
		}
	}
	

    @Test
    public void testListarUsuariosRankingDicas() {
    	dc.adicionaDica("111111111-77", "oimundoo", "Tema da Dica 2");
    	dc.adicionaDica("222222222-45", "amoclara", "Tema da Dica 7");
    	dc.adicionaDica( "111111111-00", "gatinhos123", "luis dicas");
	    dc.adicionaElementoMultimidiaDica("111111111-77", "oimundoo", 1, "www.link.com", "Cabeçalho", 120);
	    dc.adicionaElementoReferenciaDica( "111111111-00", "gatinhos123", 2, "Regulamento estágio UASC", "Res 01/2020", 2024, 5, true);
	    dc.adicionaElementoTextoDica( "222222222-45", "amoclara", 3, "Novo texto na dica jfskjfkjglkfgjlejgeowrigjerkljvrljvolerwvlrikvgnrigrkigjirjijgrejgr"); 
	    
	    String[] esperado = {"Nome: Emmily\nCPF: 222222222-45\nMatrícula: 123210718\nBonificação: 15\n",
	    	"Nome: Clara\nCPF: 111111111-77\nMatrícula: 123210361\nBonificação: 10\n", 
	    	"Nome: Luis\nCPF: 111111111-00\nMatrícula: 123210765\nBonificação: 8\n",
	    	"Nome: Danilo\nCPF: 444444444-99\nMatrícula: 123210295\nBonificação: 0\n",
	    	"Nome: Eduardo\nCPF: 888888888-77\nMatrícula: 123210289\nBonificação: 0\n"};

	    assertArrayEquals(esperado, ec.listarUsuariosRankingDicas());
	    
    }

   
	
}