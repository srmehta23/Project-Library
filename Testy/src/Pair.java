import java.util.ArrayList;

public class Pair <FirstType, SecondType> {

  // 1. Generic Type Parameter for a Method
  //    generalized this method, first by adding parameters a, b, and c
  //    then changed the types of parameters and return value from String to Object
  //    then added the MethodType generic type parameter: eliminated need for downcasting later
  public static <MethodType> MethodType notLikeTheOthers(MethodType a, MethodType b, MethodType c) {
    if( a.equals(b) && !a.equals(c) ) return c;
    else if( a.equals(c) && !a.equals(b) ) return b;
    else if( b.equals(c) && !a.equals(b) ) return a;
    else throw new IllegalArgumentException("either all arguments were the same, or all were different");
  }

  // 2. Generic Type Parameter for a Class
  //    started with Object types for first and last
  //    then made a single ClassType generic parameter for both fields
  //    then made into two different generic parameters: FirstType and SecondType
  private FirstType first;
  private SecondType last;
  public Pair(FirstType first, SecondType last) {
    this.first = first;
    this.last = last;
  }
  public FirstType getFirst() { return this.first; }
  public SecondType getLast() { return this.last; }
   
  // 3. Discussed Raw Type, and Generic Type Bounds
  //    first looked at ArrayList without generic type specification <>
  //    then revisited the findEarliest() method from last week: and fixed warnings
  //      - first changed Comparable general references to CompareType generic type parameter
  //      - then fully specified this bound as Comparable<CompareType> because Comparable type is defined with generic
  public static <CompareType extends Comparable<CompareType>> CompareType findEarliest(CompareType[] array) {
    // note that bound: <Type extends Random> includes Random, MyRandom, and any other descendants of Random
    // and that we use extends to describe type bounds whether they are base classes or interfaces
    CompareType earliest = array[0];
    for(int i=1; i<array.length; i++)
      if( earliest.compareTo( array[i] ) > 0 )
        earliest = array[i];
    return earliest;
  }

  public static void main(String[] args) {
    // 1. when notLikeTheOthers is defined with Object type parameters and return type:
    String s = (String)notLikeTheOthers("A", "B", "A"); // explicit downcast is required
    System.out.println( s.toLowerCase() );                // to undo implicit upcast from argument to parameter types
    System.out.println( (Integer)notLikeTheOthers(3, 2, 3) + 4 );
    System.out.println( notLikeTheOthers("A", 2, "A") ); // yikes, requires deep understanding of implementation details
    // 1. but when the generic type parameter is specified for this method:
    System.out.println( notLikeTheOthers("A", "B", "A").toLowerCase() ); // the compiler knows these types match up
    System.out.println( notLikeTheOthers(3, 2, 3) + 4 ); // and so no explicit cast is required by us
    //System.out.println( notLikeTheOthers("A", 4, "A").toLowerCase() ); // in this case, Object is used as the raw type

    // 2. Similar downcast is needed when we use the Pair objects defined with Object fields rather than generic type
//    Pair p = new Pair("FirstName", "LastName"); // although we just put a string in here, it's possible that:
//    if(Math.random() < 0.5) p = new Pair(1,2);  // something else could change this later
//    if(p.getFirst() instanceof String) // so we carefully check this type
//    System.out.println( ((String)p.getFirst()) .charAt(0) ); // before making the explicit cast
    // 2. Then we made use of generic type parameters instead of Object type reference
    Pair<String,String> p = new Pair<>("Gary", "Dahl");
    if(Math.random() < 0.5) p = new Pair<>(1,2); // now this is recognized as a compile-time error
    System.out.println( p.getFirst().charAt(0) ); // and the explicit cast is no longer needed here

    // 3. here are 
    ArrayList list = new ArrayList(); // leaving out generic type argument: raw type is used here (Object)
    list.add("abc"); // so anything can go into this list
    //list.add(123); 
    String s2 = (String)list.get(0); // and everything that comes out is an Object reference, which may need downcasting
    // the warnings above, are about using this raw type rather than specifying the intended use
    // 3. here is the driver that calls our findEarliest() method from last week 
    String[] strings = new String[] { "banana", "cherry", "apple", "dragonfruit" };
    Integer[] integers = new Integer[] { 19, 97, 55, 23, 42 };
    System.out.println( findEarliest(strings) );
    System.out.println( findEarliest(integers) );
  }
}