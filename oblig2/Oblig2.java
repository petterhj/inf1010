/*
     INF1010 - Oblig 2
    ---------------------------
     petterju
*/

class Oblig2 {
    public static void main(String[] args) {
        
    }
}


/*
	Book
*/

class Book {
	// Variables
	private String title;
	private String author;
	private int year;
	
	// Constructor
	Record(String t, String a, int y) {
		this.title = t;
		this.artist = a;
		this.year = y;
	}
	
	// Getters
	public String getTitle() { return this.title; }
	public String getAuthor() { return this.author; }
	public int getYear() { return this.year; }
}


/*
	Record
*/

class Plate {
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


/*
	Person
*/
class Person {
    // Variables
    private String navn;
    private Person[] acquaintances;
    private Person[] enemies;
    private Person loveinterest;
	
	private Book[] books;
	private Record[] records;

    // Constructor
    Person (String n, int lengde) {
        this.name = n;
        this.acquaintances = new Person[lengde];
        this.enemies = new Person[lengde];
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
