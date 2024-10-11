package ch.heigvd.dai.bmp;

public class BmpHeader {
    private char magic; // Nombre MAGIC
    private int fileSize; // Taille totale du fichier
    private char reserved1; // Réservé (0)
    private char reserved2; // Réservé (0)
    private int dataOffset; // Offset des données de l'image
    private int headerSize; // Taille de l'en-tête du fichier
    private int width; // Largeur de l'image
    private int height; // Hauteur de l'image
    private char planes; // Nombre de plans (toujours 1)
    private char bpp; // Bits par pixel (24 bits dans notre cas)
    private int compression; // Méthode de compression (0 pour non compressé)
    private int imageSize; // Taille de l'image (sans en-tête)
    private int xResolution; // Résolution horizontale (en pixels par mètre)
    private int yResolution; // Résolution verticale (en pixels par mètre)
    private int colorsUsed; // Nombre de couleurs utilisées dans l'image (0 pour toutes)
    private int importantColors; // Nombre de couleurs importantes (0 pour toutes)
}
