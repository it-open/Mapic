/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.itopen.gps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roland
 */
public class GPS {

    private final NMEA nmea = new NMEA();
    private Thread readtread;

    public GPS(final String inputFile) {
        nmea.addPositionUpdatedListener(new PositionUpdated() {
            @Override
            public void updatePosition(GPSPosition position) {

            }
        });

        readtread = new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader(inputFile));
                    while (!readtread.isInterrupted()) {
                        String line = br.readLine();
                        nmea.parse(line);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(GPS.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        br.close();
                    } catch (IOException ex) {
                        Logger.getLogger(GPS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }, inputFile);
        readtread.start();

    }

}
