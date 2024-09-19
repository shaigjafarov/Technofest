package az.technofest.service;

public class Bean <T>{

    String name;
    T object;

    Long order;

    Boolean primary;

    Type type;





}

enum Type{

    LAZY, EAGER
}