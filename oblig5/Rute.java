//	Class: Rute
// =================================================================================
class Rute {
	// Variabler
	private int verdi;

	// Konstruktør
	Rute(int verdi) {
		this.verdi = verdi;
	}

	// Metoder
	public void fyllUtRestenAvBrettet() {

	}

	// Hent verdi
	public int hentVerdi() {
		return this.verdi;
	}
}


// 	Class: StatiskRute
// =================================================================================
class StatiskRute extends Rute {
	// Variabler

	// Konstruktør
	StatiskRute(int verdi) {
		super(verdi);
	}
}


// 	Class: VariabelRute
// =================================================================================
class VariabelRute extends Rute {
	// Variabler

	// Konstruktør
	VariabelRute() {
		super(0);
	}
	VariabelRute(int verdi) {
		super(verdi);
	}
}