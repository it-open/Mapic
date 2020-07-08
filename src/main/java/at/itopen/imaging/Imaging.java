/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.itopen.imaging;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author roland
 */
public class Imaging {

    public static ImageSource front, back, left, right;

    public static void init() {
        WebCamSource.populate();
        GPhoto2Source.populate();
    }

    private static List<ImageSource> availableSources = new ArrayList<>();

    public static List<ImageSource> getAvailableSources() {
        return availableSources;
    }

    public static void addAvaliableImageSource(ImageSource imageSource) {
        availableSources.add(imageSource);
    }

}
