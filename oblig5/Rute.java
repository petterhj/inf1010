// Import
import java.util.ArrayList;


//	Klasse: Rute
// =================================================================================
abstract class Rute {
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
	protected abstract void fyllUtRestenAvBrettet();

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
		// Tøm brett f.o.m. denne ruten
		this.rad.hentBrett().tomBrett(this);

		// Hopp over
		if (this.neste != null)
			this.neste.fyllUtRestenAvBrettet();

		// Siste rute
		if (this.neste == null) {
			// Brett
			Brett brett = this.rad.hentBrett();

			// Sjekk om komplett
			if (brett.erUtfylt()) {
				// Lagre kopi av løst brett i beholder
				brett.hentBeholder().settInn(brett);
			}
		}
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
		// Tøm brett f.o.m. denne ruten
		this.rad.hentBrett().tomBrett(this);

		// Finn mulige verdier
		for (int verdi : this.muligeVerdier()) {
			this.settVerdi(verdi);

			if (this.neste != null)
				this.neste.fyllUtRestenAvBrettet();
		}

		// Siste rute
		if (this.neste == null) {
			// Brett
			Brett brett = this.rad.hentBrett();

			// Sjekk om komplett
			if (brett.erUtfylt()) {
				// Lagre kopi av løst brett i beholder
				brett.hentBeholder().settInn(brett);
			}
		}
	}

	// Finn mulige verdier i felter
	public ArrayList<Integer> muligeVerdier() {
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