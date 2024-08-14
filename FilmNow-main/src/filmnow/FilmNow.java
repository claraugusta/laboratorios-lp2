package filmnow;

/**
 * Sistema que mantém os seus filmes prediletos. Podem existir 100 filmes. 
 * 
 * @author 123210361 - Maria Clara Augusta Santos
 *
 */
public class FilmNow {
	
	private static final int TAMANHO = 100;
	private Filme[] filmes; 
	private Filme[] hotList;
	
    /**
     * Cria uma instância do sistema FilmNow.
     * Inicializa as listas de filmes e hotList com tamanhos fixos.
     */
	public FilmNow() {
		this.filmes = new Filme[TAMANHO];
		this.hotList = new Filme[10];
	}
	
	/**
	 * Acessa a lista de filmes mantida.
	 * @return O array de filmes.
	 */
	public Filme[] getFilmes() {
		return this.filmes.clone();
	}

	/**
	 * Acessa os dados de um filme específico.
	 * @param posicao Posição do filme no sistema.
	 * @return Dados do filme. Null se não há filme na posição.
	 */
	public Filme getFilme(int posicao) {
		return filmes[posicao-1];
	}
	
    /**
     * Adiciona um filme em uma posição específica. Se já houver um filme na posição, ele será sobrescrito.
     * 
     * @param posicao A posição onde o filme será adicionado (baseada em 1).
     * @param nome O nome do filme.
     * @param ano O ano de lançamento do filme.
     * @param local O local onde o filme pode ser assistido.
     * @return Uma mensagem indicando o resultado da operação ("POSIÇÃO INVÁLIDA", "FILME INVALIDO", "FILME JA ADICIONADO" ou "FILME ADICIONADO").
     */
	public String cadastraFilme(int posicao, String nome, String ano, String local) {
		if(posicao > TAMANHO || posicao < 1) {
			return "POSIÇÃO INVÁLIDA";
		}
		Filme novoFilme = new Filme(nome, ano, local);
		String msg = verificaFilmeExistente(novoFilme);
		if(verificaFilmeExistente(novoFilme).equals("FILME ADICIONADO")) {
			this.filmes[posicao-1] = novoFilme;
		}
		return msg;
	}

    /**
     * Verifica se um filme já existe na lista de filmes.
     * 
     * @param novoFilme O filme a ser verificado.
     * @return Uma mensagem indicando o resultado da verificação ("FILME INVALIDO", "FILME JA ADICIONADO" ou "FILME ADICIONADO").
     */
	private String verificaFilmeExistente(Filme novoFilme) {
		if(novoFilme.getNome() == "" || novoFilme.getLocal() == "" || novoFilme.getNome() == null || novoFilme.getLocal() == null) { 
			return "FILME INVALIDO";
		}
		else { 
			for(int i = 0; i < getFilmes().length; i++ ) {
				if(getFilmes()[i] != null && getFilmes()[i].equals(novoFilme)) 			
					return "FILME JA ADICIONADO";		
			}	
		}
		return "FILME ADICIONADO";
	}
	
    /**
     * Adiciona um novo local onde o filme pode ser assistido.
     * 
     * @param posicao A posição do filme na lista (baseada em 1).
     * @param local O novo local a ser adicionado.
     */
	public void adicionaLocal(int posicao, String local) {
		filmes[posicao-1].setLocal(local);
	}

    /**
     * Remove um local específico da lista de locais onde o filme pode ser assistido.
     * 
     * @param posicao A posição do filme na lista (baseada em 1).
     * @param local O local a ser removido.
     */
	public void removeLocal(int posicao, String local) {
		filmes[posicao-1].removeLocal(local);
	}
	
	 /**
     * Remove um filme da lista. Se o filme estiver na hotList, ele também será removido dela.
     * 
     * @param posicao A posição do filme na lista (baseada em 1).
     */
	public void removeFilme(int posicao) {
		if(filmes[posicao-1].isHot()) {
			for(int i = 0; i < hotList.length; i++) {
				if(filmes[posicao-1].equals(hotList[i]))
					hotList[i] = null;
			}
		}
		filmes[posicao-1] = null;	
	}
	
