package ch.heigvd.dai;

import static ch.heigvd.dai.bmpios.BmpReader.getBmp;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        getBmp("/mnt/c/Users/natha/Downloads/sample1.bmp");
    }
}
