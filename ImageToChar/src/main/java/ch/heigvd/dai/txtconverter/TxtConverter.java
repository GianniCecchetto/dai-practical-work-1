package ch.heigvd.dai.txtconverter;

import ch.heigvd.dai.bmp.Bmp;
import ch.heigvd.dai.commands.*;

import java.io.*;

public class TxtConverter {


    public static String convert(Bmp bmpImage, Root.AvailableTextEncoding encoding) {
        String strImage = "";
        int treatedPixels = 0;
        System.out.print("Converting to char |▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒|\r");
        for (int y = (bmpImage.header.height-1); y >= 0 ; y--) {
            strImage += "\n";
            for (int x = 0; x <  bmpImage.header.width; x++) {
                treatedPixels++;
                int progress = treatedPixels * 100 / (bmpImage.header.width * bmpImage.header.height);

                if (progress % 5 == 0) {
                    int progressBlocks = progress / 5;
                    String loadingBar = "Converting to char |"
                            + "█".repeat(progressBlocks)
                            + "▒".repeat(20 - progressBlocks)
                            + "| " + progress + "%";
                    System.out.print("\r" + loadingBar);
                }

                int grayScaledPixel = (bmpImage.b[y][x] + bmpImage.g[y][x] + bmpImage.r[y][x])/3;
                if(encoding == Root.AvailableTextEncoding.UTF8){
                    if(grayScaledPixel <= 25){
                        strImage += " ";
                    } else if(grayScaledPixel <= 50){
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
                }else if (encoding == Root.AvailableTextEncoding.ASCII){
                    if(grayScaledPixel <= 25){
                        strImage += " ";
                    } else if(grayScaledPixel <= 50){
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
        System.out.println();
                return strImage;
    }

    public static int saveToTxt(String getOutputFilename,Root.AvailableTextEncoding encoding,String strImage){
        try(OutputStream os = new FileOutputStream(getOutputFilename);
            Writer writer = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(writer)){
            bw.write(strImage);
            bw.flush();
            return 0;
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
