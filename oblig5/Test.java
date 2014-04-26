class Test {
	Test(Sudoku spill) {
		System.out.println("\n\n\n==================================================================");
		System.out.println("TESTING\n\n\n");


		SudokuBeholder beholder = spill.hentBeholder();

		System.out.println(beholder.hentAntallLosninger());


		Rute forste = spill.brett.hentRute(0, 0);


		forste.fyllUtRestenAvBrettet();


		System.out.println(beholder.hentAntallLosninger());

		/*
		Rute andre = spill.brett.hentRute(0, 1);
		Rute tredje = spill.brett.hentRute(1, 1);


		forste.settVerdi(2);
		andre.settVerdi(4);
		tredje.settVerdi(3);


		System.out.print("\n" + forste + " (" + forste.x + "x" + forste.y + "): ");

		for (int v : forste.finnMuligeVerdier())
			System.out.print(v + ", ");


		System.out.print("\n" + andre + " (" + andre.x + "x" + andre.y + "): ");

		for (int v : andre.finnMuligeVerdier())
			System.out.print(v + ", ");


		System.out.print("\n" + tredje + " (" + tredje.x + "x" + tredje.y + "): ");

		for (int v : tredje.finnMuligeVerdier())
			System.out.print(v + ", ");



		System.out.println(spill.brett);

		

		System.out.println(spill.brett);
		*/

		/*		
		forste.fyllUtRestenAvBrettet();

		//spill.brett.tomBrett(0, 0);

		System.out.println(spill.brett);
		*/


		//System.out.println(spill.brett);

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