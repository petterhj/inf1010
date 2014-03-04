// Class: Lege
public class Lege implements Comparable, Lik, Avtalelege {
	private String navn;
	private boolean erSpesialist = false;
	private int avtalenr;
	private Legemiddel[] resepter;
	
	@Override
    public int compareTo(Object o) {
        return 0;	 	// TODO: implementere
    }
	
	@Override
	public boolean samme(String s) {
		if (this.navn.equalsIgnoreCase(s))
			return true;
		return false;
	}
	
	@Override
	public int avtaleNr() {
		return this.avtalenr;
	}
}