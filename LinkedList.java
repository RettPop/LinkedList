//Implement LinkedList collection 
//Highlevel description
//Function to rotate list

public class LinkedList
{
	LLItem firstItem;

	public LinkedList(Object firstObj) {
		firstItem = new LLItem(firstObj);
	}

	// Linked list Item 
	public class LLItem
	{
		Object item;
		LLItem linkedItem;

		public LLItem(Object newItem) { 
			item = newItem; 
		}
		public void setLinked(LLItem newLinkedItem) { 
				linkedItem = newLinkedItem; 
			}

		public void removeLinked() { 
			linkedItem = null;
		}

		public LLItem getLinked(){
			return linkedItem;
		}
	}

	// Iterator
	public class LLIterator
	{
		LLItem current;
		LLItem first;
		LLIterator(LLItem item) { 
			first = item; 
			}
		
		public Object next() 
		{
			if( null == current ){
				current = first;
			}
			else if( null != current.getLinked() ){
				current = current.getLinked();
			}

			return current.item;
		}

		public boolean hasNext(){
			if( null != current ) {
				return (current.getLinked() != null);
			}
			else{
				return (first.getLinked() != null);
			}
		}
		
	}

	// helpers
	private LLItem getLast() {
		LLItem currItem = firstItem;
		while( null != currItem.getLinked() ) {
			currItem = currItem.linkedItem;
		}

		return currItem;
	}


	private LLItem itemWithObject(final Object obj)
	{
		LLItem currItem = firstItem;
		while( (null != currItem) && (obj != currItem.item) ) {
			currItem = currItem.getLinked();
		}

		return currItem;
	}

	// publics
	public void add(Object newItem) {
		LLItem currItem = getLast();
		currItem.linkedItem = new LLItem(newItem);
	}

	public boolean insert(Object newObject, Object oldObject) 
	{
		LLItem currItem = itemWithObject(oldObject);
		if( null == currItem ) {
			System.err.println("No object found.");
			return false;
		}

		LLItem newItem = new LLItem(newObject);
		newItem.setLinked(currItem.linkedItem);
		currItem.setLinked(newItem);
		return true;
	}

	public boolean remove(final Object obj)
	{
		LLItem target = itemWithObject(obj);

		if( null == target ) {
			System.err.println("No object found.");
			return false;
		}

		LLItem currItem = firstItem;
		while( currItem.getLinked() != target ) {
			currItem = currItem.getLinked();
		}

		currItem.setLinked(target.getLinked());
		return true;
	}

	public void rotate() 
	{
		if( null == firstItem.getLinked() ) {
			System.out.println("Single item. Nothing to do");
		}

		LLItem currentItem = firstItem;
		LLItem nextItem = currentItem.linkedItem;

		// will stop on last item — that has no linked item
		while ( null != nextItem ) 
		{
			LLItem tmp = nextItem.linkedItem;
			nextItem.setLinked(currentItem);
			currentItem = nextItem;
			nextItem = tmp;
		}
		firstItem.removeLinked();
		firstItem = currentItem;
	}
	
	public LLIterator iterator() {
		if ( null == firstItem ) {
			return null;
		}
		return new LLIterator(firstItem);
	}


	// ================================================================================
	// tests
	public static void main (String[] args)
	{
		// create
		LinkedList ll = new LinkedList("6");
		ll.add("5");
		ll.add("4");
		ll.add("3");
		ll.add("2");
		ll.add("1");
		
		LLIterator it = ll.iterator();
		do {
			System.out.println("Current object is: " + it.next());
		} while ( it.hasNext() ); 


		// rotate
		System.out.println("Rotate");
		ll.rotate();
		it = ll.iterator();
		do {
			System.out.println("Current object is: " + it.next());
		} while ( it.hasNext() );

		// remove one item
		System.out.println("Remove item");
		ll.remove("4");
		it = ll.iterator();
		do {
			System.out.println("Current object is: " + it.next());
		} while ( it.hasNext() );


		// insert item
		System.out.println("Insert item");
		ll.insert("3.1", "3");
		it = ll.iterator();
		do {
			System.out.println("Current object is: " + it.next());
		} while ( it.hasNext() );
	}
}
