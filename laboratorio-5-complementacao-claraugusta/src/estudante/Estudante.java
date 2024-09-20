package estudante;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import atividades.Atividade;

public class Estudante {
	
	private String nome;
	private String cpf;
	private String senha;
	private String matricula;
	private int bonificacao;
	private List<Atividade> atividades;
	private int atividadesCodigo;
	
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
		this.atividades = new ArrayList<>();
		this.atividadesCodigo = 1;
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
	
	public void adicionaAtividade(Atividade atividade) {
		atividades.add(atividade);
		atividadesCodigo++;
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
