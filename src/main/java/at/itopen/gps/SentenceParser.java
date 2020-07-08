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
public abstract class SentenceParser {

    private NMEA nmea;

    public SentenceParser(NMEA nmea) {
        this.nmea = nmea;
    }

    public NMEA getNmea() {
        return nmea;
    }

    public abstract boolean parse(String[] tokens, GPSPosition position);

}
