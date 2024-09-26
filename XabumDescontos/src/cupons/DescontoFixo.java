package cupons;

import xabum.Compra;

public class DescontoFixo implements Cupom{
	
	private double maxDesconto;
	private double percentualDesconto;
	
	
	public DescontoFixo(double maxDesconto, double percentualDesconto) {
		this.maxDesconto = maxDesconto;
		this.percentualDesconto = percentualDesconto;
	}

	public void aplicaDesconto(Compra compra) {
		double valor = Math.max(percentualDesconto * compra.getValorTotal(), maxDesconto);
		compra.setValorTotal(valor);
	}
	
	@Override
	public String toString() {
		return String.format("Desconto Fixo de %f%, até no máximo R$ %.2f", percentualDesconto, maxDesconto);
	}
}
