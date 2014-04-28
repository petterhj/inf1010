/*
	Sudoku
	======================
	INF1010 - v14 - Oblig5
	petterhj
*/


//	Class: Oblig5
// =================================================================================
class Oblig5 {
	// Main
	public static void main(String[] args) {
		// Hodetekst
		System.out.println("\n============================================================================");
		System.out.println(" Oblig5 - SUDOKU");
		System.out.println("============================================================================\n");

		// GUI
		if (args.length == 0) {
			// Sudoku
			Sudoku spill = new Sudoku();
		}

		// Argumenter
		else {
			String innFilnavn = null;
			String utFilnavn = null;

			if (args.length > 0)
				innFilnavn = args[0];

			if (args.length > 1)
				utFilnavn = args[1];

			// Sudoku
			Sudoku spill = new Sudoku(innFilnavn, utFilnavn);
		}

		// Fottekst
		System.out.println("\n============================================================================\n");
	}
}