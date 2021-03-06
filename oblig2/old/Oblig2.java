import java.util.ArrayList;

/*
     INF1010 - Oblig 2
    ---------------------------
     petterju
     
     Programmet er ufullstendig, og tilfredsstiller desverre ikke kravene.
     Det kjører, gjør noe rett, men har noen logiske brister. Rota meg bort.
*/

class Oblig2 {
    public static void main(String[] args) {
        Tests tests = new Tests();
        tests.run();
    }
}

class Tests {
    // Variables
    private final int STUDENT_SHELF_SPACE = 5;
    
    // Run tests
    public void run() {
        // Add some people
        Person jon = new Person("Jon");
        Person ida = new Person("Ida");
        Person per = new Person("Per");
        Person jos = new Person("Jos");
        Person una = new Person("Una");
        Person ted = new Person("Ted");
        Person uwe = new Person("Uwe");

        // Collectors
        jon.collectorOf("books", STUDENT_SHELF_SPACE);
        jon.collectorOf("records", STUDENT_SHELF_SPACE);
        jon.interestedIn("Silya Nymoen");
        jon.interestedIn(1998);

        ida.collectorOf("books", STUDENT_SHELF_SPACE);
        ida.interestedIn(1900);

        per.collectorOf("records", STUDENT_SHELF_SPACE);
        per.collectorOf("books", STUDENT_SHELF_SPACE);
        per.interestedIn("Queen");
        per.interestedIn(1946);

        jos.collectorOf("records", STUDENT_SHELF_SPACE);
        jos.interestedIn("Bob Dylan");

        una.collectorOf("books", STUDENT_SHELF_SPACE);
        una.collectorOf("records", STUDENT_SHELF_SPACE);

        uwe.collectorOf("records", STUDENT_SHELF_SPACE);
        
        
        // Gifts
        ArrayList<Person> persons = new ArrayList<Person>();
        persons.add(jon);
        persons.add(ida);
        //persons.add(per);
        //persons.add(jos);
        //persons.add(una);
        //persons.add(ted);
        //persons.add(uwe);
        
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book("Bikubesong", "Frode Grytten", 1999));
        books.add(new Book("The Cocka Hola Company", "Matias Faldbakken", 2001));
        books.add(new Book("Sult", "Knut Hamsun", 1890));
        books.add(new Book("Dr. Zhivago", "Boris Pasternak", 1957));
        books.add(new Book("Kollektivt selvmord", "Arto Paasilinna", 2007));
        books.add(new Book("The Merchant of Venice", "William Shakespeare", 1598));
        books.add(new Book("Arbeid mindre - lev mer", "Christian Vennerød", 1982));
        books.add(new Book("Brave New World", "Aldous Huxley", 1932));
        books.add(new Book("Lord of the Flies", "William Golding", 1954));
        books.add(new Book("Ekteparet Orlov", "Maxim Gorki", 1937));

        ArrayList<Record> records = new ArrayList<Record>();
        records.add(new Record("Kid A", "Radiohead", 10));
        records.add(new Record("Antiphon", "Midlake", 12));
        records.add(new Record("Queen II", "Queen", 11));
        records.add(new Record("Blonde on Blonde", "Bob Dylan", 10));
        records.add(new Record("Infidels", "Bob Dylan", 12));
        records.add(new Record("Peel Away", "Silya Nymoen", 11));
        records.add(new Record("Dagonslayer", "Sunset Rubdown", 10));
        records.add(new Record("Breakfast in America", "Supertramp", 12));
        records.add(new Record("Rip Tide", "Beirut", 11));
        
        
        /*
            Ingen skal kunne eie den sammen boken/platen ??
            Eks. Hvis første person aksepterer gaven, skal den ikke tilbys andre? (så fremt personen ikke tilbys noe han heller vil ha?)
        */
       
        for (int p = 0; p < persons.size(); p++) {
            // Items that get returned from first person, will be given to next 
            ArrayList<Book> returned = new ArrayList<Book>();
            
            System.out.println();
            
            for (Book b : books) {
                System.out.print("Book: " + b.getTitle() + " | ");
                Book ret = persons.get(p).wantGift(b);
                if(ret == null) System.out.println("KEPT");
                else {
                    //else if(ret == b) System.out.println("RETU - " + ret.getTitle());
                    //else System.out.println("EXCH - " + ret.getTitle());
                    returned.add(ret);
                }
            }
            System.out.println();
            
            // Give to next
            for (Book b : returned) {
                if((p+1) <= (persons.size()-1)) {
                    Book ret = persons.get((p+1)).wantGift(b);
                    //System.out.println(b.getTitle());
                }
                else {
                    //System.out.println("returned = TRASH if no next p?");
                }
            }
            
            System.out.println();
        }

        /* RESULT DEBUG */
        System.out.println("=========================================================================");
        for (Person p : persons) {
            p.printCollections();
            System.out.println();
        }
        System.out.println("\n=========================================================================");
        /* DEBUG */
    }
}


/* Collection */
class Collection<T> {
    // Variables
    private T[] items;
    private int itemCount;
    
    @SuppressWarnings("unchecked")
    Collection(int size) {
        items = (T[])new Object[size];
    }
    
