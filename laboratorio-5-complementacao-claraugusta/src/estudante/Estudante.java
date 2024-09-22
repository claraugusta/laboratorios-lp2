package estudante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import atividades.Atividade;

public class Estudante {
	
	private String nome;
	private String cpf;
	private String senha;
	private String matricula;
	private int bonificacao;
	private Map<String, Atividade> atividades;
	private int atividadesCodigo;
	private int creditos;
	
	public Estudante(String nome, String cpf, String senha, String matricula) throws NullPointerException, IllegalArgumentException{
		if(nome == null) { 
            throw new NullPointerException("Nome Nulo");
        }
        if(nome.isEmpty()) { 
            throw new IllegalArgumentException("Nome Vazio");
        }
        if(senha == null) {
        	throw new NullPointerException("Senha Nula");
        }
        if(senha.isEmpty()) { 
            throw new IllegalArgumentException("Senha Vazia");
        }
        if(cpf == null) { 
            throw new NullPointerException("Cpf Nulo");
        }
        if(cpf.isEmpty()) { 
            throw new IllegalArgumentException("Cpf Vazio");
        }
        if(matricula == null) { 
            throw new NullPointerException("Matricula Nula");
        }
        if(matricula.isEmpty()) { 
            throw new IllegalArgumentException("Matricula Vazia");
        }
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.matricula = matricula;
		this.bonificacao = 0;
		this.atividades = new HashMap<>();
		this.atividadesCodigo = 1;
		this.creditos = 0;
	}
	
	
	public int getCreditos() {
		return creditos;
	}


	public boolean setCreditos(int creditos) {
		if(creditos == 22) {
			return false;
		}
		if(creditos + this.creditos >= 22) {
			this.creditos = 22;
			return true;
		}
		this.creditos += creditos;
		return true;
	}


	public void alteraSenha(String novaSenha) {
		senha = novaSenha;
	}
	
	public void validaSenha(String senha) throws IllegalArgumentException {
		if(!this.senha.equals(senha)) {
			throw new IllegalArgumentException("SENHA INVÁLIDA!");
		}
	}
	
	public void setBonificacao(int bonificacao) {
		this.bonificacao = bonificacao;
	}
	
	public int getBonificacao() {
		return bonificacao;
	}
	
	public int getAtividadesCount() {
		return atividadesCodigo;
	}
	
	public void adicionaAtividade(String codigo, Atividade atividade) {
		atividades.put(codigo, atividade);
		atividadesCodigo++;
	}
	
	public Atividade pegaAtividade(String codigo) {
		return atividades.get(codigo);
	}
	
	public ArrayList<Atividade> pegaAtividadeTipo(String tipo){
		ArrayList<Atividade> atividadesTipo = new ArrayList<>();
		for(Atividade a : atividades.values()) {
			if(a.getTipo().equals(tipo.toUpperCase())) {
				atividadesTipo.add(a);
			}
		}
		return atividadesTipo;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudante other = (Estudante) obj;
		return Objects.equals(cpf, other.cpf);
	}
	public String exibeAluno() {
		return String.format("Nome: %s\nMatrícula: %s\n", nome, matricula);
	}
	public String toString() {
		return String.format("Nome: %s\nCPF: %s\nMatrícula: %s\n", nome, cpf, matricula);
	}

	public String getNome() {
		return nome;
	}

}
