

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Oblig4{
	public static void main(String[] args){
		Applikasjon a = new Applikasjon();
	}
}


class Applikasjon {
	static final String DATAFILE = "data.txt";		 

	Scanner input = new Scanner(System.in); 
	EnkelSortertListe<Lege> legeTab = new EnkelSortertListe<Lege>();
	EnkelReseptListe reseptTab = new EnkelReseptListe();
	Tab<Person> personTab = new Tab<Person>(1000);
	Tab<Legemiddel> legemiddelTab = new Tab<Legemiddel>(1000);

	Applikasjon() {
		// Last inn fil
		this.lesFil();

		// Meny
		System.out.println("=========================================");
		System.out.println(" Leger og resepter");
		System.out.println("=========================================");
		System.out.println();
		System.out.println(" [1] Nytt legemiddel");
		System.out.println(" [2] Ny lege");
		System.out.println(" [3] Ny person");
		System.out.println(" [4] Ny resept\n");

		System.out.println(" [5] Skriv ut alle legemidler");
		System.out.println(" [6] Skriv ut alle leger");
		System.out.println(" [7] Skriv ut avtaleleger");
		System.out.println(" [8] Skriv ut alle personer");
		System.out.println(" [9] Skriv ut person");
		System.out.println(" [10] Hent legemiddel på personnummer\n");

		System.out.println(" [0] Avslutt");

		int option = -1;

		while (option != 0) {
			switch (this.getInt("Velg onsket funksjon", 0, 10)) {
			case 0: System.exit(0); break;
			case 1: this.nyttLegemiddel(); break;
			case 2: this.nyLege(); break;
			case 3: this.nyPerson(); break;
			case 4: this.nyResept(); break;
			case 5: this.skrivLegemidler(); break;
			case 6: this.skrivLeger(); break;
			case 7: this.skrivAvtaleLeger(); break;
			case 8: this.skrivPersoner(); break;
			case 9: this.skrivPerson(); break;
			case 10: this.hentLegemiddel(); break;
			}
		}
	}

	// [1] Nytt legemiddel
	private void nyttLegemiddel() {
		// Input
		String navn = this.getString("Legemiddelnavn");
		String form = this.getString("Form (injeksjon|pille|liniment)");
		int mengde;

		if (form.equals("injeksjon"))
			mengde = this.getInt("Mengde (virkemiddel i mg)", 1, 100);
		else if (form.equals("liniment"))
			mengde = this.getInt("Mengde (cm3 i tube)", 1, 100);
		else
			mengde = this.getInt("Mengde (antall piller)", 1, 100);

		String type = this.getString("Type (a = narkotisk, b = vanedannende, c = vanlig)");

		int styrke;

		if (type.equals("a"))
			styrke = this.getInt("Styrke (narkotisk)", 1, 10);
		else if (type.equals("b"))
			styrke = this.getInt("Styrke (vanedannende)", 1, 10);
		else
			styrke = 0;

		int pris = this.getInt("Pris", 1, 9999);


		int nr = legemiddelTab.hentAntall();
		
		Legemiddel legemiddel = null;
							
		if (form.equals("injeksjon") && type.equals("a")) { legemiddel = new TypeAInjeksjon(nr, navn, pris, mengde, styrke); }
		if (form.equals("injeksjon") && type.equals("b")) { legemiddel = new TypeBInjeksjon(nr, navn, pris, mengde,styrke); }
		if (form.equals("injeksjon") && type.equals("c")) { legemiddel = new TypeCInjeksjon(nr, navn, pris, mengde); }

		if (form.equals("liniment") && type.equals("a")) { legemiddel = new TypeALiniment(nr, navn, pris, mengde, styrke); }
		if (form.equals("liniment") && type.equals("b")) { legemiddel = new TypeBLiniment(nr, navn, pris, mengde, styrke); }
		if (form.equals("liniment") && type.equals("c")) { legemiddel = new TypeCLiniment(nr, navn, pris, mengde); }

		if (form.equals("pille") && type.equals("a")) { legemiddel = new TypeAPille(nr, navn, pris, mengde, styrke); }
		if (form.equals("pille") && type.equals("b")) { legemiddel = new TypeBPille(nr, navn, pris, mengde, styrke); }
		if (form.equals("pille") && type.equals("c")) { legemiddel = new TypeCPille(nr, navn, pris, mengde); }
		
		legemiddelTab.settInn(legemiddel, 0);
	}

	// [2] Ny lege
	private void nyLege() {
		String navn = this.getString("Navn");
		int spesialist = this.getInt("Spesialist (1 = ja/0 = nei)", 0, 1);
		int avtaleNr = this.getInt("Avtalenummer (0 = ingen avtale)", 0, 999999); 

		legeTab.settInn(new Lege(navn, spesialist, avtaleNr));
	}

