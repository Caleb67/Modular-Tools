package io.github.caleb67.modulartools.util;

import java.util.function.Consumer;
import java.util.function.Function;

public class MethodChain<T> {
    private T object;
    public MethodChain(T object) {
        this.object = object;
    }

    public MethodChain<T> and(Consumer<T> method) {
        method.accept(object);
        return this;
    }

    public <U> MethodChain<T> and(Function<T, U> method, ResultReference<U> result_ref) {
        result_ref.value = method.apply(object);
        return this;
    }

    public static final class ResultReference<T> {
        public T value;
        public ResultReference(T value) { this.value = value;}
    }
}
