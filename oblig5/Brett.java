// Imports
import java.io.*;
import java.util.ArrayList;


// 	Class: Brett
// =================================================================================
class Brett {
	// Variabler
	private int boksRader;
	private int boksKolonner;
	public int feltStorrelse;						// TODO: PRIVATE

	public Rute[][] ruter;							// TODO: PRIVATE

	private Boks[] bokser;
	private Rad[] rader;
	private Kolonne[] kolonner;

	public ArrayList<Integer> verdier; 				// TODO: PRIVATE

	// Konstruktør
	Brett(int boksRader, int boksKolonner, ArrayList<Integer> verdier) {
		// Opprett tomt brett
		this.boksRader = boksRader;
		this.boksKolonner = boksKolonner;
		this.feltStorrelse = (this.boksRader * this.boksKolonner);
		this.verdier = verdier;

		this.ruter = new Rute[this.feltStorrelse][this.feltStorrelse];

		this.bokser = new Boks[this.feltStorrelse];
		this.rader = new Rad[this.feltStorrelse];
		this.kolonner = new Kolonne[this.feltStorrelse];

		// Generer brett
		this.genererBrett();
	}

	// Generer brett
	private void genererBrett() {
		System.out.println("[*] Genrerer " + this.feltStorrelse + "x" + this.feltStorrelse + " brett...");

		// Sett ruteverdier
		int ant, r, k;
		ant = r = k = 0;
		Rute forrige = null;

		for(int verdi : this.verdier) {
			// Rute
			Rute rute;

			if (verdi == 0)
				rute = new VariabelRute();
			else
				rute = new StatiskRute(verdi);

			// Legg til rute
			this.ruter[r++][k] = rute;

			// Sett nestepeker
			if (forrige != null)
				forrige.settNeste(rute);

			forrige = rute;

			// Ny rad
			if (++ant%this.feltStorrelse == 0) {
				r = 0;
				k++;
			}
		}

		// Opprett antall felter (rader, kolonner, bokser)
		for (int i = 0; i < this.feltStorrelse; i++) {
			this.rader[i] = new Rad(this);
			this.kolonner[i] = new Kolonne(this);
			this.bokser[i] = new Boks(this);
		}

		// Fyll feltruter
		int h = 0;
		int bnr = 0;

		for (int i = 0; i < this.feltStorrelse; i++) {
			for (int j = 0; j < this.feltStorrelse; j++) {
				this.rader[i].settInnRute(this.ruter[j][i]);
				this.kolonner[i].settInnRute(this.ruter[i][j]);

				if (j%this.boksKolonner==0) {
					if (h++%this.boksRader==0)
						bnr = 0;

					bnr++;
				}

				this.bokser[(((i+bnr)-(i%this.boksRader))-1)].settInnRute(this.ruter[j][i]);
			}
		}
	}

	// Returner bokser
	public Boks[] hentBokser() {
		return this.bokser;
	}

	// Returner rader
	public Rad[] hentRader() {
		return this.rader;
	}

	// Returner kolonner
	public Kolonne[] hentKolonner() {
		return this.kolonner;
	}

	// String-representasjon av brett
	public String toString() {
		String brettString = "\n";
		
		for (Rad r : this.hentRader())
			brettString += "\t" + r + "\n";

		return brettString;
	}
}
