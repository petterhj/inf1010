// Import
import java.util.ArrayList;


// 	Klasse: SudokuBeholder
// =================================================================================
class SudokuBeholder {
	// Variabler
	private static final int MAX = 750;

	private ArrayList<Brett> losninger;
	private int antallLosninger;

	// Konstruktør	
	SudokuBeholder() {
		losninger = new ArrayList<Brett>(MAX);
	}

	// Sett inn løsning
	public void settInn(Brett brett) {
		this.antallLosninger++;

		if (this.losninger.size() < MAX)
			losninger.add(brett);
	}

	// Ta ut løsning
	public Brett taUt(int indeks) {
		//this.antallLosninger--;
		if (indeks < this.losninger.size())
			return this.losninger.get(indeks);

		return null;
	}

	// Returner antall løsninger
	public int hentAntallLosninger() {
		return this.antallLosninger;
	}
}