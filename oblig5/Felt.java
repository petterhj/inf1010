// Imports
import java.util.Arrays;
import java.util.ArrayList;


//	Class: Felt
// =================================================================================
class Felt {
	// Variabler
	private ArrayList<Rute> ruter;
	private Brett brett;

	// Konstruktør
	Felt(Brett brett) {
		 this.ruter = new ArrayList<Rute>();
		 this.brett = brett;
	}

	// Sett inn rute
	public void leggTilRute(Rute r) {
		this.ruter.add(r);
	}

	// Returnerer feltruter
	public ArrayList<Rute> hentRuter() {
		return this.ruter;
	}

	// Sjekk om felt inneholder verdi
	public boolean inneholderVerdi(int verdi) {
		for (Rute r : this.hentRuter())
			if (r.hentVerdi() == verdi)
				return true;

		return false;
	}

	// Returnerer størrelse
	public int storrelse() {
		return this.ruter.size();
	}

	// Returner brett
	public Brett hentBrett() {
		return this.brett;
	}


	// String-representasjon
	public String toString() {
		String rep = "";

		for (Rute r : this.hentRuter()) {

			if (r.hentBoks().hentRuter().indexOf(r)%r.hentBoks().hentAntallKolonner()==0)
				rep += " ";

			if (r.hentVerdi() == 0)
				rep += "[_]";
			else
				if (r instanceof StatiskRute)
					rep += "[\033[94m" + r.hentVerdi() + "\033[0m]";
				else
					rep += "[" + r.hentVerdi() + "]";
		}

		return rep;
	}
}


// 	Class: Rad
// =================================================================================
class Rad extends Felt {
	// Konstruktør
	Rad(Brett brett) {
		super(brett);
	}

	// Sett inn rute
	public void settInnRute(Rute r) {
		super.leggTilRute(r);

		r.settRad(this);
	}
}

// 	Class: Kolonne
// =================================================================================
class Kolonne extends Felt {
	// Konstruktør
	Kolonne(Brett brett) {
		super(brett);
	}

	// Sett inn rute
	public void settInnRute(Rute r) {
		super.leggTilRute(r);

		r.settKolonne(this);
	}
}

// 	Class: Boks
// =================================================================================
class Boks extends Felt {
	// Variabler
	int rader;
	int kolonner;

	// Konstruktør
	Boks(Brett brett, int rader, int kolonner) {
		super(brett);

		this.rader = rader;
		this.kolonner = kolonner;
	}

	// Sett inn rute
	public void settInnRute(Rute r) {
		super.leggTilRute(r);

		r.settBoks(this);
	}

	// Returner antall rader i boks
	public int hentAntallRader() {
		return this.rader;
	}

	// Returner kolonner rader i boks
	public int hentAntallKolonner() {
		return this.kolonner;
	}
}
