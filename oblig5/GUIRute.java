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
	private static final Border KANTLINJE = BorderFactory.createLineBorder(FARGE_MORK_GRAA, 1);

	// Konstruktør
	GUIRute(Rute rute) {
		// Verdi
		super("" + rute.hentVerdi());

		// Egenskaper
		this.setEditable(false);
		this.setFont(FONT);
		this.setHorizontalAlignment(JTextField.CENTER);
		this.setBorder(KANTLINJE);

		if (rute instanceof StatiskRute) {
			//this.setBackground(FARGE_LYS_GRAA);
			this.setBackground(FARGE_HVIT);
			this.setForeground(FARGE_GRONN);
		}
		if (rute instanceof VariabelRute) {
			this.setBackground(FARGE_HVIT);
			this.setForeground(FARGE_GRAA);
		}

	}
}