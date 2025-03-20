package com.gildedrose;

public class ObjectDefault extends Item implements ObjectInterface{

    public ObjectDefault(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void handle(Item item){
        
    }
    
}