	// [3] Ny person
	private void nyPerson() {
		String navn = this.getString("Navn");
		String kjonn = this.getString("Kjonn (k/m)");

		personTab.settInn(new Person(personTab.hentAntall(), navn, kjonn), 0);
	}

	// [4] Ny resept
	private void nyResept() {
		// # Resepter (nr, hvit/blå, persNummer, legeNavn, legemiddelNummer, reit) 

		String farge = this.getString("Resepttype (h = hvit, b = blaa)");
		int personNr = this.getInt("Personnummer", 0, 9999);
		String legeNavn = this.getString("Lege (navn)");
		int legeMiddelNr = this.getInt("Legemiddelnummer", 1, 9999);
		int reit = this.getInt("Reit", 1, 99);

		Resepter resept = new Resepter(reseptTab.hentAntall(), farge, personNr, legeNavn, legeMiddelNr, reit); 

		for(int i = 0; i < personTab.hentLength(); i++){
			if(personTab.finn(i) != null){
				if(personTab.finn(i).hentUnikNr() == personNr){
					personTab.finn(i).giResept(resept);
				}
			}
		}

		reseptTab.settInn(resept);
	}

	// [5] Skriv ut legemidler
	private void skrivLegemidler() {
		System.out.println("\n Legemidler:\n--------------------------");	
		for(Legemiddel lm : this.legemiddelTab) {
			System.out.println(" - " + lm);
		}
	}

	// [6] Skriv ut leger
	private void skrivLeger() {
		System.out.println("\n Leger:\n------------------------------");	
		for(Lege l : this.legeTab) {
			System.out.println(" - " + l);
		}
	}
	
	// [7] Skriv ut avtaleleger
	private void skrivAvtaleLeger() {
		System.out.println("\n Avtaleleger:\n------------------------------");	
		for(Lege l : this.legeTab) {
			if(l.hentAvtaleNr() > 0) {
				int ant = 0;
				
				if(l.hentResepter().hentAntall() > 0) {
					for(Resepter r : l.hentResepter()) {
						for(Legemiddel lm : this.legemiddelTab)
							if (r.nr() == lm.hentNr())
								if((lm instanceof TypeAInjeksjon) || (lm instanceof TypeALiniment) || (lm instanceof TypeAPille))
									ant++;
					}
				}
			
				System.out.println(" - " + l.navn() + " (Narkotiske resepter: " + ant + ")");
				
			}
		}
	}

	// [8] Skriv ut personer
	private void skrivPersoner() {
		System.out.println("\n Personer:\n---------------------------");	
		int total_k = 0;
		int total_m = 0;
		for(Person p : this.personTab) {
			int ant = 0;
			
			if(p.hentResepter().hentAntall() > 0) {
				for(Resepter r : p.hentResepter())
					if(r.reit() > 0)
						for(Legemiddel lm : this.legemiddelTab)
							if (r.nr() == lm.hentNr())
								if((lm instanceof TypeBInjeksjon) || (lm instanceof TypeBLiniment) || (lm instanceof TypeBPille)) {
									ant++;
									
									if (p.hentKjonn().equals("k")) total_k++;
									if (p.hentKjonn().equals("m")) total_m++;
								}
			}
			
			System.out.println(" - " + p + " (antall gyldige vanedannende resepter: " + ant + ")");
		}
		
		System.out.println("\n Totalt antall gyldige vanedannende resepter: " + (total_k + total_m));
		System.out.println("  - Kvinner: " + total_k);
		System.out.println("  - Menn: " + total_m);
	}
	
	// [9] Skriv ut person
	private void skrivPerson() {
		int pnr = this.getInt("Personnummer", 0, 999999);
		
		for(Person p : personTab) {
			if(p.hentUnikNr() == pnr) {
				System.out.println(" - Navn: " + p.hentNavn() + " (" + p.hentKjonn() + ")");
				System.out.println(" - P.nr.: " + p.hentUnikNr());
				System.out.println(" - Blå resepter:");
				for(Resepter r : p.hentResepter())
					if(r.hentFarge().equals("b") && r.hentReit() > 0)
						for(Legemiddel lm : legemiddelTab)
							if(lm.hentNr() == r.hentLegeMiddelNr())
								System.out.println("   > " + lm.hentNavn() + " - Gjenværende: " + r.hentReit());
			}
		}
	}

