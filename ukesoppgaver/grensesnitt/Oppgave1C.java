import java.util.ArrayList;

class Oppgave1C {
	public static void main(String[] args) {
		GenericLibraryBehaviour<Book> library = new GenericLibrary<Book>();
		Book b = new Book ("Tolkien", "LotR", 1954);
		library.place(0, b);
		Book b2 = library.take(0);
		System.out.println(b2.getTitle());
	}
}


interface GenericLibraryBehaviour <T>{
	public void place (int index, T t);
	public T take (int index);
}

class GenericLibrary<T> implements GenericLibraryBehaviour<T> {
	private ArrayList<T> library = new ArrayList<T>(10000);
  
	public void place (int index, T t) {
		library.add(index, t);
	}
  
	public T take (int index) {
		return library.remove(index);
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