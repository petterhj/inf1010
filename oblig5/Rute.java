// Imports
import java.util.ArrayList;


//	Class: Rute
// =================================================================================
class Rute {
	// Variabler
	private int verdi;

	private Rad rad;
	private Kolonne kolonne;
	private Boks boks;

	private Rute neste;

	// Konstruktør
	Rute(int verdi) {
		this.verdi = verdi;
	}

	// Fyll ut resten av brettet
	public void fyllUtRestenAvBrettet() {
		System.out.println("Sjekker " + this + " (neste: " + this.neste + ")");
		
		if (this instanceof StatiskRute)
			if (this.neste != null)
				this.neste.fyllUtRestenAvBrettet();
		
		if (this instanceof VariabelRute)
			// Prøv mulig verdier
			for (int i = 1; i <= this.rad.storrelse(); i++)
				if (this.sjekkFeltVerdier(i)) {
					// Sett verdi
					this.settVerdi(i);
					
					if (this.neste != null)
						this.neste.fyllUtRestenAvBrettet();
				}
	}

	// Sjekk feltverdier
	public boolean sjekkFeltVerdier(int verdi) {
		// Sjekk rad
		if (!this.rad.inneholderVerdi(verdi))
			// Sjekk kolonne
			if (!this.kolonne.inneholderVerdi(verdi))
				// Sjekk boks
				if (!this.boks.inneholderVerdi(verdi))
					return true;

		return false;
	}

	// Sett forelder-rad
	public void settRad(Rad r) {
		this.rad = r;
	}

	// Sett forelder-kolonne
	public void settKolonne(Kolonne k) {
		this.kolonne = k;
	}

	// Sett forelder-boks
	public void settBoks(Boks b) {
		this.boks = b;
	}

	// Sett nestepeker
	public void settNeste(Rute n) {
		this.neste = n;
	}

	// Sett verdi
	private void settVerdi(int verdi) {
		this.verdi = verdi;
	}

	// Hent verdi
	public int hentVerdi() {
		return this.verdi;
	}
}


// 	Class: StatiskRute
// =================================================================================
class StatiskRute extends Rute {
	// Konstruktør
	StatiskRute(int verdi) {
		super(verdi);
	}
}


// 	Class: VariabelRute
// =================================================================================
class VariabelRute extends Rute {
	// Konstruktør
	VariabelRute() {
		super(0);
	}
}