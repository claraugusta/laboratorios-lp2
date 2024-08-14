package filmnow;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * Representa um filme com nome, ano de lançamento, locais de exibição e status de popularidade.
 * 
 * @author 123210361 - Maria Clara Augusta Santos
 */
public class Filme {
	
	private String nome;
	private String ano;
	private String[] locais;
	private boolean statusHot;
	
	/**
     * Cria uma instância de um filme.
     *
     * @param nome  o nome do filme
     * @param ano   o ano de lançamento do filme
     * @param local o local onde o filme foi exibido
     * @throws IllegalArgumentException se algum parâmetro for nulo
     */
	public Filme(String nome, String ano, String local) throws IllegalArgumentException {		
		if(nome == null) {
			throw new NullPointerException("Nome nulo");
		}
		if(ano == null) {
			throw new NullPointerException("Ano nulo");
		}
		if(local == null) {
			throw new NullPointerException("Local nulo");
		}	
		this.nome = nome;
		this.ano = ano;
		this.locais = new String[5];
		this.locais[0] = local;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getAno() {
		return this.ano;
	}
	
	public String getLocal() {
		String locais = "";
		for(int i = 0; i < this.locais.length; i++) {
			if(this.locais[i] != null) {
				locais += this.locais[i] + ", ";
			}
		}
		if (!locais.isEmpty()) {
	        locais = locais.substring(0, locais.length() - 2);
		}
		return locais;
	}
	
	/**
     * Verifica se o filme está em um local específico.
     *
     * @param local o local a ser verificado
     * @return true se o local estiver na lista de locais do filme; false caso contrário
     */
	public boolean temLocal(String local) {
		for(int i = 0; i < locais.length; i++) {
			if(locais[i] != null && locais[i].equals(local)) {
				return true;
			}
		}
		return false;
	}
	
	public void setLocal(String local) {
		if(temLocal(local) == false) {
			for(int i = 0; i < locais.length; i++) {
				if(locais[i] == null) {
					locais[i] = local;
					break;
				}
			}
			Arrays.sort(locais, comparator);
		}
	}
	
	public void removeLocal(String local) {
		int contador = 0;
		for(int i = 0; i < locais.length; i++) {
			if(locais[i] != null) {
				contador++;
			}
		}
		if(contador > 1) {
			for(int i = 0; i < locais.length; i++) {
				if(locais[i].equals(local)) {
					locais[i] = null;
					break;
				}
			}
			Arrays.sort(locais, comparator);
		}
	}
	
	public void setHot() {
		this.statusHot = true;
	}
	
	public boolean isHot() {
		return statusHot;
	}
	
	public String getHot() {
		if(ano == null || ano == "") {
			return "- " + nome;
		}
		return "- " + nome + ", " + ano;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(ano, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Filme other = (Filme) obj;
		return  Objects.equals(ano, other.ano) && Objects.equals(nome, other.nome);
	}
	
	public String nomeAno() {
		if(ano == null || ano == "") {
			return nome;
		}
		return nome + ", " + ano;
	}
	
	public String toString() {
		if(statusHot == true) {
			return "🔥 " + nomeAno() + "\n" + getLocal();
		}
		return nomeAno() + "\n" + getLocal();
	}
	
	Comparator<String> comparator = new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {
            if (s1 == null && s2 == null) {
                return 0;
            } else if (s1 == null) {
                return 1; // nulls vão para o final
            } else if (s2 == null) {
                return -1; // nulls vão para o final
            }
            return s1.compareTo(s2);
        }
    };
}