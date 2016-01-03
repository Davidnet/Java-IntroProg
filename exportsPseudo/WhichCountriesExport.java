/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
           String export = record.get("Exports");
            //Check if it contains exportOfInterest
           if (export.contains(exportOfInterest)){
                //If so, write down the "Country" from that row
                System.out.println(record.get("Country"));
            }
        }
    }

    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        countryInfo(parser, "Nauru");
        parser = fr.getCSVParser();
        ListExportersTwoProducts(parser, "cotton", "flowers");
        parser = fr.getCSVParser();
        System.out.println(numberofExporters(parser, "cocoa"));
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
    
    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
    
   public void countryInfo( CSVParser parser, String country){
   boolean flag = true;
   for (CSVRecord record : parser) {
   String countries = record.get("Country");
   String exports = record.get("Exports");
   String value = record.get("Value (dollars)");
   if (countries.contains(country)) {
       System.out.println(record.get("Country") + " : " + exports + " : " + value);
       flag = false;
   }
   }
   if (flag) {
     System.out.println("NOT FOUND");
   }
  }
  
  public void ListExportersTwoProducts( CSVParser parser, String exportitem1, String exportitem2) {
    for (CSVRecord record : parser) {
    String exports = record.get("Exports");
    if ((exports.contains(exportitem1)) && (exports.contains(exportitem2))) {
     System.out.println(record.get("Country"));
    }
   }
  }
  
  public int numberofExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        int exporters = 0;
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
           String export = record.get("Exports");
            //Check if it contains exportOfInterest
           if (export.contains(exportOfInterest)){
                exporters += 1;
            }
        }
        return exporters;
    }
  
  public void bigExporters(CSVParser parser, String amount) {
    int comparison = amount.length();
    for (CSVRecord record : parser) {
        String value = record.get("Value (dollars)");
        if ( value.length() > comparison) {
        System.out.println(record.get("Country") + " " + value);
        }
    }
  }
}

