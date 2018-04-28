package com.example.demo.visitor;

public interface Visitor {

    void visit(Visitable visitable);

    <T> T cast(Visitable visitable, Class<T> object);
}
