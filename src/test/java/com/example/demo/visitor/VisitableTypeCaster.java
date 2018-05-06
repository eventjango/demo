package com.example.demo.visitor;

import lombok.Data;

@Data
public class VisitableTypeCaster {

    private TypeCaster typeCaster = (visitable, object) -> visitable;

    public <T> T cast(Visitable visitable, Class<T> typeObject){

        T result = null;

        try {

            result = (T) typeCaster.apply(visitable, typeObject);
        }

        catch (ClassCastException e){
            e.printStackTrace();
        }

        return result;
    }
}
