/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.itopen.webcam;

import com.github.sarxos.webcam.Webcam;
import java.awt.image.BufferedImage;

/**
 *
 * @author roland
 */
public class ImageSource {

    Webcam webcam;

    public ImageSource() {
        webcam = Webcam.getDefault();
        System.out.println(webcam.getName());
        webcam.open();
    }

    public BufferedImage getImage() {
        return webcam.getImage();
    }

}
