// Imports
import java.io.*;
import java.util.ArrayList;


// 	Class: Brett
// =================================================================================
class Brett {
	// Variabler
	private Sudoku spill;

	private int boksRader;
	private int boksKolonner;
	public int feltStorrelse;						// TODO: PRIVATE

	public Rute[][] ruter;							// TODO: PRIVATE

	private Boks[] bokser;
	private Rad[] rader;
	private Kolonne[] kolonner;

	public ArrayList<Integer> verdier; 				// TODO: PRIVATE

	// Konstruktør
	Brett(Sudoku spill, int boksRader, int boksKolonner, ArrayList<Integer> verdier) {
		this.spill = spill;

		// Opprett tomt brett
		this.boksRader = boksRader;
		this.boksKolonner = boksKolonner;
		this.feltStorrelse = (this.boksRader * this.boksKolonner);
		this.verdier = verdier;

		this.ruter = new Rute[this.feltStorrelse][this.feltStorrelse];

		this.bokser = new Boks[this.feltStorrelse];
		this.rader = new Rad[this.feltStorrelse];
		this.kolonner = new Kolonne[this.feltStorrelse];

		System.out.println("[*] Genrerer " + this.feltStorrelse + "x" + this.feltStorrelse + " brett, med " + this.boksRader + "x" + this.boksKolonner + " bokser...");

		// Fordel verdier
		this.fordelVerdier();
	}

	// Fordel verdier
	private void fordelVerdier() {
		System.out.println("[*] Fordeler forhåndsgitte verdier...");

		// Sett ruteverdier
		int ant, r, k;
		ant = r = k = 0;
		Rute forrige = null;

		for(int verdi : this.verdier) {
			// Rute
			Rute rute;

			if (verdi == 0)
				rute = new VariabelRute(this);
			else
				rute = new StatiskRute(verdi, this);

			// Legg til rute
			int asd = r++;
			rute.settPos(asd, k);
			this.ruter[asd][k] = rute;

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
			this.rader[i] = new Rad();
			this.kolonner[i] = new Kolonne();
			this.bokser[i] = new Boks(boksRader, boksKolonner);
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

		System.out.println(this);
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

	// Returnerer rute
	public Rute hentRute(int x, int y) {
		return this.ruter[x][y];
	}

	// Returnerer spillobjekt
	public Sudoku hentSpill() {
		return this.spill;
	}

	// Sjekk om komplett
	public boolean erUtfylt() {
		for (int i = 0; i < this.feltStorrelse; i++)
			for (int j = 0; j < this.feltStorrelse; j++)
				if (this.ruter[j][i].hentVerdi() == 0)
					return false;

		return true;
	}

	// Tøm brett fra koordinat
	public void tomBrett(Rute r) {
		if (r != null) {
			if (r instanceof VariabelRute)
				r.settVerdi(0);

			this.tomBrett(r.neste);
		}
	}

	// String-representasjon av brett
	public String toString() {
		String cB = "\033[32m";
		String cM = "\033[37m";
		String cW = "\033[0m";

		String brettString = "";
		int linjeLengde = (this.feltStorrelse*4);

		brettString += "\t" + cB;
		for (int i = 0; i < linjeLengde; i++)
			if (i%4==0) 
				brettString += "+";
			else
				brettString += "-";
		brettString += "+" + cW + "\n";

		for (int j = 0; j < this.hentRader().length; j++) {
			brettString += "\t" + cB + "|" + cW + this.hentRader()[j] + "\n";
			brettString += "\t";
			for (int i = 0; i < linjeLengde; i++)
				if (i%4==0)
					if ((i == 0) || (j == (this.hentRader().length-1)) || ((j+1)%this.boksRader==0))
						brettString += cB + "+" + cW;
					else
						if (i%(4*this.boksKolonner)==0)
							brettString += cB + "+" + cW;
						else
							brettString += cM + "+" + cW;
				else
					if ((j == (this.hentRader().length-1)) || ((j+1)%this.boksRader==0))
						brettString += cB + "-" + cW;
					else
						brettString += cM + "-" + cW;
			brettString += cB + "+" + cW + "\n";
		}

		return brettString;
	}
	/*
	public String toString() {
		String brettString = "";
		String brettLinje = "\t+";
		int linjeLengde = ((this.feltStorrelse*3) + (this.feltStorrelse/this.boksKolonner) + 1);
		
		for (int i = 0; i < this.hentRader().length; i++) {
			if ((i != 0) && (i%this.boksRader==0)) {
				brettString += "\t|";
				for (int j = 0; j < linjeLengde; j++)
					brettString += " ";
				brettString += "|\n";
			}


			brettString += "\t|" + this.hentRader()[i] + " |\n";
		}

		for (int i = 0; i < linjeLengde; i++)
			brettLinje += "-";

		brettLinje += "+\n";

		return brettLinje + brettString + brettLinje;
	}*/
}
