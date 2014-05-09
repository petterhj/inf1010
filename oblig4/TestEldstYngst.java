

public class TestEldstYngst {
	public static void main(String[] args){
		EldsteForstReseptListe el = new EldsteForstReseptListe();
		YngsteForstReseptListe yl = new YngsteForstReseptListe();
		
		Resepter r1 = new Resepter(1, null, 0, null, 0, 0);
		Resepter r2 = new Resepter(2, null, 0, null, 0, 0);
		Resepter r3 = new Resepter(3, null, 0, null, 0, 0);
		Resepter r4 = new Resepter(4, null, 0, null, 0, 0);
		Resepter r5 = new Resepter(5, null, 0, null, 0, 0);
		
		
		yl.settInn(r1);
		yl.settInn(r3);
		yl.settInn(r5);
		yl.settInn(r2);
		yl.settInn(r3);
		el.settInn(r1);
		el.settInn(r3);
		el.settInn(r5);
		el.settInn(r2);
		el.settInn(r3);
		
		System.out.println("eldst");
		for(Resepter res : el){
			System.out.println(res);
		}
		System.out.println("yngst");
		for(Resepter res : yl){
			System.out.println(res);
		}

		
		
	}
}
