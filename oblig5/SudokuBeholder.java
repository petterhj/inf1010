// Import
import java.util.ArrayList;


// 	Klasse: SudokuBeholder
// =================================================================================
class SudokuBeholder {
	// Konstanter
	private static final int MAX = 750;

	// Variabler
	private Losning tomLosning;
	private ArrayList<Losning> losninger;
	private int antallLosninger;

	// Konstruktør	
	SudokuBeholder(Losning tomLosning) {
		this.tomLosning = tomLosning;
		this.losninger = new ArrayList<Losning>(MAX);
	}

	// Sett inn løsning
	public void settInn(Losning losning) {
		this.antallLosninger++;

		if (this.losninger.size() < MAX) {
			// Legg til
			losninger.add(losning);

			// Skriv ut hele brettet ved første løsning
			// if (this.antallLosninger == 1)
				// System.out.println(brett);

			// Skriv løsning til skjerm
			System.out.println("\t" + this.antallLosninger + ": " + losning);
		}
	}

	// Ta ut løsning
	public Losning taUt(int indeks) {
		if (indeks < this.losninger.size())
			return this.losninger.get(indeks);

		return null;
	}

	// Returner antall løsninger
	public int hentAntallLosninger() {
		return this.antallLosninger;
	}

	// Returnerer tom losning
	public Losning hentTomLosning() {
		return this.tomLosning;
	}
}