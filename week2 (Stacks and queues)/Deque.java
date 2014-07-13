package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	   
   public Deque() {
	   this.size = 0;
   }  

   private Node<Item> first;
   private Node<Item> last;
   private int size;
   
   @SuppressWarnings("hiding")
   private class Node<Item> {
	  Item item;
	  Node<Item> next;
	  Node<Item> previous;
   }
   
   public boolean isEmpty() {
	   return size == 0;
   }                
   
   public int size() {
	   return size;
   }                        
   
   public void addFirst(Item item) {	   
	   if (item == null) throw new NullPointerException();	   
	   Node<Item> oldFirst = first;
	   first = new Node<Item>();	   
	   first.item = item;	   
	   if (size == 0) {
		   last = first;
	   } else {		 
		   first.next = oldFirst;		   	   
		   oldFirst.previous = first;
	   }	   
	   size++;
   }          
  
   public void addLast(Item item) {	
	   if (item == null) throw new NullPointerException();	   
	   Node<Item> oldLast = last;
	   last = new Node<Item>();	 
	   last.item = item;
	   if (size == 0) {
		   first = last;
	   } else {
		   last.previous = oldLast; 
		   oldLast.next = last;
	   }
	   size++;
   }          
   
   public Item removeFirst() {
	   if(size == 0) throw new NoSuchElementException();	   
	   Node<Item> oldFirst = first;
	   if (size == 1) {
		   first = null;
		   last = null;
	   }
	   if (size >= 2) {
		   first = oldFirst.next;
		   first.previous = null;
	   }
	   size--;	   
	   return oldFirst.item;
   }               
   
   public Item removeLast() {
	   if(size == 0) throw new NoSuchElementException();	   
	   Node<Item> oldLast = last;
	   if (size == 1) {
			first = null;
			last = null;
	   }
	   if (size >= 2) {
		   last = oldLast.previous;
		   last.next = null;
	   }
	   size--;	   
	   return oldLast.item;
   }                 
  
   public Iterator<Item> iterator() {
	   return new DequeIterator();
   } 
   
   private class DequeIterator implements Iterator<Item>{

		private Node<Item> head = Deque.this.first;
				
		@Override
		public boolean hasNext() {
			return head != null;
		}
		
		@Override
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = head.item;
			head = head.next;
			return item;
		}
		
		@Override
		public void remove(){
			throw new UnsupportedOperationException();
		}
	   
   }
   
   public static void main(String[] args) { 
	  Deque<String> deq = new Deque<String>();
	   deq.addFirst("first");
	   deq.addFirst("second");
	   deq.addLast("last");
	   deq.addLast("lastious");
	   System.out.println(deq.size);
	   System.out.println(deq.removeLast());
	   System.out.println(deq.removeLast());
	   System.out.println(deq.removeFirst());
	   System.out.println(deq.removeLast());
	   System.out.println(deq.size());
	   Iterator<String> iter = deq.iterator();
	   System.out.println(iter.hasNext());
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
   }  
   
}