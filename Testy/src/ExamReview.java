import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

// Most requested: Iterators, Generics, Recursion, Complexity Analysis, then ADT operation complexities : Least Requested

// What should IteratorCombiner implement?
// What should the Generic type be Iterator<Integer> vs Iterator<T>?
class IteratorCombiner <T> implements Iterator<T> {

  private Iterator<T>[] array; // the array of iterators that we'll return data from
  private int index; // the index of the next iterator (0->array.length-1) that hasNext()
  
  public IteratorCombiner(Iterator<T>[] iterators) { // store this array locally, and start index to step through contents at 0
    array = iterators;
    index = 0;
    // handle unusual case where an iterator is already empty
    while(index < array.length && !array[index].hasNext()) index++; // advance index past any iterators with hasNext() that returns false
  }
  
  @Override
  public boolean hasNext() { // index advances past last iterator when all iterators' hasNext() methods return false
    return index < array.length;
  }

  @Override
  public T next() { // steps through the values returned by each of the iterators in array
    T value = array[index].next();
    while(index < array.length && !array[index].hasNext()) index++; // advance index when array[index].hasNext() return false
    return value;
  }  
}

public class ExamReview {
  public static void main(String[] args) {
    // 1. Iterators and Generics ( discuss the enhanced for / for-each loop, then implemented IteratorCombiner
    ArrayList<Integer> a = new ArrayList<>(Arrays.asList(4, 5, 6, 7, 8));
    ArrayList<Integer> b = new ArrayList<>(Arrays.asList(1, 2, 3));
    ArrayList<Integer> c = new ArrayList<>(Arrays.asList(9, 10));
    //for(Integer i : a) 
    Iterator<Integer> iterator = new IteratorCombiner( new Iterator[] { b.iterator(), a.iterator(), c.iterator() } );
    while( iterator.hasNext() ) {
      Integer i = iterator.next();
      System.out.print( i + " " );
    }
    System.out.println();
    
    // 2. Recursion: print the contents of a file/folder
    // each folder may contain other folders, so calling this printContents() method recursively may be helpful
    File cwd  = new File(".");
//    printContents( cwd, "" );
    
    // 3. Implement an Iterable FolderContents class to enable stepping through these files for any purpose:
    for(File f :  new FolderContents(cwd) ) // this class FolderContents is defined below
      System.out.println( f.getName() + ": " + f.length() );
  }

  // order of implementation:
  // defined the recursive case: calling printContents to print the contents of a contained folder
  // defined the base case: only print the name of the file for non-folder/non-directory files
  // update definition to provide more indentation for the contents of each folder
  public static void printContents(File file, String indentation) {
    System.out.println( indentation + file.getName() );
    if( file.isDirectory() ) {
      File[] files = file.listFiles();
      for(int i=0; i<files.length; i++)
        printContents( files[i], indentation + "  " );
    }
  }  
}

class FolderContents implements Iterable<File> { // able to create iterator to step through all contained files
  
  private File file;
  public FolderContents(File file) { this.file = file; }
  
  @Override
  public Iterator<File> iterator() {
    if( file.isDirectory() ) { // recursive case: create iterators for each file contained within this directory
      File[] files = file.listFiles();
      Iterator<File>[] iterators = new Iterator[files.length];
      for(int i=0;i<files.length;i++)
        iterators[i] = new FolderContents(files[i]).iterator();
      return new IteratorCombiner(iterators); // the combine those interators into a single iterator using our IteratorCombiner
    } else { // base case: return an iterator that only contains a single element
      // we could create a new class to describe this iterator's behavior
      // or we could indirectly use the iterator from another iterable class:
      return Arrays.asList( file ).iterator(); // iterator that returns a single value: file
    }
  }
  
}