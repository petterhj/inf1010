

class TypeAInjeksjon extends Legemiddel implements Injeksjon {
	int styrke;
	TypeAInjeksjon(int nr, String navn, int pris, double mengde, int styrke) {
		super(nr, navn, pris, mengde);
		this.styrke = styrke;
	}
	
	public String hentMengde(){
		return "mg";
	}
}

class TypeALiniment extends Legemiddel implements Liniment {
	int styrke;
	TypeALiniment(int nr, String navn, int pris, double mengde, int styrke) {
		super(nr, navn, pris,mengde);
		this.styrke = styrke;
	}
	public String hentMengde(){
		return "cm3";
	}
}

class TypeAPille extends Legemiddel implements Piller {
	int styrke;
	TypeAPille(int nr, String navn, int pris, double mengde, int styrke) {
		super(nr, navn, pris, mengde);
		this.styrke = styrke;
	}
	public String hentMengde(){
		return "piller";
	}
}

