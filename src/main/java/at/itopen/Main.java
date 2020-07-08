
import at.itopen.imaging.ImageSource;
import at.itopen.imaging.Imaging;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


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
        Imaging.init();
        int i = 0;
        for (ImageSource imageSource : Imaging.getAvailableSources()) {
            System.out.println(imageSource.getName() + " - " + imageSource.getId() + " : " + imageSource.getResolution().width + "x" + imageSource.getResolution().height);
            imageSource.setEnabled(true);
            ImageIO.write(imageSource.getImage(), "jpg", new File("image" + (i++) + ".jpg"));
            imageSource.setEnabled(false);
        }
    }

}
