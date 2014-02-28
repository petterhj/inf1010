/* Class: Book */
public class Book implements Collectible {
    // Variables
    private String title;
    private String author;
    private int year;
    private boolean lovethis = false;
    
    Book(String t, String a, int y) {
        this.title = t;
        this.author = a;
        this.year = y;
    }
    
    public String getTitle() { return this.title; }
    public String getAuthor() { return this.author; }
    public int getYear() { return this.year; }
    public void isOfSpecialInterest(boolean love) { this.lovethis = love; }
    public boolean isOfSpecialInterest() { return this.lovethis; }
}