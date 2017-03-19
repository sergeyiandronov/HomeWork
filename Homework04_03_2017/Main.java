package Homework04_03_2017;


//package test;
import java.io.*;
import java.util.*;
/**
 *
 * @author Сергей
 */
public class Main {
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
      TreeSet<Pupil> tree=new TreeSet();
      Scanner sc=new Scanner(System.in);
      int cn=Integer.parseInt(sc.nextLine());
      for(int t=0;t<cn;t++){
          String[] f=sc.nextLine().split(" ");
          tree.add(new Pupil(f[0],Integer.parseInt(f[1])));
         }
    
       int hm=sc.nextInt();
      
   
       for(Pupil u:tree){
           if(u.mark>hm){
               System.out.println(u.name);
           }
       }
    }}