// Import
import java.util.ArrayList;


//	Klasse: Løsning
// =================================================================================
class Losning {
	// Variabler
	private int feltStorrelse;
	private ArrayList<Rute> ruter;

	// Konstruktør
	Losning(ArrayList<Rute> ruter) {
		// Feltstorrelse
		this.feltStorrelse = (int) Math.sqrt(ruter.size());

		// Lagre kopi av ruter
		this.ruter = new ArrayList<Rute>();

		for (Rute r : ruter) {
			if (r instanceof StatiskRute)
				this.ruter.add(new StatiskRute(r.hentVerdi()));
			if (r instanceof VariabelRute)
				this.ruter.add(new VariabelRute(r.hentVerdi()));
		}
	}

	// Returner løsningsruter
	public ArrayList<Rute> hentRuter() {
		return this.ruter;
	}

	// String-representasjon
	public String toString() {
		String lStreng = "";

		for (int i = 0; i < this.ruter.size(); i++) {
			Rute r = this.ruter.get(i);

			if ((r.hentVerdi() > 0) && (r.hentVerdi() < 10))
				lStreng += r.hentVerdi();
			else
				lStreng += "ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt((r.hentVerdi()-10));

			if((i+1)%this.feltStorrelse==0)
				lStreng += "//";
		}

		return lStreng;
	}
}