/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.itopen.gps;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author roland
 */
public class GPGSV implements SentenceParser {

    /* $GPGSV,2,1,08,01,40,083,46,02,17,308,41,12,07,344,39,14,22,228,45*75

Where:
      GSV          Satellites in view
      2            Number of sentences for full data
      1            sentence 1 of 2
      08           Number of satellites in view

      01           Satellite PRN number
      40           Elevation, degrees
      083          Azimuth, degrees
      46           SNR - higher is better
           for up to 4 satellites per sentence
      *75          the checksum data, always begins with *
     */
// Unknown length TODO
    byte[] satcount = new byte[2];

    public GPGSV() {
        satcount[0] = 0;
        satcount[1] = 0;
    }

    public boolean parse(String[] tokens, GPSPosition position) {
        if (tokens[2].isEmpty()) {
            return false;
        }
        if (tokens[2] == "1") {
            position.fix3d = GPSPosition.FIX.FIX_NO;
        }
        if (tokens[2] == "2") {
            position.fix3d = GPSPosition.FIX.FIX_2D;
        }
        if (tokens[2] == "3") {
            position.fix3d = GPSPosition.FIX.FIX_3D;
        }

        List<String> sat = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            if (!tokens[3 + i].isEmpty()) {
                sat.add(tokens[3 + i]);
            }
        }
        position.usedSatellites = sat;

        return true;
    }

}
