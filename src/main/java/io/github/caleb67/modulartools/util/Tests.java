package io.github.caleb67.modulartools.util;

import java.util.Arrays;
import java.util.function.Predicate;

public class Tests {
    @SafeVarargs
    public static <T> Predicate<T> notIn(T... array) {
        return (t) -> !Arrays.asList(array).contains(t);
    }
    
    @SafeVarargs
    public static <T> Predicate<T> comprisesAll(T... array) {
        return (t) -> Arrays.stream(array).allMatch(t::equals);
    }
    
    @SafeVarargs
    public static <T> Predicate<T> in(T... array) {
        return (t) -> Arrays.asList(array).contains(t);
    }
}
