/*
	Sudoku
	======================
	INF1010 - v14 - Oblig5
	petterhj
*/


// Class: Oblig5
// =================================================================================
class Oblig5 {
	// Main
	public static void main(String[] args) {
		// Header
		System.out.println("\n============================================================================");
		System.out.println(" Oblig5 - SUDOKU");
		System.out.println("============================================================================\n");

		// Kjør
		Sudoku spill = new Sudoku(args);

		// Test
		Test test = new Test(spill);

		// Footer
		System.out.println("\n============================================================================");
	}
}