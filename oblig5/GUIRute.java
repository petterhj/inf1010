// Import
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;


// 	Klasse: GUIRute
// =================================================================================
class GUIRute extends JTextField {
	// Konstanter
	private static final Font FONT = new Font("Verdana", Font.BOLD, 18);
	private static final Color FARGE_HVIT = new Color(255, 255, 255);
	private static final Color FARGE_LYS_GRAA = new Color(239, 239, 239);
	private static final Color FARGE_MORK_GRAA = new Color(64, 64, 64);
	private static final Color FARGE_GRAA = new Color(128, 128, 128);
	private static final Color FARGE_GRONN = new Color(61, 115, 229);
	private static final Color FARGE_ROD = new Color(255, 63, 95);

	// Variabler
	private int indeks;
	private int boksRader;
	private int boksKolonner;
	private int boksStorrelse;
	private Rute rute;

	// Konstruktør
	GUIRute(int indeks, int boksRader, int boksKolonner, Rute rute) {
		// Verdi
		int verdi = rute.hentVerdi();
		String v = "";

		if ((verdi > 0) && (verdi < 10))
			v += verdi;
		else if (verdi >= 10)
			v += "ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt((verdi-10));

		this.setText(v);

		// Rute
		this.indeks = indeks;
		this.boksRader = boksRader;
		this.boksKolonner = boksKolonner;
		this.boksStorrelse = (this.boksRader * this.boksKolonner);
		this.rute = rute;

		// Egenskaper
		this.setEditable(false);
		this.setFont(FONT);
		this.setHorizontalAlignment(JTextField.CENTER);
		this.setBorder(this.genererKantlinje());

		if (rute instanceof StatiskRute) {
			//this.setBackground(FARGE_LYS_GRAA);
			this.setBackground(FARGE_HVIT);
			this.setForeground(FARGE_GRONN);
		}
		if (rute instanceof VariabelRute) {
			this.setBackground(FARGE_HVIT);
			this.setForeground(FARGE_GRAA);

			if (verdi == 0) {
				this.setText(":(");
				this.setForeground(FARGE_ROD);
			}
		}
	}

	// Generer kantlinje
	private Border genererKantlinje() {
		int topp, venstre, bunn, hoyre;
		topp = venstre = bunn = hoyre = 0;

		// Vertikale bokslinjer
		if (this.indeks%this.boksStorrelse == 0)
			venstre = 2;
		else
			venstre = 1;

		if ((this.indeks+1)%this.boksKolonner == 0)
			hoyre = 2;


		// Horisontale bokslinjer
		if ((this.indeks)%(this.boksStorrelse * this.boksRader) == 0)
			topp = 2;
		else
			topp = 1;

		for (int i = 0; i < this.boksStorrelse; i++) {
			if ((this.indeks-i)%(this.boksStorrelse * this.boksRader) == 0)
				topp = 2;

			if ((this.indeks != 0) && ((this.indeks)%((this.boksStorrelse * this.boksStorrelse) - (i+1)) == 0))
				bunn = 2;
		}

		// Returner kantlinje
		return BorderFactory.createMatteBorder(topp, venstre, bunn, hoyre, FARGE_MORK_GRAA);
	}
}