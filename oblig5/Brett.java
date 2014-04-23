// Imports
import java.io.*;
import java.util.ArrayList;


// 	Class: Brett
// =================================================================================
class Brett {
	// Variabler
	private int boksRader;
	private int boksKolonner;
	private int feltStorrelse;

	public Rute[][] ruter;			/// TODO: PRIVATE

	private Boks[] bokser;
	public Rad[] rader;				// TODO: PRIVATE
	//private ArrayList<Rad> rader;
	private Kolonne[] kolonner;

	private ArrayList<Integer> verdier;

	// Konstruktør
	Brett(int boksRader, int boksKolonner, ArrayList<Integer> verdier) {
		// Opprett tomt brett
		this.boksRader = boksRader;
		this.boksKolonner = boksKolonner;
		this.feltStorrelse = (this.boksRader * this.boksKolonner);
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
		System.out.println("\n\nBrett:");
		System.out.println("=========================================");
		System.out.println("Boksrader = " + this.boksRader);
		System.out.println("Bokskolonner = " + this.boksKolonner);
		System.out.println("Feltstørrelse = " + this.feltStorrelse);
		System.out.println("Brettstørrelse = " + (this.feltStorrelse*this.feltStorrelse));

		int ri, ki, bi;
		ri = ki = bi = 0;
		/*
		System.out.println("\nRader:");
		System.out.println("=========================================");
		for (Rad r : this.rader)
			System.out.println(ri++ + ": " + r);

		System.out.println("\nKolonner:");
		System.out.println("=========================================");
		for (Kolonne k : this.kolonner)
			System.out.println(ki++ + ": " + k);
		*/
		System.out.println("\nBokser:");
		System.out.println("=========================================");
		for (Boks b : this.bokser)
			System.out.println(bi++ + ": " + b);
	}



	// Generer brett
	private void genererBrett() {
		// Sett ruteverdier
		int ant, r, k;
		ant = r = k = 0;

		for(int verdi : this.verdier) {
			// Rute
			Rute rute;

			if (verdi == 0)
				rute = new VariabelRute();
			else
				rute = new StatiskRute(verdi);

			// Legg til rute
			this.ruter[r][k] = rute;

			r++;

			// Ny rad
			if (++ant%this.feltStorrelse == 0) {
				r = 0;
				k++;
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

		// Bokser
		int h = 0;
		int bnr = 0;

		for (int i = 0; i < this.feltStorrelse; i++) {
			int[][] boks = new int[this.boksKolonner][this.boksRader];

			int b = 0;

			for (int j = 0; j < this.feltStorrelse; j++) {
				if (j%this.boksKolonner==0) {
					if (h%this.boksRader==0) {
						bnr = 0;
						// NY BOKS HER
					}

					System.out.print("\nB" + bnr++ + ": rad=" + i + ", kol=" + j + " ----- ");

					h++;
					b = 0;
				}

				System.out.print(this.ruter[j][i].hentVerdi() + " (" + (bnr-1) +"," + b++ + "), ");
			}


			// if (i%this.y==0)
			// 	h++;
		}
	}
}
