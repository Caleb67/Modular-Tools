package io.github.caleb67.modulartools.util;

import org.apache.commons.lang3.function.TriConsumer;
import org.apache.commons.lang3.function.TriFunction;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class MethodChain<T> {
    protected T object;
    
    public MethodChain(T object) {this.object = object;}
    
    public MethodChain<T> and(Consumer<T> method) {
        method.accept(object);
        return this;
    }
    
    public <U> MethodChain<T> and(BiConsumer<T, U> method, U param) {
        method.accept(object, param);
        return this;
    }
    
    public <U, V> MethodChain<T> and(TriConsumer<T, U, V> method, U param1, V param2) {
        method.accept(object, param1, param2);
        return this;
    }
    
    public <R> ChainResult<R, T> andWithResult(Function<T, R> method) {
        return new ChainResult<>(method.apply(object), this);
    }
    
    public <R, U> ChainResult<R, T> andWithResult(BiFunction<T, U, R> method, U param) {
        return new ChainResult<>(method.apply(object, param), this);
    }
    
    public <R, U, V> ChainResult<R, T> andWithResult(TriFunction<T, U, V, R> method, U param1, V param2) {
        return new ChainResult<>(method.apply(object, param1, param2), this);
    }
    
    public <R> MethodChain<R> mutate(Function<T, R> method) {
        return new MethodChain<>(method.apply(object));
    }
    
    public <R, U> MethodChain<R> mutate(BiFunction<T, U, R> method, U param) {
        return new MethodChain<>(method.apply(object, param));
    }
    
    public <R, U, V> MethodChain<R> mutate(TriFunction<T, U, V, R> method, U param1, V param2) {
        return new MethodChain<>(method.apply(object, param1, param2));
    }
    
    public static final class ChainResult<R, T> {
        private final MethodChain<T> chain;
        public R value;
        
        public ChainResult(R value, MethodChain<T> chain) {
            this.chain = chain;
            this.value = value;
        }
        
        public MethodChain<T> then(Consumer<R> handler) {
            handler.accept(value);
            return chain;
        }
        
        public MethodChain<T> then(BiConsumer<R, T> handler) {
            handler.accept(value, chain.object);
            return chain;
        }
        
        public R get() {return value;}
        
        public MethodChain<T> put(Collection<R> collection) {
            collection.add(value);
            return chain;
        }
        
        public MethodChain<T> put(MutableSingleton<R> singleton) {
            singleton.set(0, value);
            return chain;
        }
    }
}
