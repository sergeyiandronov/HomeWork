/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Homework04_03_2017;

/**
 *
 * @author Сергей
 */
public class Pupil implements Comparable<Pupil>{
    public int mark;
    public String name;
  public Pupil(String name,int n){
      this.mark=n;
      this.name=name;
  }
    @Override
    public int compareTo(Pupil o) {
     return this.name.compareTo(o.name);
    }
    
}
