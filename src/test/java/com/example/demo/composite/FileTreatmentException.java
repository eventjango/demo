package com.example.demo.composite;

public class FileTreatmentException extends UnsupportedOperationException{

    public FileTreatmentException()
    {}

    public FileTreatmentException(String errorMessage){
        super(errorMessage);
    }

    public FileTreatmentException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
