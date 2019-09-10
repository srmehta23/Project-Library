import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class LinkedList <T> implements Queue<String> {
  
  public static void main(String[] args) {
    // After reviewing some diagrams in inkscape, we generalized removal:
    LinkedNode<String> nodeList = new LinkedNode<>(
        "A", new LinkedNode<>(
        "D", new LinkedNode<>(
        "P", new LinkedNode<>(
        "G", new LinkedNode<>(
        "B", new LinkedNode<>(
        "F", new LinkedNode<>(
        "X", new LinkedNode<>(
        "Q", new LinkedNode<>(
        "Z", null
     )))))))));

//    LinkedNode<String> a = new LinkedNode<>("A");
    // list -> A -> D -> P -> G -> B -> F -> X -> Q -> Z -> null
    // remove node containing an X from any list
    // 1. find the node before the node containing the X
    LinkedNode<String> beforeNodeX = nodeList;
    while( !beforeNodeX.getNext().getData().equals("X") )
      beforeNodeX = beforeNodeX.getNext();
    // note potential issues when:
      // there are multiple Xs in a list
      // there are no Xs in a list
      // when the X is the first element of the list (no node before it)
    //System.out.println( beforeNodeX.getData() ); // quick test
    // 2. then update that node's next reference
    beforeNodeX.setNext(beforeNodeX.getNext().getNext());

    // add() and toString() are defined below
    LinkedList<String> list = new LinkedList<>();
//    list.add("A");
//    list.add("B");
//    list.add("C");
//    list.add("D");
    System.out.println( list );
  }
  
  private LinkedNode<T> head;
  public LinkedList() { this.head = null; }
  
  public void add(T newData) { // add to the end of this list
    if(this.head == null) { // special case: adding first element to empty list
      this.head = new LinkedNode<>(newData,null); // point head to reference only node
    } else {
      // otherwise, step through nodes, looking for the last one
      LinkedNode<T> lastNode = this.head;    
      while( lastNode.getNext() != null )
        lastNode = lastNode.getNext();
      // so that we can update it's next reference to this new node
      lastNode.setNext( new LinkedNode<>(newData,null) );
    }
  }
  
  @Override
  public String toString() {
    String s = "["; // accumulate string contents of data fields within nodes here
    LinkedNode<T> displayNode = this.head; // start at the beginning of the list
    while( displayNode != null ) { // and step through every node until the end
      s += displayNode.getData().toString();
      if(displayNode.getNext() != null) s += " ,";
      displayNode = displayNode.getNext();
    }
    // could have alternatively written this as a for loop:
    //for(LinkedNode<T> i = this.head; i != null; i = i.getNext() ) {
    //  s += i.getData().toString();
    //  if(i.getNext() != null) s += " ,";
    //}    
    return s + "]";
  }

  @Override
  public boolean addAll(Collection<? extends String> arg0) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void clear() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean contains(Object arg0) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean containsAll(Collection<?> arg0) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isEmpty() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Iterator<String> iterator() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean remove(Object arg0) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean removeAll(Collection<?> arg0) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean retainAll(Collection<?> arg0) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int size() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Object[] toArray() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <T> T[] toArray(T[] arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean add(String e) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String element() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean offer(String e) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String peek() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String poll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String remove() {
    // TODO Auto-generated method stub
    return null;
  }

}

// LinkedNode class, as previously discussed
class LinkedNode <T> {
  private T data;
  private LinkedNode<T> next;
  // used eclipse to auto-generate these constructors, accessors, and mutators
  public LinkedNode(T data, LinkedNode<T> next) {
    super();
    this.data = data;
    this.next = next;
  }
  public T getData() {
    return data;
  }
  public void setData(T data) {
    this.data = data;
  }
  public LinkedNode<T> getNext() {
    return next;
  }
  public void setNext(LinkedNode<T> next) {
    this.next = next;
  }
}