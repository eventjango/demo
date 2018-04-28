package com.example.demo.visitor;


@FunctionalInterface
public interface TypeCaster<T> {

    T apply(Visitable visitable, Class<T> object);
}
