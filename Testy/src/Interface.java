import java.util.ArrayList;

public class Interface {
  public static void main(String[] args) {
    TextSource a = new Keyboard();

   // Keyboard a = (Keyboard) new TextSource(); //cannnot do the other way around
    System.out.println(a.getText()+ "asdas");
    ArrayList<TextSource> list = new ArrayList<TextSource>();
    list.add( new Keyboard() );
    list.add( new Keyboard() );
    list.add( new CellPhone() );
    list.add( new TwitterAccount() );
    list.add( new CellPhone() );
    for(int i=0; i<list.size(); i++)
      System.out.println( list.get(i).getText() );

  }
  }
interface TextSource { public String getText(); } // declare new type for objects implementing this common method signature
//this general type, allows use of different types of objects in a common way (relying only on the methods in this interface)

//here are several classes that might implement all of the methods within the TextSource interface
class Keyboard implements TextSource       {public String getText() { return "keyboard"; }  }
class CellPhone implements TextSource      { public String getText() { return "textMessage"; } }
class TwitterAccount implements TextSource { public String getText() { return "mentionedTweet"; } }
class TextFile implements TextSource       { public String getText() { return "textFromFile"; } }