

public class TestSortert {
	public static void main(String []args){
		EnkelSortertListe<Lege> tabell = new EnkelSortertListe<Lege>();
		
		Lege l1 = new Lege("l1", 0 ,0);
		Lege l2 = new Lege("l3", 0 ,0);
		Lege l3 = new Lege("l2", 0 ,0);
		Lege l4 = new Lege("l5", 0 ,0);
		Lege l5 = new Lege("l4", 0 ,0);
		
		
		tabell.settInn(l1);
		tabell.settInn(l2);
		tabell.settInn(l3);
		tabell.settInn(l4);
		tabell.settInn(l5);
		
		for(Lege lege : tabell){
			System.out.println(lege);
		}
		
	}
}
