import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
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

        //Arrays.sort(array);
        //System.out.println(Arrays.toString(array));
        //System.out.println(table.get(1).Fname);
        

        /*
        person i = search("Makena Wall  ", table);
        if(i == null){
            //System.out.println(hashCode("Cunt", table));
            System.out.println("NOT FOUND");
        }else{
            System.out.println("Name: " + i.Fname + " " +i.Lname + "\nAge:  " + i.age);
        } 
        */
        System.out.println("-------------------------------------------");
        print(table);
        System.out.println("-------------------------------------------");
        insert(table, "Clency", "Tabe", 18);
        //print(table);
        int i = search("Clency Tabe", table);
        delete(table, "Clency Tabe");
        if(table.get(i) != null){
            System.out.println("Name: " + table.get(i).Fname + " " + table.get(i).Lname + "\nAge:  " + table.get(i).age);
        }else{
            System.out.println("NOT FOUND");
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

    public static int search (String find, Hashtable<Integer, person> table) {
        
        String[] parts;
        int position;
        person output;
        
        parts = find.split(" ");
        position = hashCode(parts[0]+parts[1], table);
        output = table.get(position);
            
        //System.out.println(table.size());
        while(output != null){
           if(output.Fname.equals(parts[0]) && output.Lname.equals(parts[1]))
                return position;
            position ++;
            position %= size;
            output = table.get(position);
        }
        return position;
    }
    
    public static void print(Hashtable<Integer, person> table) {
        
        for(int i = 0; i < size; i++){
            while(table.get(i) == null){
                i++;
            }
            System.out.println("Name: " + table.get(i).Fname + " " + table.get(i).Lname + "\nAge:  " + table.get(i).age);
            
        }

        return;
    }

    public static void insert(Hashtable<Integer, person> table, String Fname, String Lname, int age) {
        
        person new_person = new person();
        int position = hashCode(Fname+Lname, table);

        new_person.Fname = Fname;
        new_person.Lname = Lname;
        new_person.age = age;
        
        while(table.get(position) != null){
            position ++;
            position %= size;
        }

        table.put(position, new_person);
    }

    public static void delete(Hashtable<Integer, person> table, String find) {
        int i = search(find, table);
        if(table.get(i) == null)
            System.out.println("DOESNT EXIST");
        else
            table.remove(i);
    }
}