    public void addItem(T item) {
        this.items[itemCount] = item;
        this.itemCount++;
    }
    public void replaceItem(int i, T item) {
        this.items[i] = item;
    }
    
    public T getItem(int index) { return this.items[index]; }
    public int getItemCount() { return this.itemCount; }    
    public double getFillRatio() { return ((double)this.itemCount / 5); }
}


/* Collectible */
interface Collectible {
    public String getTitle();
    public void isOfSpecialInterest(boolean love);
    public boolean isOfSpecialInterest();
}


/* Book */
class Book implements Collectible {
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


/* Record */
class Record implements Collectible {
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


/* Person */
class Person {
    // Variables
    private String name;
    
    private Collection<Book> books;
    private Collection<Record> records;    
    
    private String favouriteArtist;
    private int booksOlderThan;

    // Constructor
    Person(String n) {
        this.name = n;
    }
    
    
    /* DEBUG */
    public void printCollections() {
        System.out.println("\n" + this.name);
        System.out.print("  > Books (special interest: " + booksOlderThan + "):");
        
        if (this.books == null)
            System.out.print("\n    [!] Does not collect books!");
        if (this.books != null)
            for (int i = 0; i < this.books.getItemCount(); i++) {
                Book b = (Book) this.books.getItem(i);
                System.out.print("\n    - " + b.getTitle() + " (" + b.getYear() + ")");
            }
        
        System.out.print("\n\n  > Records (special interest: " + favouriteArtist + "):");
        
        if (this.records == null)
            System.out.print("\n    [!] Does not collect records!");
        if (this.records != null)
            for (int i = 0; i < this.records.getItemCount(); i++) {
                Record r = (Record) this.records.getItem(i);
                System.out.print("\n    - " + r.getTitle());
            }
    }
    /* DEBUG */
    
    // Collections
    public void collectorOf(String type, int size) {
        // Books
        if (type.equalsIgnoreCase("books"))
            this.books = new Collection<Book>(size);
        // Records
        else if (type.equalsIgnoreCase("records"))
            this.records = new Collection<Record>(size);
    }
    
    // Special interests ("megetInteressertI")
    public void interestedIn(int year) {
        if (this.books != null && year > 0) this.booksOlderThan = year;
    }
    public void interestedIn(String artist) {
        if (this.records != null && !artist.isEmpty()) this.favouriteArtist = artist;
    }
    
    // Check if acceptable gift ("vilDuHaGaven")
    public Book wantGift(Book b) {
        b.isOfSpecialInterest((b.getYear() < this.booksOlderThan));
        int status = this.checkLibrarySituation(b, this.books);
        Book ret = b;
        if (status == -2) {
            ret = b;
            //System.out.println("* Gift not accepted");
        }
        else if (status == -1) {
            this.books.addItem(b);
            ret = null;
            //System.out.println("* Gift accepted");
        }
        else if (status >= 0) {
            ret = this.books.getItem(status);
            this.books.replaceItem(status, b);
            //System.out.println("* Gift accepted, returned some other shit (" + ret.getTitle() + ")");
            
        }
        //System.out.println("===============================================================");
        
        return ret;
    }
    
    public Collectible wantGift(Record r) {
        r.isOfSpecialInterest(r.getTitle().equalsIgnoreCase(this.favouriteArtist));
        int status = this.checkLibrarySituation(r, this.records);
        Record ret = r;
        if (status == -2) {
            ret = r;
        }
        else if (status == -1) {
            this.records.addItem(r);
            ret = null;
        }
        else if (status >= 0) {
            this.records.replaceItem(status, r);
            ret = this.records.getItem(status);
        }
        
        return ret;
    }
    
    public int checkLibrarySituation(Collectible gift, Collection collection) {
        //-2 = give item back
        //-1 = keep item
        //0> = keep, and exchanged item
    
        // Check if collector of these items
        if (collection == null)
            return -2;
            
        else {
            // System.out.println();
            // System.out.println("===============================================================");
            // System.out.println("* Offered: " + gift.getTitle() + " (special interest: " + gift.isOfSpecialInterest() + ")");
            // System.out.println("  > Items: " + collection.getItemCount());
            // System.out.println("  > Space: " + collection.getFillRatio());
            
            // Check if in library
            for (int i = 0; i < collection.getItemCount(); i++) {
                Collectible item = (Collectible) collection.getItem(i);
                if(item.getTitle().equalsIgnoreCase(gift.getTitle()))
                    return -2; // No need for more checks
            }
            
            // Check if collection is full
            if (collection.getFillRatio() == 1) {
                // Exchange
                for (int i = 0; i < collection.getItemCount(); i++) {
                    Collectible item = (Collectible) collection.getItem(i);
                    
                    if (!item.isOfSpecialInterest() && gift.isOfSpecialInterest()) {
                        //System.out.println("  > Replace " + i + " (" + item.getTitle() + ")");
                        return i;
                    }
                }
                
                return -2; // Collection full. Can't accept, even if of special interest
            } else {
                // Check for shelf space shortage
                if (collection.getFillRatio() > 0.5) {
                    if (gift.isOfSpecialInterest()) return -1;
                    else return -2;
                } else {
                    return -1;
                }
            }
        }
    }
}
