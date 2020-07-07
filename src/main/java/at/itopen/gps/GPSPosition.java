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
public class GPSPosition {

    public static enum FIX {
        FIX_NO, FIX_2D, FIX_3D
    };

    public float time = 0.0f;
    public float lat = 0.0f;
    public float lon = 0.0f;
    public boolean fixed = false;
    public FIX fix3d = FIX.FIX_NO;
    public List<String> usedSatellites = new ArrayList<>();
    public int quality = 0;
    public float dir = 0.0f;
    public float altitude = 0.0f;
    public float velocity = 0.0f;

    public void updatefix() {
        fixed = quality > 0;
    }

    public String toString() {
        return String.format("POSITION: lat: %f, lon: %f, time: %f, Q: %d, dir: %f, alt: %f, vel: %f", lat, lon, time, quality, dir, altitude, velocity);
    }

}
