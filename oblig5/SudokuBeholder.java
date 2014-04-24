// Class: SudokuBeholder
class SudokuBeholder {
	// Variabler
	private int antallLosninger;

	// Konstruktør	
	SudokuBeholder() {
		antallLosninger = 0;
	}

	// Sett inn løsning
	public void settInn() {
		this.antallLosninger++;
	}

	// Ta ut løsning
	public void taUt() {
		this.antallLosninger--;
	}

	// Returner antall løsninger
	public int hentAntallLosninger() {
		return this.antallLosninger;
	}
}