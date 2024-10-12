package ch.heigvd.dai.bmp;

public class Bmp {
    public BmpHeader header;
    public int[][] r; // Red value matrix
    public int[][] g; // Green value matrix
    public int[][] b; // Blue value matrix
    public int[][] a; // Alpha value matrix (Only if bpp in header is 32)
}
