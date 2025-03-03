package com.gildedrose;

public abstract  class ObjectAbstract extends Item{

    public ObjectAbstract(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public abstract void handle();
    
}