// Import
import java.util.ArrayList;


// 	Klasse: SudokuBeholder
// =================================================================================
class SudokuBeholder {
	// Variabler
	private static final int MAX = 750;

	private ArrayList<ArrayList<Rute>> losninger;
	private int antallLosninger;

	// Konstruktør	
	SudokuBeholder() {
		losninger = new ArrayList<ArrayList<Rute>>(MAX);
	}

	// Sett inn løsning
	public void settInn(Brett brett) {
		this.antallLosninger++;

		//System.out.println(brett);

		if (this.losninger.size() < MAX) {
			// Lagre kopi av ruter
			ArrayList<Rute> losning = new ArrayList<Rute>();

			for (Rute r : brett.hentRuter()) {
				if (r instanceof StatiskRute)
					losning.add(new StatiskRute(r.hentVerdi()));
				if (r instanceof VariabelRute)
					losning.add(new VariabelRute(r.hentVerdi()));
			}

			losninger.add(losning);

			// Skriv ut hele brettet for første løsning
			if (this.antallLosninger == 1)
				System.out.println(brett);

			// Skriv til skjerm
			System.out.println("\t" + this.antallLosninger + ": " + this.losningsStreng(losning, brett.hentFeltStorrelse()));
		}
	}

	// Skriv løsningsstring
	public String losningsStreng(ArrayList<Rute> losning, int feltStorrelse) {
		String lStreng = "";

		for (int i = 0; i < losning.size(); i++) {
			lStreng += losning.get(i).hentVerdi();

			if((i+1)%feltStorrelse==0)
				lStreng += "//";
		}

		return lStreng;
	}

	// Ta ut løsning
	public ArrayList<Rute> taUt(int indeks) {
		if (indeks < this.losninger.size())
			return this.losninger.get(indeks);

		return null;
	}

	// Returner antall løsninger
	public int hentAntallLosninger() {
		return this.antallLosninger;
	}
}