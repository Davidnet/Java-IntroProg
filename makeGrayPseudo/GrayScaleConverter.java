/**
 * Create a gray scale version of an image by setting all color components of each pixel to the same value.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
    //I started with the image I wanted (inImage)
    public ImageResource makeGray(ImageResource inImage) {
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        //for each pixel in outImage
        for( Pixel pixel: outImage.pixels()){
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            //compute inPixel's red + inPixel's blue + inPixel's green
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen() )/3;
            //divide that sum by 3 (call it average)
            pixel.setRed(average);
            pixel.setBlue(average);
            pixel.setGreen(average);
        }      
        //outImage is your answer
        return outImage;
    }

    public void testGray() {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }
}
