// Imports
import java.util.ArrayList;


//	Class: Felt
// =================================================================================
class Felt {
	// Variabler
	private int storrelse;
	private ArrayList<Rute> ruter;
	//Rute[] ruter;

	// Konstruktør
	Felt(int storrelse) {
		 this.ruter = new ArrayList<Rute>(storrelse);
	}

	// Sett inn rute
	public void settInnRute(Rute r) {
		this.ruter.add(r);
	}

	// Returnerer felter
	public ArrayList<Rute> hentRuter() {
		return this.ruter;
	}

	// Returner summerte ruteverdier
	public int feltSum() {
		// TODO
		return 0;
	}

	// Returnerer antall statiske ruter
	public int antallStatiskeRuter() {
		// TODO
		return 0;
	}

	// Sjekker om felt er fult
	public boolean erFull() {
		if (this.ruter.size() == this.storrelse)
			return true;
		return false;
	}
}


// 	Class: Boks
// =================================================================================
class Boks extends Felt {
	// Variabler
	

	// Konstruktør
	Boks(int storrelse) {
		super(storrelse);
	}

	// String-representasjon
	public String toString() {
		String boksstring = "";

		for (Rute r : super.hentRuter())
			if (r.hentVerdi() == 0)
				boksstring += "[_]";
			else
				boksstring += "[" + r.hentVerdi() + "]";

		return boksstring;
	}
}


// 	Class: Kolonne
// =================================================================================
class Kolonne extends Felt {
	// Variabler
	

	// Konstruktør
	Kolonne(int storrelse) {
		super(storrelse);
	}

	// String-representasjon
	public String toString() {
		String kolstring = "";

		for (Rute r : super.hentRuter())
			if (r.hentVerdi() == 0)
				kolstring += "[_]";
			else
				kolstring += "[" + r.hentVerdi() + "]";

		return kolstring;
	}
}


// 	Class: Rad
// =================================================================================
class Rad extends Felt {
	// Variabler
	

	// Konstruktør
	Rad(int storrelse) {
		super(storrelse);
	}

	// String-representasjon
	public String toString() {
		String radstring = "";

		for (Rute r : super.hentRuter())
			if (r.hentVerdi() == 0)
				radstring += "[_]";
			else
				radstring += "[" + r.hentVerdi() + "]";

		return radstring;
	}
}