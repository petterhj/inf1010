// Class: Tester
class Tester {
    // Students
    

    // Run tests
    public void kjor() {
        //
        Personer personer = new Personer();
        
        // Personer
        Person ask = new Person("Ask");
        Person dana = new Person("Dana");
        Person tom = new Person("Tom");
        Person brynjulf = new Person("Brynjulf");
		Person uwe = new Person("Uwe");
        
		
		/*
        Person[] prs = new Person[4];
        prs[0] = ask;
        prs[1] = dana;
        prs[2] = null;
        prs[3] = brynjulf;
        
        for (Person p : prs)
            System.out.println(p);
		*/

        // Student 1: Ask
        ask.blirKjentMed(brynjulf);
        ask.blirKjentMed(tom);
        ask.blirKjentMed(dana);
        ask.blirUvennMed(dana);
        ask.blirUvennMed(tom);
        ask.blirSammenMed(brynjulf);

        // Student 2: Dana
        dana.blirKjentMed(ask);
        dana.blirKjentMed(tom);
        dana.blirKjentMed(brynjulf);
        dana.blirUvennMed(brynjulf);
        dana.blirForelsketI(tom);
		dana.blirSammenMed(tom);

        // Student 3: Tom
        tom.blirKjentMed(brynjulf);
        tom.blirKjentMed(ask);
        tom.blirKjentMed(dana);
        tom.blirUvennMed(ask);
        tom.blirUvennMed(brynjulf);
        //tom.blirSammenMed(uwe);

        // Student 4: Brynjulf
        brynjulf.blirKjentMed(ask);
        brynjulf.blirKjentMed(dana);
        brynjulf.blirKjentMed(tom);
		
        // Student 5: Uwe
        //uwe.blirSammenMed(dana);
		uwe.samlerAv("bok", 10);
		

        // Oversikt
        System.out.println();
        
        brynjulf.skrivUtAltOmMeg();
        System.out.println();
        
        ask.skrivUtAltOmMeg();
        System.out.println();
        
        dana.skrivUtAltOmMeg();
        System.out.println();
        
        tom.skrivUtAltOmMeg();
        System.out.println();
		
        uwe.skrivUtAltOmMeg();
        System.out.println();
        
        
        
        
        
        // while (personer.hentPerson() != null) {
            // Person p = personer.hentPerson();
            
            // System.out.println(p.hentNavn());
        // }
    }
}