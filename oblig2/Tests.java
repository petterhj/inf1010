// Imports
import java.util.ArrayList;


/* Class: Tests */
public class Tests {
    // Variables
    private final int STUDENT_SHELF_SPACE = 5;
    private ArrayList<Person> people = new ArrayList<Person>();
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<Record> records = new ArrayList<Record>();
    
    // Constructor
    Tests() {
        // Add some people
        Person jon = new Person("Jon");
        Person ida = new Person("Ida");
        Person per = new Person("Per");
        Person jos = new Person("Jos");
        Person una = new Person("Una");
        Person ted = new Person("Ted");
        Person uwe = new Person("Uwe");
    
        // Collectors
        jon.setCollectorOf("books", STUDENT_SHELF_SPACE);
        jon.setCollectorOf("records", STUDENT_SHELF_SPACE);
        jon.setSpecialInterest("Silya Nymoen");
        jon.setSpecialInterest(1998);

        ida.setCollectorOf("books", STUDENT_SHELF_SPACE);
        ida.setSpecialInterest(1900);

        per.setCollectorOf("records", STUDENT_SHELF_SPACE);
        per.setCollectorOf("books", STUDENT_SHELF_SPACE);
        per.setSpecialInterest("Queen");
        per.setSpecialInterest(1946);

        jos.setCollectorOf("records", STUDENT_SHELF_SPACE);
        jos.setSpecialInterest("Bob Dylan");

        una.setCollectorOf("books", STUDENT_SHELF_SPACE);
        una.setCollectorOf("records", STUDENT_SHELF_SPACE);

        uwe.setCollectorOf("records", STUDENT_SHELF_SPACE);

        this.people.add(jon);
        this.people.add(ida);
        this.people.add(per);
        this.people.add(jos);
        this.people.add(una);
        this.people.add(ted);
        this.people.add(uwe);
        
        // Add some books
        this.books.add(new Book("Bikubesong", "Frode Grytten", 1999));
        this.books.add(new Book("The Cocka Hola Company", "Matias Faldbakken", 2001));
        this.books.add(new Book("Sult", "Knut Hamsun", 1890));
        this.books.add(new Book("Dr. Zhivago", "Boris Pasternak", 1957));
        this.books.add(new Book("Kollektivt selvmord", "Arto Paasilinna", 2007));
        this.books.add(new Book("The Merchant of Venice", "William Shakespeare", 1598));
        this.books.add(new Book("Arbeid mindre - lev mer", "Christian Vennerød", 1982));
        this.books.add(new Book("Brave New World", "Aldous Huxley", 1932));
        this.books.add(new Book("Lord of the Flies", "William Golding", 1954));
        this.books.add(new Book("Ekteparet Orlov", "Maxim Gorki", 1937));
        
        // Add some records
        this.records.add(new Record("Kid A", "Radiohead", 10));
        this.records.add(new Record("Antiphon", "Midlake", 12));
        this.records.add(new Record("Queen II", "Queen", 11));
        this.records.add(new Record("Blonde on Blonde", "Bob Dylan", 13));
        this.records.add(new Record("Infidels", "Bob Dylan", 12));
        this.records.add(new Record("Peel Away", "Silya Nymoen", 11));
        this.records.add(new Record("Dagonslayer", "Sunset Rubdown", 10));
        this.records.add(new Record("Breakfast in America", "Supertramp", 12));
        this.records.add(new Record("Rip Tide", "Beirut", 11));
    }
    
    // Run tests
    public void run() {
        // Christmas
        ArrayList<Collectible> returned = new ArrayList<Collectible>();
        
        for (int i = 0; i < this.people.size();) {
            Person p = this.people.get(i);
            
            for (Book b : this.books) {
                Collectible rtrn = p.givenGift(b);
                if (rtrn != null) returned.add(rtrn);
            }
            /*
            for (Record r : this.records) {
                Collectible rtrn = p.givenGift(r);
                if (rtrn != null) returned.add(rtrn);
            }*/
                
            if (returned.size() > 0) i++;
            else break;
            
            System.out.println();
            
            System.out.println(p.getName() + " returned:");
            for (Collectible c : returned)
                System.out.println(" - " + c.getTitle());
                
        }

            
        
        System.out.println();        
            

        /*System.out.println(this.books.get(0));
        System.out.println(gift);        
        println(gift.getTitle());*/
        
        
        
    }
    
    public void print(String s) { System.out.print(s); }
    public void println(String s) { System.out.println(s); }
}