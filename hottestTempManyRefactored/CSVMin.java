
/**
 * Write a description of CSVMin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMin {
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord lowestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
           lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar, "TemperatureF");
        }
        //The largestSoFar is the answer
        return lowestSoFar;
    }

    public CSVRecord getLowestOfTwo(CSVRecord currentRow, CSVRecord lowestSoFar, String attribute) {
        if (lowestSoFar == null) {
            lowestSoFar = currentRow;
        }
        else {
            double currentTemp = Double.parseDouble(currentRow.get(attribute));
            double lowestTemp = Double.parseDouble(lowestSoFar.get(attribute));
            if ( currentTemp != -9999 && currentTemp < lowestTemp ) {
            lowestSoFar = currentRow;
            }
        }
        return lowestSoFar;   
    }
    
    public void testColdestHourinFile() {
    FileResource fr = new FileResource();
    CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
    //System.out.println("lowest temperature was " + lowest.get("TemperatureF") + " at " + lowest.get("TimeEST"));
    System.out.println("lowest temperature was " + lowest.get("TemperatureF") + " at " + lowest.get("DateUTC"));
    }
    
    public String fileWithColdestTemperature() {
        CSVRecord lowestSoFar = null;
        String filepath = "";
        FileResource flow = null;
        DirectoryResource files = new DirectoryResource();
        for (File f : files.selectedFiles() ) {
            FileResource fr = new FileResource(f);
            CSVRecord flag = lowestSoFar;
            CSVRecord lowestcurrentday = coldestHourInFile(fr.getCSVParser());
            lowestSoFar = getLowestOfTwo( lowestcurrentday, lowestSoFar, "TemperatureF" );
            if (flag != null) {
                if (! flag.equals(lowestSoFar) ) {
                filepath = f.getName();
                flow = fr;
            }
        }
    }
    //System.out.println("lowest temperature was " + lowestSoFar.get("TemperatureF") + " at " + lowestSoFar.get("TimeEST") + " on file " + filepath);
    System.out.println("lowest temperature was " + lowestSoFar.get("TemperatureF") + " at " + lowestSoFar.get("DateUTC") + " on file " + filepath);
    System.out.println("All the Temperatures on the coldest day were: ");
    dayInfo(flow.getCSVParser());
    return filepath;
    }
    
    public void dayInfo( CSVParser parser){
    for (CSVRecord record : parser) {
        String DateUTC = record.get("DateUTC");
        String temperature = record.get("TemperatureF");
        System.out.println(DateUTC + ": " + temperature);
    }
    }
    
    public CSVRecord lowestHumidityInFile( CSVParser parser) {
    //start with largestSoFar as nothing
        CSVRecord lowestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
           String humidities = currentRow.get("Humidity");
           if (!humidities.contains("N/A")){
               lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar, "Humidity" );
            }
        }
        //The largestSoFar is the answer
        System.out.println("Lowest Humidity was " + lowestSoFar.get("Humidity") + " at " + lowestSoFar.get("DateUTC"));
        return lowestSoFar;
    }
    
    public void testLowestHumidityInFile() {
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    CSVRecord csv = lowestHumidityInFile(parser);
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestSoFar = null;
        String filepath = "";
        FileResource flow = null;
        DirectoryResource files = new DirectoryResource();
        for (File f : files.selectedFiles() ) {
            FileResource fr = new FileResource(f);
            CSVRecord flag = lowestSoFar;
            CSVRecord lowestcurrentday = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getLowestOfTwo( lowestcurrentday, lowestSoFar, "Humidity" );
            if (flag != null) {
                if (! flag.equals(lowestSoFar) ) {
                filepath = f.getName();
                flow = fr;
            }
        }
    }
    System.out.println("lowest Humidity was " + lowestSoFar.get("Humidity") + " at " + lowestSoFar.get("DateUTC") + " on file " + filepath);
    return lowestSoFar;
    }
    
    public void testLowestHumidityInManyFiles(){
    CSVRecord lowest = lowestHumidityInManyFiles();
    }
    
    public double averageTemperatureInFile(CSVParser parser){
     int items = 0;
     double temperatureavgsum = 0;
     for (CSVRecord currentRow : parser) {
     double temperature = Double.parseDouble(currentRow.get("TemperatureF"));
     items += 1;
     temperatureavgsum += temperature;
     }
     return temperatureavgsum / items;
    }
    
    public void testAverageTemperatureInFile(){
    FileResource fr = new FileResource();
    double avg = averageTemperatureInFile(fr.getCSVParser());
    System.out.println("Average temperature was " + avg);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
    int items = 0;
    double temperatureavgsum = 0;
    for (CSVRecord currentRow : parser) {
    double temperature = Double.parseDouble(currentRow.get("TemperatureF"));
    int humidity = Integer.parseInt(currentRow.get("Humidity"));
    if (humidity > value){
    items += 1;
    temperatureavgsum += temperature;
    }
    }
    if (items == 0){
    System.out.println("No temperatures with that humidity");
    return 0.0;
    }
    double result = temperatureavgsum / items;
    if (items != 0){
    System.out.println("Average Temp when high Humidity is " + value + " is " + result);
    }
   return temperatureavgsum / items;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    double avg = averageTemperatureWithHighHumidityInFile(parser, 80);
    }
}
