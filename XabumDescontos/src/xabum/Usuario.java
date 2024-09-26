package xabum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cupons.Cupom;

public class Usuario implements Comparable<Usuario>{
	private String cpf;
	private String nome;
	private List<Cupom> cupons;
	private int cuponsTotal;
	
	public Usuario(String cpf, String nome) {
		this.cupons = new ArrayList<>(); 
		this.cpf = cpf;
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}
	
	public void adicionaCupom(Cupom c) {
		cupons.add(c);
		cuponsTotal++;
	}
	
	public void aplicarCupom(int index, Compra compra) {
		this.cupons.get(index).aplicaDesconto(compra);
		cupons.set(index, null);
	}
	
	private int contaCuponsUsados() {
		int cont = 0;
		for(Cupom c : cupons) {
			if(c != null) {
				cont++;
			}
		}
		return cont;
	}
	
	public int getCuponsTotal() {
		return cuponsTotal;
	}
	
	public String[] listarCupons() {
		String[] lista = new String[cupons.size() + 1];
		lista[0] = String.format("%s\n%d cupons - %d cupons ativos",toString(), cupons.size(), contaCuponsUsados());
		for(int i = 1; i < cupons.size(); i++) {
			if(cupons.get(i) != null) {
				lista[i] = cupons.get(i).toString();
			}
		}
		return lista;
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
		Usuario other = (Usuario) obj;
		return Objects.equals(cpf, other.cpf);
	}

	@Override
	public String toString() {
		return nome + " - " + cpf;
	}

	@Override
	public int compareTo(Usuario o) {
		return this.nome.compareTo(o.getNome());
	}
	
	
}
