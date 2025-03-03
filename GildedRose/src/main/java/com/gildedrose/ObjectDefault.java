package com.gildedrose;

public class ObjectDefault extends ObjectAbstract {

    public ObjectDefault(String name, int sellIn, int quality, boolean conjured) {
        super(name, sellIn, quality, conjured);
    }

    public void handle(){
       int degrade = 1;
       if (this.conjure){
           degrade = 2;
       }
        if (this.quality > 0) {
            this.quality = this.quality - degrade;
            this.sellIn = this.sellIn - 1;
            if (this.sellIn < 0) {
                this.quality = this.quality - degrade;
            }
        }
        
    }
    
}