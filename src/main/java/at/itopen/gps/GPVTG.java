/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.itopen.gps;

/**
 *
 * @author roland
 */
public class GPVTG extends SentenceParser {

    public GPVTG(NMEA nmea) {
        super(nmea);
    }

    public boolean parse(String[] tokens, GPSPosition position) {
        if (tokens[3].isEmpty()) {
            return false;
        }
        position.dir = Float.parseFloat(tokens[3]);
        return true;
    }

}
