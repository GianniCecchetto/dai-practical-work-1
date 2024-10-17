package ch.heigvd.dai.commands;

import ch.heigvd.dai.bmp.Bmp;
import picocli.CommandLine;

import java.util.concurrent.Callable;

import static ch.heigvd.dai.bmpios.BmpReader.*;
import static ch.heigvd.dai.txtconverter.TxtConverter.*;

@CommandLine.Command(
        name = "Convert",
        description = "A small CLI application to convert BMP image to a text version of it",
        version = "1.0.0",
        scope = CommandLine.ScopeType.INHERIT,
        mixinStandardHelpOptions = true)
public class Root implements Callable<Integer> {
    public enum AvailableTextEncoding {
        ASCII,
        UTF8
    }

    @CommandLine.Parameters(index = "0", description = "The name of the input file.")
    protected String inputFilename;

    @CommandLine.Parameters(index = "1", description = "The name of the output file.")
    protected String outputFilename;

    @CommandLine.Option(
            names = {"-e", "--encoding"},
            description = "The text encoding to use (possible values: ${COMPLETION-CANDIDATES}).",
            required = true)
    protected AvailableTextEncoding textEncoding;

    @CommandLine.Option(
            names = {"-c", "--compression"},
            description = "The value by which the image will be divided (value in int).")
    protected int compression = 1;

    @Override
    public Integer call() {
        System.out.println(
                "Writing from "
                        + inputFilename
                        + " to "
                        + outputFilename
                        + " with "
                        + textEncoding
                        + " encoding, image size will be divided by "
                        + compression);

        Bmp bmpImage = getBmp(inputFilename);

        String imageString = convert(bmpImage, textEncoding, compression);

        saveToTxt(outputFilename, textEncoding, imageString);
        return 0;
    }
}