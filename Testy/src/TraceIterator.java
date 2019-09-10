import java.util.Iterator;

public class TraceIterator implements Iterator<Integer>, Iterable<Integer> {
  private static int nextCount = 3;
  private int count;
  public TraceIterator(int count) { this.count = count; }  
  @Override
  public boolean hasNext() { return count < 3; }
  @Override
  public Integer next() { count++; return count; }
  @Override
  public Iterator<Integer> iterator() {
    if(TraceIterator.nextCount>0) nextCount--;
    return new TraceIterator(TraceIterator.nextCount);
  }
  // How many times will print() be called: A) 0, B) 1-2, C) 3-4, D) 5-6, E) more than 6 
  public static void main(String[] args) { // nextCount: 3 => 2 => 1
    //for(Integer i : new TraceIterator(4))
    Iterator<Integer> iterator = new TraceIterator(4).iterator();
    while( iterator.hasNext() ) {
      Integer i = iterator.next(); // i: 3
      for(Integer j: new TraceIterator(i)) {// j: 2, 3
        System.out.print( i+j+2 ); // prints: 78
      }
    }
  }
}