/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.itopen.imaging;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author roland
 */
public abstract class ImageSource {

    private Map<String, String> properties = new HashMap<>();

    public void setProperty(String name, String value) {
        properties.put(name, value);
    }

    public String getProperty(String name) {
        return getProperty(name, null);
    }

    public String getProperty(String name, String defaultValue) {
        String value = properties.get(name);
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

    public abstract BufferedImage getImage();

    public abstract String getName();

    public abstract Dimension getResolution();

    public abstract String getId();

    public abstract void setEnabled(boolean enable);

}
