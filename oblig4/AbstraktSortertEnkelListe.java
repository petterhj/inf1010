

public interface AbstraktSortertEnkelListe<D extends Comparable<D> & Lik> extends Iterable<D> {
	public void settInn(D d);
	public D finn(String n);
}
