import java.util.NoSuchElementException;

/*
 * Name: Oliver DiPuccio
 * Assignment: Project04 - SortedLinkedPriorityQueue Class
 * Due Date: 12/5/2021
 */

public class SortedLinkedPriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T> {
	//============================================ Properties
	private class Node {
		private T data;
		private Node next;
		
		@SuppressWarnings("unused")
		private Node(T data) {
			this(data, null);
		}
		
		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
	//============================================ Properties
	private Node head;
	private int size;
	
	//============================================ Constructors
	public SortedLinkedPriorityQueue() {
		super();
		clear();
	}
	
	public SortedLinkedPriorityQueue(T[] entries) {
		this();
		for(T entry : entries) add(entry);
	}
	
	//============================================ Methods
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(T newEntry) {
		Node curr = head;
		Node prev = null;
		
		while(curr != null && curr.data.compareTo(newEntry) <= 0) {
			prev = curr;
			curr = curr.next;
		}
		
		if(prev == null)	head = new Node(newEntry, curr);
		else				prev.next = new Node(newEntry, curr);
		
		size++;
	}

	@Override
	public T peek() {
		return isEmpty() ? null : (T)head.data;
	}

	@Override
	public T remove() {
		if(isEmpty()) throw new NoSuchElementException();
		T ret = (T)head.data;
		head = head.next;
		size--;
		return ret;
	}
	
	@Override
	public String toString() {
		String ret = "";
		Node curr = head;
		while(curr != null) {
			ret += ", " + curr.data.toString();
		}
		
		return "[" + ret.substring(2) + "]";
	}
}
