package client;

import com.beust.jcommander.Parameter;

public class Args {

    @Parameter(names = {"--type", "-t"})
    private String type;

    @Parameter(names = {"--key", "-k"})
    private String key;

    @Parameter(names = {"--value", "-v"})
    private String value;

    @Parameter(names = {"--input", "-in"})
    private String inputFile = null;

    public String getInputFile() {
        return inputFile;
    }
}
