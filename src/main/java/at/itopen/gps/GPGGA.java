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
public class GPGGA extends SentenceParser {

    public GPGGA(NMEA nmea) {
        super(nmea);
    }

    public boolean parse(String[] tokens, GPSPosition position) {
        if (tokens[1].isEmpty()) {
            return false;
        }
        position.time = Float.parseFloat(tokens[1]);
        position.lat = Latitude2Decimal(tokens[2], tokens[3]);
        position.lon = Longitude2Decimal(tokens[4], tokens[5]);
        position.quality = Integer.parseInt(tokens[6]);
        position.altitude = Float.parseFloat(tokens[9]);
        getNmea().callPositionUpdated(position);
        return true;
    }

}
