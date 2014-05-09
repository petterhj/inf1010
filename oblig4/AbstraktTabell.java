

import java.util.Iterator;

public abstract interface AbstraktTabell<D> extends Iterable<D> {
	
	public boolean settInn(D elem, int indeks);
	
	public D finn(int k);
}
