//Filename: Lab7.java
//Author: Keidy Lopez
//Description: program that takes an manipulates animal objects

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Lab7 {
    //gets as many animals objects as the use desires
    public static void getAnimals(Scanner input, ArrayList<Animal> myAnimals){
        boolean flag = true;

        //creates as many animal objects as desired
        while (flag) {
            Animal animal = Animal.getAnimal(input);

            //gets weight of the animal
            double weight;
            do {
                System.out.print("What is their weight(Lb in decimal form, no oz.)? ");
                weight = input.nextDouble();
                if (!(weight >= 0.5)) System.out.println("invalid input, try again");
            } while (!(weight >= 0.5));
            animal.weight = weight;


            //gets last time the animal was weighted
            int weightDate;
            do {
                System.out.print("Last time they were weighted(year only): ");
                weightDate = input.nextInt();
                input.skip("\n");
                if (!(weightDate >= 1000)) System.out.println("invalid input, try again");
            } while (!(weightDate >= 1000));
            animal.weightDate=weightDate;


            //gets the size of the animal
            String size1;
            do {
                System.out.print("Their size(S,M,L)? ");
                size1 = input.nextLine();
                if (!(size1.equalsIgnoreCase("S") || size1.equalsIgnoreCase("M") ||
                        size1.equalsIgnoreCase("L"))) {
                    System.out.println("invalid input, try again");
                }
            } while (!(size1.equalsIgnoreCase("S") || size1.equalsIgnoreCase("M") ||
                    size1.equalsIgnoreCase("L")));
            animal.size = size1.charAt(0);


            //gets the time the animal need to be fed
            int time;
            do {
                System.out.print("What time do they get fed(24hr format)? ");
                time = input.nextInt();
                if (time > 24 || time < 1) System.out.println("invalid input, try again");
            } while (time > 24 || time < 1);
            animal.feedHour=time;


            //get the type of food the animal eats
            int choice;
            do {
                System.out.print("Please select a food type:\n1)Grains\n2)Grass\n3)Meat\n4)Fruit\n5)Insects\nEnter choice here:");
                choice = input.nextInt();
                input.skip("\n");
                if (choice > 5 || choice < 1) System.out.println("invalid input, try again");
            } while (choice > 5 || choice < 1);

            //assigns the food type corresponding to the number given
            if (choice == 1) animal.food_type = "Grains";
            else if (choice == 2) animal.food_type = "Grass";
            else if (choice == 3) animal.food_type = "Meat";
            else if (choice == 4) animal.food_type = "Fruit";
            else animal.food_type = "Insects";

            myAnimals.add(animal);

            //asks user if they want to add another animal
            String ans;
            do {
                System.out.print("Would you like to add another animal(Y/N)? ");
                ans = input.nextLine();
                if (!(ans.equalsIgnoreCase("yes") || ans.equalsIgnoreCase("y")) ||
                        ans.equalsIgnoreCase("n") || ans.equalsIgnoreCase("no")) {
                    flag = false;
                }
            }while(!(ans.equalsIgnoreCase("yes") || ans.equalsIgnoreCase("y") ||
                    ans.equalsIgnoreCase("n") || ans.equalsIgnoreCase("no")));
        }
    }

    //prints out animal object information
    public static void printAnimals(ArrayList<Animal> myAnimals) {
        if (!(myAnimals.isEmpty())){
            //prints animals in arraylist as CSVs
            for (Animal a : myAnimals) {System.out.println(a);}
        }
        else{System.out.print("No animals found");}
    }

    //outputs the info from an array to a specified file
    public static void outputToFile(File file,ArrayList<Animal> myAnimals){
        //outputs info to specified file
        try {
            PrintStream fInput = null;
            if(file.exists()) {
                fInput = new PrintStream(file);
                //csv header
                fInput.println("id,name,cage_num,enclosure_num,weight,weightDate,size,feedHour,food_type");

                //adds the animal info
                for (Animal a : myAnimals) {
                    fInput.println(a);
                }
            }
            else{System.out.println("file not found");}

            //close file
            fInput.close();
        }catch (IOException exception){
            System.out.print("Error occured while processing the information");
            exception.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        ArrayList<Animal> myAnimals = new ArrayList<>(30);
        String[] header;
        Scanner input = new Scanner(System.in);
        int choice = 0;

        while (choice != 7){
            //determines what algorithm to run
            do {
                System.out.print("ANIMAL INDEXING\n|\n| What would you like to do?\n|\n| 1)Manually enter animal information\n" +
                        "| 2)Read information from a file\n| 3)Save all animal information to a file\n| 4)See all hun" +
                        "gry animals\n| 5)See all animals that need to be weighted\n| 6)Search database for all animals" +
                        " in a particular enclosure\n| 7) Exit the program" +
                        "\n|\n| Enter choice here:");
                choice = input.nextInt();
                input.skip("\n");
                if (choice > 7 || choice < 1) System.out.println("Invalid input, please try again");
            } while (choice > 7 || choice < 1);
//COULD HAVE USED A SWITCH STATEMENT

            //checks if user wants to exit the program
            if (choice == 7){
                int choices = 1;
                do {
                    System.out.print("All data not previously saved in a file will be deleted\nDo you want to continue(Y/N)? ");
                    String ans = input.nextLine();
                    if (ans.equalsIgnoreCase("yes") || ans.equalsIgnoreCase("y")) {
                        choices = 0;
                    } else if (ans.equalsIgnoreCase("no") || ans.equalsIgnoreCase("n")) {
                        choice = 0;
                        choices=0;
                    } else {
                        System.out.println("invalid input, try again");
                    }
                }while(!(choices==0));
            }

            //gets input from the console
            if (choice == 1) {
                System.out.print("\n");
                getAnimals(input, myAnimals);
                System.out.println("Processing done!");
            }

            //gets input from the console and send that info to a different file
            else if (choice == 2) {
                System.out.print("What is the filename: ");
                String file_name = input.nextLine();
                File file = new File(file_name);
                try {
                    Scanner fInput = new Scanner(file);
                    String head3r = fInput.nextLine();
                    header = head3r.split(",");
                    while (fInput.hasNextLine()) {
                        String line = fInput.nextLine();

                        //stops on line 21, the split method doesnt get all 9 indexes just 2
                        if (!(line.isEmpty())) {
                            String[] entries = line.split(",");
                            // header: id,name,cage_num,enclosure_num,weight,weightDate,size,feedHour,food_type
                            int id = Integer.parseInt(entries[0]);
                            int cageNum = Integer.parseInt(entries[2]);
                            int enclosureNum = Integer.parseInt(entries[3]);
                            double weight = Double.parseDouble(entries[4]);
                            int weightDate = Integer.parseInt(entries[5]);
                            char size = entries[6].charAt(0);
                            int feedHour = Integer.parseInt(entries[7]);

                            Animal animal = new Animal(id, entries[1], cageNum, enclosureNum);
                            animal.weight = weight;
                            animal.weightDate = weightDate;
                            animal.size = size;
                            animal.feedHour = feedHour;
                            animal.food_type = entries[8];

                            myAnimals.add(animal);
                        }
                    }
                    fInput.close();
                } catch (IOException exception) {
                    System.out.print("Something went wrong while reading the file");
                    exception.printStackTrace();
                    System.exit(1);
                }
                System.out.println("Processing done!");
            }

            //saves all info to a specified file
            else if (choice == 3) {
                //gets name of the file
                if (myAnimals.isEmpty()) {
                    System.out.println("no animals found in database");
                } else {
                    String out_name;
                    do {
                        System.out.print("What file would you like to output the information to? ");
                        out_name = input.nextLine();
                        if (out_name.isBlank()) System.out.println("Invalid input, try again");
                    } while (out_name.isBlank());
                    File file = new File(out_name);

                    outputToFile(file, myAnimals);
                    System.out.println("Processing done!");
                }
            }

            //checks the arraylist for all the hungry animals and prints them out
            else if (choice == 4) {
                if (myAnimals.isEmpty()) {
                    System.out.println("no animals found in database");
                }
                //gets all the hungry animals and prints their feedhour, ID, Cage, and Enclosure, foodtype
                else {
                    int currhour;
                    do {
                        System.out.print("\nPlease enter current hour(24 hr format):");
                        currhour = input.nextInt();
                    }while (currhour>24||currhour<1);

                    for(Animal a: myAnimals){
                        if(a.isHungry(currhour)){
                            System.out.printf("(%d)ID: %d, Cage: %d, Enclosure: %d, Food Type: %s\n",a.feedHour,a.getID(),
                                    a.getCage(),a.getEnclosure(),a.food_type);
                        }
                    }
                }

            }

            //checks the arraylist for all the overdue weight checks prints them out
            else if (choice == 5) {
                if (myAnimals.isEmpty()) {
                    System.out.println("no animals found in database");
                }
                else {
                    int weightDate;
                    do {
                        System.out.print("Current year: ");
                        weightDate = input.nextInt();
                        input.skip("\n");
                        if (!(weightDate >= 1000)) System.out.println("invalid input, try again");
                    } while (!(weightDate >= 1000));

                    //gets all overdue animals
                    for(Animal a: myAnimals){
                        if(a.overdueWeightCheck(weightDate)){
                            System.out.printf("Last weight check: %d, ID: %d, Cage: %d, Enclosure: %d\n",
                                    a.weightDate,a.getID(),a.getCage(),a.getEnclosure());
                        }
                    }
                }

            }

            else if (choice == 6){
                if (myAnimals.isEmpty()) {
                    System.out.println("no animals found in database");
                }
                else {
                    int enclosure_num;
                    do{
                        System.out.print("Enclosure number: ");
                        enclosure_num = input.nextInt();
                        input.skip("\n");
                        if(enclosure_num<=-1)System.out.println("invalid input, try again");
                    }while (enclosure_num<=-1);

                    //gets all animals in specified enclosure
                    for(Animal a: myAnimals){
                        if(a.getEnclosure() == enclosure_num){
                            System.out.printf("ID: %d, Cage: %d, Enclosure: %d\n",a.getID(),a.getCage(),
                                    a.getEnclosure());
                        }
                    }
                }


            }



        }

        System.out.println("Goodbye :)");
    }
}
