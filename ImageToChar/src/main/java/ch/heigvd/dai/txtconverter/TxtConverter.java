package ch.heigvd.dai.txtconverter;

import ch.heigvd.dai.bmp.Bmp;
import ch.heigvd.dai.commands.*;

import java.io.*;

public class TxtConverter {
    /**
     * Reduce the pixel size of the resulting image by dividing the number of pixels by the resolution
     *
     * @param x          The current x index
     * @param y          The current y index
     * @param resolution By how much do we have to reduce the pixel size (1/resolution)
     * @param bmpImage   The bmImage to affect
     * @return A double that contains the gray scale of resolution^2 pixels merged together
     */
    private static double reducePixelSize(int x, int y, int resolution, Bmp bmpImage) {
        double grayScaledPixel = 0;

        for (int i = 0; i < resolution; i++) {
            for (int j = 0; j < resolution; j++) {
                grayScaledPixel += (double) (bmpImage.b[Math.max(y - i, 0)][Math.min(x + j, bmpImage.header.width - 1)] +
                        bmpImage.g[Math.max(y - i, 0)][Math.min(x + j, bmpImage.header.width - 1)] +
                        bmpImage.r[Math.max(y - i, 0)][Math.min(x + j, bmpImage.header.width - 1)]) / 3;
            }
        }

        grayScaledPixel /= Math.pow(resolution, 2);
        System.out.println(grayScaledPixel);

        return grayScaledPixel;
    }

    public static String convert(Bmp bmpImage, Root.AvailableTextEncoding encoding, int resolution) {
        String strImage = "";

        for (int y = (bmpImage.header.height - 1); y >= 0; y -= resolution) {
            strImage += "\n";
            for (int x = 0; x < bmpImage.header.width; x += resolution) {
                double grayScaledPixel = reducePixelSize(x, y, resolution, bmpImage);

                if (encoding == Root.AvailableTextEncoding.UTF8) {
                    if (grayScaledPixel <= 25) {
                        strImage += " ";
                    } else if (grayScaledPixel <= 50) {
                        strImage += "▁";
                    } else if (grayScaledPixel <= 75) {
                        strImage += "▗";
                    } else if (grayScaledPixel <= 100) {
                        strImage += "▒";
                    } else if (grayScaledPixel <= 125) {
                        strImage += "▃";
                    } else if (grayScaledPixel <= 150) {
                        strImage += "▓";
                    } else if (grayScaledPixel <= 175) {
                        strImage += "▂";
                    } else if (grayScaledPixel <= 200) {
                        strImage += "▄";
                    } else if (grayScaledPixel <= 225) {
                        strImage += "▆";
                    } else if (grayScaledPixel <= 256) {
                        strImage += "█";
                    }
                } else if (encoding == Root.AvailableTextEncoding.ASCII) {
                    if (grayScaledPixel <= 25) {
                        strImage += " ";
                    } else if (grayScaledPixel <= 50) {
                        strImage += ".";
                    } else if (grayScaledPixel <= 75) {
                        strImage += ":";
                    } else if (grayScaledPixel <= 100) {
                        strImage += "-";
                    } else if (grayScaledPixel <= 125) {
                        strImage += "=";
                    } else if (grayScaledPixel <= 150) {
                        strImage += "+";
                    } else if (grayScaledPixel <= 175) {
                        strImage += "*";
                    } else if (grayScaledPixel <= 200) {
                        strImage += "#";
                    } else if (grayScaledPixel <= 225) {
                        strImage += "%";
                    } else if (grayScaledPixel <= 256) {
                        strImage += "@";
                    }
                }


            }
        }
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
