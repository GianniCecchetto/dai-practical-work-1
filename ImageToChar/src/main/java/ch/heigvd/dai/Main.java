package ch.heigvd.dai;

import ch.heigvd.dai.bmp.Bmp;
import ch.heigvd.dai.commands.Root;
import java.io.File;
import picocli.CommandLine;

import static ch.heigvd.dai.bmpios.BmpReader.getBmp;
import static ch.heigvd.dai.commands.Root.AvailableTextEncoding.UTF8;
import static ch.heigvd.dai.txtconverter.TxtConverter.convert;
import static ch.heigvd.dai.txtconverter.TxtConverter.saveToTxt;

public class Main {
    public static void main(String[] args) {
        // Define command name - source: https://stackoverflow.com/a/11159435
        String jarFilename =
                new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath())
                        .getName();

        // Create root command
        Root root = new Root();

        // Run root command
        int exitCode =
                new CommandLine(root)
                        .setCommandName(jarFilename)
                        .setCaseInsensitiveEnumValuesAllowed(true)
                        .execute(args);

        System.exit(exitCode);
    }
}
