
class TypeCInjeksjon extends Legemiddel implements Injeksjon{
	TypeCInjeksjon(int nr, String navn, int pris, double mengde) {
		super(nr, navn, pris, mengde);
	
	}
	public String hentMengde(){
		return "mg";
	}
}

class TypeCLiniment extends Legemiddel implements Liniment{
	TypeCLiniment(int nr, String navn, int pris, double mengde) {
		super(nr, navn, pris, mengde);
		
	}
	
	public String hentMengde(){
		return "cm3";
	}
}

class TypeCPille extends Legemiddel implements Piller{
	
	TypeCPille(int nr, String navn, int pris, double mengde) {
		super(nr, navn, pris, mengde);
		
	}
	
	public String hentMengde(){
		return "piller";
	}
}
