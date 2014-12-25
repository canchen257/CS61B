/*LockDList.java*/

package list;

/**
 *  A LockDList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list. A list in which any node can be "locked".
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */
public class LockDList extends DList{

	protected DListNode newNode(Object item, DListNode prev, DListNode next) {
    	return new LockDListNode(item, prev, next);
    	
  }

	public void lockNode(DListNode node){
		((LockDListNode) node).lock = true;
	}

	public void remove(DListNode node){
		if(((LockDListNode) node).lock == true){
			return;
		}else{
			super.remove(node);
		}
	}
}