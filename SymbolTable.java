/*
 * Colin Fitzpatrick
 * 
 * Professor Gendreau
 * 
 * Homework 2
 * 
 * Symbol Table
 */
import java.util.*;
public class SymbolTable {
  private class Node {
    //nodes used to build the linked lists used in a
    //separate chaining has table
    private String key;
    private Object data;
    private Node next;
    private Node(String k, Object d, Node n) {
    key = k;
    data = d;
    next = n;
      }
    }
   private Node table[];
   private int tableSize;
   
   private final int primes[] = {7, 23, 59, 131, 271, 563, 1171,
       2083, 4441, 8839, 16319, 32467,
       65701, 131413, 263983, 528991};
       private int primeIndex;
       private int filled;

       private int nextPrime(int p) {
       while (primes[primeIndex] <= p)
       primeIndex++;
       return primes[primeIndex];
       }
      public SymbolTable(int s) {
       primeIndex = 0;
       tableSize = nextPrime(s);
       table = new Node[tableSize];
       filled = 0; 
      }
      
      private void resize() {
        //"double" the table size and reinsert the values stored in the
        //current table. the table size should remain prime
        int oldTableSize = tableSize;
        tableSize = nextPrime(primeIndex);
        Node[] tempN = table;
        table = new Node[tableSize];
        
        for (int i = 0; i < oldTableSize; i++) {
          Node temp1 = tempN[i];
          while(tempN[i].next != null) {
            table[i] = temp1;
            }
          }
        }
      
      private int hash(String k) {
       //return the hash function value for k
        return Math.abs(k.hashCode() % tableSize);
        }

       public boolean insert(String k, Object d) {
        //if k is not in the table insert a new item (k,d) and return true
        //otherwise return false
        //if after inserting a new item the table has more than 2*tablesize 
        //items in it then resize the table
         int numElements = 0;
         Node tempN = table[hash(k)];
           while (tempN != null) {
             if (k.equals(tempN.key)) {
              return false;
             }
             numElements++;
             tempN = tempN.next;
           }
         
        table[hash(k)] =  new Node(k, d, table[hash(k)]); 
         if (tableSize * 2 < numElements) {
           resize();
         }
         return true;
       }
        
       public Object find(String k) {
       //return d if (k, d) is in the table otherwise return null
         Node tempN = table[hash(k)];
           while (tempN != null) {
             if (k.equals(tempN.key)) {
              return tempN.data;
             }
             tempN = tempN.next;
           }
         
        return null;
       }
       
       public class STIterator implements Iterator<String> {
         //An iterator that iterates through the keys in the table
         //each call to next returns the next key in the table
         private Node current = null;
         private  int currentLoc;
         
         public STIterator() {
         current = table[0];
         currentLoc = 0;
         }
         
         public boolean hasNext() {
          while(currentLoc < tableSize - 1 && current == null) {
            currentLoc++;
            current = table[currentLoc];
          }
          if (current != null) {
           return true; 
          }
           return false;
         }
         
         public String next() {
          if(hasNext()) {
            String currentString = current.key;
            current = current.next;
            return currentString;
          }
          else {
          return null;
          }
          
         } 
         public void remove() {
           //optional method not implemented
           }
       }
       
       
       public boolean remove(String k) {
       //if k is in the table remove the item (k,d) and return true
       //otherwise return false
         Node tempN = table[hash(k)];
           while (tempN != null) {
             if (k.equals(tempN.key)) {
               tempN.key = null;
               return true;
              }
             tempN = tempN.next;
           }
        
        return false;
         }
       
         public Iterator<String> iterator() {
         //return a new STiterator object
          STIterator iterator = new STIterator();
          return iterator;
          }
}
         

