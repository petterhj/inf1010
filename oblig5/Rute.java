// Import
import java.util.ArrayList;


//	Klasse: Rute
// =================================================================================
class Rute {
	// Variabler
	private int verdi;
	
	protected Rad rad;
	protected Kolonne kolonne;
	protected Boks boks;

	public Rute neste;

	// Konstruktør
	Rute(int verdi) {
		this.verdi = verdi;
	}

	// Fyll ut resten av brettet
	protected void fyllUtRestenAvBrettet() {
		// Tøm brett f.o.m. denne ruten
		this.rad.hentBrett().tomBrett(this);
	}

	// Felt
	public void settRad(Rad r) { this.rad = r; }
	public void settKolonne(Kolonne k) { this.kolonne = k; }
	public void settBoks(Boks b) { this.boks = b; }

	public Rad hentRad() { return this.rad; }
	public Kolonne hentKolonne() { return this.kolonne; }
	public Boks hentBoks() { return this.boks; }

	// Verdi
	public void settVerdi(int verdi) { this.verdi = verdi; }
	public int hentVerdi() { return this.verdi; }
}


// 	Klasse: StatiskRute
// =================================================================================
class StatiskRute extends Rute {
	// Konstruktør
	StatiskRute(int verdi) {
		super(verdi);
	}

	// Fyll ut resten av brettet
	public void fyllUtRestenAvBrettet() {
		super.fyllUtRestenAvBrettet();

		// Hopp over
		if (this.neste != null)
			this.neste.fyllUtRestenAvBrettet();

		// Siste rute
		if (this.neste == null)
			this.rad.hentBrett().sjekkLosning();
	}
}


// 	Klasse: VariabelRute
// =================================================================================
class VariabelRute extends Rute {
	// Konstruktør
	VariabelRute() {
		super(0);
	}
	VariabelRute(int verdi) {
		super(verdi);
	}

	// Fyll ut resten av brettet
	public void fyllUtRestenAvBrettet() {
		super.fyllUtRestenAvBrettet();

		// Finn mulige verdier
		for (int verdi : this.finnMuligeVerdier()) {
			this.settVerdi(verdi);

			if (this.neste != null)
				this.neste.fyllUtRestenAvBrettet();
		}

		// Siste rute
		if (this.neste == null)
			this.rad.hentBrett().sjekkLosning();
	}

	// Finn mulige verdier i felter
	public ArrayList<Integer> finnMuligeVerdier() {
		ArrayList<Integer> verdier = new ArrayList<Integer>();

		for (int i = 1; i <= this.rad.storrelse(); i++)
			// Sjekk rad
			if (!this.rad.inneholderVerdi(i))
				// Sjekk kolonne
				if (!this.kolonne.inneholderVerdi(i))
					// Sjekk boks
					if (!this.boks.inneholderVerdi(i))
						verdier.add(i);
		
		return verdier;
	}
}