/* Class: Collection */
public class Collection<T> {
    // Variables
    private T[] items;
    private int itemCount;
    
    // Constructor
    @SuppressWarnings("unchecked")
    Collection(int size) {
        items = (T[])new Object[size];
    }
    
    // Add item to collection
    public void addItem(T item) {
        this.items[itemCount] = item;
        this.itemCount++;
    }
    
    // Replace given item in collection
    public void replaceItem(int i, T item) {
        this.items[i] = item;
    }
    
    // Return given collectible
    public T getItem(int index) {
        return this.items[index];
    }
    
    // Return number of items in collection
    public int getItemCount() { 
        return this.itemCount;
    }
    
    // Return 
    public int getSize() {
        return this.items.length;
    }
    
    // Return 
    public double getFillRatio() {
        return ((double)this.getItemCount() / this.getSize());
    }
}