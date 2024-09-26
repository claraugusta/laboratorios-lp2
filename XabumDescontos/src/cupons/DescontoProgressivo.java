package cupons;

import xabum.Compra;

public class DescontoProgressivo implements Cupom{

	public void aplicaDesconto(Compra compra) {
		double valor = compra.getValorTotal();
		if(valor <= 100) {
			compra.setValorTotal(valor * 0.99);
		}else if (valor <= 500) {
			compra.setValorTotal(valor * 0.95);
		}else {
			compra.setValorFrete(valor * 0.9);
		}
	}
	
	@Override
	public String toString() {
		return "Desconto progressivo, 1%, 5% po 10%";
	}

}
