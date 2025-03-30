package axl.compiler.parser.impl.feature;

import axl.compiler.lexer.data.TokenType;
import axl.compiler.parser.data.RootSyntaxNode;
import axl.compiler.parser.impl.SyntaxAnalyzerImpl;
import lombok.Data;

import java.util.List;

@Data
public class ParserStruct implements ParserFeature {

    private RootSyntaxNode root;

    private static List<TokenType> locationPackageContext = List.of(
            TokenType.IDENTIFY,
            TokenType.IMPLICATION,
            TokenType.LEFT_BRACE
    );

    @Override
    public void analyze(SyntaxAnalyzerImpl analyzer) {

    }
}
