package com.company.webexample;

public abstract class HouseBuilder {
    protected House house = new House();
    
    protected String showProgress() {
        return house.toString();
    }

    abstract public void buildFoundation();
    abstract public void buildFrame();
    abstract public void buildExterior();
    abstract public void buildInterior();
}


