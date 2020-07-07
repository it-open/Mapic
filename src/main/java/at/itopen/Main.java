
import at.itopen.gps.NMEA;
import at.itopen.webcam.ImageSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

    public static NMEA nmea = new NMEA();

    /**
     * Main method takes one command-line argument, the name of the file to
     * read.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) throws IOException {

        ImageSource is = new ImageSource();
        ImageIO.write(is.getImage(), "jpg", new File("test.jpg"));

        File f = new File("/dev/ttyACM0");
        BufferedReader br = new BufferedReader(new FileReader(f));
        while (true) {
            String line = br.readLine();
            System.out.println("Line:" + line);
            nmea.parse(line);
        }
    }

}
