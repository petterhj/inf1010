	// Import
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.ArrayList;


// 	Klasse: GUIBrett
// =================================================================================
class GUIBrett extends JFrame {
	// Variabler
	private JPanel brettPanel;
	private JPanel bunnPanel;
	private JButton knappLosninger;
	private JLabel antallLosninger;

	private int boksRader;
	private int boksKolonner;

	private SudokuBeholder beholder;
	private int maksLagrede;
	private int losningsIndeks = 0;
	private Losning losning;

	private HendelsesBehandler hendelse = new HendelsesBehandler();

	// Konstruktør
	GUIBrett(SudokuBeholder beholder, int boksRader, int boksKolonner) {
		// Størrelse
		this.boksRader = boksRader;
		this.boksKolonner = boksKolonner;

		// Beholder
		this.beholder = beholder;
		this.maksLagrede = beholder.hentMaks();

		// Brett
		this.brettPanel = new JPanel();
		this.brettPanel.setLayout(new GridLayout(0, (boksRader * boksKolonner)));
		this.brettPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

		// Bunnpanel
		this.bunnPanel = new JPanel();
		this.bunnPanel.setLayout(new BoxLayout(this.bunnPanel, BoxLayout.Y_AXIS));
		this.bunnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		this.antallLosninger = new JLabel();
		this.antallLosninger.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
		this.bunnPanel.add(antallLosninger);

		this.knappLosninger = new JButton();
		this.knappLosninger.setText("Neste løsning");
		this.knappLosninger.addActionListener(this.hendelse);
		this.bunnPanel.add(this.knappLosninger);


		JLabel maks = new JLabel("Viser maks " + this.maksLagrede);
		maks.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));
		maks.setForeground(new Color(120, 120, 120));
		this.bunnPanel.add(maks);

		// Konteiner
		Container konteiner = getContentPane();
		konteiner.add(this.brettPanel, BorderLayout.CENTER);
		konteiner.add(this.bunnPanel, BorderLayout.PAGE_END);

		// Legg til første løsning
		ArrayList<Rute> ruter = this.beholder.hentTomLosning().hentRuter();
		Losning losning = this.beholder.taUt(losningsIndeks);

		if (losning != null)
			ruter = losning.hentRuter();

		// Fyll brett
		this.fyllBrett(ruter);

		// Vis vindu
		this.setTitle("Oblig5 - Sudoku");
		this.setSize(((this.boksRader * this.boksKolonner) * 45), (((this.boksRader * this.boksKolonner) * 45) + 100));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	// Fyll brett
	private void fyllBrett(ArrayList<Rute> ruter) {
		// Tøm brett
		this.brettPanel.removeAll();

		// Ruter
		for (int i = 0; i < ruter.size(); i++)
			this.brettPanel.add(new GUIRute(i, this.boksRader, this.boksKolonner, ruter.get(i)));

		// Antall løsninger
		this.oppdaterAntallLosninger();

		// Tegn på nytt
		this.revalidate();
		this.repaint();
	}

	// Oppdater antall løsninger
	private void oppdaterAntallLosninger() {
		int antall = this.beholder.hentAntallLosninger();

		// Tekst
		if (antall == 0)
			this.antallLosninger.setText("Ingen løsninger");
		if (antall > 0)
			this.antallLosninger.setText("Løsning: " + (this.losningsIndeks+1) + "/" + this.beholder.hentAntallLosninger());

		// Knapp
		if (((this.losningsIndeks+1) == antall) || ((this.losningsIndeks+1) == this.maksLagrede)) {
			this.knappLosninger.setEnabled(false);
		}
	}

	// Hendelsesbehandler
	class HendelsesBehandler implements ActionListener {
		public void actionPerformed(ActionEvent e){
			// Hent neste løsning
			Losning losning = GUIBrett.this.beholder.taUt(++GUIBrett.this.losningsIndeks);

			if (losning != null)
				GUIBrett.this.fyllBrett(losning.hentRuter());
		}
	}
}