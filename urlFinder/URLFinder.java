/**
 * Prints out all links within the HTML source of a web page.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;

public class URLFinder {
	public StorageResource findURLs(String url) {
		URLResource page = new URLResource(url);
		String source = page.asString();
		StorageResource store = new StorageResource();
		int start = 0;
		while (true) {
			int index = source.indexOf("href=", start);
			if (index == -1) {
				break;
			}
			int firstQuote = index+6; // after href="
			int endQuote = source.indexOf("\"", firstQuote);
			String sub = source.substring(firstQuote, endQuote);
			if (sub.startsWith("http")) {
				store.add(sub);
			}
			start = endQuote + 1;
		}
		return store;
	}
	
	public int SecureFinder(StorageResource data) {
	    int count = 0;
	    for (String item : data.data()){
	        if (item.startsWith("https")){
	            System.out.println(item);
	            count += 1;
	           }
	    }
	    return count;
	}
	
	public int COMFinder(StorageResource data) {
	    int count = 0;
	    for (String item :data.data()){
	        if( item.contains(".com") ){
	            count += 1;
	            System.out.println(item);
	           }
	       }
	    return count;
	   }
	   
	public int COMFinderSlash(StorageResource data) {
	    int count = 0;
	    for (String item :data.data()){
	        if( item.endsWith(".com") ){
	            count += 1;
	            System.out.println(item);
	           }
	        if( item.endsWith(".com/") ){
	            count += 1;
	            System.out.println(item);
	           }
	       }
	    return count;
	   }
	   
	public int pointFinder(StorageResource data) {
	   int pointsindata = 0;
	   for (String item : data.data()) {
	   int pointsinString = 0;
	   int start = 0;
	   while (true) {
	       int loc = item.indexOf(".", start);
	       if ( loc == -1) {
	           break;
	       }
	       if (loc != -1) {
	       pointsinString += 1;
	       start = loc + 1;
	   }
       }
	   pointsindata += pointsinString;
       }
       return pointsindata;
    }


	public void testURL() {
	    StorageResource s0 = findURLs("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html ");
	    //StorageResource s0 = findURLs("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
		//StorageResource s1 = findURLs("https://www.whitehouse.gov");
		//StorageResource s2 = findURLs("http://www.doctorswithoutborders.org");
		System.out.println("URL to organize " + " http://www.dukelearntoprogram.com/course2/data/manylinks.html ");
		for (String link : s0.data()) {
			System.out.println(link);
		}
		System.out.println("Number of links found = " + s0.size());
		System.out.println("Number of secure links found = " + SecureFinder(s0));
		System.out.println("Number of com links found = " + COMFinder(s0));
		System.out.println("Number of com ending links found = " + COMFinderSlash(s0));
		System.out.println("number of dot founds = " + pointFinder(s0));
	}
}
