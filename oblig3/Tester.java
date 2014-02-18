// Class: Tester
class Tester {
	// Kjør test
	public void kjor() {
        // (1) Legg personer inn i lenkelista
		Personer personer = new Personer();
		
		ListeAvPersoner liste = new ListeAvPersoner();
		
		Person p = personer.hentPerson();
		
		while (p != null) {
			liste.settInnSist(p);
			p = personer.hentPerson();
		}
		
		// (2) Gavedryss
		GaveLager gavelager = new GaveLager();
		
		String[] personnavn = personer.hentPersonnavn();
		
		Gave gave = gavelager.hentGave();
		
		while (gave != null) {
			// Sjekk om noen vil ha
			for (String navn : personnavn) {
				Person pers = liste.finnPerson(navn);
				Gave retur = pers.gisGave(gave);
				
				if (retur == null)
					break;
			}
			
			// Hent ut ny gave
			gave = gavelager.hentGave();
		}
		
		// (3) Personoversikt
		liste.skrivAlle();
	}
}