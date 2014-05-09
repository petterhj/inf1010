

public class Legemiddel {
	private int id;
	private String navn;
	private int pris;
	protected double mengde;

	Legemiddel(int nr, String navn, int pris, double mengde) {
		this.id = nr;
		this.navn = navn;
		this.pris = pris;
		this.mengde = mengde;
	}
	
	public int hentNr() { return this.id; }
	public int hentPris() { return this.pris; }
	public String hentNavn() { return this.navn; }
	public String hentMengde() { return " mengde: " + this.mengde;}
	
	
	public String toString(){
		return this.id + ": " + this.navn + " (pris: " + this.pris + "kr, mengde: " + this.mengde + ")";
	}
}
