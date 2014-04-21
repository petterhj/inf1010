import java.util.HashMap;

class Oppgave1A {
	public static void main(String[] args) {
		LibraryBehaviour library = new Library();
	}
}

class Book {
	private String author;
	private String title;
	private int year;

	Book (String author, String title, int year) {
		this.author = author;
		this.title = title;
		this.year = year;
	}

	public String getAuthor() { return author; }
	public String getTitle() { return title; }
	public int getYear() { return year; }
}

interface LibraryBehaviour {
	public void place (String title, Book book);
	public Book take (String title);
}

class Library implements LibraryBehaviour {
	private HashMap<String, Book> library = new HashMap<String, Book>();

	public void place (String title, Book book) {
		library.put(title, book);
	}

	public Book take (String title) {
		return library.remove(title);
	}
}