// Import
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.ArrayList;



// 	Klasse: Vindu
// =================================================================================
//class Vindu extends JFrame {
class GUIBrett extends JFrame {

	int brettStorrelse = 4;
	int vinduStorrelse = (brettStorrelse * 90);

	GUIBrett(SudokuBeholder beholder) {
		// Tittel
		this.setTitle("Oblig5 - Sudoku");

		System.out.println(beholder.hentAntallLosninger());
		ArrayList<Rute> losning = beholder.taUt(0);
		System.out.println(losning);

		// Brett
		JPanel brettPanel = new JPanel();
		brettPanel.setLayout(new GridLayout(0, this.brettStorrelse));
		brettPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		for (Rute r : losning)
			brettPanel.add(new GUIRute(r));


		// Bunnpanel
		JPanel bunnPanel = new JPanel();
		bunnPanel.setLayout(new BoxLayout(bunnPanel, BoxLayout.LINE_AXIS));
		bunnPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

		JButton knappLosninger = new JButton();
		knappLosninger.setText("Neste løsning");
		knappLosninger.setMargin(new Insets(3, 5, 3, 5));
		bunnPanel.add(knappLosninger);

		JLabel antallLosninger = new JLabel("Viser løsning 1/288 (viser maks 750)");
		antallLosninger.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		bunnPanel.add(antallLosninger);

		Container contentPane = getContentPane();
		contentPane.add(brettPanel, BorderLayout.CENTER);
		contentPane.add(bunnPanel, BorderLayout.PAGE_END);


		// Vis vindu
		this.setSize(this.vinduStorrelse, (this.vinduStorrelse + 60));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}





