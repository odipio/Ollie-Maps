/*
 * Name: Oliver DiPuccio
 * Assignment: Project04 - PriorityQueue Interface
 * Due Date: 12/5/2021
 */

public interface PriorityQueueInterface<T extends Comparable<? super T>> {
	boolean isEmpty();
	void clear();
	int size();
	void add(T newEntry);
	T peek();
	T remove();
}
