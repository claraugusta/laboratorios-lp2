package estudante;

import java.util.Comparator;

public class ComparadorBonus implements Comparator<Estudante> {

	@Override
	public int compare(Estudante estudante1, Estudante estudante2) {
		return Integer.compare(estudante2.getBonificacao(), estudante1.getBonificacao());
	}
	
}
