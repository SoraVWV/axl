package axl.compiler.parser.impl.feature.expression;

import axl.compiler.data.Node;
import axl.compiler.data.expression.CallExpressionNode;
import axl.compiler.lexer.data.TokenType;
import axl.compiler.parser.exception.IllegalParserContentException;
import axl.compiler.parser.impl.SyntaxAnalyzerImpl;
import axl.compiler.parser.impl.feature.ParserFeature;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;

@Data
@AllArgsConstructor
public class ParserCallFeature<Parent extends Node<?>> implements ParserFeature {

    private final CallExpressionNode<Parent> callExpressionNode;

    @Override
    public void analyze(SyntaxAnalyzerImpl analyzer) {
        analyzer.getFeatures().pop();
        analyzer.eat(TokenType.LEFT_PARENT);
        analyzer.getFeatures().push(_ -> {
            analyzer.getFeatures().pop();
            if (!analyzer.getTokenStream().hasNext())
                throw new IllegalParserContentException(null, ""); // TODO end of file

            if (analyzer.boolEat(TokenType.RIGHT_PARENT)) {
                callExpressionNode.setArguments(Collections.emptyList());
            } else {
                callExpressionNode.setArguments(new ArrayList<>());
                analyzer.getFeatures().push(new ParserCallArgumentFeature());
                analyzer.getFeatures().push(getExpressionFeature());
            }
        });
    }

    private ParserExpressionFeature<CallExpressionNode<Parent>> getExpressionFeature() {
        return new ParserExpressionFeature<>(callExpressionNode.getArguments()::add, callExpressionNode);
    }

    private class ParserCallArgumentFeature implements ParserFeature {

        @Override
        public void analyze(SyntaxAnalyzerImpl analyzer) {
            if (!analyzer.getTokenStream().hasNext())
                throw new IllegalParserContentException(null, ""); // TODO end of file

            if (analyzer.boolEat(TokenType.RIGHT_PARENT)) {
                analyzer.getFeatures().pop();
                return;
            }

            analyzer.eat(TokenType.COMMA);
            analyzer.getFeatures().push(getExpressionFeature());
        }
    }
}
