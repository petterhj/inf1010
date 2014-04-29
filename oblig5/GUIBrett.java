// Import
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.ArrayList;


// 	Klasse: GUIBrett
// =================================================================================
class GUIBrett extends JFrame {
	// Konstruktør
	GUIBrett(SudokuBeholder beholder, int boksRader, int boksKolonner) {
		// Tittel
		this.setTitle("Oblig5 - Sudoku");

		// Brett
		int feltStorrelse = (boksRader * boksKolonner);

		JPanel brettPanel = new JPanel();
		brettPanel.setLayout(new GridLayout(0, feltStorrelse));
		brettPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		// Løsning
		Losning losning = beholder.taUt(0);
		
		// Ruter
		ArrayList<Rute> ruter = beholder.hentTomLosning().hentRuter();

		if (losning != null)
			ruter = losning.hentRuter();

		for (int i = 0; i < ruter.size(); i++)
			brettPanel.add(new GUIRute(i, boksRader, boksKolonner, ruter.get(i)));


		// Bunnpanel
		JPanel bunnPanel = new JPanel();
		bunnPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 25, 10));

		JButton knappLosninger = new JButton();
		knappLosninger.setText("Neste løsning");
		knappLosninger.setEnabled(false);
		bunnPanel.add(knappLosninger);

		if (beholder.hentAntallLosninger() > 1)
			knappLosninger.setEnabled(true);

		JLabel antallLosninger = new JLabel("1/" + beholder.hentAntallLosninger() + " (viser maks 750)");
		bunnPanel.add(antallLosninger);

		Container contentPane = getContentPane();
		contentPane.add(brettPanel, BorderLayout.CENTER);
		contentPane.add(bunnPanel, BorderLayout.PAGE_END);


		// Vis vindu
		this.setSize((feltStorrelse * 45), ((feltStorrelse * 45) + 80));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}





