package com.gildedrose;

public class ObjectDefault extends ObjectAbstract {

    public ObjectDefault(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void handle(){
        if (this.quality > 0) {
            this.quality = this.quality - 1;
            this.sellIn = this.sellIn - 1;
            if (this.sellIn < 0) {
                this.quality = this.quality - 1;
            }
        }
        
    }
    
}