package com.apt612.depaybackend.exception;

public class InvalidDataException extends Exception{
    private String invalidField;

    public InvalidDataException(String invalidField){
        this.invalidField = invalidField;
    }

    @Override
    public String getMessage(){
        return  "Field \"" + this.invalidField + "\" is not valid, please check and retry.";
    }
}
