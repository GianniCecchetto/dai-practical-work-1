package ch.heigvd.dai;

import ch.heigvd.dai.bmp.Bmp;
import static ch.heigvd.dai.bmpios.BmpReader.getBmp;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        Bmp bmp = getBmp("/mnt/c/Users/natha/Downloads/sample1.bmp");

        System.out.println("Width: " + bmp.header.width);
        System.out.println("Height: " + bmp.header.height);
        System.out.println("Bits per Pixel: " + bmp.header.bpp );

        for (int y = 0; y < bmp.header.height; y++) {
          for (int x = 0; x < bmp.header.width; x++) {
                System.out.printf("Pixel at (%d, %d): R=%d, G=%d, B=%d%n", x, y, bmp.r[y][x] , bmp.g[y][x], bmp.b[y][x]);
          }
        }



    }
}
