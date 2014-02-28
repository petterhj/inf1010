class Ord {
	String ordet;
	Ord neste; // Peker til neste ord
	
	Ord(String o) {
		ordet = o;
		neste = null;
	}
}

class Ordliste {
	Ord smerte, hjerte;
	Ord førsteord;
	
	Ordliste() {
		Ord o = new Ord("smerte");
		førsteord = o;
		o = new Ord("hjerte");
		førsteord.neste = o;
		o = new Ord("kongen");
		førsteord.neste.neste = o;
		o = new Ord("dronninga");
		førsteord.neste.neste.neste = o;
	}
	
	public void skrivUtAlleOrdeneILista() {
		for (Ord o = førsteord; o != null; o = o.neste)
			System.out.println(o.ordet);
	}
}

class Lenkeliste {
	public static void main(String[] a) {
		Ordliste ol = new Ordliste();
		ol.skrivUtAlleOrdeneILista();
	}
}