package ch.heigvd.dai.txtconverter;

import ch.heigvd.dai.bmp.Bmp;
import ch.heigvd.dai.commands.*;

import java.io.*;

public class TxtConverter {


    public static String convert(Bmp bmpImage, Root.AvailableTextEncoding encoding) {
        String strImage = "";


        for (int y = (bmpImage.header.height-1); y >= 0 ; y--) {
            strImage += "\n";
            for (int x = 0; x <  bmpImage.header.width; x++) {
                int grayScaledPixel = (bmpImage.b[y][x] + bmpImage.g[y][x] + bmpImage.r[y][x])/3;

                if(grayScaledPixel <= 51){
                    strImage += " ";
                } else if(grayScaledPixel <= 102){
                    strImage += "░";
                } else if (grayScaledPixel <= 153) {
                    strImage += "▒";
                } else if (grayScaledPixel <= 204) {
                    strImage += "▓";
                } else if (grayScaledPixel <= 256) {
                    strImage += "█";
                }
            }
        }
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
