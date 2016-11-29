public interface Figure{
void draw();
}

public class Circle implements Figure{ 
@Override
  public void draw(){
  System.out.println("Circle");
 }
}

public class Oval implements Figure{
@Override
 public void draw(){
  System.out.println("Oval");
 
 }


}
