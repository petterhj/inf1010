/* Class: Person */
public class Person {
    // Variabler
    private String navn;
	
    private Person[] kjenner;
    private Person[] likerikke;
    private Person forelsketi;
    private Person sammenmed;
    
    private Gave[] mineGaver;
    private String samlerav;
	
    public Person neste;

    // Konstruktør
    Person (String n) {
        this.navn = n;
        this.kjenner = new Person[100];
        this.likerikke = new Person[10];
    }

    // Returner navn
    public String hentNavn() {
        return this.navn;
    }

    // Skriv ut profil
    public void skrivUtAltOmMeg ( ) {
        System.out.print(" " + this.navn + "\n========================================\n");
        skrivUtKjenninger();
		skrivUtLikerIkke();
		skrivUtVenner();

        if (this.forelsketi != null)
            System.out.println("* Forelsket i: " + this.forelsketi.hentNavn());
            
        if (this.sammenmed != null)
            System.out.println("* Sammen med: " + this.sammenmed.hentNavn());
        
		if (this.samlerav != null && this.mineGaver != null) {
			System.out.println("* Samler av: " + this.samlerav + " (" + this.mineGaver.length + " plasser)");
            this.skrivUtSamleobjekter();
		}
        
        System.out.println();
    }


    // ===== Samling og gaver ================
    
    // Samler av
    public void samlerAv(String kategori, int ant) {
        this.samlerav = kategori;
        this.mineGaver = new Gave[ant];
    }
    
    // Får gave
    public Gave gisGave(Gave gave) {
        // Sjekker om interessert i gave
        if (this.erSamlerAv(gave.kategori()) && this.harPlass()) {
            this.leggTilGave(gave);
			return null;
        }
        // Gi gave videre
        else {
            // (1) Forsøk å gi videre til eventuell kjæreste
			if (this.sammenmed != null)
				gave = this.sammenmed.gisRestGave(gave);
				
			// (2) Gi evt. videre til den man er forelsket i
			if (gave != null && this.forelsketi != null)
				gave = this.forelsketi.gisRestGave(gave);
			
			// (3) Gi evt. videre til venner
			if (gave != null) {
				for (Person p : this.hentVenner()) {
					gave = p.gisRestGave(gave);
					
					if (gave == null)
						break;
				}
					
			}
				
            return gave;
        }
    }
    
    // Får viderelevert gave (gis ikke videre, returneres)
    public Gave gisRestGave(Gave gave) {
        // Sjekker om interessert i gave
        if (this.erSamlerAv(gave.kategori()) && this.harPlass()) {
            this.leggTilGave(gave);
			return null;
        }
        
        return gave;
    }
    
    // Sjekker om samler av gavetype
    public boolean erSamlerAv(String kategori) {
        if (kategori.equals(this.samlerav)) return true;
        return false;
    }
    
    // Sjekker om plass i samling
    public boolean harPlass() {
        for (Gave g : this.mineGaver)
            if (g == null) return true;
        return false;
    }
    
    // Legg til gave i samling
    public void leggTilGave(Gave gave) {
        for (int i = 0; i < this.mineGaver.length; i++)
			if (this.mineGaver[i] == null) {
				this.mineGaver[i] = gave;
				break;
			}
    }
    
    // Skriv ut samleobjekter
    public void skrivUtSamleobjekter() {
        System.out.println("* Samling:\t");
		int i = 1;
        for (Gave g : this.mineGaver)
			if (g != null)
				System.out.println("  (" + i++ + ") " + g.kategori().substring(0, 1).toUpperCase() + g.kategori().substring(1) + ": " + g.gaveId());
		System.out.println();
    }
    
    
    // ===== Sosialt nettverk ================
    
    // Legg til bekjent
    public void blirKjentMed(Person p) {
        if (p != this)
            for (int i = 0; i < this.kjenner.length; i++)
                if (this.kjenner[i] == null) {
                    this.kjenner[i] = p;
                    break;
                }
    }
    
    // Legg til uvenn
    public void blirUvennMed(Person p) {
        if (p != this)
            for (int i = 0; i < this.likerikke.length; i++)
                if (this.likerikke[i] == null) {
                    this.likerikke[i] = p;
                    break;
                }
    }

    // Forelskelse
    public void blirForelsketI(Person p) {
        if (p != this && !blantFolk(p, this.likerikke)) this.forelsketi = p;
    }

    // Kjæresteri
    public void blirSammenMed(Person p) {
        if (p != this) {
			// Slå opp med evt tidligere kjæreste
			if (this.sammenmed != null) this.sammenmed.blirSingel();
		
			// Ny kjæreste
			this.sammenmed = p;
		
			// Ny kjæreste blir også kjæreste med denne
			if (!p.erSammenMed(this)) p.blirSammenMed(this);
		}
    }
	
	public void blirSingel() {
		this.sammenmed = null;
	}

    // Sett som venn (fjern fra uvenner)
    public void blirVennMed(Person p) {
        for (int i = 0; i < this.likerikke.length; i++)
            if (this.likerikke[i] == p) {
                this.likerikke[i] = null;
                break;
            }
    }

    // Sjekk om bekjent
    public boolean erKjentMed(Person p) {
        return this.blantFolk(p, kjenner);
    }
    
    // Sjekk om venn
    public boolean erVennMed(Person p) {
        if (blantFolk(p, kjenner) && !blantFolk(p, likerikke)) return true;
        return false;
    }
    
	// Sjekk om kjæreste
	public boolean erSammenMed(Person p) {
		if (p == this.sammenmed) return true;
		return false;
	}
	
    // Returner bestevenn
    public Person hentBestevenn() {
        Person[] venner = this.hentVenner();
        return venner[0];
    }

    // Returner venner
    public Person[] hentVenner() {
        Person[] venner = new Person[this.antVenner()];
        int vi = 0;

        for (int i = 0; i < this.kjenner.length; i++)
            if (this.erVennMed(this.kjenner[i]))
                venner[vi++] = this.kjenner[i];

        return venner;
    }

    // Returner antall venner
    public int antVenner() {
        int ant = 0;

        for (Person p : this.kjenner)
            if (this.erVennMed(p)) ant++;

        return ant;
    }

    // Skriv ut liste over venner
    public void skrivUtKjenninger() {
        System.out.print("* Bekjente: ");
        
        for (Person p : this.kjenner)
            if (p != null) System.out.print(p.hentNavn( ) + ", ");
			
		System.out.println();
    }

    // Skriv ut liste over uvenner
    public void skrivUtLikerIkke() {
        System.out.print("* Misliker: ");
        
        for (Person p : this.likerikke)
            if (p != null) System.out.print(p.hentNavn( ) + ", ");
		
		System.out.println();
    }
    
    // Skriv ut venneliste
    public void skrivUtVenner() {
        System.out.print("* Venner: ");

        for (Person p : this.hentVenner())
            System.out.print(p.hentNavn() + ", ");
			
		System.out.println();
    }


	// ===== Hjelpemetoder ==================
	
    // Sjekk om person er i personarray
    private boolean blantFolk(Person n, Person[] h) {
        for (Person p : h)
            if (p == n) return true;
        return false;
    }

}
