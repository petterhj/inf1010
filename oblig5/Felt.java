// Imports
import java.util.Arrays;
import java.util.ArrayList;


//	Klasse: Felt
// =================================================================================
class Felt {
	// Variabler
	private ArrayList<Rute> ruter;

	// Konstruktør
	Felt() {
		 this.ruter = new ArrayList<Rute>();
	}

	// Sett inn rute
	public void leggTilRute(Rute r) {
		this.ruter.add(r);
	}

	// Returnerer feltruter
	public ArrayList<Rute> hentRuter() {
		return this.ruter;
	}

	// Sjekk om felt inneholder gitt verdi
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

	// String-representasjon
	public String toString() {
		String cB = "\033[32m";
		String cM = "\033[37m";
		String cR = "\033[91m";
		String cW = "\033[0m";
		String rad = "";

		for (int i = 0; i < this.hentRuter().size(); i++) {
			Rute r = this.hentRuter().get(i);
			String v = " ";
			if (r.hentVerdi() != 0) {
				if ((r.hentVerdi() > 0) && (r.hentVerdi() < 10))
					v = "" + r.hentVerdi();
				else
					v = "" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt((r.hentVerdi()-10));
			}

			rad += " ";
			if (r instanceof StatiskRute)
				rad += cR + v + cW;
			if (r instanceof VariabelRute) {
				rad += v;
			}
			if ((i+1)%r.hentBoks().hentAntallKolonner()==0)
				rad += cB + " |" + cW;
			else
				rad += cM + " |" + cW;
		}

		return rad;
	}
}


// 	Klasse: Rad
// =================================================================================
class Rad extends Felt {
	// Sett inn rute
	public void settInnRute(Rute r) {
		super.leggTilRute(r);

		r.settRad(this);
	}
}

// 	Klasse: Kolonne
// =================================================================================
class Kolonne extends Felt {
	// Sett inn rute
	public void settInnRute(Rute r) {
		super.leggTilRute(r);

		r.settKolonne(this);
	}
}

// 	Klasse: Boks
// =================================================================================
class Boks extends Felt {
	// Variabler
	int rader;
	int kolonner;

	// Konstruktør
	Boks(int rader, int kolonner) {
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
