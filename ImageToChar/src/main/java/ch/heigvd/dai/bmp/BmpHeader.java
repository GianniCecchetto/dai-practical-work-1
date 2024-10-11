package ch.heigvd.dai.bmp;

public class BmpHeader {
    public char magic; // Nombre MAGIC
    public int fileSize; // Taille totale du fichier
    public char reserved1; // Réservé (0)
    public char reserved2; // Réservé (0)
    public int dataOffset; // Offset des données de l'image
    public int headerSize; // Taille de l'en-tête du fichier
    public int width; // Largeur de l'image
    public int height; // Hauteur de l'image
    public char planes; // Nombre de plans (toujours 1)
    public byte bpp; // Bits par pixel (24 bits dans notre cas)
    public int compression; // Méthode de compression (0 pour non compressé)
    public int imageSize; // Taille de l'image (sans en-tête)
    public int xResolution; // Résolution horizontale (en pixels par mètre)
    public int yResolution; // Résolution verticale (en pixels par mètre)
    public int colorsUsed; // Nombre de couleurs utilisées dans l'image (0 pour toutes)
    public int importantColors; // Nombre de couleurs importantes (0 pour toutes)
}
