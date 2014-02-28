import java.util.ArrayList;

class Oblig2Test2 {
    public static void main(String[] args) {
		Collection<Book> books = new Collection<Book>(5);
		Collection<Record> records = new Collection<Record>(5);
		
		Book b1 = new Book("Bikubesong", "Frode Grytten", 1999);
		Book b2 = new Book("Kollektivt selvmord", "Arto Paasilinna", 2007);
		
		
		books.addItem(b1);
		books.addItem(b2);
		
		for (Book b : books.getItems())
			System.out.println(b.getTitle());
			
		books.inCollection(b2);
	}
}



/* Gift */
interface Gift <T> {
	public boolean inCollection(T item);
}

/* Collection */
class Collection <T> implements Gift <T> {
	private ArrayList<T> items;
	
	// Constructor
	Collection (int size) {
		items = new ArrayList<T>(size);
	}
	
	public void addItem(T item) {
		items.add(item);
	}
	public ArrayList<T> getItems() {
		return this.items;
	}
	
	public boolean inCollection(T item) { 
		for (T i : this.getItems())
			System.out.println(i.getTitle());
			
		return true;
	}
}

/* Collectible */
class Collectible {
    // Variables
	private String title;
	private String writer;
	
	// Constructor
	Collectible(String t, String w) {
		this.title = t;
		this.writer = w;
	}
	
	public String getTitle() { return this.title; }
	public String getWriter() { return this.writer; }
}
 
class Book extends Collectible {
	// Variables
	private int year;
	
	// Constructor
	Book(String t, String w, int y) {
		super(t, w);
		this.year = y;
	}
	
	public int getYear() { return this.year; }
}

class Record extends Collectible {
	// Variables
	private int tracks;
	
	// Constructor
	Record(String t, String w, int tr) {
		super(t, w);
		this.tracks = tr;
	}
	
	public int getTracks() { return this.tracks; }
}
