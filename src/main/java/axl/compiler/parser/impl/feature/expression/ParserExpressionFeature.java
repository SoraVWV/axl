package axl.compiler.parser.impl.feature.expression;

import axl.compiler.data.ExpressionNode;
import axl.compiler.data.Node;
import axl.compiler.parser.exception.IllegalParserContentException;
import axl.compiler.parser.impl.SyntaxAnalyzerImpl;
import axl.compiler.parser.impl.feature.ParserFeature;
import axl.compiler.parser.impl.feature.ParserFeatureReference;
import axl.compiler.parser.impl.utils.LimitedStack;
import lombok.Data;

@Data
public class ParserExpressionFeature<Parent extends Node<?>> implements ParserFeature {


    private final ParserFeatureReference<ExpressionNode<Parent>> expressionNodeReference;

    private final Parent parent;

    public ParserExpressionFeature(ParserFeatureReference<ExpressionNode<Parent>> expressionNodeReference, Parent parent) {
        this.expressionNodeReference = expressionNodeReference;
        this.parent = parent;
    }

    private SyntaxAnalyzerImpl analyzer;

    private LimitedStack<OperatorEntry> context;

    private LimitedStack<Node<?>> nodes;

    private int parents = 0;

    @Override
    public void analyze(SyntaxAnalyzerImpl analyzer) {
        analyzer.getFeatures().pop();
        this.analyzer = analyzer;
        this.context = new LimitedStack<>(analyzer.getContext());
        this.nodes = new LimitedStack<>(analyzer.getNodes());

        // TODO add feature
    }

    int sizeExpressions() {
        return analyzer.getNodes().size() - levelNodes;
    }

    void pushExpression(ExpressionNode<?> expression) {
        this.analyzer.getNodes().push(expression);
    }

    ExpressionNode<?> popExpression() {
        if (sizeExpressions() <= 0)
            throw new IllegalParserContentException(analyzer.getTokenStream().peek(), "Invalid expression");

        Node<?> node = this.analyzer.getNodes().pop();

        if (node instanceof ExpressionNode<?>)
            return (ExpressionNode<?>) node;

        throw new IllegalParserContentException(analyzer.getTokenStream().peek(), "Invalid expression");
    }

    int sizeContext() {
        return analyzer.getContext().size() - levelContext;
    }

    void pushContext(OperatorEntry entry) {
        this.analyzer.getContext().push(entry);
    }

    OperatorEntry popContext() {
        if (sizeContext() <= 0)
            throw new IllegalParserContentException(analyzer.getTokenStream().peek(), "Invalid expression");

        Object object = this.analyzer.getContext().pop();

        if (object instanceof OperatorEntry)
            return (OperatorEntry) object;

        throw new IllegalParserContentException(analyzer.getTokenStream().peek(), "Invalid expression");
    }

    OperatorEntry peekContext() {
        if (sizeContext() <= 0)
            throw new IllegalParserContentException(analyzer.getTokenStream().peek(), "Invalid expression");

        Object object = this.analyzer.getContext().peek();

        if (object instanceof OperatorEntry)
            return (OperatorEntry) object;

        throw new IllegalParserContentException(analyzer.getTokenStream().peek(), "Invalid expression");
    }
}
