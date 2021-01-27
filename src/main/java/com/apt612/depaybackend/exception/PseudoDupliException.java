package com.apt612.depaybackend.exception;

public class PseudoDupliException extends Exception{
    private String pseudo;

    public PseudoDupliException(String pseudo){
        this.pseudo = pseudo;
    }

    @Override
    public String getMessage(){
        return  "Username \"" + this.pseudo + "\" already exists, please choose another one.";
    }
}
