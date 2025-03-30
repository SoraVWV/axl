package axl.compiler.parser.impl.feature;

import axl.compiler.data.RootNode;
import axl.compiler.data.declaration.ImportNode;
import axl.compiler.data.declaration.PackageNode;
import axl.compiler.lexer.data.Token;
import axl.compiler.lexer.data.TokenType;
import axl.compiler.parser.exception.IllegalParserContentException;
import axl.compiler.parser.impl.SyntaxAnalyzerImpl;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Data
public class ParserRoot implements ParserFeature {

    private RootNode root;

    private static List<TokenType> locationPackageContext = List.of(
            TokenType.IDENTIFY,
            TokenType.DOT
    );

    private static List<TokenType> locationImportContext = List.of(
            TokenType.IDENTIFY,
            TokenType.DOT,
            TokenType.AS,
            TokenType.MULTIPLY
    );

    public void pushFeatures(Stack<ParserFeature> features) {
        features.push(analyzer -> {
            Token importToken = analyzer.lowEat(TokenType.IMPORT);
            if (importToken == null) {
                analyzer.getFeatures().pop();
                return;
            }

            features.push(new ParserRootImport(importToken));
        });
        features.push(new ParserRootPackage());
    }

    @Override
    public void analyze(SyntaxAnalyzerImpl analyzer) {

    }

    private class ParserRootPackage implements ParserFeature {

        @Override
        public void analyze(SyntaxAnalyzerImpl analyzer) {
            analyzer.getFeatures().pop();
            Token packageToken = analyzer.lowEat(TokenType.PACKAGE);
            if (packageToken == null)
                return;

            PackageNode packageNode = new PackageNode(root);
            packageNode.setPackageToken(packageToken);
            packageNode.setLocationTokens(new ArrayList<>());
            root.setLocation(packageNode);

            List<TokenType> context = analyzer.getTokenStream().getContext();
            analyzer.getTokenStream().setContext(locationPackageContext);

            Token locationPartToken = analyzer.eat(TokenType.IDENTIFY);
            packageNode.getLocationTokens().add(locationPartToken);

            while (analyzer.boolEat(TokenType.DOT)) {
                locationPartToken = analyzer.eat(TokenType.IDENTIFY);
                packageNode.getLocationTokens().add(locationPartToken);
            }

            analyzer.getTokenStream().setContext(context);
        }
    }

    @AllArgsConstructor
    private class ParserRootImport implements ParserFeature {

        private final Token importToken;

        @Override
        public void analyze(SyntaxAnalyzerImpl analyzer) {
            analyzer.getFeatures().pop();

            if (root.getImports() == null)
                root.setImports(new ArrayList<>());

            ImportNode importNode = new ImportNode(root);
            importNode.setImportToken(importToken);
            importNode.setLocationTokens(new ArrayList<>());
            root.getImports().add(importNode);

            List<TokenType> context = analyzer.getTokenStream().getContext();
            analyzer.getTokenStream().setContext(locationImportContext);

            Token locationPartToken = analyzer.eat(TokenType.IDENTIFY);
            importNode.getLocationTokens().add(locationPartToken);

            while (analyzer.boolEat(TokenType.DOT)) {
                Token starToken = analyzer.lowEat(TokenType.MULTIPLY);
                if (starToken != null) {
                    importNode.setStarToken(starToken);
                    break;
                }

                locationPartToken = analyzer.eat(TokenType.IDENTIFY);
                importNode.getLocationTokens().add(locationPartToken);
            }

            Token asToken = analyzer.lowEat(TokenType.AS);
            if (asToken != null) {
                if (importNode.getStarToken() == null) {
                    importNode.setAsToken(asToken);
                    Token name = analyzer.eat(TokenType.IDENTIFY);
                    importNode.setNameToken(name);
                } else {
                    throw new IllegalParserContentException(asToken, "Associations cannot be used when importing all elements");
                }
            }

            analyzer.getTokenStream().setContext(context);
        }
    }
}
