package com.gildedrose;

public class Concert extends ObjectAbstract {

    public Concert(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void handle() {
        if (this.quality < 50) {
            this.quality = this.quality + 1;

            if (this.sellIn < 11) {
                if (this.quality < 50) {
                    this.quality = this.quality + 1;
                }
                if (this.sellIn < 6) {
                    if (this.quality < 50) {
                        this.quality = this.quality + 1;
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
