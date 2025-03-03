package com.gildedrose;

public class Brie extends ObjectAbstract  {

    public Brie(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void handle() {
        if (this.quality < 50) {
            this.quality = this.quality + 1;
            if (this.sellIn < 6) {
                if (this.quality < 50) {
                    this.quality = this.quality + 1;
                }
            }
            this.sellIn = this.sellIn - 1;
        }
    }

}
