package cupons;

import xabum.Compra;

public class FreteGratis implements Cupom{
	
	private double maxDesconto;
	
	
	
	public FreteGratis(double maxDesconto) {
		this.maxDesconto = maxDesconto;
	}

	public void aplicaDesconto(Compra compra) {
		if(maxDesconto == 0) {
			compra.setValorFrete(0);
		}else {
			compra.setValorFrete(compra.getValorFrete() - maxDesconto);
		}
	}
	
	@Override
	public String toString() {
		return String.format("1. FreteGratis\n2. Desconto de até R$ %.2f no Frete.", maxDesconto);
	}
}
