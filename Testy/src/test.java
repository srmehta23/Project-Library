
public class test {
  public static void main(String[] args) {
    B x = new B(1.2);

}
}
class A {
  public String y; // protected can be accessed from child class
    public A(String s) {
      //super(); // implicitly calls paren't no-arg constructor
      this.y = s;
    }
 }
  
 class B extends A {
   private Double z; 
   private double y;
   public B(double d) {
     super("this is a string"); // must call constructor explicitly, to pass String
     this.z = d; // auto boxing
     int y = 3; // local variable shadows these fields
     this.y = d + 2.3; // y field defined within class B
     super.y += ", or something else"; // w field defined within parent class A
     System.out.println( this.z);
     System.out.println(y);
     System.out.println(this.y);
     System.out.println(super.y);
     
   }  
 }
 