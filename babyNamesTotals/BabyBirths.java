/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
    }

    public void testTotalBirths () {
        FileResource fr = new FileResource();
        //FileResource fr = new FileResource("data/yob2014.csv");
        totalBirths(fr);
    }
    
    public int getRankFiles(CSVParser parser, String name, String gender) {
        int rank = 0;
        for (CSVRecord rec : parser) {
        String genderdata = rec.get(1);
        if (genderdata.equals(gender)) {
            rank += 1;
        if ( rec.get(0).equals(name) ) {
            return rank;
        }
        }
        }
        return -1;
       }
       
    public int getRank(int year, String name, String gender) {
        int rank = 0;
        String yearfilename = "yob" + Integer.toString(year) + ".csv";
        FileResource fr = new FileResource("data/" + yearfilename);
        for (CSVRecord rec : fr.getCSVParser(false)) {
        String genderdata = rec.get(1);
        if (genderdata.equals(gender)) {
            rank += 1;
        if ( rec.get(0).equals(name) ) {
            return rank;
        }
        }
        }
        return -1;
       }
       
    public void testgetRank() {
        System.out.println(getRank(1971, "Frank", "M"));
    }
    
    public void ReturnRank(int ranks, int newYear, String gender){
    int rankbornyear = ranks;
    String newyearfilename = "yob" + Integer.toString(newYear) + ".csv";
    FileResource fr = new FileResource("data/" + newyearfilename);
    int rank = 0;
    String newname = "";
    for (CSVRecord rec : fr.getCSVParser(false)) {
    String genderdata = rec.get(1);
    if (genderdata.equals(gender)) {
    rank += 1;
    if (rank == rankbornyear) {
    newname = rec.get(0);
    break;
    }
    }
    }
    if (gender.equals("F") ){
    System.out.println(  newname + " if she was born in " + newYear);
    }
    if (gender.equals("M") ){
    System.out.println(  newname + " if he was born in " + newYear);
    }
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
    int rankbornyear = getRank(year, name, gender);
    //String newyearfilename = "yob" + Integer.toString(newYear) + "short.csv";
    String newyearfilename = "yob" + Integer.toString(newYear) + ".csv";
    FileResource fr = new FileResource("data/" + newyearfilename);
    int rank = 0;
    String newname = "";
    for (CSVRecord rec : fr.getCSVParser(false)) {
    String genderdata = rec.get(1);
    if (genderdata.equals(gender)) {
    rank += 1;
    if (rank == rankbornyear) {
    newname = rec.get(0);
    break;
    }
    }
    }
    if (gender.equals("F") ){
    System.out.println( name + " born in " + year + " would be " + newname + " if she was born in " + newYear);
    }
    if (gender.equals("M") ){
    System.out.println( name + " born in " + year + " would be " + newname + " if he was born in " + newYear);
    }
    }
    
    public void testwhatIsNameInYear(){
    whatIsNameInYear("Susan", 1972, 2014, "F");
    whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public double getAverageRank (String name, String gender) {
    DirectoryResource dr = new DirectoryResource();
    int avgRank = 0;
    int count = 0;
    int currentRank = 0;
    for (File fr : dr.selectedFiles() ) {
    FileResource f = new FileResource(fr);
    currentRank = getRankFiles(f.getCSVParser(false), name, gender);
    if (currentRank != -1) {
    avgRank += currentRank;
    }
    count += 1;
    }
    if (avgRank == 0){
        return -1;
    }
    return (float)avgRank / count;
    }
    
    public void testgetAverageRank(){
    System.out.println(getAverageRank("Susan", "F"));
    System.out.println(getAverageRank("Robert", "M"));
    }
    
    public int getTotalBirthsRankedHigher( int year, String name, String gender ) {
        int rank = 0;
        int totalbornbefore = 0;
        String yearfilename = "yob" + Integer.toString(year) + ".csv";
        FileResource fr = new FileResource("data/" + yearfilename);
        for (CSVRecord rec : fr.getCSVParser(false)) {
        String genderdata = rec.get(1);
        if (genderdata.equals(gender)) {
            rank += 1;
        if ( rec.get(0).equals(name) ) {
            return totalbornbefore;
        }
        totalbornbefore += Integer.parseInt(rec.get(2));
        }
        }
        return -1;
       }
    
    public void testgetTotalBirthsRankedHigher() {
    System.out.println(getTotalBirthsRankedHigher(1990, "Emily", "F"));
    System.out.println(getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }
    
    public void HighestRank(String name, String gender) {
    DirectoryResource dr = new DirectoryResource();
    int Highrank = 99999;
    String year = "";
    for (File fr : dr.selectedFiles() ) {
    FileResource f = new FileResource(fr);
    int currentRank = getRankFiles(f.getCSVParser(false), name, gender);
    if ((currentRank != -1) && (Highrank > currentRank)) {
    Highrank = currentRank;
    year = fr.getName();
    }
    }
    System.out.println(Highrank + "   " + year);
    }
}

