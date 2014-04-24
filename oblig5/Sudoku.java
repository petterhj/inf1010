// Imports
import java.io.*;
import java.util.ArrayList;


// 	Class: Sudoku
// =================================================================================
class Sudoku {
	// Variabler
	private String brettFil;
	private String losningsFil;

	public Brett brett; 			// TODO: PRIVATE

	// Konstruktør
	Sudoku(String[] args) {
		// Sjekk argumenter
		if (args.length == 1)
			this.brettFil = args[0];
		if (args.length == 2)
			this.losningsFil = args[1];

		// Sjekk om brettfil er gitt
		if (this.brettFil == null) {
			System.out.println("[FEIL] Ingen brettfil angitt, gis med: Oblig5 <brettfil> [løsningsfil]");
		}
		else {
			// Les brettet
			this.lesInnBrett();
		}
	}

	// Finn losninger
	public void finnLosninger() {
		System.out.println(this.brett);
		System.out.println("[*] Leter etter løsninger...");
	}

	// Les sudokubrett
	public void lesInnBrett() {
		System.out.println("[*] Leser brettfil: \"" + this.brettFil + "\"");

		int boksRader = 0;
		int boksKolonner = 0;

		try {
			System.out.println("[*] Leser inn verdier...");

            BufferedReader data = new BufferedReader(new FileReader(this.brettFil));
            
            String dataLinje;
            int linjeNr = 0;
            ArrayList<Integer> verdier = new ArrayList<Integer>();

            while ((dataLinje = data.readLine()) != null) {
            	linjeNr++;

                // Antall rader i boks
                if (linjeNr == 1)
                	boksRader = Integer.parseInt(dataLinje);

                // Antall kolonner i boks
                if (linjeNr == 2)
                	boksKolonner = Integer.parseInt(dataLinje);

                // Verdier
                if (linjeNr > 2) {
                	for (String v : dataLinje.split("")) {
                		int verdi = 0;

                		if (!v.equals(".")) {
	                		try {
								verdi = Integer.parseInt(v);
							}
							catch (NumberFormatException e) {
								verdi = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(v) + 10);
							}
						}

                		verdier.add(verdi);
                	}
                }
            }
            
            data.close();

            // Brett
            brett = new Brett(boksRader, boksKolonner, verdier);

        } catch(IOException e) {
            // Exit
            System.out.println("[FEIL] Kunne ikke lese datafilen (" + this.brettFil + ")!");
            System.exit(1);
        }
	}

	// Lagre løsning (til fil)
	public void lagreLosning() {

	}

	// Skriv løsning (til skjerm)
	public void skrivLosning() {

	}
}