import edu.duke.*;
/**
 * Write a description of youtubeSearch here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class youtubeSearch {
    public void youtubeLinks(){
    URLResource wl = new URLResource("http://dukelearntoprogram.com/course2/data/manylinks.html");
    for (String s : wl.words()){
        if (s.toLowerCase().contains("youtube.com")){
        System.out.print(s + "\n");
        }
    }
    }
}
