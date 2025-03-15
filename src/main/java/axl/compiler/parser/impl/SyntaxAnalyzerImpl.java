package axl.compiler.parser.impl;

import axl.compiler.lexer.TokenStream;
import axl.compiler.parser.SyntaxAnalyzer;
import axl.compiler.parser.data.Node;
import axl.compiler.parser.data.RootNode;
import axl.compiler.parser.impl.feature.ParserFeature;
import axl.compiler.parser.impl.feature.ParserRoot;
import lombok.Data;

import java.util.Stack;

@Data
public class SyntaxAnalyzerImpl implements SyntaxAnalyzer<RootNode> {

    private final TokenStream tokenStream;

    private final Stack<Node<?>> nodes = new Stack<>();

    private final Stack<Object> context = new Stack<>();

    private final Stack<ParserFeature> features = new Stack<>();

    @Override
    public RootNode analyze() {
        RootNode root = new RootNode();

        ParserRoot rootFeature = new ParserRoot();
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
