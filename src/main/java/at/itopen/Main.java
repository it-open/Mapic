
import at.itopen.imaging.ImageSource;
import at.itopen.imaging.ImagingCapture;
import java.io.IOException;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author roland
 */
public class Main {

    /**
     * Main method takes one command-line argument, the name of the file to
     * read.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Start");
        ImagingCapture.init();

        for (ImageSource imageSource : ImagingCapture.getAvailableSources()) {
            System.out.println(imageSource.getName() + " : " + imageSource.getResolution().width + "x" + imageSource.getResolution().height);
        }
    }

}
