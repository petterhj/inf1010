

public class Person {
	EnkelReseptListe resepter = new EnkelReseptListe();
	int personNr;
	String navn;
	String kjonn;
	
	Person(int pnr, String navn, String kjonn) {
		this.personNr = pnr;
		this.navn = navn;
		this.kjonn = kjonn;
	}
	
	// Legg til resept
	public void giResept(Resepter r) {
		resepter.settInn(r);
	}
	
	// Hentemetoder
	public String hentNavn() { return this.navn; }
	public int hentUnikNr() { return this.personNr; }
	public EnkelReseptListe hentResepter() { return this.resepter; }
	public String hentKjonn() { return this.kjonn; }
	
	public String toString(){
		return this.personNr + ": " + this.navn + " (" + this.kjonn + ")";
	}
}
