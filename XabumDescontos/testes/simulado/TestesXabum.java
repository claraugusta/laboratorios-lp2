package simulado;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cupons.DescontoFixo;
import cupons.FreteGratis;
import xabum.Compra;
import xabum.XabumDescontos;
import xabum.Usuario;

public class TestesXabum {

    private XabumDescontos xabumDescontos;

    @BeforeEach
    public void setUp() {
        xabumDescontos = new XabumDescontos();
    }

    @Test
    public void testAdicionaUsuario() {
        xabumDescontos.adicionaUsuario("12345678901", "João");
        assertNotNull(xabumDescontos.pegaUsuario("12345678901"));
        assertEquals("João - 12345678901", xabumDescontos.pegaUsuario("12345678901").toString());
    }

    @Test
    public void testCriaCupomFreteGratis() {
        String resultado = xabumDescontos.criaCupomFreteGratis(15.0);
        assertEquals("adicionado", resultado);
        assertEquals(1, xabumDescontos.getCupons().size());  // Método para obter cupons
    }

    @Test
    public void testCriaCupomDescontoFixo() {
        String resultado = xabumDescontos.criaCupomDescontoFixo(0.1, 10.0);
        assertEquals("adicionado", resultado);
        assertEquals(1, xabumDescontos.getCupons().size());  // Método para obter cupons
    }

    @Test
    public void testAddCupomUsuario() {
        xabumDescontos.adicionaUsuario("12345678901", "João");
        xabumDescontos.criaCupomFreteGratis(15.0);
        
        String resultado = xabumDescontos.addCupomUsuario("12345678901", 0);
        assertEquals("cupom adicionado no usuario", resultado);
        assertEquals(1, xabumDescontos.pegaUsuario("12345678901").getCuponsTotal());
    }

    @Test
    public void testAplicarDescontoCompra() {
        xabumDescontos.adicionaUsuario("12345678901", "João");
        xabumDescontos.criaCupomDescontoFixo(0.1, 10.0);
        xabumDescontos.addCupomUsuario("12345678901", 0);
        
        Compra compra = new Compra("C001", "12345678901", 100.0, 10.0);
        String resultado = xabumDescontos.aplicarDescontoCompra("12345678901", compra, 0);
        
        assertEquals("aplicado", resultado);
        assertEquals(90.0, compra.getValorTotal());
    }

    @Test
    public void testListarUsuariosPorNome() {
    	
    	xabumDescontos.adicionaUsuario("98765432100", "Maria");
        xabumDescontos.adicionaUsuario("12345678901", "João");
       

        String[] usuarios = xabumDescontos.listarUsuariosPorNome();
        assertEquals(2, usuarios.length);
        assertEquals("João - 12345678901", usuarios[0]);
        assertEquals("Maria - 98765432100", usuarios[1]);
    }

    @Test
    public void testListarUsuariosPorCuponsTotais() {
        xabumDescontos.adicionaUsuario("12345678901", "João");
        xabumDescontos.adicionaUsuario("98765432100", "Maria");
        xabumDescontos.criaCupomFreteGratis(15.0);
        xabumDescontos.addCupomUsuario("12345678901", 0);
        
        String[] usuarios = xabumDescontos.listarUsuariosPorCuponsTotais();
        assertEquals(2, usuarios.length);
        assertEquals("João - 12345678901", usuarios[0]);
        assertEquals("Maria - 98765432100", usuarios[1]);
    }
}

