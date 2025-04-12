package axl.compiler.parser.impl.utils;

import lombok.NonNull;

import java.util.EmptyStackException;
import java.util.Stack;

public class LimitedStack<T> extends Stack<T> {

    private final Stack<T> stack;

    private final Integer level;

    public LimitedStack(@NonNull Stack<T> stack) {
        this.stack = stack;
        this.level = stack.size();
    }

    public int size() {
        return stack.size() - level;
    }

    public T push(T entry) {
        return stack.push(entry);
    }

    public T pop() {
        if (size() == 0)
            throw new EmptyStackException();

        return stack.pop();
    }

    public T peek() {
        if (size() == 0)
            throw new EmptyStackException();

        return stack.peek();
    }
}