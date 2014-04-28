// Import
import java.util.ArrayList;


//	Klasse: Løsning
// =================================================================================
class Losning {
	// Variabler
	private int boksRader;
	private int boksKolonner;
	private int feltStorrelse;
	private ArrayList<Rute> ruter;

	// Konstruktør
	Losning(int boksRader, int boksKolonner, ArrayList<Rute> ruter) {
		// Størrelsesverdier
		this.boksRader = boksRader;
		this.boksKolonner = boksKolonner;
		this.feltStorrelse = (this.boksRader * this.boksKolonner);
		
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

	// Returner boksrader
	public int hentBoksRader() {
		return this.boksRader;
	}

	// Returner bokskolonner
	public int hentBoksKolonner() {
		return this.boksKolonner;
	}

	// String-representasjon
	public String toString() {
		String lStreng = "";

		for (int i = 0; i < this.ruter.size(); i++) {
			lStreng += this.ruter.get(i).hentVerdi();

			if((i+1)%this.feltStorrelse==0)
				lStreng += "//";
		}

		return lStreng;
	}
}