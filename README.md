# Mapic
Mapic is a Mapillary capture Server with HTML Gui usable on any PC or Raspberry to capture and Upload Images for Mapillary.
The Server is written in JAVA. Because then you can use an Raspberry or similar PC even without Display. Just point your Browser to it.

## GPS
Read data from a NMEA source (U-Blox USB Stick). Currently in Linux (ttyACM0) later in Windows via COM. Support for other Sources (gpsd, or similar) will follow when tey are nedded.

## Images
Support ImageCapturing from any Webcam via OpenCV. Multiple Webcams for different directions. Network Cameras or DSLR Support will follow

## Mapillary
Uploading Sequences and Images should work via API. This part is still not finalized because the Mapillary API does not support uploading. Then the Mapillary Commandlinetools have to be used.


