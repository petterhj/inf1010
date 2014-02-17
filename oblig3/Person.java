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
        System.out.print(" " + this.navn + "\n========================================");
        skrivUtKjenninger();
		skrivUtLikerIkke();

        if (this.forelsketi != null)
            System.out.print("\n > Forelsket i:\t" + this.forelsketi.hentNavn());
            
        if (this.sammenmed != null)
            System.out.print("\n > Sammen med:\t" + this.sammenmed.hentNavn());
        
		if (this.samlerav != null && this.mineGaver != null) {
			System.out.print("\n > Samler av:\t" + this.samlerav);
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
    
    // Bestemmer hvorvidt gave beholdes
    public Gave gisGave(Gave gave) {
        // Sjekker om interessert i gave
        if (this.harPlass() && this.erSamlerAv(gave.kategori())) {
            // BEHOLDE?
			return null;
        }
        // Gi gave videre
        else {
            // Forsøk å gi videre til eventuell kjæreste
            return gave;
        }
    }
    
    // Gi gave videre
    public Gave giGaveVidere(Person p, Gave g) {
        if ((p != null) && (g != null)) {
            g = p.gisGave(g);
        }
        
        return g;
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
        
    }
    
    // Skriv ut samleobjekter
    public void skrivUtSamleobjekter() {
        System.out.println("\n > Samling:\t");

        for (Gave g : this.mineGaver)
			if (g != null)
				System.out.println("  - " + g.gaveId());
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
        System.out.print("\n > Bekjente:\t");
        
        for (Person p : this.kjenner)
            if (p != null) System.out.print(p.hentNavn( ) + ", ");
    }

    // Skriv ut liste over uvenner
    public void skrivUtLikerIkke() {
        System.out.print("\n > Misliker:\t");
        
        for (Person p : this.likerikke)
            if (p != null) System.out.print(p.hentNavn( ) + ", ");
    }
    
    // Skriv ut venneliste
    public void skrivUtVenner() {
        System.out.println("\n > Venner:\t");

        for (Person p : this.hentVenner())
            System.out.print(p.hentNavn() + ", ");
    }


	// ===== Hjelpemetoder ==================
	
    // Sjekk om person er i personarray
    private boolean blantFolk(Person n, Person[] h) {
        for (Person p : h)
            if (p == n) return true;
        return false;
    }

}
