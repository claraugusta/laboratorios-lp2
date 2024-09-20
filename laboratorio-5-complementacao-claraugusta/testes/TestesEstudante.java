import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dica.DicaController;
import estudante.Estudante;
import estudante.EstudanteController;

class TestesEstudante {
	
	EstudanteController ec;
	DicaController dc;
	
	@BeforeEach
	void setUp() {
		ec = new EstudanteController();
		ec.criaEstudante("Clara", "111111111-77", "oimundoo", "123210361");
		ec.criaEstudante("Emmily", "222222222-45", "amoclara", "123210718");
		ec.criaEstudante("Danilo", "444444444-99", "amolucas", "123210295");
		ec.criaEstudante("Luis", "111111111-00", "gatinhos123", "123210765");	
		ec.criaEstudante("Eduardo", "888888888-77", "vaqueijada", "123210289");
	}

	@Test
	void testCriaEstudante() {	
		assertTrue(ec.criaEstudante("Lucas", "123456789-00", "senha123", "mat001"));	
	}

    @Test
    void testCriaEstudanteNomeNulo() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new Estudante(null, "111111111-77", "oimundo", "mat001");
        });
        assertEquals("Nome Nulo", exception.getMessage());
    }

    @Test
    void testCriaEstudanteNomeVazio() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Estudante("", "111111111-77", "senha123", "mat001");
        });
        assertEquals("Nome Vazio", exception.getMessage());
    }

    @Test
    void testCriaEstudanteCpfNulo() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new Estudante("Clara", null, "senha123", "mat001");
        });
        assertEquals("Cpf Nulo", exception.getMessage());
    }

    @Test
    void testCriaEstudanteCpfVazio() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Estudante("Clara", "", "senha123", "mat001");
        });
        assertEquals("Cpf Vazio", exception.getMessage());
    }

    @Test
    void testCriaEstudanteSenhaNula() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new Estudante("Clara", "111111111-77", null, "mat001");
        });
        assertEquals("Senha Nula", exception.getMessage());
    }

    @Test
    void testCriaEstudanteSenhaVazia() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Estudante("Clara", "111111111-77", "", "mat001");
        });
        assertEquals("Senha Vazia", exception.getMessage());
    }

    @Test
    void testCriaEstudanteMatriculaNula() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new Estudante("Clara", "111111111-77", "senha123", null);
        });
        assertEquals("Matricula Nula", exception.getMessage());
    }

    @Test
    void testCriaEstudanteMatriculaVazia() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Estudante("Clara", "111111111-77", "senha123", "");
        });
        assertEquals("Matricula Vazia", exception.getMessage());
    }
    
	@Test public void testCriaEstudanteDuplicado() {
		assertFalse(ec.criaEstudante("Eduardo", "888888888-77", "vaqueijada", "123210289"));
	}
	
	@Test
	void testListaEstudantes() {		
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
	void testAlteraSenhaMenosCaracteres() {
		// nova senha com tamanho menor que 8
		assertFalse(ec.alteraSenhaEstudante("111111111-77", "oimundoo", "987654"));	
	}
	
	@Test
	void testAlteraSenhaInvalida() {
		try {
			ec.alteraSenhaEstudante("111111111-77", "qwertyuiop", "9485908903");
		} catch(IllegalArgumentException iae) {
			assertEquals(iae.getMessage(), "SENHA INVÁLIDA!");
		}
	}
	@Test
	void testAlteraSenhaNula() {
		try {
			ec.alteraSenhaEstudante("111111111-77", "oimundoo", null);
		} catch(NullPointerException npe) {
			assertEquals(npe.getMessage(), "SENHA NULA");
		}
	}
	

//    @Test
//    public void testListarUsuariosRankingDicas() {
//        // Assumindo que há um método de bonificação configurado em Estudante
//        controller.criaEstudante("Maria", "12345678901", "senha123", "mat002");
//        controller.criaEstudante("João", "12345678900", "senha456", "mat001");
//
//        // Aqui, você pode simular a bonificação se necessário
//        // Exemplo: controller.pegaEstudante("12345678900").setBonificacao(10);
//
//        String[] ranking = controller.listarUsuariosRankingDicas();
//
//        assertEquals(2, ranking.length);
//        // Adicione asserções específicas de acordo com os valores de bonificação definidos.
//    }
//
//    @Test
//    public void testAdicionaAtividadeEstudante() {
//        controller.criaEstudante("João", "12345678900", "senha123", "mat001");
//        Estudante estudante = controller.pegaEstudante("12345678900");
//        Atividade atividade = new Atividade("Atividade 1"); // Exemplo de atividade
//
//        assertTrue(controller.adicionaAtividadeEstudante(estudante, atividade));
//        assertTrue(estudante.getAtividades().contains(atividade));
//    }
	
}