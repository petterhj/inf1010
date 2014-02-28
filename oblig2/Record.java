/* Class: Record */
public class Record implements Collectible {
    // Variables
    private String title;
    private String artist;
    private int tracks;
    private boolean lovethis = false;
    
    Record(String t, String a, int tr) {
        this.title = t;
        this.artist = a;
        this.tracks = tr;
    }
    
    public String getTitle() { return this.title; }
    public String getArtist() { return this.artist; }
    public int getTracks() { return this.tracks; }
    public void isOfSpecialInterest(boolean love) { this.lovethis = love; }
    public boolean isOfSpecialInterest() { return this.lovethis; }
}