import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* 
 * Author: Clency Tabe
*/

public class HashTable {
    
    private HashNode[] table;       // The HashTable
    private int tableSize;          // Size of the HashTable
    private int size;               // Number of items in the HashTables


    /*
     * This function prints all the HashNode.player names on the HashTable with their unique hash keys.
     * this displays how the HashTable is formatted with chainned HashNodes
     * @param: None 
     * @return: None
    */
    public void printKeys(){
        
        for(int i = 0; i < tableSize; i++){
            HashNode head = table[i];
            if(head != null){
                System.out.println("");
                System.out.print("Index " + i + ":");
            }
            while(head != null){
                System.out.print(" -- " + head.player.name + "'s key: " + head.key);
                head = head.next;
            }
        }
        System.out.println("\n");
    }

    /* 
     * Initialies the table size to 20 (if no arguemnet is passed) and size to 0.
     * @param: None
    */
    public HashTable(){
        this(1);        // default capacity
        this.size = 0;
        this.table = new HashNode[20];
    }

    /* 
     * Initialies the table size to passed arguement and size to 0.
     * @param: None
     * @return: None.
    */
    public HashTable(int capacity){
        this.tableSize = capacity;
        this.table = new HashNode[tableSize];
        this.size = 0;
    }
    
    /* 
     * this function reads a file (data file) and calls the load function.
     * @param: File type of name file.
    */
    public void read_file(File file) throws FileNotFoundException{
        //File file = new File("C:\\Users\\PC\\Desktop\\proj\\myGit\\Hash-tables\\hash_table\\team.txt");
        Scanner readFile = new Scanner(file);
        load(readFile);
        
        return;
    }

    /* 
     * this function loads data from a file received into a HashTable.
     * @param: Scanner type of name file.
     * @return: None.
    */
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

            player player = new player(name, team, position, goals, assists, goals_ga);
            key = getKey(player);
            put(key, player);           
        }

        return;
    }

    /* 
     * this functions generates a unique key for a player(HashNode) by adding all the ascii values for the player's name.
     * @param: player class of name player.
     * @return: int (unique HashKey for the Node)
    */
    public int getKey(player player){

        int key = 0;

        for(int i = 0; i < player.name.length(); i++){
            key += player.name.charAt(i);
        }

        return key;
    }

    /* 
     * this function generates the index of a HashNode (hashIndex) by diving the key by the HashTable size.
     * @param: Integer type with name key.
     * @param: int
    */
    public int hashIndex(Integer key) { return key % tableSize; }

    /* 
     * returns a boolean value by checking if the HashTable is empty or not.
     * @param: None.
     * @return: Boolean.
    */
    public boolean isEmpty() { return size == 0; }

    /* 
     * this function prints the HashTable with all its data (displays all player info).
     * it displays each HashNode(player's data) with their hash key.
     * @param: None.
     * @return: None.
    */
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

    /* 
     * this function puts adds HashNode into the HashTable. It uses the key of the HashNode to find its HashIndex and
     *  pairs the player data with the HashNode.
     * @param: Integer type with name key & player type with name player
     * @return: None.
    */
    public void put(Integer key, player player){
        
        if(key == null || player == null){
            System.out.println("Key or Node is null");
        }else{
            int index = hashIndex(key);
            HashNode head = table[index];
            while(head != null){                   // checks if key already exist
                if(head.key.equals(key)){
                    head.player = player;
                    return;
                }
                head = head.next;
            }
            size ++;
            head = table[index];
            HashNode newNode = new HashNode(key, player);
            newNode.player = player;
            newNode.next = head;
            table[index] = newNode;
        }
    
        return;
    }

    /* 
     * this functions searches and returns HashNode of key. It recieves a hash key and returns the hash node with the exact key.
     * returns null if HashNode is not found.
     * @param: Integer type of name key.
     * @return: a HashNode.
    */
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

    /* 
     * this function removes the HashNode associated with the passed key.
     * @param: Interger type with name key
     * @return String stating if the node was has been removed or not found.
    */
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
                return head.player.name + " with Key: " + "'" + key + "'" + " has been removed.";
            }
            prev = head;
            head = head.next;

            
        }

        return "\nKey: " + key + " not found.";
    }
    
    public static void main(String[] args) throws FileNotFoundException {

        HashTable myTable = new HashTable(25);                        // initialized capacity to 25

        File file = new File("C:\\Users\\PC\\Desktop\\proj\\myGit\\Hash-tables\\hash_table\\team.txt"); // file path.
        myTable.read_file(file);
        
        // Display HashTable contents
        System.out.print("\n");
        System.out.println(" Number of items in HashTable: " + myTable.size + "\n");
        myTable.printTable();
        System.out.println("--------------------------------------------------");
        System.out.println("HashTable format before a key is removed -- key 1152 to be removed (Anni Keisala -- Index 2) \n");
        myTable.printKeys();
        System.out.println("--------------------------------------------------");

        // Testing HashTable functions, .get() & .remove()
        System.out.println(myTable.get(1152));            // 1600 is the HashKey forKristen Campbell's HashNode.
        System.out.println(" Number of items in HashTable: " + myTable.size + "\n");
        System.out.println(myTable.remove(1152) + "\n");
        System.out.println(" Number of items in HashTable: " + myTable.size + "\n");
        System.out.println("--------------------------------------------------");
        System.out.println("HashTable format after a key is removed -- key 1152 to be removed (Anni Keisala -- Index 2) \n");
        myTable.printKeys();
        System.out.println("--------------------------------------------------");

        return;
    }

    /* 
     * this is a class of name HashNode which contains all the components of my created HashNode
     * HashNode next - points to the next HashNode incase two HashNodes have the same HashIndex. (Collision)
     * player player - a player class which contains all the data of a single player.
     * Integer key - the HashNodes unique key (HashKey)
    */
    class HashNode{

        Integer key;
        player player;
        HashNode next;

        HashNode(int key, player player){
            this.key = key;
            this.player = null;
            this.next = null;
        }

        public String toString(){
            return "-------------------------------------------------- \nPlayer name:" + this.player.name + "  ---  Key: " + this.key + " \nTeam: " + this.player.team + " \nPosition: " + this.player.position + " \nGoals: " + 
            this.player.goals + " \nAsists:  " + this.player.assist + " \nGoals against: " + 
            this.player.goals_ga + "\n--------------------------------------------------\n";
        }
    }

    /* 
     * this is a class of name player which contains all the components of a player. It will be stored in a HashNode
     * with its unique HashKey
    */
    class player{
        String name;
        char team;
        char position;
        int goals;
        int assist;
        int goals_ga;

        player(String name, char team, char position, int goals, int assist, int goals_ga){
            this.name = name;
            this.team = team;
            this.position = position;
            this.goals = goals;
            this.assist = assist;
            this.goals_ga = goals_ga;
        }
    }
}
