import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dica.DicaController;
import estudante.EstudanteController;

class TestesDicas {


    private DicaController dc;
    private EstudanteController ec;

    @BeforeEach
    public void setUp() {
        ec = new EstudanteController();
        ec.criaEstudante("Clara", "111111111-77", "oimundoo", "123210361");
        dc = new DicaController(ec);
    }
    
    @Test
    public void testAdicionaDica() {
        int dicaId = dc.adicionaDica("oimundoo", "111111111-77", "Tema de Estudo");
        assertEquals(0, dicaId);
        int dicaId2 = dc.adicionaDica("oimundoo", "111111111-77", "Calculo 1");
        assertEquals(1, dicaId2);
    }
    
    @Test
    public void testAdicionaElementoTexto() {
        int dicaId = dc.adicionaDica("oimundoo", "111111111-77", "Tema de Estudo");      
        assertTrue(dc.adicionaElementoTextoDica("111111111-77", "oimundoo", dicaId, "Conteúdo de texto"));
    }
    
    @Test
    public void testAdicionaElementoReferencia() {
    }
}
