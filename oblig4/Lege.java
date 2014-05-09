


public class Lege implements Comparable<Lege>, Lik{
	private String navn;
	int avtaleNr = 0;
	private boolean erSpesialist = false;
	EnkelReseptListe resepter = new EnkelReseptListe();


	public Lege(String navn, int type, int avtaleNr){
		this.navn = navn;
		this.avtaleNr = avtaleNr;
		
		if (type == 1)
			this.erSpesialist = true;
	}

	public int compareTo(Lege o) {
		return this.navn.compareTo(o.navn());	 
	}

	@Override
	public boolean samme(String s) {
		if (this.navn.equalsIgnoreCase(s))
			return true;
		return false;
	}
	

	public String navn(){ return this.navn; }
	public int hentAvtaleNr() { return this.avtaleNr; }
	public EnkelReseptListe hentResepter() { return this.resepter; }

	public String toString(){
		String out = this.navn;
				
		if (this.erSpesialist)
			out = out + " (spesialist)";
			
		if (this.avtaleNr > 0)
			out = out + " (avtalenr.: " + this.avtaleNr + ")";
				
		return out;
	}

}
