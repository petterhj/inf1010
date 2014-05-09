

import java.util.Iterator;


public class EnkelReseptListe implements Iterable<Resepter> {

	Node head;
	int antallResepter;


	public EnkelReseptListe() {
		head = null;
		antallResepter = 0;
	}

	public void settInn(Resepter r){
		if(head == null){
			head = new Node(r, null);
			antallResepter++;
		}
		else{
			Node ny = new Node(r,head);
			head = ny;
			antallResepter++;
		}
	}

	public Resepter finn(int reseptnr){
		Node tmp = head;

		while(tmp != null){
			if(tmp.data.nr() == reseptnr){
				return tmp.data;
			}
			tmp = tmp.neste;
		}
		throw new IllegalStateException("Det finnes ikke");
	}

	public void fjern(Resepter r){
		fjernIter(r,head);
	}

	public void fjernIter(Resepter r, Node n){

		if(n != null){

			if(n.data == r && n.neste == null){
				n = null;
				antallResepter--;
			}
			else if(n.data == r && n == head){
				n.neste = head;
				antallResepter--;
			}
			else if(n.data == r){
				n = n.neste; 
				antallResepter--;
			}
			else{
				fjernIter(r, n.neste);
			}

		}

	}
	
	public int hentAntall(){
		return this.antallResepter;
	}

	class Node{
		Node neste;
		Resepter data;

		Node(Resepter data, Node neste){
			this.data = data;
			this.neste = neste;
		}
	}


	@Override
	public Iterator iterator() {
		return new ITE();
	}

	class ITE implements Iterator {

		Node tmp = head;

		public boolean hasNext() {
			if(tmp == null){
				return false;
			}
			return true;
		}

		public Resepter next() {
			Resepter ob = tmp.data;
			tmp = tmp.neste;
			return ob;
		}
		public void remove() {
		}

	}
}
