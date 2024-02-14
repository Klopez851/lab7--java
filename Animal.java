//Filename: Animal.java
//Author: Keidy Lopez
//Description:

import java.util.Scanner;

public class Animal {
    //every animal has a unique id number (no repeats or negatives)
  private int id;

    //tells us the type of animal, not its actual name
  private String name;

    //the cage number is in an enclosure number, so a cage is part of an area of a zoo which is the...
    // ...enclosure, every single animal has its own cage number(no two animal can have the same cage)
  private int cage_num;
  private int enclosure_num;

    //in pounds, at least 0.5lb, no ounces just decimal
  public double weight;

    //weighted annually(only year is needed, no month or day)
  public int weightDate;

    // only allow S.M.L as possible answers
  public char size;

    //feed them once a day in a 24hr cycle, animals get fed at different times, this variable is the
    //time that the animal gets fed
  public int feedHour;

    //valid food types: grains, grass,meat,fruit,insects
  public String food_type;



  //-----------------//
  //   CONSTRUCTOR   //
  //-----------------//
  public Animal(int id,String name, int cage_num,int enclosure_num){
      this.id = id;
      this.name = name;
      this.cage_num=cage_num;
      this.enclosure_num=enclosure_num;
  }

  //-------------//
  //   GETTERS   //
  //-------------//
  public int getID(){return id;}

  public String getName(){return name;}

  public int getEnclosure(){return enclosure_num;}

  public int getCage(){return cage_num;}


  //-------------------//
  //   OTHER METHODS   //
  //-------------------//
  public void changeName(String newName){this.name = newName;}

  public void changeEnclosure(int newEnclosure){this.enclosure_num = newEnclosure;}

  public void changeCage(int newCage){this.cage_num = newCage;}


  //they are hungry only if it is their time to get fed
  public boolean isHungry(int currHour){
      if(!(currHour==feedHour))return false;
      return true;
  }

  public boolean overdueWeightCheck( int currYear){
      if(currYear<=weightDate)return false;
      return true;
  }

  public String toString(){
    return id+","+name+","+cage_num+","+enclosure_num+","+weight+","+weightDate+","+size+","+feedHour+","+food_type;
  }


  //--------------------//
  //   STATIC METHODS   //
  //--------------------//
  public static Animal getAnimal(Scanner input){
      //gets name
      String name;
      do {
          System.out.print("Animal type: ");
          name = input.nextLine();
          if(name.isBlank())System.out.println("invalid input, try again");
      }while(name.isBlank());


      //gets id number
      int id;
      do {
          System.out.print("ID number: ");
          id = input.nextInt();
          input.skip("\n");
          if(id<=-1)System.out.println("invalid input, try again");
      }while(id<=-1);


      //gets cage number
      int cage_num;
      do {
          System.out.print("Cage number: ");
          cage_num = input.nextInt();
          input.skip("\n");
          if(cage_num<=-1)System.out.println("invalid input, try again");
      }while (cage_num<=-1);


      //gets enclosure number
      int enclosure_num;
      do{
          System.out.print("Enclosure number: ");
          enclosure_num = input.nextInt();
          input.skip("\n");
          if(enclosure_num<=-1)System.out.println("invalid input, try again");
      }while (enclosure_num<=-1);


      return new Animal(id,name,cage_num,enclosure_num);
  }
}
