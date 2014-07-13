package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.introcs.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] queue;
	public int tail;
			
	
	@SuppressWarnings("unchecked")
	public RandomizedQueue(){
		queue = (Item[]) new Object[1];
		tail = 0;
	}
	
	public boolean isEmpty(){
		return tail == 0;
	}
	
	public int size(){
		return tail;
	}
	
	public void enqueue(Item item){
		if (item == null) throw new NullPointerException();
		if (tail == queue.length) resize(2 * queue.length);
		queue[tail++] = item;
		}
	
	public Item dequeue(){
		if (tail <= 0) throw new NoSuchElementException();
		if (tail <= (0.25 * queue.length)) resize(queue.length / 2);
		int rnd = StdRandom.uniform(tail);			
		Item item = queue[rnd];
		queue[rnd] = queue[--tail];
		queue[tail] = null;		
		return item;
	}
	
	public Item sample(){
		if (tail == 0) throw new NoSuchElementException();
		int rnd = StdRandom.uniform(tail);
		return queue[rnd];
	}
	
	
	public Iterator<Item> iterator() {
		return new RQIterator();
	}
	
	
	private class RQIterator implements Iterator<Item>{
		private int size = tail;
		private int[] shuffled = new int[size];
		
		{
			for (int i = 0; i < size; i++){
				shuffled[i] = i;
			}
			StdRandom.shuffle(shuffled);
		}
		
		public boolean hasNext() {
			return size != 0;
		}

		
		public Item next() {
			if (size == 0) throw new NoSuchElementException();			
			return queue[shuffled[--size]];			
		}

		
		public void remove() {
			throw new UnsupportedOperationException();	
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	private void resize(int capacity){
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < tail; i++) {			
			copy[i] = queue[i];
		}
		queue = copy;
	}
	
	public static void main(String[] args){
		RandomizedQueue<String> queue = new RandomizedQueue<String>();
		queue.enqueue("item");
		queue.enqueue("item1");
		queue.enqueue("item2");
		queue.enqueue("item3");
		queue.enqueue("item43");
		queue.enqueue("item41");
		queue.enqueue("item44");
		queue.enqueue("item144");
		queue.enqueue("item41");
		queue.enqueue("item64");
		queue.enqueue("item84");
		queue.enqueue("item744");
		System.out.println("tail:" + queue.tail);
		
		@SuppressWarnings("rawtypes")
		Iterator iterator = queue.iterator();		
		while (iterator.hasNext()){
			System.out.println(iterator.next());
		}
		
		
		/*System.out.println("tail:" + queue.tail);
		System.out.println(queue.dequeue());
		System.out.println("tail:" + queue.tail);
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		
		System.out.println(queue.dequeue());
		
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println("sample = " + queue.sample());
		System.out.println(queue.dequeue());
		System.out.println(); */
		
		
	}

}
