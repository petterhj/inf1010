

class TypeBInjeksjon extends Legemiddel implements Injeksjon{
int styrke;
	TypeBInjeksjon(int nr, String navn, int pris, double mengde, int styrke) {
		super(nr, navn, pris, mengde);
		this.styrke = styrke;
	}
	
	public String hentMengde(){
		return "mg";
	}
}

class TypeBLiniment extends Legemiddel implements Liniment{
	int styrke;
	TypeBLiniment(int nr, String navn, int pris, double mengde, int styrke) {
		super(nr, navn, pris, mengde);
		this.styrke = styrke;
	}
	
	public String hentMengde(){
		return "cm3";
	}
}

class TypeBPille extends Legemiddel implements Piller{
int styrke;
	TypeBPille(int nr, String navn, int pris, double mengde, int styrke) {
		super(nr, navn, pris, mengde);
		this.styrke = styrke;
	}
	
	public String hentMengde(){
		return "piller";
	}
}