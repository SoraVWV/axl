package axl.compiler.lexer.impl.feature;

import axl.compiler.lexer.data.Lexer;
import axl.compiler.lexer.data.Token;

public interface LexerFeature {

    Token tokenize(Lexer lexer);
}
