/* Class: Person */
public class Person {
    // Variables
    private String name;
    
    private Collection<Collectible> books;
    private Collection<Collectible> records;    
    
    private String favouriteArtist;
    private int booksOlderThan;

    // Constructor
    Person(String n) {
        this.name = n;
    }
    
    // Return name
    public String getName() {
        return this.name;
    }
    
    // Create new collection of given type
    public void setCollectorOf(String type, int size) {
        // Books
        if (type.equalsIgnoreCase("books"))
            this.books = new Collection<Collectible>(size);
        // Records
        else if (type.equalsIgnoreCase("records"))
            this.records = new Collection<Collectible>(size);
    }
    
    // Set special interest
    public void setSpecialInterest(int year) {
        this.booksOlderThan = year;
    }
    public void setSpecialInterest(String artist) {
        if (!artist.isEmpty()) this.favouriteArtist = artist;
    }
    
    // Receive gift
    public Collectible givenGift(Book g) {
        Collectible gift = g;
        
        // Decide what to do with gift
        gift.isOfSpecialInterest((g.getYear() < this.booksOlderThan));
        
        Collectible rtrn = checkLibrary(gift, this.books);
        
        System.out.println("* Comparison: " + gift + " (g) | " + rtrn + " (r)");
        
        if (rtrn == null) this.books.addItem(gift); // (Replacing of items implemented below)
            
        // Return
        return rtrn;
    }
    public Collectible givenGift(Record g) {
        Collectible gift = g;
        
        // Decide what to do with gift
        gift.isOfSpecialInterest((g.getTitle().equalsIgnoreCase(this.favouriteArtist)));
        
        Collectible rtrn = checkLibrary(gift, this.records);
        
        System.out.println("* Comparison: " + gift + " (g) | " + rtrn + " (r)");
        
        if (rtrn == null) this.records.addItem(gift); // (Replacing of items implemented below)
            
        // Return
        return rtrn;
    }
    
    // Check library
    public Collectible checkLibrary(Collectible gift, Collection collection) {        
        System.out.println("\n* OFFERED: " + gift.getTitle() + " (special interest: " + gift.isOfSpecialInterest() + ")");
        if (collection != null) {
            System.out.println("  > Items: " + collection.getItemCount());
            System.out.println("  > Space: " + collection.getFillRatio());
        }
        
        // [0] Check if collector of type / if duplicate
        if ((collection == null) || this.isDuplicate(gift, collection)) {
            System.out.println("* REJECTED: non-collector or duplicate");
            return gift;
        }
            
        // [1] Check if collection is full
        else if (collection.getFillRatio() == 1) {
            // Replace something of lesser interest, if gift of special interest
            if (gift.isOfSpecialInterest()) {
                for (int i = 0; i < collection.getItemCount(); i++) {
                    Collectible item = (Collectible) collection.getItem(i);
                    
                    //if (!item.isOfSpecialInterest() && gift.isOfSpecialInterest()) {
                    if (!item.isOfSpecialInterest()) {
                        System.out.println("* ACCEPTED: collection full, replaced " + i + " (" + item.getTitle() + ", special interest: " + item.isOfSpecialInterest() + ")");
                        collection.replaceItem(i, gift);
                        return item;
                    }
                }
                System.out.println("* REJECTED: collection full, nothing replaceable, even though of interest");
                return gift;
            }
            else {
                System.out.println("* REJECTED: collection full, not of interest");
                return gift;
            }
        }
        
        // [2] Check for shelf space shortage
        else if (collection.getFillRatio() > 0.5) {
            if (gift.isOfSpecialInterest()) {
                System.out.println("* ACCEPTED: room shortage, but of special interest");
                return null;
            }
            else {
                System.out.println("* REJECTED: room shortage, not of special interest");
                return gift;
            }
        }
        
        // [3] Plenty of space, accept anything
        else {
            System.out.println("* ACCEPTED: plenty room");
            return null;
        }
    }
    
    // Returns wheter item is a duplicate
    public boolean isDuplicate(Collectible gift, Collection collection) {
        for (int i = 0; i < collection.getItemCount(); i++) {
            Collectible item = (Collectible) collection.getItem(i);
            if(item.getTitle().equalsIgnoreCase(gift.getTitle()))
                return true;
        }
        return false;
    }
}