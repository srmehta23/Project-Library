
  public class GenericsType<T> {

    private T t;
    
    public T get(){

      System.out.println(t.getClass());
        return this.t;
    }
    public T gett(T a) {
      a = this.t;
      return a;
    }
    
    public void set(T t1){
        this.t=t1;
    }
    
    public static void main(String args[]){
        GenericsType<String> type = new GenericsType<String>();
        type.set("Pankaj"); //valid
        
        GenericsType type1 = new GenericsType(); //raw type
        type1.set("Pankaj"); //valid
        type1.set(10);
        System.out.println(type1.gett());
    }
  }
