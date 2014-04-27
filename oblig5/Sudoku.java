// Import
import java.io.*;
import java.util.ArrayList;


// 	Klasse: Sudoku
// =================================================================================
class Sudoku {
	// Variabler
	private String brettFil;
	private String losningsFil;

	public SudokuBeholder beholder;
	public Brett brett; 			// TODO: PRIVATE
	private Brett2 brett2;

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
			// Opprett beholder
			this.beholder = new SudokuBeholder();

			// Les brettet
			this.lesInnBrett();

			// Finn løsninger
			this.finnLosninger();

			// Skriv ut første løsning
			this.skrivLosning();
		}
	}

	// Finn losninger
	public void finnLosninger() {
		long startTime = System.currentTimeMillis();

		System.out.println("[*] Leter etter løsninger...");

		// Start i første rute
		this.brett.hentRute(0, 0).fyllUtRestenAvBrettet();

		long stopTime = System.currentTimeMillis();
      	long elapsedTime = (stopTime - startTime);

		System.out.println("[*] Fant totalt " + this.beholder.hentAntallLosninger() + " løsning(er), fullførte på " + elapsedTime + " ms");
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
            ArrayList<Rute> ruter = new ArrayList<Rute>();

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

							ruter.add(new StatiskRute(verdi, null));
						}
						else {
							ruter.add(new VariabelRute(null));
						}

                		verdier.add(verdi);
                	}
                }
            }
            
            data.close();

            // Brett
            brett2 = new Brett2(boksRader, boksKolonner, ruter);
            brett = new Brett(this, boksRader, boksKolonner, verdier);

        } catch(IOException e) {
            // Exit
            System.out.println("[FEIL] Kunne ikke lese datafilen (" + this.brettFil + ")!");
            System.exit(1);
        }
	}

	// Lagre løsning (til fil)
	public void lagreLosning() {

	}

	// Skriv første løsning (til skjerm)
	public void skrivLosning() {
		if (this.beholder.hentAntallLosninger() > 0) {
			System.out.println("[*] Skriver første løsning til skjerm...");

			System.out.println(this.beholder.taUt(0));
		}
	}

	// Returner beholder
	public SudokuBeholder hentBeholder() {
		return this.beholder;
	}
}