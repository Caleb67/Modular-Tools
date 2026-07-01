package io.github.caleb67.modulartools.util;

import org.apache.commons.lang3.function.TriConsumer;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class MethodChain<T> {
    private T object;
    public MethodChain(T object) { this.object = object;}

    public MethodChain<T> and(Consumer<T> method) {
        method.accept(object); return this;
    }
    public <U> MethodChain<T> and(BiConsumer<T, U> method, U param) {
        method.accept(object, param); return this;
    }
    public <U, V> MethodChain<T> and(TriConsumer<T, U, V> method, U param1, V param2) {
        method.accept(object, param1, param2); return this;
    }

    public <R> MethodChain<T> and(Function<T, R> method, ResultReference<R> result_ref) {
        result_ref.value = method.apply(object); return this;
    }
    public <R, U> MethodChain<T> and(BiFunction<T, U, R> method, U param, ResultReference<R> result_ref) {
        result_ref.value = method.apply(object, param); return this;
    }

    public <R> MethodChain<T> and(Function<T, R> method, Consumer<R> result_consumer) {
        result_consumer.accept(method.apply(object)); return this;
    }
    public <R, U> MethodChain<T> and(BiFunction<T, U, R> method, U param, Consumer<R> result_consumer) {
        result_consumer.accept(method.apply(object, param)); return this;
    }

    public static final class ResultReference<T> {
        public T value;
        public ResultReference(T value) { this.value = value;}
    }
}
