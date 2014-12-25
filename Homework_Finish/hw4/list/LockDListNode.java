/*LockDListNode.java*/

package list;

/**
 *  A LockDListNode is a lock node in a DList (doubly-linked list), which can 
 *  never be removed from its list.
 */

public class LockDListNode extends DListNode{

	protected boolean lock;

/**
   *  LockDListNode() constructor.
   *  @param lock the information of lock.
   */

	LockDListNode(Object i, DListNode p, DListNode n){
		super(i, p, n);
		lock = false;
	}
}