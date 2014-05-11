class Test2 {
    // Main
    public static void main(String[] args) {
        /*
            En måte å gjøre dette på er å først lage en tom resultatarray av riktig lengde, 
            og så gå i en løkke og flytte det til enhver tid minste elementet fra en av de 
            to arrayene over i resultatarrayen.
        */

        String n1 = "Arne";
        String n2 = "Geir";
        String n3 = "Arve";
        String n4 = "Aage";

        String[] a1 = {"Jon", "Nils", "Ragnar"};
        String[] a2 = {"Arne", "Asgeir", "Geir", "Kjell"};

        String[] rs = new String[(a1.length + a2.length)];

        System.out.println("---------------------------------");


        for (int i = 0, j = 0, k = 0; i < a1.length || j < a2.length; k++){
            if (j==a2.length || (i!=a1.length && a1[i].compareTo(a2[j]) <= 0))
                rs[k] = a1[i++];
            else
                rs[k] = a2[j++];
        }

        System.out.println("---------------------------------");

        int p = 0;
        for (String s : rs)
            System.out.println(p++ + ": " + s);


        System.out.println("---------------------------------");
 

 //       System.out.println(n1.compareTo(n4));   // if compareTo(navn) > 0, legg inn foran, ellers bakerst

	}
}