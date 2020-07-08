/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.itopen.imaging;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamDiscoveryEvent;
import com.github.sarxos.webcam.WebcamDiscoveryListener;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

/**
 *
 * @author roland
 */
public class WebCamSource extends ImageSource {

    public static void populate() {
        Webcam.addDiscoveryListener(new WebcamDiscoveryListener() {
            @Override
            public void webcamFound(WebcamDiscoveryEvent webcamDiscoveryEvent) {
                Webcam webcam = webcamDiscoveryEvent.getWebcam();
                WebCamSource webCamSource = new WebCamSource(webcam);
                Imaging.addAvaliableImageSource(webCamSource);

            }

            @Override
            public void webcamGone(WebcamDiscoveryEvent webcamDiscoveryEvent) {

            }
        });
        Webcam.getWebcams();
    }

    private Webcam webcam;

    public WebCamSource(Webcam webcam) {
        this.webcam = webcam;

        Dimension max = null;
        for (Dimension d : webcam.getDevice().getResolutions()) {
            if (max == null) {
                max = d;
            }
            if (d.width > max.width) {
                max = d;
            }
        }
        if (max != null) {
            webcam.getDevice().setResolution(max);
        }
    }

    public Webcam getWebcam() {
        return webcam;
    }

    @Override
    public BufferedImage getImage() {
        return webcam.getImage();
    }

    @Override
    public String getName() {
        return webcam.getName();
    }

    @Override
    public String getId() {
        return webcam.getDevice().getName();
    }

    @Override
    public void setEnabled(boolean enable) {

        if (enable) {
            webcam.open();
        } else {
            webcam.close();
        }
    }

    @Override
    public Dimension getResolution() {
        return webcam.getDevice().getResolution();
    }

}
