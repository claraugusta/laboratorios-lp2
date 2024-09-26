package xabum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cupons.Cupom;
import cupons.DescontoFixo;
import cupons.DescontoProgressivo;
import cupons.FreteGratis;

public class XabumDescontos {
	
	private Map<String, Usuario> usuarios;
	private List<Cupom> cupons;
	private int proxId;
	
	public XabumDescontos() {
		this.usuarios = new HashMap<>();
		this.cupons = new ArrayList<>(); 
		this.proxId = 0;
	}
	
	public Usuario pegaUsuario(String cpf) {
		return usuarios.get(cpf);
	}
	
	public void adicionaUsuario(String cpf, String nome) {
		usuarios.put(cpf, new Usuario(cpf, nome));
	}
	
	public String criaCupomFreteGratis(double maxDesconto) {
		cupons.add(new FreteGratis(maxDesconto));
		return "adicionado";
	}
	
	public String criaCupomDescontoFixo(double percentualDesconto, double maxDesconto) {
		cupons.add(new DescontoFixo(percentualDesconto, maxDesconto));
		return "adicionado";
	}
	
	public String criaCupomDescontoProgressivo() {
		cupons.add(new DescontoProgressivo());
		return "adicionado";
	}
	
	public String addCupomUsuario(String cpf, int indexCupom) {
		pegaUsuario(cpf).adicionaCupom(cupons.get(indexCupom));
		cupons.set(indexCupom, null);
		return "cupom adicionado no usuario";
	}
	
	public String aplicarDescontoCompra(String cpf, Compra minhaCompra, int indexCupomUsuario) {
		pegaUsuario(cpf).aplicarCupom(indexCupomUsuario, minhaCompra);
		return "aplicado";
	}
	
	public String[] listarCuponsUsuario(String cpf) {
		return pegaUsuario(cpf).listarCupons();
		
	}
	
	public String[] listarUsuariosPorNome() {
		ArrayList<String> usuarios = new ArrayList<>();
		for(Usuario u : this.usuarios.values()) {
			usuarios.add(u.toString());
		}
		Collections.sort(usuarios);
		return usuarios.toArray(new String[0]);
	}
	
	public String[] listarUsuariosPorCuponsTotais() {
		ArrayList<Usuario> usuarios = new ArrayList<>(this.usuarios.values());
		Collections.sort(usuarios, new CuponsComparator());
		String[] out = new String[this.usuarios.size()];
		
		for(int i = 0; i < usuarios.size(); i++) {
			out[i] = usuarios.get(i).toString();
		}
		return out;
	}

	public List<Cupom> getCupons() {
		return cupons;
	}
}
