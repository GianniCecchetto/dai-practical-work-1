package ch.heigvd.dai.commands;

import picocli.CommandLine;

import java.util.concurrent.Callable;

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

    public String getInputFilename() {
        return inputFilename;
    }

    public String getOutputFilename() {
        return outputFilename;
    }

    public AvailableTextEncoding getEncoding() {
        return textEncoding;
    }

    @Override
    public Integer call() {
        System.out.println(
                "Writing from "
                        + getInputFilename()
                        + " to "
                        + getOutputFilename()
                        + " with "
                        + getEncoding()
                        + " encoding");

        //Bmp bmpImage = BmpReader.read(getInputFilename());

        //String imageString = bmpImage.convert(getEncoding());

        //Writer.write(getOutputFilename(), getEncoding(), imageString);
        return 0;
    }
}