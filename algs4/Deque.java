import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> {

	private class Node{
		Item item;
		Node previous, next;
	}
	
	private Node first, last;
	private int dequesize;
	
	public boolean isEmpty(){
		return (dequesize==0);
	}
	
	public int size(){
		return dequesize;
	}
	
	public Deque(){
		dequesize = 0;
		last = first = new Node();
		
	}
	
	public void addFirst(Item item){
		if (item == null) throw new IllegalArgumentException("can not add null item");
		Node oldfirst = first;
		first = new Node();
		first.item = item;
        first.next = oldfirst;
        oldfirst.previous=first;
        dequesize++;
        if (dequesize == 1) last = first;        
	}

	public void addLast(Item item){
		if (item == null) throw new IllegalArgumentException("can not add a null item");
		Node oldlast = last;
		last = new Node();
		last.item = item;
        last.previous = oldlast;
        oldlast.next=last;
        dequesize++;
        if (dequesize == 1) first = last;        
	}
	
	public Item removeFirst(){
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		Item item = first.item;        
        first = first.next;           
        dequesize--;        
        return item;
	}
	
	public Item removeLast(){
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
	
	private class ListIterator implements Iterator<Item>{
        private Node current = first;
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
	/*	Deque<Integer> s= new Deque<Integer>();		
		for(int i=0; i<21;i++)
		{
			s.addLast(i);
		}
		
		for(int j=0; j<21;j++)
		{
			StdOut.print(s.removeLast()+" ");
		} */
		
	}

}
