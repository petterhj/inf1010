// Import
import java.io.*;
import java.util.ArrayList;
import java.awt.GraphicsEnvironment;
import javax.swing.JFileChooser;


// 	Klasse: Sudoku
// =================================================================================
class Sudoku {
	// Konstanter
	private static final boolean HAR_VINDUMILJO = !GraphicsEnvironment.getLocalGraphicsEnvironment().isHeadless();

	// Variabler
	private File innFil = null;
	private File utFil = null;

	private Brett brett;

	// Konstruktør
	Sudoku() {
		// Sjekk etter mulighet for GUI
		if (HAR_VINDUMILJO) {
			// Brettfil
			this.innFil = this.visFilVelger();
			//this.innFil = new File("oppgaver/fraoppgave2.txt");	// TEST

			if ((this.innFil != null) && (this.innFil.isFile())) {
				// Les brett
				this.brett = this.lesInnBrett();

				if (this.brett != null) {
					// Finn løsninger
					this.brett.finnLosninger();

					// Løsninger
					GUIBrett vindu = new GUIBrett(this.brett.hentBeholder(), this.brett.hentAntallBoksRader(), this.brett.hentAntallBoksKolonner());
				}
			}
			else {
				System.out.println("[X] Ingen gyldig brettfil angitt!");
			}
		}
		else {
			System.out.println("[X] Kan ikke kjøre GUI-versjon: Intent vindusmiljø funnet.");
		}
	}

	Sudoku(String innFilnavn, String utFilnavn) {
		// Brettfil
		this.innFil = this.finnFil(innFilnavn);

		if ((this.innFil != null) && (this.innFil.isFile())) {
			// Les brett
			this.brett = this.lesInnBrett();

			if (this.brett != null) {
				// Finn løsninger
				this.brett.finnLosninger();

				// Løsninger
				if (utFilnavn != null) {
					this.utFil = this.finnFil(utFilnavn);

					if (this.utFil != null) {
						// Skriv løsninger til fil
						this.lagreLosninger();
					}
					else {
						System.out.println("[X] Ingen gyldig løsningsfil angitt!");
					}
				}
			}
		}
		else {
			System.out.println("[X] Ingen gyldig brettfil angitt!");
		}
	}

	// Les sudokubrett
	public Brett lesInnBrett() {
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
                if (linjeNr == 1) {
                	try {
                		boksRader = Integer.parseInt(dataLinje);
            		}
            		catch (NumberFormatException e) {
            			System.out.println("[X] Feil under brettinnlesing: Mulig feil format!");
            			return null;
            		}
        		}

                // Antall kolonner i boks
                if (linjeNr == 2) {
                	try {
                		boksKolonner = Integer.parseInt(dataLinje);
            		}
            		catch (NumberFormatException e) {
            			System.out.println("[X] Feil under brettinnlesing: Mulig feil format!");
            			return null;
            		}
        		}

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
            return new Brett(boksRader, boksKolonner, ruter);

        } catch(IOException e) {
            System.out.println("[X] Kunne ikke lese brettfilen!");
        	return null;
        }
	}

	// Lagre løsninger (til fil)
	public void lagreLosninger() {
		System.out.println("SKRIV TIL FIL");
	}

	// Filvelger
	public File visFilVelger() {
		System.out.println("[*] Venter på brettfil...");

		//JFileChooser filVelger = new JFileChooser(new File(new File(".").getCanonicalPath()));
		JFileChooser filVelger = new JFileChooser();

		if (filVelger.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			return filVelger.getSelectedFile();

		return null;
	}
		
	// Finn fil
	public File finnFil(String filnavn) {
		if ((filnavn != null) || (filnavn == "")) {
			return new File(filnavn);
		}

		return null;
	}
}