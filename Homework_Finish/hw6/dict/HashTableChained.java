/* HashTableChained.java */

package dict;
import list.*; // Apply the singly-linked list to finish this homework.

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {
  private int size;                        // the number of Entries in hash table.
  private int prime;                       // the number of buckets in hash table.
  private SList[] slist;

  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/


  public static boolean isPrime(int N){
    if(N<2) return false; 
        for(int i=2; i*i <= N; ++i)  
            if(N%i == 0) return false; 
        return true;  
  }

  public HashTableChained(int sizeEstimate) {
    size = 0;
    prime = sizeEstimate;  // the number of buckets is a prime.
    while(!isPrime(prime) && prime <= 2*sizeEstimate){
      prime++;
    }
    slist = new SList[prime];
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    size = 0;
    prime = 97;
    slist = new SList[prime];
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    return (Math.abs(code) % prime);    // The simplest compression function: h(i) = |i| mod N.
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    return size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    return (size() == 0);
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    Entry newEntry = new Entry();
    newEntry.key = key;
    newEntry.value = value;
    int index = compFunction(key.hashCode());
    if(slist[index] == null){
      slist[index] = new SList();
    }
    slist[index].insertBack(newEntry);
    size++;
    return newEntry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    if(slist[compFunction(key.hashCode())].isEmpty()) {return null;}
    else{
      try{
      SListNode current = (SListNode) slist[compFunction(key.hashCode())].front();
      while(current != null){
        if(((Entry) (current.item())).key().equals(key)){
          return (Entry) current.item();
        }else{
          current = (SListNode) current.next();
        }
      }
      }catch(InvalidNodeException e){
        System.out.println(e);
     }
      return null;   
  }
 }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    if(slist[compFunction(key.hashCode())].isEmpty()) {
      return null;
    }else{
      try{
      SListNode current =(SListNode) slist[compFunction(key.hashCode())].front();
      while(current != null){
        if(((Entry) (current.item())).key().equals(key)){
          current.remove();
          size--;
          return (Entry) current.item();
        }else{
          current = (SListNode) current.next();
        }
      }
    }catch(InvalidNodeException e){
      System.out.println(e);
    }
      return null;
    }
    }


  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    size = 0;
    slist = new SList[prime];  
  }
  
  public void numCollisions(){
    int num = 0;
    double expectedCollisions = 0;
    expectedCollisions = size - prime + prime * Math.pow((1-1/(double) prime), (double)size);
    for(int i = 0; i < slist.length; i++){
      if(slist[i] == null){
        System.out.print("[ " + 0 + " ]");
      }else{
        System.out.print("[ " +slist[i].length() + " ]");
        if(slist[i].length() > 1){
          num++;
        }
      }
    }
    System.out.println('\n'+"The actual collision size is: " + num);
    System.out.println("The expected collision size is: " + expectedCollisions);
  }
}
