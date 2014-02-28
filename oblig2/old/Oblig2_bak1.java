/*
     INF1010 - Oblig 2
    ---------------------------
     petterju
*/


// Imports
import java.util.ArrayList;


/* Oblig2 */
class Oblig2 {
    public static void main(String[] args) {
		// Run tests
        Tests tests = new Tests();
        tests.run();
    }
}


/* Tests */
class Tests {
	// Variables
    private ArrayList<Person> people = new ArrayList<Person>();

    // Run tests
    public void run() {
		// Add some people
		Person jon = new Person("Jon", 3);
		Person ida = new Person("Ida", 3);
		Person per = new Person("Per", 3);
		Person jos = new Person("Jos", 3);
		Person una = new Person("Una", 3);
		Person ted = new Person("Ted", 3);
		Person uwe = new Person("Uwe", 3);
		
		this.people.add(jon);
		this.people.add(ida);
		this.people.add(per);
		this.people.add(jos);
		this.people.add(una);
		this.people.add(ted);
		this.people.add(uwe);

		// Collectors
		String b = "books";
		String r = "records";
		int c = 5;
		
		jon.collectorOf(b, c);
		jon.collectorOf(r, c);
		
		ida.collectorOf(b, c);
		
		per.collectorOf(r, c);
		per.collectorOf(b, c);
		per.interestedIn("Queen");
		per.interestedIn(1900);
		
		jos.collectorOf(r, c);
		jos.interestedIn("Bob Dylan");
		
		una.collectorOf(b, c);
		una.collectorOf(r, c);
		
		uwe.collectorOf(r, c);
		
		// Gifting
		per.receiveGift(new Book("Bikubesong", "Frode Grytten", 1999));
		per.receiveGift(new Record("Antiphon", "Midlake", 2013));
    }
}


/* Collectible */
class Collectible {
    // Variables
	private String title;
	private String writer;
	
	Collectible(String t, String w) {
		this.title = t;
		this.writer = w;
	}
}
 
class Book extends Collectible {
	// Variables
	private int year;
}

class Record extends Collectible {
	// Variables
	private int tracks;
}
 

 
/* Book */
class Book {
	
	private int year;
	
	// Constructor
	Book(String t, String a, int y) {
		this.title = t;
		this.author = a;
		this.year = y;
	}
	
	// Getters
	public String getTitle() { return this.title; }
	public String getAuthor() { return this.author; }
	public int getYear() { return this.year; }
}


/* Record */
class Record {
	// Variables
	private String title;
	private String artist;
	private int tracks;
	
	// Constructor
	Record(String t, String a, int tr) {
		this.title = t;
		this.artist = a;
		this.tracks = tr;
	}
	
	// Getters
	public String getTitle() { return this.title; }
	public String getArtist() { return this.artist; }
	public int getTracks() { return this.tracks; }
}



/* Collection */
class Collection <T> {
	// Variables
	private T[] collection;
	
	// Constructor
	Collection (int size) {
		collection = (T[]) new Object[size];
	}
	
	// Add to collection
	public void addItem(T item) {
		this.collection[0] = item;
	}
	
	// Check if item in collection
	public boolean hasItem(T gift) {
		for (T item : this.collection)
			System.out.println(Class<T>);
			/*
			if (item != null) {
				System.out.print("Opptatt: ");
				System.out.println(item.getTitle());
			}
			else 
				System.out.println("Ledig plass");
			/*
			if (item != null)
				if (item.getTitle().equalsIgnoreCase(gift.getTitle()))
					return true;*/
						
		return false;
	}
}



/* Gift */


/* Person */
class Person {
    // Variables
    private String name;
    private Person[] acquaintances;
    private Person[] enemies;
    private Person loveinterest;
	
	private Collection<Book> books;
	private Collection<Record> records;
	
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
			this.books = new Collection<Book>(size);
		else if (type.equalsIgnoreCase("record"))
			this.records = new Collection<Record>(size);
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
		this.books.addItem(new Book("Bikubesong", "Frode Grytten", 1999));
		
		if (this.books != null)
			System.out.println(this.books.hasItem(b));
				
	}
	public void receiveGift(Record r) {
		//if (this.records != null)
			//System.out.println(this.records.desiredItem(r));
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
