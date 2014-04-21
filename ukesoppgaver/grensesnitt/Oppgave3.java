class Oppgave3 {
	public static void main(String[] args) {
		Forurensning[] forurensning = new Forurensning[10];
		
		Fly f1 = new Fly();
		Fly f2 = new Fly();
		Bil b1 = new Bil();
		Bil b2 = new Bil();
		
		forurensning[0] = b1;
		forurensning[1] = b2;
		forurensning[2] = f1;
		forurensning[3] = f2;
		
		int s = 0;
		
		for (Forurensning f : forurensning) {
			if (f != null) {
				s += f.forurensningsMengde();
			}
		}
		
		System.out.println(s);
	}
}

interface Forurensning {
	public int forurensningsMengde();
}

class Fly implements Forurensning {
	public int forurensningsMengde() {
		return 100;
	}
}

class Bil implements Forurensning  {
	public int forurensningsMengde() {
		return 100000;
	}	
}