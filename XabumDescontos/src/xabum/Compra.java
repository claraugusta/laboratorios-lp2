package xabum;

public class Compra {
	
	private String codigo;
	private String cpf;
	private double valorTotal;
	private double valorFrete;
	
	public Compra(String codigo, String cpf, double valorTotal, double valorFrete) {
		this.codigo = codigo;
		this.cpf = cpf;
		this.valorTotal = valorTotal;
		this.valorFrete = valorFrete;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public double getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(double valorFrete) {
		this.valorFrete = valorFrete;
	}

	
	
}
