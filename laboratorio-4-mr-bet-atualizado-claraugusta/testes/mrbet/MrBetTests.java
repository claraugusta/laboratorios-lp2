package mrbet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MrBetTests {
	
	private MrBet mb;
	
	@BeforeEach
	void preparaMrBet() {
		mb = new MrBet();
		mb.cadastraTime("250_PB", "Nacional de Patos", "Canário");
		mb.cadastraTime("252_PB", "Sport Lagoa Seca", "Carneiro");
		mb.cadastraTime("002_RJ", "Clube de Regatas do Flamengo", "Urubu");
		mb.cadastraTime("105_PB", "Sociedade Recreativa de Monteiro (SOCREMO)", "Gavião");
	}
	
	@Test
	void CadastraCampeonatoSucessotest() {
		assertEquals(mb.cadastraCampeonato("Brasileirão série A 2023", 10), "CAMPEONATO ADICIONADO!");
	}

	@Test
	void CadastraCampeonatoNomeExistenteTest() {
		assertEquals(mb.cadastraCampeonato("Brasileirão série A 2023", 10), "CAMPEONATO ADICIONADO!");
		assertEquals(mb.cadastraCampeonato("Brasileirão série A 2023", 10), "CAMPEONATO JÁ EXISTE!");	
	}
	
	@Test
	void incluirTimeTest() {
		mb.cadastraCampeonato("Brasileirão série A 2023", 10);		
		assertEquals(mb.incluirTime("250_PB", "Brasileirão série A 2023"), "TIME INCLUÍDO NO CAMPEONATO!");
		assertEquals(mb.incluirTime("252_PB", "Brasileirão série A 2023"), "TIME INCLUÍDO NO CAMPEONATO!");
	}
	
	@Test
	void incluirTimeJaIncluidoTest() {
		mb.cadastraCampeonato("Brasileirão série A 2023", 10);		
		assertEquals(mb.incluirTime("250_PB", "Brasileirão série A 2023"), "TIME INCLUÍDO NO CAMPEONATO!");
		assertEquals(mb.incluirTime("252_PB", "Brasileirão série A 2023"), "TIME INCLUÍDO NO CAMPEONATO!");
		assertEquals(mb.pegaCampeonato("Brasileirão série A 2023").contaTimes(), 2);
		assertEquals(mb.incluirTime("252_PB", "Brasileirão série A 2023"), "TIME INCLUÍDO NO CAMPEONATO!");
		assertEquals(mb.pegaCampeonato("Brasileirão série A 2023").contaTimes(), 2);
	}
}
