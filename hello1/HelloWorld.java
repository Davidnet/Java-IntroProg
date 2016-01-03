import edu.duke.*;

public class HelloWorld {
	public void runHello () {
		//FileResource res = new FileResource("hello_unicode.txt");
		URLResource rs = new URLResource("http://www.dukelearntoprogram.com/java/hello_unicode.txt");
		for (String line : rs.lines()) {
			System.out.println(line);
		}
	}
}
