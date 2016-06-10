package com.fic.thinkinpattern.example.abstractfactory;

/*
 * AbstractFactory
 */
public abstract  class Room  {
    public abstract Wall makeWall();
    public abstract Door makeDoor();
}