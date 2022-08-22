import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

class Main{

    //Struct
    public static class person{
        String name;
        int age;
    }
    //static LinkedList<person> chain = new LinkedList<>();

    
    static int size = 5;
    static int[] array = new int[size];

    public static void main(String[] args) throws FileNotFoundException{

        Hashtable<Integer, person> table = new Hashtable<>();

        //System.out.println(array.length + " l");
        //System.out.println(Arrays.toString(array));

        read_file(array, table);

        //System.out.println(Arrays.toString(array));
        //System.out.println(table.get(1).name);
        int i = search("Cunt ", table, array);
        if(i == 0){
            //System.out.println(hashCode("Cunt", table));
            System.out.println("NOT FOUND");
        }else{
            System.out.println(table.get(i).name);
        }
    }

    public static void read_file(int[] array, Hashtable<Integer, person> table /*LinkedList<person>> table*/) throws FileNotFoundException {
        File file = new File("C:/Users/PC/Desktop/proj/msc/hash_table/text.txt");
        Scanner input_file = new Scanner(file);
        int[] n = {-1};
        while(input_file.hasNextLine()){
            
            String name = input_file.next();
            int age = input_file.nextInt();
            //System.out.println(name);
            //System.out.println(age);
            //System.out.println(hashCode(name, table));
            //System.out.println('u'-'a');
            fill_table(table, array, name, age, n);
            n[0]++;
            
        }
        input_file.close();
        
    }

    public static void fill_table(Hashtable<Integer, person> table/*LinkedList<person>> table*/, int[] array, String name, int age, int[] n) {
        person person = new person();
        //LinkedList<person> chain = new LinkedList<person>();
        
        person.name = name;
        person.age = age;
        int position = hashCode(name, table);

        while(exist(position, array) || position > array.length){
            if(position > array.length){
                position = 0;
            }
            position ++;
        }
        table.put(position, person);
        array[n[0] + 1] = position;
        
    }

    public static int hashCode(String name, Hashtable<Integer, /*LinkedList<person>>*/ person> table){
        
        int ascii = 0;
        for(int i = 0; i < name.length(); i++){
            ascii += name.charAt(i);
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

    public static int search (String find, Hashtable<Integer, person> table, int[] array) {
        int ini = hashCode(find, table);
        int k = hashCode(find, table);
        /*
        if(!exist(ini, array)){
            ini = 0;
        }else{
            
            
        }
        */
        while(!table.get(ini).name.equals(find)){             
            ini++;
            if(ini > size){
                ini = 1;
            }
        }
        if(ini == k && !table.get(ini).name.equals(find)){
            ini = 0;
        }
        

        return ini;
    }
}