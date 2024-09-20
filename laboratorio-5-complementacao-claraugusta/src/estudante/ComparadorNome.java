package estudante;

import java.util.Comparator;

public class ComparadorNome implements Comparator<Estudante> {

	@Override
	public int compare(Estudante e1, Estudante e2) {
		return e1.getNome().compareTo(e2.getNome());
	}
	
}
