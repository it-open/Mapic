/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.itopen.imaging;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.gphoto2.Camera;
import org.gphoto2.CameraList;
import org.gphoto2.CameraUtils;

/**
 *
 * @author roland
 */
public class GPhoto2Source extends ImageSource {

    public static void populate() {
        if (Camera.getLibraryVersion() == null) {
            return;
        }
        if (Camera.getLibraryVersion().isEmpty()) {
            return;
        }
        System.out.println("Using GPhoto2: " + Camera.getLibraryVersion());
        CameraList cl = new CameraList();
        for (int i = 0; i < cl.getCount(); i++) {

            Camera c = new Camera();
            c.setPortInfo(cl.getPortInfo(i));
            ImagingCapture.addAvaliableImageSource(new GPhoto2Source(c, cl.getModel(i)));
        }

        CameraUtils.closeQuietly(cl);
    }

    private Camera camera;
    private String model;
    private Dimension dimension;

    public GPhoto2Source(Camera camera, String model) {
        this.camera = camera;
        this.model = model;

        camera.initialize();
        try {
            File tmp = File.createTempFile("img", ".jpg");
            camera.captureImage().save(tmp.getAbsolutePath());
            Image img = ImageIO.read(tmp);
            dimension = new Dimension(img.getWidth(null), img.getHeight(null));
            tmp.delete();
        } catch (IOException ex) {
            Logger.getLogger(GPhoto2Source.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Camera getCamera() {
        return camera;
    }

    @Override
    public BufferedImage getImage() {
        try {
            File tmp = File.createTempFile("img", ".jpg");
            camera.captureImage().save(tmp.getAbsolutePath() + File.separator + tmp.getName());
            BufferedImage img = ImageIO.read(tmp);
            tmp.delete();
            return img;
        } catch (IOException ex) {
            Logger.getLogger(GPhoto2Source.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getName() {
        return model;
    }

    @Override
    public void setEnabled(boolean enable) {

        if (enable) {
            if (!camera.isInitialized()) {
                camera.initialize();
            }
        } else {
            if (camera.isInitialized()) {
                try {
                    camera.deinitialize();
                } catch (IOException ex) {
                    Logger.getLogger(GPhoto2Source.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Dimension getResolution() {
        return dimension;
    }

}
