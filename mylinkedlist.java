
import java.util.Iterator;

public class mylinkedlist<T> implements mylist<T> {
    private int size = 0;
    private Node<T> first;
    private Node<T> last;

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
    @Override
    public void add(T item) {
        Node<T> l = last;
        Node<T> newNode = new Node<>(l, item, null);
        last = newNode;
        if (l == null) {
            first = newNode; 
        } else {
            l.next = newNode; 
        }
        size++;
    }
    @Override
    public void addFirst(T item) {
        Node<T> f = first;
        Node<T> newNode = new Node<>(null, item, f);
        first = newNode;
        if (f == null) {
            last = newNode; 
        } else {
            f.prev = newNode; 
        }
        size++;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        if (index == size) {
            addLast(item);
        } else if (index == 0) {
            addFirst(item);
        } else {
            Node<T> succ = node(index); 
            Node<T> pred = succ.prev;   
            Node<T> newNode = new Node<>(pred, item, succ);
            
            succ.prev = newNode;
            pred.next = newNode;
            size++;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = first; 

            @Override
            public boolean hasNext() {
                return current != null; 
            }

            @Override
            public T next() {
                if (!hasNext()) throw new RuntimeException(); 
                T item = current.item; 
                current = current.next; 
                return item;
            }
        };
    }
    @Override
    public void addLast(T item) {
        Node<T> l = last;
        Node<T> newNode = new Node<>(l, item, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    @Override
    public T getFirst() {
        if (first == null) throw new RuntimeException();
        return first.item;
    }

    @Override
    public T getLast() {
        if (last == null) throw new RuntimeException();
        return last.item;
    }

    @Override
    public T get(int index) {
        return node(index).item;
    }

    @Override
    public void set(int index, T item) {
        Node<T> x = node(index);
        x.item = item;
    }


    private Node<T> node(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<T> x;
  
        if (index < (size >> 1)) {
            x = first;
            for (int i = 0; i < index; i++) x = x.next;
        } else {
            x = last;
            for (int i = size - 1; i > index; i--) x = x.prev;
        }
        return x;
    }

    @Override
    public void removeFirst() {
        if (first == null) throw new RuntimeException();
        Node<T> next = first.next;
        first.item = null; 
        first = next;
        if (next == null) last = null;
        else next.prev = null;
        size--;
    }

    @Override
    public void removeLast() {
        if (last == null) throw new RuntimeException();
        Node<T> prev = last.prev;
        last.item = null;
        last = prev;
        if (prev == null) first = null;
        else prev.next = null;
        size--;
    }

    @Override
    public void remove(int index) {
        Node<T> x = node(index);
        Node<T> next = x.next;
        Node<T> prev = x.prev;

        if (prev == null) first = next;
        else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) last = prev;
        else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
    }

    @Override
    public int indexOf(Object object) {
        int index = 0;
        for (Node<T> x = first; x != null; x = x.next) {
            if (object == null ? x.item == null : object.equals(x.item)) return index;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        int index = size - 1;
        for (Node<T> x = last; x != null; x = x.prev) {
            if (object == null ? x.item == null : object.equals(x.item)) return index;
            index--;
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<T> x = first; x != null; x = x.next) result[i++] = x.item;
        return result;
    }

    @Override
    public void clear() {
        for (Node<T> x = first; x != null; ) {
            Node<T> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void sort() {
        if (size < 2) return;
        for (int i = 0; i < size - 1; i++) {
            Node<T> current = first;
            for (int j = 0; j < size - i - 1; j++) {
                Node<T> next = current.next;
                if (((Comparable<T>) current.item).compareTo(next.item) > 0) {
                    T temp = current.item;
                    current.item = next.item;
                    next.item = temp;
                }
                current = next;
            }
        }
    }
}