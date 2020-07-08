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
public class GPGSV extends SentenceParser {

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

    public GPGSV(NMEA nmea) {
        super(nmea);
        satcount[0] = 0;
        satcount[1] = 0;
    }

    public boolean parse(String[] tokens, GPSPosition position) {
        if (tokens[1].isEmpty()) {
            return false;
        }
        int sections = Integer.parseInt(tokens[1]);
        if (satcount.length != sections) {
            satcount = new byte[sections];
            for (int i = 0; i < satcount.length; i++) {
                satcount[i] = 0;
            }
        }
        int section = Integer.parseInt(tokens[2]);
        byte sats = (byte) Integer.parseInt(tokens[3]);
        satcount[section] = sats;

        int anz = 0;
        for (int i = 0; i < satcount.length; i++) {
            anz += satcount[i];
        }
        position.seenSatellites = anz;

        return true;
    }

}
