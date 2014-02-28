class Oblig2Test3 {
    public static void main(String[] args) {
	
	}
}

interface Beholder<E> {
	public boolean settInn (E den);
}

class EnkelBeholder<E> implements Beholder<E> {
	public boolean settInn(E den) {
		return true;
	}
}


/* Gift */
interface Gift <T> {
	public boolean inCollection(T item);
}

/* Collection */
class Collection <T> implements Gift <T> {	
	public boolean inCollection(T item) { return true; }
}