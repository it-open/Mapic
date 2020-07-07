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
public interface SentenceParser {

    public boolean parse(String[] tokens, GPSPosition position);

}
