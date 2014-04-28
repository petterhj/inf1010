// Import
import java.io.*;
import java.util.ArrayList;


// 	Klasse: Sudoku
// =================================================================================
class Sudoku {
	// Variabler
	private String brettFil;
	private String losningsFil;

	private File innFil;
	private File utFil;

	public Brett brett; 			// TODO: PRIVATE

	// Konstruktør
	Sudoku(File innFil, File utFil) {
		// Filer
		this.innFil = innFil;
		this.utFil = utFil;


		// Les brettet
		this.lesInnBrett();

		// Finn løsninger
		this.finnLosninger();


		/* TEST */

		GUIBrett gb = new GUIBrett(brett.hentBeholder());

		/* TEST */
	}

	// Les sudokubrett
	public void lesInnBrett() {
		System.out.println("[*] Leser brettfil: \"" + this.innFil.getName() + "\"");

		int boksRader = 0;
		int boksKolonner = 0;

		try {
			System.out.println("[*] Leser inn verdier...");

            BufferedReader data = new BufferedReader(new FileReader(this.innFil));
            
            String dataLinje;
            int linjeNr = 0;
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
                		// Variabel rute
						if (v.equals(".")) {
							ruter.add(new VariabelRute());
						}
						// Statisk rute
                		if ((!v.equals(".")) && (!v.equals(""))) {
	                		try {
								ruter.add(new StatiskRute(Integer.parseInt(v)));
							}
							catch (NumberFormatException e) {
								ruter.add(new StatiskRute(("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(v) + 10)));
							}
						}
                	}
                }
            }
            
            data.close();

            // Brett
            brett = new Brett(boksRader, boksKolonner, ruter);

        } catch(IOException e) {
            // Exit
            System.out.println("[X] Kunne ikke lese brettfilen (" + this.innFil.getName() + ")!\n");
            System.exit(1);
        }
	}

	// Finn losninger
	public void finnLosninger() {
		long startTime = System.currentTimeMillis();

		System.out.println("[*] Leter etter løsninger...");

		this.brett.finnLosninger();

		long stopTime = System.currentTimeMillis();
      	long elapsedTime = (stopTime - startTime);

		System.out.println("\n[*] Fant totalt " + this.brett.hentBeholder().hentAntallLosninger() + " løsning(er), fullførte på " + elapsedTime + " ms");
	}

	// Lagre løsninger (til fil)
	public void lagreLosninger() {

	}
}