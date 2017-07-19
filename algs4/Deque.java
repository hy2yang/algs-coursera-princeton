import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> {
	
	private class node
	{
		Item item;
		node previous, next;
	}
	
	private node first, last;
	private int dequesize;
	
	public boolean isEmpty()
	{
		return (dequesize==0);
	}
	
	public int size()
	{
		return dequesize;
	}
	
	public Deque()
	{
		dequesize = 0;
		first = null;
		last = null; 
	}
	
	public void addFirst(Item item)
	{
		if (item == null) throw new IllegalArgumentException("can not add null item");
		node oldfirst = first;
		first = new node();
		first.item = item;
        first.next = oldfirst;
        dequesize++;
	}

	public void addLast(Item item)
	{
		if (item == null) throw new IllegalArgumentException("can not add a null item");
		node oldlast = last;
		last = new node();
		last.item = item;
        first.previous = oldlast;
        dequesize++;
	}
	
	public Item removeFirst()
	{
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		Item item = first.item;        
        first = first.next;           
        dequesize--;        
        return item;
	}
	
	public Item removeLast()
	{
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		Item item = last.item;        
        last = last.previous;           
        dequesize--;        
        return item;
	}
	
	public Iterator<Item> iterator()
	{
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
        private node current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
	
	public static void main(String[] args)
	{
		
	}

}