	/**
     * Obtém uma representação detalhada do filme na posição fornecida.
     * 
     * @param posicao A posição do filme (baseada em 1).
     * @return Uma string com a descrição do filme, ou uma string vazia se a posição for inválida ou não houver filme.
     */
	public String detalhaFilme(int posicao) {
		if(posicao > TAMANHO || posicao < 1) {
			return "POSIÇÃO INVÁLIDA";
		}
		if(getFilme(posicao) == null) {
			return "";
		}
		return getFilme(posicao).toString();
	}
	

	/**
     * Adiciona um filme à lista de filmes "hot" na posição especificada.
     * Marca o filme como "hot".
     * 
     * @param posicaoFilme A posição do filme na lista de filmes (baseada em 1).
     * @param posicao A posição onde o filme será adicionado na hotList (baseada em 1).
     * @return Uma mensagem indicando o resultado da operação ("POSIÇÃO INVÁLIDA" ou "ADICIONADO À HOTLIST").
     */
	public String adicionaHot(int posicaoFilme, int posicao) {
		if(posicao < 1|| posicao > hotList.length) {
	        return "POSIÇÃO INVÁLIDA";
		} 
		hotList[posicao - 1] = getFilme(posicaoFilme);
	    getFilme(posicaoFilme).setHot();
		return "ADICIONADO À HOTLIST";
	}
	
	/**
     * Remove um filme da lista de filmes "hot" na posição fornecida.
     * 
     * @param posicao A posição do filme na hotList (baseada em 1).
     */
	public void removeHot(int posicao) {
		this.hotList[posicao-1] = null;
	}
	
	public Filme[] getHotList() {
		return this.hotList.clone();
	}
	
	public Filme getHot(int posicao) {
		return hotList[posicao-1];
	}
	
	/**
     * Exibe a lista de filmes com seus índices e nomes.
     * 
     * @return Uma string com a lista de filmes, cada um em uma linha.
     */
	public String exibeFilmes() {
		StringBuilder exibicao = new StringBuilder();
		for (int i = 0; i < filmes.length - 1; i++) {
			if (filmes[i] != null) {
				exibicao.append(i+1 + " - " + filmes[i].getNome() + "\n");
			}
		}
		if (exibicao.length() > 0) {
	        exibicao.setLength(exibicao.length() - 1);
	    }
		return exibicao.toString();
	}
	
	/**
     * Exibe a lista de filmes que contêm algum nome com seus índices e nomes.
     * 
     * @return Uma string com a lista de filmes, cada um em uma linha.
     */
	public String exibeFilmesNome(String nome) {
		StringBuilder exibicao = new StringBuilder();
		for (int i = 0; i < filmes.length - 1; i++) {
			if (filmes[i] != null && filmes[i].getNome().toLowerCase().contains(nome.toLowerCase())) {
				exibicao.append(i+1 + " - " + filmes[i].getNome() + "\n");
			}
		}
		if (exibicao.length() > 0) {
	        exibicao.setLength(exibicao.length() - 1);
	    }
		return exibicao.toString();
	}
	
	/**
     * Exibe a lista de filmes de um mesmo ano com seus índices e nomes.
     * 
     * @return Uma string com a lista de filmes, cada um em uma linha.
     */
	public String exibeFilmesAno(String ano) {
		StringBuilder exibicao = new StringBuilder();
		for (int i = 0; i < filmes.length - 1; i++) {
			if (filmes[i] != null && filmes[i].getAno().equals(ano)) {
				exibicao.append(i+1 + " - " + filmes[i].getNome() + "\n");
			}
		}
		if (exibicao.length() > 0) {
	        exibicao.setLength(exibicao.length() - 1);
	    }
		return exibicao.toString();
	}
	
}
