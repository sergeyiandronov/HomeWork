import java.util.*;


/**
 *
 * @author Сергей
 */
public class HW {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Scanner in=new Scanner(System.in);
       int o=Integer.parseInt( in.nextLine());
       
       
       String j= in.nextLine();
       String l=in.nextLine();
       String[] h;
       h=l.split(" ");
       
       for(int i=0;i<h.length;i++){
            StringBuffer p =new StringBuffer();
               for(int k=0;k<h[i].length();k++){
                     p.append(h[i].charAt(k));
                        }
              if(o<h[i].length()){ 
               p.deleteCharAt(o-1);
               p.insert(o-1, j);
               h[i]=p.toString();}
                  }
        for(int i=0;i<h.length;i++){
            System.out.print(h[i]+" ");
        }
    }
}
