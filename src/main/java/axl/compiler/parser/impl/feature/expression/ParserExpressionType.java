package axl.compiler.parser.impl.feature.expression;

import axl.compiler.data.ExpressionNode;
import axl.compiler.lexer.data.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EmptyStackException;

@Getter
@AllArgsConstructor
public enum ParserExpressionType {
    BINARY((analyzer, token, type) -> {
        analyzer.popContext();
        try {
            ExpressionNode<?> right = analyzer.popExpression();
            ExpressionNode<?> left = analyzer.popExpression();

            analyzer.pushExpression(new Expression.BinaryExpression(left, right, token));
            analyzer.setLastExpression(true);
        } catch (EmptyStackException e) {
            throw new IllegalStateException("Invalid expression: insufficient operands for binary operator " + type, e);
        }
    }),
    UNARY,
    PREFIX,
    PARENT,
    NONE;

    private final Generator lambda;

    interface Generator {

        void accept(ParserExpressionFeature<?> state, Token token, OperatorEntry.OperatorType type);
    }
}
