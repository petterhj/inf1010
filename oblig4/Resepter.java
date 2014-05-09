

public class Resepter {
	
	private Legemiddel legemiddel;
	private Lege lege;
	private int nrTilPers;
	private String farge;
	private String legeNavn;
	private int nr;
	private int legeMiddelNr;
	private int reit;
	
	public Resepter(int nr, String farge, int personNr, String legeNavn, int legeMiddelNr, int reit) {
		this.nr = nr;
		this.farge = farge;
		this.nrTilPers = personNr;
		this.legeNavn = legeNavn;
		this.legeMiddelNr = legeMiddelNr;
		this.reit = reit;
	}
	
	public void brukReit() {
		this.reit = this.reit - 1;
	}
	
	// Hentemetoder
	public int nr(){ return this.nr; }
	public int reit(){ return this.reit; }
	public String hentFarge(){ return this.farge; }
	public int hentReit(){ return this.reit; }
	public String hentLegeNavn(){ return this.legeNavn; }
	public int hentPersonNr(){ return this.nrTilPers; }
	public int hentLegeMiddelNr() { return this.legeMiddelNr; }
	
	public String toString(){
		String p = "resept nr " + this.nr; 
		return p;
	}
}
