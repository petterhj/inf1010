

import java.util.Iterator;

public class EnkelSortertListe<T extends Comparable<T> & Lik> implements AbstraktSortertEnkelListe<T> {
	private Node head;
	private Node tail;

	EnkelSortertListe(){
		head = null;
	}

	public T finn(String el) {
		Node tmp = head;
		T retur = null;
		while(tmp != null){
			
			if(tmp.data.samme(el)){
				retur = tmp.data;
			}
			tmp = tmp.neste;
		}
		return retur;
	}


	public void settInn(T o){
		if(this.head == null){
			this.head = new Node(o,null);
			tail = head;
		}
		else{
			if(o.compareTo(head.data) < 0){
				Node ny = new Node(o,head);
				head = ny;
			}
			else if(o.compareTo(tail.data) > 0){
				tail.neste = new Node(o,null);
				tail = tail.neste;
			}
			else{
				hSettInn(o,head);
			}
		}
	}
	
	
	private void hSettInn(T o, Node n){
		if(o.compareTo(n.data) > 0){
			Node ny = new Node(o,n.neste);
			n.neste = ny;
		}
		else{
			hSettInn(o, n.neste);
		}
	}

	public Iterator <T> iterator () {
		return new ITE();
	}

	private class ITE implements Iterator <T>  {
		Node tmp = head;

		public boolean hasNext() {
			if(tmp == null){
				return false;
			}
			return true;
		}

		public T next() {
			T ob = tmp.data;
			tmp = tmp.neste;
			return ob;
		}
		public void remove() {
		}
	}

	private class Node{
		Node neste;
		T data;

		Node(T data, Node neste){
			this.data = data;
			this.neste = neste;
		}

	}

}



