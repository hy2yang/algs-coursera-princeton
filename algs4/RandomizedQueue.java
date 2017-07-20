import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;         
    private int n;
    
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
    
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        n = 0;
    }
    
    public boolean isEmpty() {
        return n == 0;
    }
    
    public int size() {
        return n;
    }
    
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("can not add a null item");
        if (n == a.length) resize(2*n);    
        a[n++] = item;                            
    }
    
    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException();
        int x = StdRandom.uniform(n);        
        Item item=a[x];
        a[x]=a[n-1];
        a[--n] = null;
        if (n > 0 && n == a.length/4) resize(a.length/2);
        return item;
    }
    
    public Item sample(){
        if (isEmpty()) throw new NoSuchElementException();
        int x = StdRandom.uniform(n);
        Item item = a[x];
        return item;
    }
    
    public Iterator<Item> iterator()
    {
        return new RandomizedQueueIterator();
    }
    
    private class RandomizedQueueIterator implements Iterator<Item>{
        private int i;   
        private Item[] temp;
        public RandomizedQueueIterator() {
            temp = (Item[]) new Object[n];
            for (i = 0; i < n; i++) {
                temp[i] = a[i];
            }
            StdRandom.shuffle(temp);
            i=0;
        }

        public boolean hasNext() {
            return (i < n);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();            
            return temp[i++];
        }
    }
    
    public static void main(String[] args) {
        
        }

}
