package ch.heigvd.dai.txtconverter;

import ch.heigvd.dai.bmp.Bmp;
import ch.heigvd.dai.loadingbar.LoadingBar;
import ch.heigvd.dai.commands.*;

import java.io.*;
//{'▇','▆','▅', '▃', '▂','▁',' '}

public class TxtConverter {
    private static String utf8 = "█▓▒░";
    private static String ascii = "@%#*+=-:. ";

    /**
     * Calculate the gray value of 1 or compression^2 pixels
     *
     * @param x           The current x index
     * @param y           The current y index
     * @param compression By how much do we have to reduce the pixel size (1/compression)
     * @param bmpImage    The bmImage to affect
     * @return A double that contains the gray scale of compression^2 pixels merged together
     */
    private static double getGrayScale(int x, int y, int compression, Bmp bmpImage) {
        double grayScaledPixel = 0;

        for (int i = 0; i < compression; i++) {
            for (int j = 0; j < compression; j++) {
                grayScaledPixel += (double) (bmpImage.b[Math.max(y - i, 0)][Math.min(x + j, bmpImage.header.width - 1)] +
                        bmpImage.g[Math.max(y - i, 0)][Math.min(x + j, bmpImage.header.width - 1)] +
                        bmpImage.r[Math.max(y - i, 0)][Math.min(x + j, bmpImage.header.width - 1)]) / 3;
            }
        }

        grayScaledPixel /= Math.pow(compression, 2);

        return grayScaledPixel;
    }

    public static String convert(Bmp bmpImage, Root.AvailableTextEncoding encoding, int compression, boolean isInverted) {
        String strImage = "";
        int treatedPixels = 0;
        LoadingBar loadingBar = new LoadingBar();
        loadingBar.initLoadingBar("Converting to char", 50);
        for (int y = (bmpImage.header.height - 1); y >= 0; y -= compression) {
            strImage += "\n";
            for (int x = 0; x < bmpImage.header.width; x += compression) {
                treatedPixels++;
                int progress = treatedPixels * 100 * (int)Math.pow(compression, 2) / (bmpImage.header.width * bmpImage.header.height);
                loadingBar.updateLoadingBar(progress);

                double grayScaledPixel = getGrayScale(x, y, compression, bmpImage);

                char[] grayScaleInChar = switch (encoding) {
                    case ASCII -> ascii.toCharArray();
                    case UTF8 -> utf8.toCharArray();
                    default -> ascii.toCharArray();
                };

                // Calculate the index of the char to match the gray scaled pixel
                int indexOfChar = (int) (grayScaledPixel * grayScaleInChar.length / 255);

                if (!isInverted) {
                    strImage += grayScaleInChar[indexOfChar];
                } else {
                    strImage += grayScaleInChar[(grayScaleInChar.length - 1) - indexOfChar];
                }
            }
        }

        System.out.println();

        return strImage;
    }

    public static int saveToTxt(String getOutputFilename, Root.AvailableTextEncoding encoding, String strImage) {
        try (OutputStream os = new FileOutputStream(getOutputFilename);
             Writer writer = new OutputStreamWriter(os);
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(strImage);
            bw.flush();
            return 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
