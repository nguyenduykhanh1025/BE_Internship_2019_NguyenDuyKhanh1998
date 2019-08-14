package com.kunlez.bookstore.converters.base;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Converter<S, T> {

    public abstract T convert(S source);

    public List<T> convert(List<S> sources){
        return sources.stream().map(this::convert).collect(Collectors.toList());
    }
}
