

public class TestTab {
	public static void main (String[] args){
		Tab<Resepter> t = new Tab<Resepter>(10);
		
		
		Resepter r1 = new Resepter(1, null, 0, null, 0, 0);
		Resepter r2 = new Resepter(2, null, 0, null, 0, 0);
		Resepter r3 = new Resepter(3, null, 0, null, 0, 0);
		Resepter r4 = new Resepter(4, null, 0, null, 0, 0);
		Resepter r5 = new Resepter(5, null, 0, null, 0, 0);
		
		t.settInn(r1, 0);
		t.settInn(r2, 1);
		t.settInn(r3, 2);
		t.settInn(r4, 3);
		t.settInn(r5, 4);

		
		for(Resepter i : t){
			System.out.println(i.nr());
		}
		
	}
}
