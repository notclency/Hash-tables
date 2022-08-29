import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashTable {
    
    private HashNode[] table;
    private int tableSize;
    private int size;

    public void printKeys(){
        
        for(int i = 0; i < tableSize; i++){
            HashNode head = table[i];
            if(head != null){
                System.out.println("");
                System.out.print("Index " + i + ":");
            }
            while(head != null){
                System.out.print(" -- " + head.person.name + "'s key: " + head.key);
                head = head.next;
            }
        }
        System.out.println("\n");
    }

    public HashTable(){
        this(20);        // default capacity
    }

    public HashTable(int capacity){
        this.tableSize = capacity;
        this.table = new HashNode[tableSize];
        this.size = 0;
    }
    
    public void read_file() throws FileNotFoundException{
        File file = new File("C:\\Users\\PC\\Desktop\\proj\\myGit\\Hash-tables\\hash_table\\team.txt");
        Scanner readFile = new Scanner(file);
        load(readFile);
        
        return;
    }

    public void load(Scanner file) {
        
        char team;
        char position;
        int goals;
        int assists;
        int goals_ga;
        String name;

        int key;

        while(file.hasNextLine()){
            team = file.next().charAt(0);
            position = file.next().charAt(0);
            goals = file.nextInt();
            assists = file.nextInt();
            goals_ga = file.nextInt();
            name = file.nextLine();

            person person = new person(name, team, position, goals, assists, goals_ga);
            key = getKey(person);
            put(key, person);           
        }

        return;
    }

    public int getKey(person person){

        int key = 0;

        for(int i = 0; i < person.name.length(); i++){
            key += person.name.charAt(i);
        }

        return key;
    }

    public int hashIndex(Integer key) { return key % tableSize; }

    public boolean isEmpty(Integer key) { return size == 0; }

    public void printTable() {
        
        for(int i = 0; i < tableSize; i++){
            HashNode head = table[i];
            while(head != null){
                System.out.println(head);
                head = head.next;
            }
        }
        System.out.println("");
    
        return;
    }

    public void put(Integer key, person person){
        
        if(key == null || person == null){
            System.out.println("Key or Node is null");
        }else{
            int index = hashIndex(key);
            HashNode head = table[index];
            while(head != null){                   // checks if key already exist
                if(head.key.equals(key)){
                    head.person = person;
                    return;
                }
                head = head.next;
            }
            size ++;
            head = table[index];
            HashNode newNode = new HashNode(key, person);
            newNode.person = person;
            newNode.next = head;
            table[index] = newNode;
        }
    
        return;
    }

    public HashNode get(Integer key){
        
        int index = hashIndex(key);
        HashNode head = table[index];

        while(head != null){
            if(head.key.equals(key)){
                return head;
            }
            head = head.next;
        }

        return null;
    }

    public String remove(Integer key){
        
        HashNode prev = null;
        HashNode head = table[hashIndex(key)];

        while(head != null){
            if(head.key.equals(key)){
                if(prev == null){
                    if(head.next == null){
                        table[hashIndex(key)] = null;
                    }else{
                        table[hashIndex(key)] = head.next;
                    }
                }else{
                    prev.next = head.next;
                }
                size --;
                return "\nKey: " + key + " has been removed.";
            }
            prev = head;
            head = head.next;

            
        }

        return "\nKey: " + key + " not found.";
    }
    
    public static void main(String[] args) throws FileNotFoundException {

        HashTable myTable = new HashTable(25);
        myTable.read_file();
        System.out.print("\n");
        //myTable.printTable();
        //System.out.println("--------");
        myTable.printKeys();
        System.out.println("--------");
        System.out.println(myTable.remove(1600));
        System.out.println(myTable.size);
        myTable.printKeys();

        return;
    }

    class HashNode{

        Integer key;
        person person;
        HashNode next;

        HashNode(int key, person person){
            this.key = key;
            this.person = null;
            this.next = null;
        }

        public String toString(){
            return this.person.name + " " + this.person.team + " " + this.person.position + " " + 
            this.person.goals + " " + this.person.assist + " " + 
            this.person.goals_ga;
        }
    }

    class person{
        String name;
        char team;
        char position;
        int goals;
        int assist;
        int goals_ga;

        person(String name, char team, char position, int goals, int assist, int goals_ga){
            this.name = name;
            this.team = team;
            this.position = position;
            this.goals = goals;
            this.assist = assist;
            this.goals_ga = goals_ga;
        }
    }
}
