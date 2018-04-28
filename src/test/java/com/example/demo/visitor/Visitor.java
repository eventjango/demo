package com.example.demo.visitor;

public interface Visitor {

    void visit(Visitable visitable);

    <T> T typeCast(Visitable visitable, Class<T> object);
}
