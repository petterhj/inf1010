


public class TestEnkelSortertListe {
	public static void main(String[] args){
		EnkelSortertListe<Lege> tab = new EnkelSortertListe<Lege>();

		Lege l1 = new Lege("a", 0, 0);
		Lege l2 = new Lege("b", 0, 0);
		Lege l3 = new Lege("c", 0, 0);
		Lege l4 = new Lege("d", 0, 0);
		Lege l5 = new Lege("e", 0, 0);
		
		tab.settInn(l1);
		tab.settInn(l3);
		tab.settInn(l5);
		tab.settInn(l2);
		tab.settInn(l4);
		
	
		
		for(Lege l : tab){
			System.out.println(l);
		}
		
	


	}

}