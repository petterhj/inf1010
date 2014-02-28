class Personliste {
	Person første;
	Person siste;
	
	public static void main(String[] args) {
		
	}
	
	void settInn(Person p) {
		if (første == null) {
			første = p;
			siste = p;
		}
		else {
			siste.neste = p;
			siste = p;
		}
		
		p.neste = null;
	}
	
	Person taUt() {
		Person p = første;
		
		if (p != null)
			første = første.neste;
		if (første == null)
			siste = null;
		
		return p;
	}
}

class Person {
	String navn;
	Person neste;
}