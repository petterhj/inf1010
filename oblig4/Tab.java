

import java.util.Iterator;


public class Tab<D> implements AbstraktTabell<D> {
	private D[] data;
	private int antalldata;

	public Tab(int antall){
		data = (D[])new Object[antall];
		antalldata = 0;
	}


	@Override
	public boolean settInn(D elem, int indeks) {
		if(indeks > data.length) return false;
		
		
		if(data[indeks] == null){
			data[indeks] = elem;
			antalldata++;
			return true;
		}
		else{
			D tmp = data[indeks];
			data[indeks] = elem; 
			
			for(int i = 0; i < data.length; i++){
				if(data[i] == null){
					data[i] = tmp;
					antalldata++;
					return true;
				}
			}
		}
		return false;
	}


	@Override
	public D finn(int k) {
		return data[k];
	}






	class LenkeListeIterator implements Iterator<D> {
		int i = 0;
		int count = 0;
		
		D[] elms = (D[])new Object[antalldata];
		
		LenkeListeIterator() {
			for (D d : data)
				if (d != null)
					elms[i++] = d;
		}
		
		public boolean hasNext() {
			return (count < elms.length);
		}

		public D next() {
			return elms[count++];
		}

		public void remove() {
		}
	}

	@Override
	public Iterator<D> iterator() {
		return new LenkeListeIterator();
	}
	
	public int hentAntall() {
		return this.antalldata;
	}
	
	public int hentLength(){
		return this.data.length;
	}

}
