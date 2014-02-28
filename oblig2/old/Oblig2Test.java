import java.util.ArrayList;

/* Oblig2 */
class Oblig2Test {
    public static void main(String[] args) {
		// Add some people
		Person jon = new Person("Jon", 3);
		Person ida = new Person("Ida", 3);
		Person per = new Person("Per", 3);
		Person jos = new Person("Jos", 3);
		Person una = new Person("Una", 3);
		Person ted = new Person("Ted", 3);
		Person uwe = new Person("Uwe", 3);

		// Collectors
		String b = "books";
		String r = "records";
		int shelfSpace = 5;
		
		jon.collectorOf(b, shelfSpace);
		jon.collectorOf(r, shelfSpace);
		
		ida.collectorOf(b, shelfSpace);
		
		per.collectorOf(r, shelfSpace);
		per.collectorOf(b, shelfSpace);
		per.interestedIn("Queen");
		per.interestedIn(1900);
		
		jos.collectorOf(r, shelfSpace);
		jos.interestedIn("Bob Dylan");
		
		una.collectorOf(b, shelfSpace);
		una.collectorOf(r, shelfSpace);
		
		uwe.collectorOf(r, shelfSpace);
		
		// Gifting
		per.receiveGift(new Book("Bikubesong", "Frode Grytten", 1999));
		per.receiveGift(new Record("Antiphon", "Midlake", 2013));
    }
}


/* Collection */
class Item <T> {
	/*
	// Variables
	private T[] collection;
	private int items;
	
	// Constructor
	@SuppressWarnings("unchecked")
	Collection (int size) {
		collection = (T[]) new Object[size];
	}
	
	// Add to collection
	public void addItem(T item) {
			this.collection[items] = item;
			items++;
	}
	
	// Check if room in collection
	public boolean isFull() {
		if (this.items < this.collection.length)
			return false;
		else return true;
	}
	
	public int size() { return this.collection.length; }
	public int items() { return this.items; }
	
	// Return all items in collection
		// Probably a bad implementation
	public Object[] getAll() { return this.collection; }
	*/
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


/* Person */
class Person {
    // Variables
    private String name;
    private Person[] acquaintances;
    private Person[] enemies;
    private Person loveinterest;
	
	private Item<Book>[] books;
	private Item<Record>[] records;
	
	private String lovesArtist;
	private int lovesWorksBefore;

    // Constructor
    Person(String n, int lengde) {
        this.name = n;
        this.acquaintances = new Person[lengde];
        this.enemies = new Person[lengde];
    }
	
	// Collecting
	public void collectorOf(String type, int size) {
		// Books
		if (type.equalsIgnoreCase("books"))
			this.books = (Collection<Book>[]) new Object[size]; /* feels bad, not type-safe */
		else if (type.equalsIgnoreCase("record"))
			this.books = (Collection<Records>[]) new Object[size];
	}
	
	// Special interests
	public void interestedIn(String artist) {
		if (artist != null && !artist.isEmpty()) lovesArtist = artist;
	}
	
	public void interestedIn(int olderThan) {
		if (olderThan > 0) lovesWorksBefore = olderThan;
	}
	
	// Gifting
	public void receiveGift(Book b) {
		// TEST
		//this.books.addItem(new Book("Bikubesong", "Frode Grytten", 1999));
	
		// Check if book collector
			// Check if already in collection

	}
	public void receiveGift(Record r) {
		
	}
	

    // Return name
    public String getName() {
        return this.name;
    }

    // Check if acquainted
    public boolean knows(Person p) {
        return this.inArray(p, this.acquaintances);
    }

    // Add acquaintance
    public void addAcquaintance(Person p) {
        if (!this.isSelf(p))
            for (int i = 0; i < this.acquaintances.length; i++)
                if (this.acquaintances[i] == null) {
                    this.acquaintances[i] = p;
                    break;
                }
    }

    // Set love interest
    public void fallsInLoveWith(Person p) {
        if (!this.isSelf(p)) this.loveinterest = p;
    }

    // Set acquaintance as enemy
    public void setEnemy(Person p) {
        if (!this.isSelf(p))
            for (int i = 0; i < this.enemies.length; i++)
                if (this.enemies[i] == null) {
                    this.enemies[i] = p;
                    break;
                }
    }

    // Check if friend
    public boolean isFriend(Person p) {
        if (inArray(p, this.acquaintances) && !inArray(p, this.enemies)) return true;
        return false;
    }

    // Set as friend (remove from enemies)
    public void setFriend(Person p) {
        for (int i = 0; i < this.enemies.length; i++)
            if (this.enemies[i] == p) {
                this.enemies[i] = null;
                break;
            }
    }

    // Print friend list
    public void printFriends() {
        System.out.println("Venner:");

        for (Person p : this.getFriends())
            System.out.println(" > " + p.getName());
    }

    // Return best friend
    public Person getBestFriend() {
        Person[] friends = this.getFriends();
        return friends[0];
    }

    // Return friends
    public Person[] getFriends() {
        Person[] friends = new Person[this.friendCount()];
        int vi = 0;

        for (int i = 0; i < this.acquaintances.length; i++)
            if (this.isFriend(this.acquaintances[i]))
                friends[vi++] = this.acquaintances[i];

        return friends;
    }

    // Return friend count
    public int friendCount() {
        int ant = 0;

        for (Person p : this.acquaintances)
            if (this.isFriend(p)) ant++;

        return ant;
    }

    // Print acquaintances
    public void printAcquaintances() {
        for (Person p : this.acquaintances)
            if (p != null) System.out.print(p.getName( ) + " ");
        System.out.println("");
    }

    // Print enemies
    public void printEnemies( ) {
        for (Person p : this.enemies)
            if (p != null) System.out.print(p.getName( ) + " ");
        System.out.println("");
    }

    // Print profile
    public void printProfile ( ) {
        System.out.print(this.name + " kjenner: ");
        printAcquaintances();

        if (this.loveinterest != null)
            System.out.println(this.name + " er forelsket i " + this.loveinterest.getName());

        if (this.enemies[0] != null) {
            System.out.print(this.name + " liker ikke: ");
            printEnemies();
        }
    }

    // Check if person in array
    private boolean inArray(Person n, Person[] h) {
        for (Person p : h)
            if (p == n) return true;
        return false;
    }

    // Check if self
    private boolean isSelf(Person p) {
        if (this == p) return true;
        return false;
    }
}
