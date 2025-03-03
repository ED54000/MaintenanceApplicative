package com.gildedrose;

public abstract  class ObjectAbstract extends Item{



    boolean conjure;

    public ObjectAbstract(String name, int sellIn, int quality, boolean conjured) {
        super(name, sellIn, quality);
        conjure = conjured;
    }

    public abstract void handle();
    
}