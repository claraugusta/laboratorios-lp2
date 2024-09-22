package dica;

import java.util.ArrayList;
import java.util.List;

import estudante.Estudante;
import estudante.EstudanteController;


public class DicaController {
	
	private List<Dica> dicas;
	private int proxID;
	private EstudanteController estudanteController;
	
	
	public DicaController(EstudanteController estudanteController) {
		dicas = new ArrayList<>();
		this.estudanteController = estudanteController;
		proxID = 0;
	}
	
	private Estudante pegaEstudante(String cpf) {
		estudanteController.verificaEstudante(cpf);
		return estudanteController.pegaEstudante(cpf);
	}
	
	private void validaSenha(String cpf, String senha) {
		pegaEstudante(cpf).validaSenha(senha);
	}
	
	public int adicionaDica(String senha, String cpf, String tema) {
		validaSenha(cpf, senha);
		Dica novaDica = new Dica(pegaEstudante(cpf), tema);
		dicas.add(novaDica);
		pegaDica(proxID).setID(proxID);;
		return proxID++;
	}
	
	private Dica pegaDica(int posicao) {
		return dicas.get(posicao);
	}
	
	public boolean adicionaElementoReferenciaDica(String cpf, String senha, int posicao, String titulo, String fonte, int ano, int importancia, boolean conferida) {
		validaSenha(cpf, senha);
		return pegaDica(posicao).adicionaElementoReferencia(titulo,fonte, ano, importancia, conferida);
	}
	
	public boolean adicionaElementoTextoDica(String cpf, String senha, int posicao, String texto) {
		validaSenha(cpf, senha);
		return pegaDica(posicao).adicionaElementoTexto(texto);
	}
	
	public boolean adicionaElementoMultimidiaDica(String cpf, String senha, int posicao, String link, String cabecalho, int tempo) {
		validaSenha(cpf, senha);
		return pegaDica(posicao).adicionaElementoMultimidia(link, cabecalho, tempo);
	}
		
	public void BonificacaoTotalEstudante(String cpf) {
		int bonusTotal = 0;
		for(Dica d : dicas) {
			if( d.pegaAutor().equals(pegaEstudante(cpf))) {
				bonusTotal += d.calculaBonificacao();
			}
		}
		pegaEstudante(cpf).setBonificacao(bonusTotal);
	}
	
	public String[] listaDicas() {
		String[] dicasList = new String[dicas.size()];
		int prox = 0;
		for(Dica d : dicas) {
			dicasList[prox++] = d.listaDica();
		}
		return dicasList;
	}
	
	public String[] listaDicasDetalhadas() {
		String[] dicasList = new String[dicas.size()];
		int prox = 0;
		for(Dica d : dicas) {
			dicasList[prox++] = d.listaDicaDetalhada();
		}
		return dicasList;
	}
	
	public String listaDicaPosicao(int posicao) {
		return pegaDica(posicao).listaDica();
	}
	
	public String listaDicaDetalhadaPosicao(int posicao) {
		return pegaDica(posicao).listaDicaDetalhada();
	}
}
