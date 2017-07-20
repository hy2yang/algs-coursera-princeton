import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{

    private Node first, last;
    private int dequesize;
    
    private class Node{
        Item item;
        Node previous, next;
    }
        
    public boolean isEmpty(){
        return (dequesize==0);
    }
    
    public int size(){
        return dequesize;
    }
    
    public Deque(){
        dequesize = 0;
        last = first = null;
        
    }
    
    public void addFirst(Item item){
        if (item == null) throw new IllegalArgumentException("can not add null item");
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;        
        dequesize++;
        if (dequesize == 1) last = first;
        else oldfirst.previous=first;
    }

    public void addLast(Item item){
        if (item == null) throw new IllegalArgumentException("can not add a null item");
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.previous = oldlast;        
        dequesize++;
        if (dequesize == 1) first = last; 
        else oldlast.next=last;
    }
    
    public Item removeFirst(){
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;        
        first = first.next;        
        if (dequesize == 1) last=null;
        else first.previous=null;
        dequesize--;        
        return item;
    }
    
    public Item removeLast(){
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = last.item;        
        last = last.previous;        
        if (dequesize == 1) first=null;
        else last.next=null;
        dequesize--;        
        return item;
    }
    
    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext()  { return current != null;}
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
