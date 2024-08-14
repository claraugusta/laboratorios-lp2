package filmnow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestFilmNow {
	
	
	private FilmNow fn;
	
	@BeforeEach
	void preparaFilmNow() {
		fn = new FilmNow();		
	}

	@Test
	void testAdicionaFilmePosicaoVazia() {
		Filme AVATAR = new Filme("Avatar", "2009", "Disney+");
		String mensagem = fn.cadastraFilme(1,"Avatar", "2009", "Disney+");
		assertEquals(AVATAR, fn.getFilme(1));
		assertEquals(mensagem, "FILME ADICIONADO");
	}
	
	@Test
	void testAdicionaFilmePosicaoExistente() {
		Filme FILME2 = new Filme("20 dias em Mariupol", "2023", "Cinema");
		fn.cadastraFilme(1,"Avatar", "2009", "Disney+");
		String mensagem = fn.cadastraFilme(1,"20 dias em Mariupol", "2023", "Cinema");
		assertEquals(FILME2, fn.getFilme(1));
		assertEquals(mensagem, "FILME ADICIONADO");
	}
	
	@Test
	void testAdicionaFilmeJaAdicionado() {
		fn.cadastraFilme(1,"Avatar", "2009", "Disney+");
		String mensagem = fn.cadastraFilme(3,"Avatar", "2009", "Disney+");
		assertEquals(fn.getFilme(3), null);
		assertEquals(mensagem, "FILME JA ADICIONADO");
	}
	
	@Test
	void testAdicionaFilmeLocalDiferente() {
		fn.cadastraFilme(1,"Avatar", "2009", "Disney+");
		String mensagem = fn.cadastraFilme(3,"Avatar", "2009", "Popcornflix");
		assertEquals(fn.getFilme(3), null);
		assertEquals(mensagem, "FILME JA ADICIONADO");
	}
	
	@Test
	void testAdicionaFilmePosicaoLimite() {
		Filme AVATAR = new Filme("Avatar", "2009", "Disney+");
		String mensagem = fn.cadastraFilme(100,"Avatar", "2009", "Disney+");
		assertEquals(fn.getFilme(100), AVATAR);
		assertEquals(mensagem, "FILME ADICIONADO");
	}
	
	@Test
	void testAdicionaFilmePosicaoAcimaLimite() {
		String mensagem = fn.cadastraFilme(101,"Avatar", "2009", "Disney+");
		assertEquals(mensagem, "POSIÇÃO INVÁLIDA");
	}
	
	@Test
	void testAdicionaFilmePosicaoAbaixoLimite() {
		String mensagem = fn.cadastraFilme(0,"Avatar", "2009", "Disney+");
		assertEquals(mensagem, "POSIÇÃO INVÁLIDA");
	}
	
	@Test
	void testAdicionaFilmeLocalVazio() {
		String mensagem = fn.cadastraFilme(1,"20 dias em Mariupol", "2023", "");
		assertEquals(fn.getFilme(1), null);
		assertEquals(mensagem, "FILME INVALIDO");
	}
	
	@Test 
	void testAdicionaFilmeAnoVazio() {
		Filme FILME2 = new Filme("20 dias em Mariupol", "", "Cinema");
		String mensagem = fn.cadastraFilme(1,"20 dias em Mariupol", "", "Cinema");
		assertEquals(FILME2, fn.getFilme(1));
		assertEquals(mensagem, "FILME ADICIONADO");
	}
	
	@Test
	void testAdicionaFilmeNomeVazio() {
		String mensagem = fn.cadastraFilme(1,"", "2023", "Cinema");
		assertEquals(fn.getFilme(1), null);
		assertEquals(mensagem, "FILME INVALIDO");
	}
	
	@Test
	void testDetalharFilmeTodosDados() {
		fn.cadastraFilme(1,"Avatar", "2009", "Disney+");
		assertEquals(fn.detalhaFilme(1), "Avatar, 2009\nDisney+");
	}
	
	@Test
	void testDetalharFilmeSemAno() {
		fn.cadastraFilme(1,"Avatar", "", "Disney+");
		assertEquals(fn.detalhaFilme(1), "Avatar\nDisney+");
	}
	
	@Test
	void testDetalharFilmePosicaoSemFilme() {
		assertEquals(fn.detalhaFilme(100), "");
	}
	
	@Test
	void testDetalharFilmePosicaoAbaixoLimite() {
		assertEquals(fn.detalhaFilme(0), "POSIÇÃO INVÁLIDA");
	}
	
	@Test
	void testDetalharFilmePosicaoAcimaLimite() {
		assertEquals(fn.detalhaFilme(101), "POSIÇÃO INVÁLIDA");
	}
	
	@Test
	void testDetalharFilmeHotList() {
		fn.cadastraFilme(1,"Avatar", "2009", "Disney+");
		fn.adicionaHot(1,1);
		assertEquals(fn.detalhaFilme(1), "🔥 Avatar, 2009\nDisney+");
	}
	
	@Test
	void testDetalharFilmeHotListSemAno() {
		fn.cadastraFilme(1,"Avatar", "", "Disney+");
		fn.adicionaHot(1,1);
		assertEquals(fn.detalhaFilme(1), "🔥 Avatar\nDisney+");
	}
	
	@Test
	void testAdicionarHot() {
		fn.cadastraFilme(1,"Avatar", "2009", "Disney+");
		fn.adicionaHot(1, 1);
		assertEquals(fn.getHotList()[0].getHot(), "- Avatar, 2009");
	}
	// Testar também adicionar hot e remover hot
	
	@Test
	void testAdicionaHotAbaixoLimite() {
			fn.cadastraFilme(1,"Avatar", "2009", "Disney+");
			fn.adicionaHot(1, 0);
			assertEquals("POSIÇÃO INVÁLIDA", fn.adicionaHot(1, 0));
	}
	
	@Test
	void testAdicionaHotAcimaLimite() {

			fn.cadastraFilme(1,"Avatar", "2009", "Disney+");	    
		    assertEquals("POSIÇÃO INVÁLIDA", fn.adicionaHot(1, 11));

	}

	@Test
	void testRemoveHot() {
		fn.cadastraFilme(1,"Avatar", "2009", "Disney+");
		fn.removeHot(1);
		assertEquals(fn.getHot(1), null);
	}
	
	@Test
	void testNomeNull() {
		 try {
		     Filme filmeInvalido = new Filme(null, "2009", "Disney+");
		     fail("Uma exceção era esperada ao passar nome nulo");
		  } catch (NullPointerException npe) {
		       assertEquals("Nome nulo", npe.getMessage());
		  }
	}
	
	@Test
	void testAnoNull() {
		 try {
		     Filme filmeInvalido = new Filme("Avatar", null, "Disney+");
		     fail("Uma exceção era esperada ao passar ano nulo");
		  } catch (NullPointerException npe) {
		       assertEquals("Ano nulo", npe.getMessage());
		  }
	}
	
	@Test
	void testLocalNull() {
		 try {
		     Filme filmeInvalido = new Filme("Avatar", "2009", null);
		     fail("Uma exceção era esperada ao passar local nulo");
		  } catch (NullPointerException npe) {
		       assertEquals("Local nulo", npe.getMessage());
		  }
	}
	
	@Test
	void testNull() {
		 try {
		     Filme filmeInvalido = new Filme(null, null, null);
		     fail("Uma exceção era esperada ao passar ano nulo");
		  } catch (NullPointerException npe) {
		       assertEquals("Nome nulo", npe.getMessage());
		  }
	}
	
	@Test
	void testExibeFilmes() {
		fn.cadastraFilme(1,"Avatar", "2009", "Disney+");
		fn.cadastraFilme(2, "Barbie", "2024", "max");
		assertEquals(fn.exibeFilmes(),"1 - Avatar\n2 - Barbie");
	}
	
	@Test
	void testExibeFilmesNome() {
		fn.cadastraFilme(1,"Avatar", "2009", "Disney+");
		fn.cadastraFilme(2,"Avatar: o caminho da água", "2022", "Disney+");
		fn.cadastraFilme(3,"Up: Altas Aventuras", "2009", "Disney+");
		assertEquals(fn.exibeFilmesNome("avatar"), "1 - Avatar\n2 - Avatar: o caminho da água");
	}
	
	@Test 
	void testExibeFilmesAno() {
		fn.cadastraFilme(1,"Avatar", "2009", "Disney+");
		fn.cadastraFilme(2,"Up: Altas Aventuras", "2009", "Disney+");
		fn.cadastraFilme(3,"Avatar: o caminho da água", "2022", "Disney+");
		assertEquals(fn.exibeFilmesAno("2009"), "1 - Avatar\n2 - Up: Altas Aventuras");
	}

}
