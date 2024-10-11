package ch.heigvd.dai.bmp;

public class Bmp {
    private BmpHeader header;
    private byte[][] r; // Red value matrix
    private byte[][] g; // Green value matrix
    private byte[][] b; // Blue value matrix
    private byte[][] a; // Alpha value matrix (Only if bpp in header is 32)
}
