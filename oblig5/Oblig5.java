/*
	Sudoku
	======================
	INF1010 - v14 - Oblig5
	petterhj
*/

// Import
import java.awt.GraphicsEnvironment;
import java.io.File;
import javax.swing.JFileChooser;


// Class: Oblig5
// =================================================================================
class Oblig5 {
	// Main
	public static void main(String[] args) throws Exception {
		// Header
		System.out.println("\n============================================================================");
		System.out.println(" Oblig5 - SUDOKU");
		System.out.println("============================================================================\n");

		// Filer
		File innFil = null;
		File utFil = null;

		// Sjekk argumenter
		if (args.length == 0) {
			// Filvelger
			if (!GraphicsEnvironment.getLocalGraphicsEnvironment().isHeadless()) {
				System.out.println("[*] Venter på brettfil...");

				JFileChooser filVelger = new JFileChooser(new File(new File(".").getCanonicalPath()));

				if (filVelger.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					innFil = filVelger.getSelectedFile();
				}
			}
			else {
				System.out.println("[X] Intent vindusmiljø funnet, bruk <innfil> [<utfil>]!");
			}
		}

		// Argumenter
		if (args.length > 0)
			innFil = new File(args[0]);

		if (args.length > 1)
			utFil = new File(args[1]);

		// Klar
		if (innFil != null) {
			if (innFil.isFile()) {
				// Kjør
				Sudoku spill = new Sudoku(innFil, utFil);
			}
			else {
				System.out.println("[X] Brettfilen ser ikke ut til å eksistere!");
			}
		}
		else {
			System.out.println("[X] Ingen brettfil valgt!");
		}

		// Footer
		System.out.println("\n============================================================================");
	}
}