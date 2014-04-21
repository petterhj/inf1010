class Eks2014 {
	public static void main(String[] klargs) {
		new ForelesnEks();
	}
}

class ForelesnEks {
	ForelesnEks() {
		ListeAvPersoner mineVenner = new ListeAvPersoner();
		
		Person ane = new Person("Ane");
		mineVenner.settInnBak(ane);
		
		Person jonas = new Person("Jonas");
		mineVenner.settInnForan(jonas);
		
		Person imran = new Person("Imran");
		mineVenner.settInnBak(imran);
		
		Person siri = new Person("Siri");
		mineVenner.settInnEtter(ane, siri);
		
		Person jan = new Person("Jan");
		mineVenner.settInnEtter(ane, jan);
		
		ane = mineVenner.finnPerson("Ane");
		mineVenner.settInnEtter(ane, new Person("Ida"));
		
		mineVenner.skrivAlle();
	}
}

class ListeAvPersoner {
	private Person første;
	private Person siste;
	private int antall;
	
	ListeAvPersoner() {
		Person lh = new Person("LISTEHODE");
		første = lh;
		siste = lh;
		antall = 0;
	}
	
	public void settInnForan(Person nypers) {
		nypers.neste = første.neste;
		første.neste = nypers;
		if (siste.neste == nypers) // nyperson er ny siste!
			siste = nypers;
		antall++;
	}
	
	public void settInnBak(Person inn) {
		siste.neste = inn;
		siste = inn;
		antall++;
	}
	
	public void settInnEtter(Person denne, Person nypers) {
		nypers.neste = denne.neste;
		denne.neste = nypers;
		if (siste.neste == nypers) // nyperson er ny siste!
			siste = nypers;
		antall++;
	}
	
	public Person finnPerson(String s) {
		Person p = første.neste;
		for (int i = antall; i > 0; i--) {
			if (p.navn.equals(s)) return p;
			else p = p.neste;
		}
		return null;
	}
	
	public void skrivAlle() {
		Person p = første.neste;
		for (int i = antall; i > 0; i--) {
			p.skriv();
			p = p.neste;
		}
	}
}

class Person {
	String navn;
	Person neste;
	
	Person(String n) {
		navn = n;
	}
	
	public void skriv() {
		System.out.println(navn);
	}
}