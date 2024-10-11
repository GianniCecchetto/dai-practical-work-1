package ch.heigvd.dai.bmpios;


import java.io.*;

import java.io.FileInputStream;
import java.io.IOException;


public class BmpReader {

    static public void getBmp( String fileName){
        String content = "";

        try (FileInputStream fis = new FileInputStream(fileName)) {
            // Read BMP file header (14 bytes)
            byte[] bmpFileHeader = new byte[14];
            fis.read(bmpFileHeader);

            // Read DIB header (40 bytes for BMP version 3)
            byte[] header = new byte[40];
            fis.read(header);

            // Manually parsing the header
            int width = getInt(header, 4);  // Offset 4 bytes for width
            int height = getInt(header, 8); // Offset 8 bytes for height
            short bitsPerPixel = getShort(header, 14); // Offset 14 bytes for bits per pixel

            System.out.println("Width: " + width);
            System.out.println("Height: " + height);
            System.out.println("Bits per Pixel: " + bitsPerPixel);

            // Calculate row size (each row is padded to a multiple of 4 bytes)
            int rowSize = ((bitsPerPixel * width + 31) / 32) * 4;
            byte[] pixelData = new byte[rowSize * height];

            // Skip to pixel data based on the file header
            int pixelDataOffset = getInt(bmpFileHeader, 10);
            fis.skip(pixelDataOffset - 54); // Skip to start of pixel data

            // Read the pixel data
            fis.read(pixelData);

            // Process pixel data (3 bytes per pixel)
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int index = (y * rowSize) + (x * 3);
                    int blue = pixelData[index] & 0xFF;
                    int green = pixelData[index + 1] & 0xFF;
                    int red = pixelData[index + 2] & 0xFF;
                    System.out.printf("Pixel at (%d, %d): R=%d, G=%d, B=%d%n", x, y, red, green, blue);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
