/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.itopen.imaging;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.jpeg.exif.ExifRewriter;
import org.apache.commons.imaging.formats.tiff.constants.ExifTagConstants;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputDirectory;
import org.apache.commons.imaging.formats.tiff.write.TiffOutputSet;

/**
 *
 * @author roland
 */
public class Exif {

    File imageFile;
    ImageMetadata metadata = null;
    JpegImageMetadata jpegMetadata = null;
    TiffOutputSet tiffOutputSet = null;

    public Exif(File imageFile) {
        try {
            this.imageFile = imageFile;
            metadata = Imaging.getMetadata(imageFile);
            jpegMetadata = (JpegImageMetadata) metadata;
            if (jpegMetadata != null) {
                tiffOutputSet = jpegMetadata.getExif().getOutputSet();
            } else {
                tiffOutputSet = new TiffOutputSet();
            }
            setSoftware();
        } catch (ImageReadException ex) {
            Logger.getLogger(Exif.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Exif.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ImageWriteException ex) {
            Logger.getLogger(Exif.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isOpen() {
        return (jpegMetadata != null);
    }

    public boolean isWriteable() {
        return (tiffOutputSet != null);
    }

    public void setSoftware() {
        try {
            for (TiffOutputDirectory dir : tiffOutputSet.getDirectories()) {
                dir.removeField(ExifTagConstants.EXIF_TAG_SOFTWARE);
            }

            TiffOutputDirectory exifDirectory = tiffOutputSet.getOrCreateRootDirectory();
            exifDirectory.add(ExifTagConstants.EXIF_TAG_SOFTWARE, "Mapic");
        } catch (ImageWriteException ex) {
            Logger.getLogger(Exif.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setGPS(double lat, double lon) {
        try {
            tiffOutputSet.setGPSInDegrees(lon, lat);
        } catch (ImageWriteException ex) {
            Logger.getLogger(Exif.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void write() {
        OutputStream os = null;
        try {
            File tmp = new File(imageFile.getAbsoluteFile().getParent() + File.separator + System.currentTimeMillis() + ".jpg");
            os = new BufferedOutputStream(new FileOutputStream(tmp));
            new ExifRewriter().updateExifMetadataLossless(imageFile, os, tiffOutputSet);
            os.flush();
            os.close();
            imageFile.delete();
            tmp.renameTo(imageFile);
        } catch (IOException | ImageReadException | ImageWriteException ex) {
            Logger.getLogger(Exif.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Exif.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
