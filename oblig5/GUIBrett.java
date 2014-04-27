// Import
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;



// 	Klasse: Vindu
// =================================================================================
//class Vindu extends JFrame {
class GUIBrett extends JFrame {

	int brettStorrelse = 4;
	int vinduStorrelse = (brettStorrelse * 90);

	GUIBrett() {
		// Tittel
		this.setTitle("Oblig5 - Sudoku");

		// Brett
		JPanel brettPanel = new JPanel();
		brettPanel.setLayout(new GridLayout(0, this.brettStorrelse));
		brettPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		/*
		for (int r = 0; r < this.brettStorrelse; r++) {
			for (int k = 0; k < this.brettStorrelse; k++) {
				// Rute
				brettPanel.add(new GUIRute("1", 0));
			}
		}
		*/
		brettPanel.add(new GUIRute("1", 0));
		brettPanel.add(new GUIRute("4", 0));
		brettPanel.add(new GUIRute("2", 1));
		brettPanel.add(new GUIRute("1", 0));
		brettPanel.add(new GUIRute("2", 0));
		brettPanel.add(new GUIRute("4", 0));
		brettPanel.add(new GUIRute("2", 1));
		brettPanel.add(new GUIRute("1", 0));
		brettPanel.add(new GUIRute("3", 0));
		brettPanel.add(new GUIRute("4", 0));
		brettPanel.add(new GUIRute("2", 0));
		brettPanel.add(new GUIRute("3", 1));
		brettPanel.add(new GUIRute("1", 0));
		brettPanel.add(new GUIRute("2", 0));
		brettPanel.add(new GUIRute("4", 1));
		brettPanel.add(new GUIRute("3", 0));

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





