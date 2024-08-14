package filmnow;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilmeTest {


    private Filme filmeBase;


    @BeforeEach
    void preparaFilmes() {
        this.filmeBase = new Filme("Avatar", "2009", "Disney+");
    }

    @Test
    void testNomeAno() {
       String msg = "Esperando Nome e Ano";
       assertEquals( "Avatar, 2009", this.filmeBase.nomeAno(), msg);
    }
    @Test
    void testEqualsFalse() {
    	Filme avatar1 = new Filme("Avatar", "2009", "Disney+");
    	Filme avatar2 = new Filme("Avatar", "2008", "Disney+");
    	assertFalse(avatar1.equals(avatar2));
    }
    @Test
    void testEqualsLocalDiferente() {
    	Filme avatar1 = new Filme("Avatar", "2009", "Disney+");
    	Filme avatar2 = new Filme("Avatar", "2009", "max");
    	assertTrue(avatar1.equals(avatar2));
    }
    @Test
    void testEquals() {
    	Filme avatar1 = new Filme("Avatar", "2009", "Disney+");
    	Filme avatar2 = new Filme("Avatar", "2009", "Disney+");
    	assertTrue(avatar1.equals(avatar2));
    }
    
    @Test
    void testHashCode() {
    	Filme avatar1 = new Filme("Avatar", "2009", "Disney+");
    	Filme avatar2 = new Filme("Avatar", "2009", "Disney+");
    	assertEquals(avatar1.hashCode(), avatar2.hashCode());
    }
    
    @Test
    void testHashCodeDiferente() {
    	Filme avatar1 = new Filme("Avatar", "2009", "Disney+");
    	Filme coringa = new Filme("Coringa", "2020", "max");
    	assertNotEquals(avatar1.hashCode(), coringa.hashCode());
    }
}
