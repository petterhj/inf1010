// Imports
import java.io.*;
import java.util.ArrayList;


// 	Class: Brett
// =================================================================================
class Brett {
	// Variabler
	private int feltStorrelse;

	public Rute[][] ruter;			/// TODO: PRIVATE

	private Boks[] bokser;
	public Rad[] rader;				// TODO: PRIVATE
	//private ArrayList<Rad> rader;
	private Kolonne[] kolonner;

	private ArrayList<Integer> verdier;

	// Konstruktør
	Brett(int x, int y, ArrayList<Integer> verdier) {
		// Opprett tomt brett
		this.feltStorrelse = (x * y);
		this.verdier = verdier;

		this.ruter = new Rute[this.feltStorrelse][this.feltStorrelse];

		this.bokser = new Boks[this.feltStorrelse];
		this.rader = new Rad[this.feltStorrelse];
		//this.rader = new ArrayList<Rad>();
		this.kolonner = new Kolonne[this.feltStorrelse];

		// Generer brett
		this.genererBrett();

		// // Generer rader
		// for (int i = 0; i < this.feltStorrelse; i++) {
		// 	int[] fra = {0, i};
		// 	int[] til = {(this.feltStorrelse-1), i};

		// 	this.rader[i] = (Rad) this.genererFelt(fra, til);
		// }

		// TESTING
		System.out.println("\nRader:");
		System.out.println("=========================================");
		for (Rad r : this.rader)
			// System.out.println(this.rader.indexOf(r) + ": " + r);
			System.out.println(r);

		System.out.println("\nKolonner:");
		System.out.println("=========================================");
		for (Kolonne k : this.kolonner)
			// System.out.println(this.rader.indexOf(r) + ": " + r);
			System.out.println(k);
	}



	// Generer brett
	private void genererBrett() {
		// Sett ruteverdier
		int ant, y, x;
		ant = y = x = 0;

		for(int verdi : this.verdier) {
			// Rute
			Rute rute;

			if (verdi == 0)
				rute = new VariabelRute();
			else
				rute = new StatiskRute(verdi);

			// Legg til rute
			this.ruter[x][y] = rute;

			x++;

			// Ny rad
			if (++ant%this.feltStorrelse == 0) {
				x = 0;
				y++;
			}
		}

		// Rader og kolonner
		for (int i = 0; i < this.feltStorrelse; i++) {
			this.rader[i] = new Rad(this.feltStorrelse);
			this.kolonner[i] = new Kolonne(this.feltStorrelse);

			for (int j = 0; j < this.feltStorrelse; j++) {
				this.rader[i].settInnRute(this.ruter[j][i]);
				this.kolonner[i].settInnRute(this.ruter[i][j]);
			}
		}




		// Løp gjennom gitte verdier
		/*
		for (int i = 0; i < verdier.size(); i++) {
			// Rute
			Rute rute;

			if (verdier.get(i) == 0)
				rute = new VariabelRute(verdier.get(i));
			else
				rute = new StatiskRute(verdier.get(i));


			// Ny rad
			if (i%this.feltStorrelse==0) {
				// Ny rad
				gjeldendeRad = new Rad(this.feltStorrelse);
				this.rader.add(gjeldendeRad);

				// Kolonne
				if ((gjeldendeKolonne == null) || (gjeldendeKolonne.erFull()))
					// Ny kollonne
				gjeldendeKolonne.settInnRute(rute);
			}


			gjeldendeRad.settInnRute(rute);
		}*/


		/*
		int ant, y, x;
		ant = y = x = 0;

		for(String verdi : this.verdier) {
			// Rute
			Rute rute;

			if (verdi.equals(".")) {
				// Variabel verdi
				rute = new VariabelRute();
			}
			else {
				// Statisk verdi
				try {
					rute = new StatiskRute(Integer.parseInt(verdi));
				}
				catch (NumberFormatException e) {
					rute = new StatiskRute(("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(verdi) + 10));
				}
			}

			// Legg til rute
			this.ruter[x][y] = rute;

			x++;

			// Ny rad
			if (++ant%this.feltStorrelse == 0) {
				x = 0;
				y++;
			}
		}
		*/
	}

	// Generer felter
	/*
	public Felt genererFelt(int[] fra, int[] til) {
		System.out.println("[*] Fyller felt fra " + fra[0] + "x" + fra[1] + " til " + til[0] + "x" + til[1]);

		int index = 0;

		Rute[] feltRuter = new Rute[this.feltStorrelse];

		for (int i = fra[0]; i <= til[0]; i++)
			for (int j = fra[1]; j <= til[1]; j++)
				feltRuter[index++] = this.ruter[i][j];
				//System.out.println(i + "x" + j + "=" + this.ruter[i][j].hentVerdi());

		return new Felt(feltRuter);
	}*/

}
