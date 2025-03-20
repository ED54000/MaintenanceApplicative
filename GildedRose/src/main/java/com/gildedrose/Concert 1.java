package com.gildedrose;

public class Concert extends Item implements ObjectInterface{

    public Concert(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void handle(Item item){
        
    }
    
}
