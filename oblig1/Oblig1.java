class Oblig1 {
    public static void main(String[] args) {
		Testklasse test = new Testklasse();
		test.kjorTest();
    }
}

class Testklasse {
	public void kjorTest() {
		// Studenter
		Person p1 = new Person("Ask", 10);
		Person p2 = new Person("Dana", 10);
		Person p3 = new Person("Tom", 10);
		Person p4 = new Person("Brynjulf", 10);
		
		
		// Student 1: Ask
		p1.blirKjentMed(p2);
		p1.blirKjentMed(p3);
		p1.blirKjentMed(p4);
		p1.blirUvennMed(p2);
		p1.blirUvennMed(p3);
		p1.blirForelsketI(p4);
		
		// Student 2: Dana
		p2.blirKjentMed(p1);
		p2.blirKjentMed(p2);
		p2.blirKjentMed(p3);
		p2.blirKjentMed(p4);
		p2.blirUvennMed(p4);
		p2.blirForelsketI(p3);
		
		// Student 3: Tom
		p3.blirKjentMed(p1);
		p3.blirKjentMed(p2);
		p3.blirKjentMed(p4);
		p3.blirUvennMed(p1);
		p3.blirUvennMed(p4);
		p3.blirForelsketI(p2);
		
		// Student 4: Meg
		p4.blirKjentMed(p1);
		p4.blirKjentMed(p2);
		p4.blirKjentMed(p3);
		
		// Oversikt
		p4.skrivUtAltOmMeg();
		p1.skrivUtAltOmMeg();
		p2.skrivUtAltOmMeg();
		p3.skrivUtAltOmMeg();
		
		// Bestevennen til Dana
		Person[] danasVenner = p2.hentVenner();
		System.out.print("Danas venner (" + p2.antVenner() + "): ");
		for (Person p : danasVenner)
			System.out.print(p.hentNavn() + " ");
		System.out.println("\nDanas bestevenn: " + p2.hentBestevenn().hentNavn());
	}
}

class Person {
	// Variable
    private String navn;
    private Person[] kjenner;	// = bekjente
    private Person[] likerikke; // = uvenner
    private Person forelsketi;
    private Person sammenmed;
    
	
	// Konstruktør
    Person(String n, int lengde) {
		this.navn = n;
		
		this.kjenner = new Person[lengde];
		this.likerikke = new Person[lengde];
    }
	
	// Returner navn
    public String hentNavn() {
        return this.navn;
    }
    
	// Ret
    public boolean erKjentMed(Person p) {
        // Sann hvis Dana kjenner personen p peker på.
        return inArray(p, kjenner);
    }
    
    public void blirKjentMed(Person p) {
        // Dana blir kjent med p, bortsett fra hvis p peker
        // på Dana (Dana kan ikke være kjent med seg selv).
		
		System.out.println(this.hentNavn() + " er seg selv? " + this.erSegSelv(p) + ", p = " + p.hentNavn());
		
		if (!this.erSegSelv(p))
			for (int i = 0; i < this.kjenner.length; i++)
				if (this.kjenner[i] == null) {
					this.kjenner[i] = p;
					break;
				}
    }
    
    public void blirForelsketI(Person p) {
        // Dana blir forelsket i p, bortsett fra hvis p peker på Dana
		if (!this.erSegSelv(p)) this.forelsketi = p;
    }
    
    public void blirUvennMed(Person p) {
        // Dana blir uvenn med p, bortsett fra hvis p peker på Dana
		if (!this.erSegSelv(p))
			for (int i = 0; i < this.likerikke.length; i++)
				if (this.likerikke[i] == null) {
					this.likerikke[i] = p;
					break;
				}
    }
    
    public boolean erVennMed(Person p) {
        // returnerer sann hvis Dana kjenner p og ikke er uvenner med p
        if (inArray(p, kjenner) && !inArray(p, likerikke)) return true;
        return false;
    }
    /*
    public void blirVennMed(Person p) {
        // samme virkning som blirKjentMed(p), men hvis Dana ikke
        // liker p dvs. (likerikke[i] == p) for en gitt i
        // blir likerikke[i] satt til null.
    }*/
    
    public void skrivUtVenner() {
		System.out.println("Venner:");
		
        for (Person p : this.hentVenner())
			System.out.println(" > " + p.hentNavn());
    }
    
    public Person hentBestevenn() {
        // returtypen skal du bestemme
        // returnerer en peker til Danas bestevenn.
        // En persons bestevenn er for enkelhets skyld definert til å være
        // det objektet som pekes på av kjennerarrayens indeks 0.
		Person[] venner = this.hentVenner();
		return venner[0];
    }
    
    public Person[] hentVenner() {
        // returnerer en array som peker på Danas venner
        // Arrayen skal være akkurat så lang at lengden er lik antallet venner,
        // og rekkefølgen skal være den samme som i kjenner-arrayen.
		Person[] venner = new Person[this.antVenner()];
		int vi = 0;
		
		for (int i = 0; i < this.kjenner.length; i++)
			if (this.erVennMed(this.kjenner[i]))
				venner[vi++] = this.kjenner[i];
				
		return venner;
    }
    
    public int antVenner() {
		int ant = 0;
		
        for (Person p : this.kjenner)
			if (this.erVennMed(p)) ant++;
			
		return ant;
    }
    
    // Disse metodene trenger du ikke lage selv, men gjør det gjerne for
    // øvelsens skyld:
    public void skrivUtKjenninger( ) {
        for (Person p : this.kjenner )
            if (p != null) System.out.print(p.hentNavn( ) + " ");
        System.out.println("");
    }
    
    public void skrivUtLikerIkke( ) {
        for (Person p : this.likerikke)
            if (p != null) System.out.print(p.hentNavn( ) + " ");
        System.out.println("");
    }
    
    public void skrivUtAltOmMeg ( ) {
		System.out.print("--- " + this.navn + " ");
		for (int i = 0; i < (50 - this.navn.length()); i++) System.out.print("-");
        System.out.print("\n > Kjenner:\t\t");
        skrivUtKjenninger();
        
        if(forelsketi != null)
            System.out.println(" > Er forelsket i\t" + this.forelsketi.hentNavn());
        
        System.out.print(" > Liker ikke:\t\t");
        skrivUtLikerIkke();
		
		System.out.println();
    }
    
	// Hjelpemetoder
    private boolean inArray(Person n, Person[] h) {
        for (Person p : h)
            if (p == n) return true;
        return false;
    }
	
	private boolean erSegSelv(Person p) {
		if (this == p) return true;
		return false;
	}
}
