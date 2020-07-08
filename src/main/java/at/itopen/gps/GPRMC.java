/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.itopen.gps;

import static at.itopen.gps.NMEA.Latitude2Decimal;
import static at.itopen.gps.NMEA.Longitude2Decimal;

/**
 *
 * @author roland
 */
public class GPRMC extends SentenceParser {

    public GPRMC(NMEA nmea) {
        super(nmea);
    }

    public boolean parse(String[] tokens, GPSPosition position) {
        if (tokens[1].isEmpty()) {
            return false;
        }
        position.time = Float.parseFloat(tokens[1]);
        position.lat = Latitude2Decimal(tokens[3], tokens[4]);
        position.lon = Longitude2Decimal(tokens[5], tokens[6]);
        position.velocity = Float.parseFloat(tokens[7]);
        position.dir = Float.parseFloat(tokens[8]);
        getNmea().callPositionUpdated(position);
        return true;
    }

}
