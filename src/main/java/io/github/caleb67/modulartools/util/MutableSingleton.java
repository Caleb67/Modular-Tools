package io.github.caleb67.modulartools.util;

import it.unimi.dsi.fastutil.objects.ObjectIterators;
import org.jspecify.annotations.NonNull;

import java.util.AbstractList;
import java.util.Iterator;

public class MutableSingleton<T> extends AbstractList<T> {
    public T value;
    
    public MutableSingleton(T value) {this.value = value;}
    
    @Override public @NonNull Iterator<T> iterator() {
        return ObjectIterators.singleton(value);
    }
    
    @Override public int size() {return 1;}
    
    @Override public boolean isEmpty() {return false;}
    
    @Override public Object @NonNull [] toArray() {
        return new Object[]{value};
    }
    
    @Override public boolean contains(Object o) {return value.equals(o);}
    
    @Override public T set(int index, T element) {
        if (index != 0) throw new IndexOutOfBoundsException("index: " + index);
        value = element;
        return value;
    }
    
    @Override public T get(int index) {
        if (index != 0) throw new IndexOutOfBoundsException("index: " + index);
        return value;
    }
    
    @Override public boolean add(T t) {throw new UnsupportedOperationException();}
    
    @Override public boolean remove(Object o) {throw new UnsupportedOperationException();}
}
