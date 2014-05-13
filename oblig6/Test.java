class Test {
	// Main
	public static void main(String[] args) {


		// String s1 = "Kjell";
		// String s2 = "Bjarne";

		// System.out.println(s1.compareTo(s2));
		// System.out.println(s2.compareTo(s1));


		String[] range = {
			"Ole", "Per", "Knut", "Kjell", "Asta", "Aage", "Wenche", "Xavier", "Cunt"
		};

		System.out.println();
		for (String s : range)
			System.out.println(s);
		System.out.println();

		// Sort
		for (int i = 0; i < range.length; i++) {
			// Loop through ahead
			for (int j = (i + 1); j < range.length; j++) {
				// Checks whether word range[j] comes *before* range[i] alphabetically
				if (range[j].compareTo(range[i]) < 0) {
					// Swap places
					// 	if range[j] before range[i], move range[j] "upwards"
					String current = range[i];
					range[i] = range[j]; 
					range[j] = current;
				}
			}
		}

		System.out.println();
		for (String s : range)
			System.out.println(s);
		System.out.println();

	}
}