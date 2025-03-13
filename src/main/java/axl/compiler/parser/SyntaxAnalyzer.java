package axl.compiler.parser;

import axl.compiler.lexer.TokenStream;
import axl.compiler.parser.data.RootNode;

public interface SyntaxAnalyzer {

    RootNode analyze(TokenStream tokenStream);
}
