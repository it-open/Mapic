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
public class GPGSA extends SentenceParser {

    public GPGSA(NMEA nmea) {
        super(nmea);
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
