package axl.compiler.config;

import lombok.Data;

@Data
public class Config {

    private String inputFile;

    private String outputFile;

    private String sourceDir;

    private boolean debug = false;

    private String emitFormat;
}