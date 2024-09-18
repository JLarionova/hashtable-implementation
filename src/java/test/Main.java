package src.java.test;

import src.java.main.HashTable;

public class Main {
    public static void main(String[] args) {

        HashTable hashTable = HashTable.getInstance();


        System.out.println("Inserting key-value pairs:");
        hashTable.insert(1, "Value 1");
        hashTable.insert(2, "Value 2");
        hashTable.insert(3, "Value 3");
        hashTable.insert(4, "Value 4");


        System.out.println("Size after insertions: " + hashTable.size()); // Expected: 4


        System.out.println("Search for key 1: " + hashTable.search(1)); // Expected: "Value 1"
        System.out.println("Search for key 2: " + hashTable.search(2)); // Expected: "Value 2"
        System.out.println("Search for key 5 (non-existent): " + hashTable.search(5)); // Expected: null
        System.out.println();


        System.out.println("Updating key 1's value to 'Updated Value 1'");
        hashTable.insert(1, "Updated Value 1");
        System.out.println("Search for key 1 after update: " + hashTable.search(1)); // Expected: "Updated Value 1"
        System.out.println();

        System.out.println("Removing key 2");
        hashTable.remove(2);
        System.out.println("Search for key 2 after removal: " + hashTable.search(2)); // Expected: null
        System.out.println();

        System.out.println("Size after removal: " + hashTable.size()); // Expected: 3


        System.out.println("Keys in the table:");
        int[] keys = hashTable.keys();
        for (int key : keys) {
            if (key != 0) {
                System.out.println(key);
            }
        }


        System.out.println("Removing all elements.");
        hashTable.remove(1);
        hashTable.remove(3);
        hashTable.remove(4);


        System.out.println("Size after removing all elements: " + hashTable.size()); // Expected: 0


        System.out.println("Search for key 1 after all removals: " + hashTable.search(1)); // Expected: null
    }
}
