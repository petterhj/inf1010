class Test {
	Test(Sudoku spill) {
		System.out.println("\n\n\n==================================================================");
		System.out.println("TESTING\n\n\n");


		Rute forste = spill.brett.hentForsteRute();
		Rute siste = spill.brett.hentSisteRute();

		
		forste.fyllUtRestenAvBrettet();


		System.out.println(spill.brett);

		// TESTING
		/*
		System.out.println("\n\nBrett:");
		System.out.println("=========================================");
		// System.out.println("Boksrader = " + this.boksRader);
		// System.out.println("Bokskolonner = " + this.boksKolonner);
		// System.out.println("Feltstørrelse = " + this.feltStorrelse);
		// System.out.println("Brettstørrelse = " + (this.feltStorrelse*this.feltStorrelse));

		int ri, ki, bi;
		ri = ki = bi = 0;

		System.out.println("\nRader:");
		System.out.println("=========================================");
		for (Rad r : spill.brett.hentRader())
			System.out.println(ri++ + ": " + r);

		System.out.println("\nKolonner:");
		System.out.println("=========================================");
		for (Kolonne k : spill.brett.hentKolonner())
			System.out.println(ki++ + ": " + k);

		System.out.println("\nBokser:");
		System.out.println("=========================================");
		for (Boks b : spill.brett.hentBokser())
			System.out.println(bi++ + ": " + b);
		*/



		


		System.out.println("\n\n\n==================================================================");
	}


}