package com.gildedrose;

public class Brie extends ObjectAbstract  {

    public Brie(String name, int sellIn, int quality, boolean conjured) {
        super(name, sellIn, quality, conjured);
    }


    public void handle() {
        int degrade = 1;
        if (this.conjure){
            degrade = 2;
        }
        if (this.quality < 50) {
            this.quality = this.quality + degrade;
            if (this.sellIn < 6) {
                if (this.quality < 50) {
                    this.quality = this.quality + degrade;
                }
            }
            this.sellIn = this.sellIn - 1;
        }
    }

}
