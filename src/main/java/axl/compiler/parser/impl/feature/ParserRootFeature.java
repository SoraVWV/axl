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
import java.util.Stack;

@Data
public class ParserRootFeature implements ParserFeature {

    private RootNode root;

    public void pushFeatures(Stack<ParserFeature> features) {
        features.push(analyzer -> {
            Token importToken = analyzer.lowEat(TokenType.IMPORT);
            if (importToken == null) {
                analyzer.getFeatures().pop();
                return;
            }

            features.push(new ParserRootImportFeature(importToken));
        });
        features.push(new ParserRootPackageFeature());
    }

    @Override
    public void analyze(SyntaxAnalyzerImpl analyzer) {
        // TODO
    }

    private class ParserRootPackageFeature implements ParserFeature {

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

            Token locationPartToken = analyzer.eat(TokenType.IDENTIFY);
            packageNode.getLocationTokens().add(locationPartToken);

            while (analyzer.boolEat(TokenType.DOT)) {
                locationPartToken = analyzer.eat(TokenType.IDENTIFY);
                packageNode.getLocationTokens().add(locationPartToken);
            }
        }
    }

    @AllArgsConstructor
    private class ParserRootImportFeature implements ParserFeature {

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
        }
    }
}
