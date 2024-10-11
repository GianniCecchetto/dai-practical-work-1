package ch.heigvd.dai.bmpios;


import ch.heigvd.dai.bmp.Bmp;
import ch.heigvd.dai.bmp.BmpHeader;

import java.io.*;

import java.io.FileInputStream;
import java.io.IOException;


public class BmpReader {

    static public Bmp getBmp(String fileName){
        Bmp bmp = new Bmp();
        bmp.header = new BmpHeader();


        try (FileInputStream fis = new FileInputStream(fileName)) {
            // Read BMP file header (14 bytes)
            byte[] bmpFileHeader = new byte[14];
            fis.read(bmpFileHeader);

            // Read DIB header (40 bytes for BMP version 3)
            byte[] header = new byte[40];
            fis.read(header);

            // Manually parsing the header
            bmp.header.width = getInt(header, 4);  // Offset 4 bytes for width
            bmp.header.height = getInt(header, 8); // Offset 8 bytes for height
            bmp.header.bpp =  (byte)getShort(header, 14); // Offset 14 bytes for bits per pixel
            bmp.r = new int[bmp.header.height][bmp.header.width];
            bmp.g = new int[bmp.header.height][bmp.header.width];
            bmp.b = new int[bmp.header.height][bmp.header.width];
            // Calculate row size (each row is padded to a multiple of 4 bytes)
            int rowSize = ((bmp.header.bpp  *  bmp.header.width + 31) / 32) * 4;
            byte[] pixelData = new byte[rowSize * bmp.header.height];

            // Skip to pixel data based on the file header
            int pixelDataOffset = getInt(bmpFileHeader, 10);
            fis.skip(pixelDataOffset - 54); // Skip to start of pixel data

            // Read the pixel data
            fis.read(pixelData);

            // Process pixel data (3 bytes per pixel)
            for (int y = 0; y < bmp.header.height; y++) {
                for (int x = 0; x < bmp.header.width; x++) {

                    int index = (y * rowSize) + (x * 3);
                    bmp.b[y][x] = (pixelData[index] & 0xFF);// blue
                    bmp.g[y][x] = (pixelData[index + 1] & 0xFF); // green
                    bmp.r[y][x] = (pixelData[index + 2] & 0xFF);; // red
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bmp;
    }

    public int writeText(String fileName,String content){
        try(OutputStream os = new FileOutputStream(fileName);
            Writer writer = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(writer)){
                bw.write(content);
                bw.flush();
                return 0;
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static int getInt(byte[] data, int offset) {
        return (data[offset] & 0xFF) |
                ((data[offset + 1] & 0xFF) << 8) |
                ((data[offset + 2] & 0xFF) << 16) |
                ((data[offset + 3] & 0xFF) << 24);
    }

    // Helper method to convert 2 bytes to a short (Little-endian format)
    private static short getShort(byte[] data, int offset) {
        return (short)((data[offset] & 0xFF) |
                ((data[offset + 1] & 0xFF) << 8));
    }
}
