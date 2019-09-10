// TopHat Trace to Review Casting and Generics
class Parent {
  protected int x; // Parent(): 0=>2
  public Parent() { this.x = 2; } // implicit super()
  @Override
  public String toString() { return "Parent: " + this.x; } // 2
  public String methodOfAllParentObjects() { return "6"; }
}
public class Child extends Parent {
  protected int x; // Child(): 0=>1
  public Child() { this.x = super.x; } // implicit super()
  @Override
  public String toString() { return "Child: " + x; } // 1

  public static void main(String[] args) {
    Parent p = new Parent();
    // compile time type of reference p is Parent
    // run time type of object that references is Child
  System.out.println(p);  
  }
  public static<T extends Parent> void print(T object) {
    System.out.println( object.toString() ); // uses run time type determines implementation called here
    // since T can be any type, we cannot call methods specific to Parents or Children from here
    // instead we can add a type bound to T, to accomplish this: change <T> above to <T extends Parent>
    //System.out.println( object.methodOfAllParentObjects() );
  }  
}