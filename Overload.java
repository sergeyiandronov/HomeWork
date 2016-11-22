public class Dice{
public int roll(int k){
 return (int)(Math.random()*k)+1;
}
}
public class Hexahedron extends Dice{
public int roll(){
 return (int)(Math.random()*6)+1;
}
}
public class Dodecahedron extends Dice{
public int roll(){
 return (int)(Math.random()*12)+1;
}
}
public class Icosahedron extends Dice{
public int roll(){
 return (int)(Math.random()*20)+1;
}
}
