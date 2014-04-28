// Import
import java.util.ArrayList;


//	Klasse: Løsning
// =================================================================================
class Losning {
	// Variabler
	private int feltStorrelse;
	private ArrayList<Rute> ruter;

	// Konstruktør
	Losning(int feltStorrelse, ArrayList<Rute> ruter) {
		this.feltStorrelse = feltStorrelse;
		this.ruter = ruter;
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