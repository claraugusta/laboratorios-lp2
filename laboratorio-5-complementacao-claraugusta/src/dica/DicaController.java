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
	
	public int adicionaDica(String senha, String cpf, String tema) {
		pegaEstudante(cpf).validaSenha(senha);
		Dica novaDica = new Dica(pegaEstudante(cpf), tema);
		dicas.add(novaDica);
		pegaDica(proxID).setID(proxID);;
		return proxID++;
	}
	
	private Dica pegaDica(int posicao) {
		return dicas.get(posicao);
	}
	
	public boolean adicionaElementoReferenciaDica(String cpf, String senha, int posicao, List<String> referencias, boolean conferida) {
		pegaEstudante(cpf).validaSenha(senha);
		return pegaDica(posicao).adicionaElementoReferencia(referencias, conferida);
	}
	
	public boolean adicionaElementoTextoDica(String cpf, String senha, int posicao, String texto) {
		pegaEstudante(cpf).validaSenha(senha);
		return pegaDica(posicao).adicionaElementoTexto(texto);
	}
	
	public boolean adicionaElementoMultimidiaDica(String cpf, String senha, int posicao, String link, String cabecalho, int tempo) {
		pegaEstudante(cpf).validaSenha(senha);
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
	
	
}
