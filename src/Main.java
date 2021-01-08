import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class Main {
    public static void main(String[] args) {
        //reads in the file and set the names and sequences in a hahsmap
        HashMap list = new HashMap
                (Objects.requireNonNull(readFile("src\\rosalind_grph (3).txt")));
        //determines the sequences that overlap with more than three nuclotides and stores those in a arraylist
        ArrayList adjencentlist = makeadjencentlist(list);
        //loops through the arraylist and prints the result
        for (Object value:adjencentlist){
            System.out.println(value);
        }

    }

    /**
     * determines if the suffix of 3 nucleotides long of each sequence is the same as the prefix
     * of three nucleotide long of one of the others
     * @param list a hasmap with the sequences as key and the headers as values
     * @return adjencentlist a arraylist with the headers of the matching sequences
     */
    public static ArrayList makeadjencentlist(HashMap list){
        ArrayList adjencentlist =new ArrayList();  //makes a empty arraylist
        //loops through sequences
        for (Object i: list.keySet()){
            String seq1 = (String) i; //casts the object to a string
            // gets the last three letters of this string
            String lastletters = seq1.substring(seq1.length()-3);
            //loops through the sequences again
            for(Object j:list.keySet()){
                String seq2 =(String) j;   //casts the sequences you are going to compare with to a string
                if(!seq1.equals(seq2)){ //checks if it is not the same sequence
                    //gets the three first letters of the sequence you are comparing to
                String firstletters = seq2.substring(0,3);
        //checks if the first letters of the first sequence is the same as the last letters of the second sequence
                if(lastletters.equals(firstletters)){
                    //adds the headers of these sequences to a arraylist
                    adjencentlist.add(list.get(seq1)+" "+list.get(seq2));
                }} } }
        return adjencentlist;
    }
    /**
     * read in the file, and add the information in a hashmap with the sequences as key and the headers as value
     * @param filename name of the file containing the information
     * @return list a hasmap with the sequences as key and the headers as values
     */
    public static HashMap readFile(String filename) {
        String line;
        BufferedReader inFile = null;
        HashMap list= new HashMap(200);
        String header = "";
        StringBuilder seq = new StringBuilder();
        try {
            inFile = new BufferedReader(new FileReader(filename));
            //loops through the file
            while ((line = inFile.readLine()) != null) {
                if(line.startsWith(">")){  //checks if it starts with a >
                    if(seq.length()!=0){             //checks if the seq variable is empty
                        String sequentie = seq.toString();   //turn the stringbuilder into a string
                        list.put(sequentie,header);
                        seq= new StringBuilder();     //empties the seq variable
                    }
                    header= line.replace(">",""); }//removes the > in front of the names
                else if(!line.startsWith(">")){
                    seq.append(line);   //adds the line to the seq variable
                }
            }
            //add the last sequence
            String sequentie = seq.toString();
            list.put(sequentie,header);
            return list;

        }
        catch (IOException |NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage());
            return null;
        }
        finally {
            try {
                inFile.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        e.getMessage());
            }
        }
    }
}
