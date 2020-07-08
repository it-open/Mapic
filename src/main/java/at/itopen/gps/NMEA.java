/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.itopen.gps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author roland
 */
public class NMEA {

    // utils
    static float Latitude2Decimal(String lat, String NS) {
        float med = Float.parseFloat(lat.substring(2)) / 60.0f;
        med += Float.parseFloat(lat.substring(0, 2));
        if (NS.startsWith("S")) {
            med = -med;
        }
        return med;
    }

    static float Longitude2Decimal(String lon, String WE) {
        float med = Float.parseFloat(lon.substring(3)) / 60.0f;
        med += Float.parseFloat(lon.substring(0, 3));
        if (WE.startsWith("W")) {
            med = -med;
        }
        return med;
    }

    private GPSPosition position = new GPSPosition();

    private static final Map<String, SentenceParser> sentenceParsers = new HashMap<String, SentenceParser>();

    public NMEA() {
        sentenceParsers.put("GPGGA", new GPGGA(this));
        sentenceParsers.put("GPGLL", new GPGLL(this));
        sentenceParsers.put("GPRMC", new GPRMC(this));
        sentenceParsers.put("GPRMZ", new GPRMZ(this));
        sentenceParsers.put("GPGSA", new GPGSA(this));
        //only really good GPS devices have this sentence but ...
        sentenceParsers.put("GPVTG", new GPVTG(this));
    }

    private List<PositionUpdated> positionUpdatedListener = new ArrayList<>();

    public void addPositionUpdatedListener(PositionUpdated positionUpdated) {
        positionUpdatedListener.add(positionUpdated);
    }

    public void callPositionUpdated(GPSPosition position) {
        for (PositionUpdated positionUpdated : positionUpdatedListener) {
            positionUpdated.updatePosition(position);
        }
    }

    public GPSPosition parse(String line) {

        if (line.startsWith("$")) {
            String nmea = line.substring(1);
            String[] tokens = nmea.split(",");
            String type = tokens[0];
            //TODO check crc
            boolean found = false;
            if (sentenceParsers.containsKey(type)) {
                found = true;
                sentenceParsers.get(type).parse(tokens, position);
            }
            if (!found) {
                System.out.println("Not Found: " + type);
            }
            position.updatefix();
        }

        return position;
    }

    public GPSPosition getPosition() {
        return position;
    }

}
