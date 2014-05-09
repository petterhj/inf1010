

import java.util.Iterator;


public class YngsteForstReseptListe extends EnkelReseptListe {
	public Iterator iterator() {
		return new ITE();
	}
	
	class ITE implements Iterator {
		
		Resepter[] tab;
		Node tmp = head;
		int count2 = 0;
		
		public ITE() {
			int count = 0;
			tab = new Resepter[antallResepter];
			
			while(tmp != null){
				tab[count++] = tmp.data;
				tmp = tmp.neste;
			}
			bubbleSort1(tab);
		}
		
		public void bubbleSort1(Resepter[] x) {
		    int n = x.length;
		    for (int pass=1; pass < n; pass++) {  
		        for (int i=0; i < n-pass; i++) {
		            if (x[i].nr() > x[i+1].nr()) {
		                Resepter temp = x[i];  x[i] = x[i+1];  x[i+1] = temp;
		            }
		        }
		    }
		}

		public boolean hasNext() {
			return (count2 < tab.length);
		}

		public Resepter next() {
			return tab[count2++];
		}

		public void remove() {
		}
		
	}
}
