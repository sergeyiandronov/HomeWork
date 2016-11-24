/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw;

import java.util.*;

/**
 *
 * @author Сергей
 */
public class HW {
    public  static void main(String[] args){
     Scanner in=new Scanner(System.in);
     String t=in.nextLine();
     StringBuffer t1=new StringBuffer();
     for(int i=0;i<t.length();i++){
        t1.append(t.substring(i, i+1).compareTo("а")+1);
        t1.append(" ");
     }
     System.out.print(t1.toString());
     
    }
}
