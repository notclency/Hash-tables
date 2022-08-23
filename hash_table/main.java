import java.io.File;
import java.io.FileNotFoundException;
//import java.util.Arrays;
import java.util.Hashtable;
//import java.util.LinkedList;
import java.util.Scanner;

class Main{

    //Struct
    public static class person{
        String Fname;
        String Lname;
        int age;
    }
    //static LinkedList<person> chain = new LinkedList<>();

    
    static int size = 40;
    static int[] array = new int[size];

    public static void main(String[] args) throws FileNotFoundException{

        Hashtable<Integer, person> table = new Hashtable<>();

        //System.out.println(array.length + " l");
        //System.out.println(Arrays.toString(array));

        read_file(array, table);

        //System.out.println(Arrays.toString(array));
        //System.out.println(table.get(1).Fname);
        person i = search("Makena Wall", table);
        if(i == null){
            //System.out.println(hashCode("Cunt", table));
            System.out.println("NOT FOUND");
        }else{
            System.out.println("Name: " + i.Fname + " " +i.Lname + "\nAge:  " + i.age);
        }
    }

    public static void read_file(int[] array, Hashtable<Integer, person> table /*LinkedList<person>> table*/) throws FileNotFoundException {
        //File file = new File("C:/Users/PC/Desktop/proj/myGit/Hash-tables/hash_table/dat.txt");
        File file = new File("C:/Users/PC/Desktop/proj/myGit/Hash-tables/hash_table/data.txt");

        Scanner input_file = new Scanner(file);
        int[] n = {-1};
        while(input_file.hasNextLine()){
            
            String Fname = input_file.next();
            String Lname = input_file.next();
            int age = input_file.nextInt();
            fill_table(table, array, Fname, Lname, age, n);
            n[0]++;
            
        }
        input_file.close();
        
    }

    public static void fill_table(Hashtable<Integer, person> table/*LinkedList<person>> table*/, int[] array, String Fname, String Lname, int age, int[] n) {
        person person = new person();
        //LinkedList<person> chain = new LinkedList<person>();
        
        person.Fname = Fname;
        person.Lname = Lname;
        person.age = age;
        int position = hashCode(Fname+Lname, table);
        //System.out.println(position);

        while(exist(position, array) || position > array.length){
            if(position > array.length){
                position = 0;
            }
            position ++;
        }
        table.put(position, person);
        //hain.addLast(person);
        array[n[0] + 1] = position;
        
    }

    public static int hashCode(String Fullname, Hashtable<Integer, /*LinkedList<person>>*/ person> table){
        
        int ascii = 0;
        for(int i = 0; i < Fullname.length(); i++){
            ascii += Fullname.charAt(i);
        }

        //System.out.println(ascii % size);

        return ascii % size;
    }

    public static boolean exist(int value, int[] array) {

        boolean test = false;
        for (int element : array) {
            if (element == value) {
                test = true;
                break;
            }
        }
        
        return test;
    }

    public static person search (String find, Hashtable<Integer, person> table) {
        
        String[] parts;
        int position;
        person output;
        
        parts = find.split(" ");
        position = hashCode(parts[0]+parts[1], table);
        output = table.get(position);
            
        //System.out.println(table.size());
        while(output != null){
           if(output.Fname.equals(parts[0]) && output.Lname.equals(parts[1]))
                return output;
            position ++;
            position %= size;
            output = table.get(position);
        }
        return null;
    }
}