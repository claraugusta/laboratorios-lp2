package xabum;

import java.util.Comparator;

public class CuponsComparator implements Comparator<Usuario>{

	@Override
	public int compare(Usuario o1, Usuario o2) {
		return Integer.compare(o2.getCuponsTotal(), o1.getCuponsTotal());
	}

}