	// [10] Hent legemiddel
	private void hentLegemiddel() {
		int pnr = this.getInt("Personnummer", 0, 999999);
		int rnr = this.getInt("Reseptnummer", 0, 999999);
		
		for(Person p : personTab) {
			if(p.hentUnikNr() == pnr) {
				for(Resepter r : p.hentResepter()) {
					if (r.nr() == rnr) {
						for(Legemiddel lm : legemiddelTab) {
							if(lm.hentNr() == r.hentLegeMiddelNr()) {
								if(r.reit() == 0) {
									System.out.println("Ugyldig resept.");
								}
								else {
									r.brukReit();
									
									System.out.println("Lege: " + r.hentLegeNavn());
									System.out.println("Person: " + p.hentNavn());
									System.out.println("Legemiddel: " + lm + " Enhet: " + lm.hentMengde());
									
								}
							}
						}
					}
				}
			}
		}
	}
	
	
	// Les inn datafil
	private void lesFil() {
		try {
			BufferedReader data = new BufferedReader(new FileReader(DATAFILE));

			String line;
			int count = 0;

			while ((line = data.readLine()) != null) {
				if(line.length() != 0) {
					if(line.contains("#")){
						count++;
					}
					else{
						// Personer
						if(count == 1){
							personTab.settInn(new Person(Integer.parseInt(line.split(", ")[0]), line.split(", ")[1], line.split(", ")[2]), Integer.parseInt(line.split(", ")[0]));
						}
						// Legemidler
						else if(count == 2){
							int nr = Integer.parseInt(line.split(", ")[0]);
							String navn = line.split(", ")[1];
							String form = line.split(", ")[2];
							String type = line.split(", ")[3];
							int pris = Integer.parseInt(line.split(", ")[4]);
							int mengde = Integer.parseInt(line.split(", ")[5]);
							int styrke = 0;
							
							if (type.equals("a") || type.equals("b"))
								styrke = Integer.parseInt(line.split(", ")[6]);
							
							Legemiddel legemiddel = null;
							
							if (form.equals("injeksjon") && type.equals("a")) { legemiddel = new TypeAInjeksjon(nr, navn, pris, mengde, styrke); }
							if (form.equals("injeksjon") && type.equals("b")) { legemiddel = new TypeBInjeksjon(nr, navn, pris, mengde,styrke); }
							if (form.equals("injeksjon") && type.equals("c")) { legemiddel = new TypeCInjeksjon(nr, navn, pris, mengde); }

							if (form.equals("liniment") && type.equals("a")) { legemiddel = new TypeALiniment(nr, navn, pris, mengde, styrke); }
							if (form.equals("liniment") && type.equals("b")) { legemiddel = new TypeBLiniment(nr, navn, pris, mengde, styrke); }
							if (form.equals("liniment") && type.equals("c")) { legemiddel = new TypeCLiniment(nr, navn, pris, mengde); }

							if (form.equals("pille") && type.equals("a")) { legemiddel = new TypeAPille(nr, navn, pris, mengde, styrke); }
							if (form.equals("pille") && type.equals("b")) { legemiddel = new TypeBPille(nr, navn, pris, mengde, styrke); }
							if (form.equals("pille") && type.equals("c")) { legemiddel = new TypeCPille(nr, navn, pris, mengde); }
							
							legemiddelTab.settInn(legemiddel, 0);
							
						}
						// Leger
						else if(count == 3){
							legeTab.settInn(new Lege(line.split(", ")[0], Integer.parseInt(line.split(", ")[1]), Integer.parseInt(line.split(", ")[2])));
						}
						// Resepter
						else if(count == 4){
							// Resept
							Resepter r = new Resepter(Integer.parseInt(line.split(", ")[0]), line.split(", ")[1], Integer.parseInt(line.split(", ")[2]), line.split(", ")[3], Integer.parseInt(line.split(", ")[4]), Integer.parseInt(line.split(", ")[5]));
							
							// Link resept til lege
							for(Lege l : this.legeTab) {
								if(l.navn().equalsIgnoreCase(r.hentLegeNavn()))
									l.hentResepter().settInn(r);
							}
							
							// Link resept til person
							for(Person p : this.personTab)
								if(p.hentUnikNr() == r.hentPersonNr())
									p.giResept(r);
						}
						else {
							break;
						}
					}
				}
			}

			// Lukk fil
			data.close();
			
		} catch(IOException e) {
			System.out.println("FEIL: Kunne ikke aapne fil!");
		}
	}


	// Hjelpefunksjoner
	private int getInt(String query, int min, int max) {
		int i;

		do {
			System.out.print("\n > " + query + " (" + min + "-" + max + "): ");
			while (!input.hasNextInt()) {
				System.out.print(" > Ikke gyldig tall, proov igjen: ");
				input.next();
			}
			i = input.nextInt();
		} while (i < min || i > max);

		input.nextLine();

		return i;
	}

	private String getString(String queryText) {
		System.out.print(" > " + queryText + ": ");

		String choice = input.nextLine().trim();

		return choice;
	}

}
