
import edu.duke.*;
import java.io.*;

public class FindGenes {
    public int findStopIndex(String dna, int index) {
    int start = index
    while(true) {
    int stop1 = dna.indexOf("tag", index + 3);
    if (stop1 == -1){
    System.out.println("No Genes found with codon ending tag");
    break;
    }
    if ((stop1 - start) % 3 == 0) {
    System.out.println(dna.substring(start, stop1+3));
    break;
    }
    }
    while(True) {
    int stop2 = dna.indexOf("tga", index + 3);
    if (stop2 == -1){
    System.out.println("No Genes found with codon ending tga");
    break;
    }
    if ((stop2 - start) % 3 == 0) {
    System.out.println(dna.substring(start, stop2+3));
    break;
    }
    }
    while(True) {
    int stop3 = dna.indexOf("taa", index + 3);
    if (stop3 == -1){
    System.out.println("No Genes found with codon ending tga");
    break;
    }
    if ((stop3 - start) % 3 == 0) {
    System.out.println(dna.substring(start, stop3+3));
    break;
    }
    }
    
    }
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
    
    public void testing() {
        String a = "cccatggggtttaaataataataggagagagagagagagttt";
        String ap = "atggggtttaaataataatag";
        //String a = "atgcctag";
        //String ap = "";
        //String a = "ATGCCCTAG";
        //String ap = "ATGCCCTAG";
        String result = findProtein(a);
        if (ap.equals(result)) {
            System.out.println("success for " + ap + " length " + ap.length());
        }
        else {
            System.out.println("mistake for input: " + a);
            System.out.println("got: " + result);
            System.out.println("not: " + ap);
        }
    }

    public void realTesting() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String s = fr.asString();
            System.out.println("read " + s.length() + " characters");
            String result = findProtein(s);
            System.out.println("found " + result);
        }
    }
    public String stopCodon() {
        String sCodon0 = "AATGCTAGTTTAAATCTGA";
        String sCodon1 = "ataaactatgttttaaatgt";
        String Scodon2 = "acatgataacctaag";
        sCodon0 = sCodon0.toLowerCase();
        sCodon0 = findProtein(sCodon0);
        if  (sCodon0.length() == 0) {
            return "";
           }
        else {
	        return sCodon0.substring(sCodon0.length() - 3);
	       }
	   }
}
