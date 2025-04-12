package axl.compiler.parser.impl;

import axl.compiler.data.ExpressionNode;
import axl.compiler.data.RootNode;
import axl.compiler.lexer.TokenStream;
import axl.compiler.parser.SyntaxAnalyzer;
import axl.compiler.parser.data.RootSyntaxNode;
import axl.compiler.parser.impl.feature.ParserFeature;
import axl.compiler.parser.impl.feature.ParserRootFeature;
import axl.compiler.parser.impl.feature.expression.OperatorEntry;
import lombok.Data;

import java.util.Stack;

@Data
public class SyntaxAnalyzerImpl implements SyntaxAnalyzer<RootSyntaxNode> {

    private static final Stack<OperatorEntry> context = new Stack<>();

    private static final Stack<ExpressionNode<?>> nodes = new Stack<>();

    private final TokenStream tokenStream;

    private final Stack<ParserFeature> features = new Stack<>();

    @Override
    public RootNode analyze() {
        RootNode root = new RootNode();

        ParserRootFeature rootFeature = new ParserRootFeature();
        rootFeature.setRoot(root);
        features.push(rootFeature);
        rootFeature.pushFeatures(features);

        while(features.size() > 1 || getTokenStream().hasNext())
            features.peek().analyze(this);

        if (getTokenStream().hasNext())
            throw new RuntimeException();

        return root;
    }
}
