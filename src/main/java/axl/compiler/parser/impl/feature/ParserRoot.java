package axl.compiler.parser.impl.feature;

import axl.compiler.lexer.data.Token;
import axl.compiler.lexer.data.TokenType;
import axl.compiler.parser.data.RootNode;
import axl.compiler.parser.data.declaration.ImportNode;
import axl.compiler.parser.data.declaration.PackageNode;
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
            packageNode.setLocation(new ArrayList<>());
            root.setPackageNode(packageNode);

            List<TokenType> context = analyzer.getTokenStream().getContext();
            analyzer.getTokenStream().setContext(locationPackageContext);

            Token locationPartToken = analyzer.eat(TokenType.IDENTIFY);
            packageNode.getLocation().add(locationPartToken);

            while (analyzer.boolEat(TokenType.DOT)) {
                locationPartToken = analyzer.eat(TokenType.IDENTIFY);
                packageNode.getLocation().add(locationPartToken);
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

            if (root.getImportNodes() == null)
                root.setImportNodes(new ArrayList<>());

            ImportNode importNode = new ImportNode(root);
            importNode.setImportToken(importToken);
            importNode.setLocation(new ArrayList<>());
            root.getImportNodes().add(importNode);

            List<TokenType> context = analyzer.getTokenStream().getContext();
            analyzer.getTokenStream().setContext(locationImportContext);

            Token locationPartToken = analyzer.eat(TokenType.IDENTIFY);
            importNode.getLocation().add(locationPartToken);

            while (analyzer.boolEat(TokenType.DOT)) {
                Token starToken = analyzer.lowEat(TokenType.MULTIPLY);
                if (starToken != null) {
                    importNode.setStar(starToken);
                    break;
                }

                locationPartToken = analyzer.eat(TokenType.IDENTIFY);
                importNode.getLocation().add(locationPartToken);
            }

            Token asToken = analyzer.lowEat(TokenType.AS);
            if (asToken != null) {
                if (importNode.getStar() == null) {
                    importNode.setAs(asToken);
                    Token name = analyzer.eat(TokenType.IDENTIFY);
                    importNode.setName(name);
                } else {
                    throw new IllegalParserContentException(asToken, "Associations cannot be used when importing all elements");
                }
            }

            analyzer.getTokenStream().setContext(context);
        }
    }
}
