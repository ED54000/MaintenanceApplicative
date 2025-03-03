package com.gildedrose;

import java.util.ArrayList;

class GildedRose {
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    ArrayList<ObjectAbstract> items = new ArrayList<ObjectAbstract>();

    public GildedRose(Item[] items) {
        for (Item item : items) {
            boolean conjured = false;
            ObjectAbstract itemCreate;
            switch (item.name) {
                case AGED_BRIE:
                    itemCreate = new Brie(item.name, item.sellIn, item.quality,conjured);
                    break;
                case BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT:
                    itemCreate = new Concert(item.name, item.sellIn, item.quality,conjured);
                    break;
                case SULFURAS_HAND_OF_RAGNAROS:
                    itemCreate = new Sulfuras(item.name, item.sellIn, item.quality,conjured);
                    break;
                default:
                    itemCreate = new ObjectDefault(item.name, item.sellIn, item.quality,conjured);
                    break;
            }
            this.items.add(itemCreate);

        }
    }

    public void updateQuality() {
        for (ObjectAbstract item : items) {
            item.handle();
        }
    }

}
