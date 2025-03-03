package com.gildedrose;

class GildedRose {
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            handleItem(item);
        }
    }

    public void handleItem(Item item) {
        if (item.name.equals(AGED_BRIE)) {
            handleBrie(item);
        } else if (item.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT)) {
            handleConcert(item);
        } else if (item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
            handleSulfuras(item);
        } else {
           handleDefault(item);

        }

    }

    public void handleBrie(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
            if (item.sellIn < 6) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
            item.sellIn = item.sellIn - 1;
        }
    }

    public void handleConcert(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;

            if (item.sellIn < 11) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
                if (item.sellIn < 6) {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0) {
            item.quality = item.quality - item.quality;
        }

    }

    public void handleSulfuras(Item item) {

    }

    public void handleDefault(Item item){
        if (item.quality > 0) {
            item.quality = item.quality - 1;
            item.sellIn = item.sellIn - 1;
            if (item.sellIn < 0) {
                item.quality = item.quality - 1;
            }
        }
    }
}
