package axl.compiler.parser.impl.feature;

import axl.compiler.parser.impl.SyntaxAnalyzerImpl;

@FunctionalInterface
public interface ParserFeature {

    void analyze(SyntaxAnalyzerImpl analyzer);
}
