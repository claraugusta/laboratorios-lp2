package estudante;

import java.util.Comparator;

public class ComparadorBonus implements Comparator<Estudante> {

	@Override
	public int compare(Estudante estudante1, Estudante estudante2) {
		return estudante1.getBonificacao() - estudante2.getBonificacao();
	}
	
}
