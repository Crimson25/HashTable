/*
 * Colin Fitzpatrick
 * 
 * Professor Gendreau
 * 
 * Homework 2
 * 
 * Symbol Table 
 */
import java.io.*;
import java.util.*; 
public class H2 {
 
   private SymbolTable words;

   public H2(String args[]) throws IOException {
   readWords(args[0]);
   findWords(args[1]);
   removedWords(args[2]);
   printWords();
   }
   
   private void readWords(String infile) throws IOException {
     BufferedReader in = new BufferedReader(new FileReader(infile));
     words = new SymbolTable(100);
     String line = in.readLine();
     while (line != null) {
     Scanner s = new Scanner(line);
     while (s.hasNext()) {
     words.insert(s.next(), "Found");
     }
     line = in.readLine();
     }
   }
   
   private void findWords(String searchfile) throws IOException {
     BufferedReader in = new BufferedReader(new FileReader(searchfile));
     String line = in.readLine();
     while (line != null) {
     System.out.println(line + ": "+ words.find(line));
     line = in.readLine();
     }
   }
   
   private void removedWords(String removefile) throws IOException {
     BufferedReader in = new BufferedReader(new FileReader(removefile));
     String line = in.readLine();
     while (line != null) {
     words.remove(line);
     line = in.readLine();
       }
     }
   
   private void printWords() throws IOException {
     Iterator<String> iter = words.iterator();
     while (iter.hasNext()) {
     System.out.println(iter.next());
       }
     }
   
   public static void main(String args[]) throws IOException {
     if (args.length != 3)
     System.out.println("You must provide 3 command line arguments");
     else
     new H2(args);
     }
  }

