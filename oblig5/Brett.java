// Import
import java.io.*;
import java.util.ArrayList;


class Brett {
	// Størrelse
	private int boksRader;
	private int boksKolonner;
	private int feltStorrelse;
	private int brettStorrelse;

	// Ruter
	private Rute[][] ruter;

	// Felter
	private Boks[] bokser;
	private Rad[] rader;
	private Kolonne[] kolonner;

	// Beholder
	private SudokuBeholder beholder;

	// Konstruktør
	Brett(int boksRader, int boksKolonner, ArrayList<Rute> ruter) {
		// Beholder
		this.beholder = new SudokuBeholder();

		// Opprett brett
		this.boksRader = boksRader;
		this.boksKolonner = boksKolonner;
		this.feltStorrelse = (this.boksRader * this.boksKolonner);
		this.brettStorrelse = (this.feltStorrelse * this.feltStorrelse);

		System.out.println("[*] Genrerer " + this.feltStorrelse + "x" + this.feltStorrelse + " brett, med " + this.boksRader + "x" + this.boksKolonner + " bokser...");

		// Felter
		this.bokser = new Boks[this.feltStorrelse];
		this.rader = new Rad[this.feltStorrelse];
		this.kolonner = new Kolonne[this.feltStorrelse];

		for (int i = 0; i < this.feltStorrelse; i++) {
			// Felter
			this.rader[i] = new Rad(this);
			this.kolonner[i] = new Kolonne(this);
			this.bokser[i] = new Boks(this, this.boksRader, this.boksKolonner);
		}

		// Ruter
		this.ruter = new Rute[this.feltStorrelse][this.feltStorrelse];

		int b = 0;
		int h = 0;
		Rute forrige = null;

		for (int r = 0; r < this.feltStorrelse; r++) {
			for (int k = 0; k < this.feltStorrelse; k++) {
				if (k%this.boksKolonner==0) {
					if (h++%this.boksRader==0)
						b = 0;
					b++;
				}

				int rNr = ((r * this.feltStorrelse) + k);
				int bNr = (((r+b)-(r%this.boksRader))-1);

				Rute rute = ruter.get(rNr);

				this.ruter[r][k] = rute;
				this.rader[r].settInnRute(rute);
				this.kolonner[k].settInnRute(rute);
				this.bokser[bNr].settInnRute(rute);

				// Nestepekere
				if (forrige != null)
					forrige.neste = rute;

				forrige = rute;
			}
		}

		System.out.println(this);
	}

	// Sjekk løsning
	public void sjekkLosning() {
		if (this.erUtfylt())
			// Legg til løsning
			this.hentBeholder().settInn(new Losning(this.hentFeltStorrelse(), this.hentRuter()));
	}

	// Sjekk om komplett
	public boolean erUtfylt() {
		for (int i = 0; i < this.feltStorrelse; i++)
			for (int j = 0; j < this.feltStorrelse; j++)
				if (this.ruter[j][i].hentVerdi() == 0)
					return false;

		return true;
	}

	// Tøm brett fra f.o.m. rute
	public void tomBrett(Rute r) {
		if (r != null) {
			if (r instanceof VariabelRute)
				r.settVerdi(0);

			this.tomBrett(r.neste);
		}
	}

	// Felter
	public int hentFeltStorrelse() { return this.feltStorrelse; }
	public int hentAntallBoksRader() { return this.boksRader; }
	public int hentAntallBoksKolonner() { return this.boksKolonner; }
	public Boks[] hentBokser() { return this.bokser; }
	public Rad[] hentRader() { return this.rader; }
	public Kolonne[] hentKolonner() { return this.kolonner; }

	// Returnerer ruter
	public ArrayList<Rute> hentRuter() {
		ArrayList<Rute> ruter = new ArrayList<Rute>();

		for (int r = 0; r < this.feltStorrelse; r++)
			for (int k = 0; k < this.feltStorrelse; k++)
				ruter.add(this.ruter[r][k]);

		return ruter;
	}

	// Returner beholder
	public SudokuBeholder hentBeholder() {
		return this.beholder;
	}

	// Finn løsninger
	public void finnLosninger() {
		long startTime = System.currentTimeMillis();

		System.out.println("[*] Leter etter løsninger...");

		// Start i første rute
		this.ruter[0][0].fyllUtRestenAvBrettet();

		long stopTime = System.currentTimeMillis();
      	long elapsedTime = (stopTime - startTime);

      	if (this.hentBeholder().hentAntallLosninger() > 0)
			System.out.println("\n[*] Fant totalt " + this.hentBeholder().hentAntallLosninger() + " løsning(er) (brukte " + elapsedTime + " ms.)");
		else
			System.out.println("[*] Fant ingen løsninger (brukte " + elapsedTime + " ms.)");
	}


	// String-representasjon av brett
	public String toString() {
		String cB = "\033[91m";
		String cM = "\033[37m";
		String cW = "\033[0m";

		if (System.getProperty("os.name").startsWith("Windows"))
			cB = cM = cW = "";

		String brettString = "\n";
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
}