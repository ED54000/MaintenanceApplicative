package com.gildedrose;

public class Concert extends ObjectAbstract {

    public Concert(String name, int sellIn, int quality, boolean conjured) {
        super(name, sellIn, quality, conjured);
    }

    @Override
    public void handle() {
        int degrade = 1;
        if (this.conjure){
            degrade = 2;
        }
        if (this.quality < 50) {
            this.quality = this.quality + degrade;

            if (this.sellIn < 11) {
                if (this.quality < 50) {
                    this.quality = this.quality + degrade;
                }
                if (this.sellIn < 6) {
                    if (this.quality < 50) {
                        this.quality = this.quality + degrade;
                    }
                }
            }
        }
        this.sellIn = this.sellIn - 1;
        if (this.sellIn < 0) {
            this.quality = this.quality - this.quality;
        }
    }

}
