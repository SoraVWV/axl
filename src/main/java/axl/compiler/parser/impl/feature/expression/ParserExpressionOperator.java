package axl.compiler.parser.impl.feature.expression;

import axl.compiler.lexer.data.TokenType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParserExpressionOperator {
    RIGHT_PARENT(TokenType.RIGHT_PARENT, ParserExpressionType.PARENT, 0),
    LEFT_PARENT(TokenType.LEFT_PARENT, ParserExpressionType.NONE, 15),
    ASSIGN(TokenType.ASSIGN, ParserExpressionType.BINARY, -1),
    PLUS_ASSIGN(TokenType.PLUS_ASSIGN, ParserExpressionType.BINARY, -1),
    MINUS_ASSIGN(TokenType.MINUS_ASSIGN, ParserExpressionType.BINARY, -1),
    MULTIPLY_ASSIGN(TokenType.MINUS_ASSIGN, ParserExpressionType.BINARY, -1),
    DIVIDE_ASSIGN(TokenType.DIVIDE_ASSIGN, ParserExpressionType.BINARY, -1),
    MODULO_ASSIGN(TokenType.MODULO_ASSIGN, ParserExpressionType.BINARY, -1),
    EQUALS(TokenType.EQUALS, ParserExpressionType.BINARY, 0),
    NOT_EQUALS(TokenType.NOT_EQUALS, ParserExpressionType.BINARY, 0),
    GREATER(TokenType.GREATER, ParserExpressionType.BINARY, 0),
    LESS(TokenType.LESS, ParserExpressionType.BINARY, 0),
    GREATER_OR_EQUAL(TokenType.GREATER_OR_EQUAL, ParserExpressionType.BINARY, 0),
    LESS_OR_EQUAL(TokenType.LESS_OR_EQUAL, ParserExpressionType.BINARY, 0),
    ADD(TokenType.PLUS, ParserExpressionType.BINARY, 1),
    SUB(TokenType.MINUS, ParserExpressionType.BINARY, 1),
    MULTI(TokenType.MULTIPLY, ParserExpressionType.BINARY, 2),
    DIV(TokenType.DIVIDE, ParserExpressionType.BINARY, 2),
    MOD(TokenType.MODULO, ParserExpressionType.BINARY, 2),
    BIT_AND(TokenType.BIT_AND, ParserExpressionType.BINARY, 3),
    BIT_OR(TokenType.BIT_OR, ParserExpressionType.BINARY, 4),
    AND(TokenType.AND, ParserExpressionType.BINARY, 5),
    OR(TokenType.OR, ParserExpressionType.BINARY, 6),
    UNARY_MINUS(TokenType.UNARY_MINUS, ParserExpressionType.PREFIX, 7),
    NOT(TokenType.NOT, ParserExpressionType.PREFIX, 7),
    BIT_NOT(TokenType.BIT_NOT, ParserExpressionType.PREFIX, 7),
    BIT_XOR(TokenType.BIT_XOR, ParserExpressionType.BINARY, 7),
    BIT_SHIFT_LEFT(TokenType.BIT_SHIFT_LEFT, ParserExpressionType.BINARY, 8),
    BIT_SHIFT_RIGHT(TokenType.BIT_SHIFT_RIGHT, ParserExpressionType.BINARY, 8),
    ACCESS(TokenType.DOT, ParserExpressionType.BINARY, 9),
    IS(TokenType.IS, ParserExpressionType.BINARY, 9),
    AS(TokenType.AS, ParserExpressionType.BINARY, 9),
    INCREMENT(TokenType.INCREMENT, ParserExpressionType.UNARY, 9),
    DECREMENT(TokenType.DECREMENT, ParserExpressionType.UNARY, 9);

    private final TokenType operatorToken;

    private final ParserExpressionType type;

    private final int priority;
}
