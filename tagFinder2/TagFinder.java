/**
 * Finds a protein within a strand of DNA represented as a string of c,g,t,a letters.
 * A protein is a part of the DNA strand marked by start and stop codons (DNA triples)
 * that is a multiple of 3 letters long.
 *
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class TagFinder {
    public String findProtein(String dna) {
        int start = dna.indexOf("atg");
        if (start == -1) {
            return "";
        }
        int stop = dna.indexOf("tag", start+3);
        if ((stop - start) % 3 == 0) {
            return dna.substring(start, stop+3);
        }
        stop = dna.indexOf("tga", start+3);
        if ((stop - start) % 3 ==0) {
            return dna.substring(start, stop+3);
          }
        stop =  dna.indexOf("taa", start+3);
        if ((stop - start) % 3 == 0) {
            return dna.substring(start, stop+3);
        }
        else {
            return "";
        }
    }

       
    /**
     * Find a valid stop codon in dna that occurs after index.
     * If no valid stop codon found, return dna.length()
     * @param dna is String being searched
     * @param index is index where search starts
     * @return index of beginnning of a valid stop codon,
     * or dna.length() if no valid codon
     */
    public int findStopIndex(String dna, int index){
    int stop1 = dna.indexOf("tag", index+3);
    if (stop1 == -1 || (stop1 - index) % 3 != 0){
        stop1 = dna.length();
    }
    int stop2 = dna.indexOf("tga", index+3);
    if (stop2 == -1 || (stop2 - index) % 3 != 0){
        stop2 = dna.length();
    }
    int stop3 = dna.indexOf("taa", index);
    if (stop3 == -1 || (stop3 - index+3) % 3 != 0){
        stop3 = dna.length();
    }
    int result = Math.min(stop1, Math.min(stop2,stop3));
    if (result == dna.length()){
    return -1;
    }
    return result;
    }
    
    /**
     * Print all the genes it finds in DNA.
     * 
     */
    public void printAll(String dna) {
    int start = 0;
    while(true){
      int loc = dna.indexOf("atg",start);
      if (loc == -1) {
       break;
      }
      int gene = findStopIndex(dna, loc);
      if ( gene != -1) {
       System.out.println("Gene found is: " + dna.substring(loc,gene+3));
       start = gene + 3;
      }
      if (gene == -1) {
          start = start + 3;
        }
    }
    }
    
    public StorageResource storeAll(String dna) {
    StorageResource bank = new StorageResource();
    int start = 0;
    while(true){
      int loc = dna.indexOf("atg",start);
      if (loc == -1) {
       break;
      }
      int gene = findStopIndex(dna, loc);
      if ( gene != -1) {
       bank.add(dna.substring(loc,gene+3));
       start = gene + 3;
      }
      if (gene == -1) {
          start = start + 3;
        }
    }
    return bank;
    }
        
    /**
     * testerFinder
     */
    public void testFinder() {
    String dna1 = "ATGAAATGAAAA";
    String dna2 = "ccatgccctaataaatgtctgtaatgtaga";
    String dna3 = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA".toLowerCase();
    System.out.println("String to analyze is : " + dna1);
    printAll(dna1.toLowerCase());
    System.out.println("String to analyze is : " + dna2);
    printAll(dna2.toLowerCase());
    System.out.println("String to analyze is : " + dna3);
    printAll(dna3);
    //System.out.println(cgRatio("atgccatag"));
    }
    
    public void testStorageFinder(){
    StorageResource data = new StorageResource();
    FileResource fr = new FileResource();
    for (String s : fr.lines()){
        s = s.toLowerCase();
        data = storeAll(s);
    }
    System.out.println("There are " + data.size());
    prIntGenes(data);
    }
    
    public float cgRatio(String dna){
    int start = 0;
    int countcg = 0;
    while(true){
       int loc = dna.indexOf("c",start);
       if (loc == -1) {
           break;
        }
       start = loc + 1;
       countcg = countcg + 1;
    }
    start = 0;
    while(true){
       int loc = dna.indexOf("g",start);
       if (loc == -1) {
           break;
        }
       start = loc + 1;
       countcg = countcg + 1;
    }
    float z =  countcg;
    float size = dna.length();
    return z / size;
}

   public void prIntGenes(StorageResource sr){
       int longstrings = 0;
       int cdhigh = 0;
       for (String item : sr.data()) {
       if (item.length() > 60) {
       System.out.println("gen is " + item);
       longstrings += 1;
       }
       }
       System.out.println("The number of long genes is " + longstrings);
       for (String item : sr.data()) {
       if (cgRatio(item) > 0.35) {
       System.out.println("gen is " + item);
       cdhigh += 1;
       }
       }
       System.out.println("The number of cdhigh  is " + cdhigh);
       int ctgcount = 0;
       for (String item : sr.data()) {
       ctgcount += countCTG(item);     
       }
       System.out.println("The number of CTG apperances is " + ctgcount);
       System.out.println("the largest size is : " + largestsize(sr));
   
    }
    
   public int countCTG (String dna) {
       int count= 0;
       int start= 0;
       while(true) {
           int loc  = dna.indexOf("ctg", start);
           if (loc == -1){
               break;
            }
           if (loc != -1){
            count = count + 1;
            start = loc + 3;
            }
        }
        return count;
    }
    
   public int largestsize (StorageResource bank) {
    int longestsize = 0;
    while(true){
    for (String item: bank.data()) {
        if ( item.length() > longestsize ) {
            longestsize = item.length();
        }
    }
    return longestsize;
    }
}
}