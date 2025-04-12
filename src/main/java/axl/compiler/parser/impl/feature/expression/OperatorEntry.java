package axl.compiler.parser.impl.feature.expression;

import axl.compiler.lexer.data.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@SuppressWarnings("ClassCanBeRecord")
public class OperatorEntry {

    private final ParserExpressionOperator operator;

    private final Token token;

    private final OperatorType type;

    public void accept(ParserExpressionFeature<?> state) {
        operator.getType().getLambda().accept(state, token, type);
    }

    enum OperatorType {
        BINARY,
        PREFIX,
        POSTFIX
    }
}